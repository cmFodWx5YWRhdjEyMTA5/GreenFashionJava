package com.zeowls.store.greenfashion.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.Token_interactor;
import com.zeowls.domain.scope.ForLogin;

import org.json.JSONObject;

import javax.inject.Inject;


@ForLogin
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private CallbackManager callbackManager;
    private ProfileTracker mProfileTracker;
    private MultiInteractor<UserResponse, Login> loginInteractor;
    private MultiInteractor<UserResponse, FacebookLogin> facebookInteractor;
    private MultiInteractor<UserResponse, GoogleLogin> googleInteractor;
    private Token_interactor tokenInteractor;
    private String email;

    @Inject
    public LoginPresenter(CallbackManager callbackManager, MultiInteractor<UserResponse, Login> loginInteractor, MultiInteractor<UserResponse, FacebookLogin> facebookInteractor, MultiInteractor<UserResponse, GoogleLogin> googleInteractor, Token_interactor tokenInteractor) {
        this.callbackManager = callbackManager;
        this.loginInteractor = loginInteractor;
        this.facebookInteractor = facebookInteractor;
        this.googleInteractor = googleInteractor;
        this.tokenInteractor = tokenInteractor;
        initFacebook();
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void Login(Login user) {
        loginInteractor.execute(this::catchLogin, this::catchError, user);
    }

    @Override
    public void FacebookLogin(Profile profile, AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                (JSONObject json, GraphResponse response) -> {
                    email = json.optString("email");
                    if (profile != null && email != null) {
                        FacebookLogin user = new FacebookLogin();
                        user.setFirstName(profile.getFirstName());
                        user.setLastName(profile.getLastName());
                        user.setPicture(profile.getProfilePictureUri(400, 400).toString());
                        user.setFbId(profile.getId());
                        user.setEmail(email);
                        user.setFbToken(accessToken.getToken());
                        facebookInteractor.execute(this::catchFacebook, this::catchError, user);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void initFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if (!loginResult.getRecentlyGrantedPermissions().contains("email")) {
                            LoginManager.getInstance().logOut();
                        } else if (Profile.getCurrentProfile() == null) {
                            mProfileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                    FacebookLogin(currentProfile, loginResult.getAccessToken());
                                    if (mProfileTracker.isTracking())
                                        mProfileTracker.stopTracking();
                                }
                            };
                        } else {
                            Profile profile = Profile.getCurrentProfile();
                            FacebookLogin(profile, loginResult.getAccessToken());
                        }
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                    }
                });
    }

    @Override
    public void GoogleLogin(Task<GoogleSignInAccount> result) {
        GoogleSignInAccount account = result.getResult();
        String givenName = account.getGivenName();
        String familyName = account.getFamilyName();
        String email = account.getEmail();
        String id = account.getId();
        String img_url = String.valueOf(account.getPhotoUrl()).replace("s96-c", "s300-c");
        GoogleLogin user = new GoogleLogin();
        user.setGoogleId(id);
        user.setEmail(email);
        user.setFirstName(givenName);
        user.setLastName(familyName);
        user.setPicture(img_url);
        googleInteractor.execute(this::catchGoogle, this::catchError, user);
    }

    @Override
    public void saveToken(DeviceToken token) {
        tokenInteractor.saveDeviceToken(token).subscribe();
    }

    @Override
    public void dispose() {
        loginInteractor.dispose();
        facebookInteractor.dispose();
        googleInteractor.dispose();
    }


    private void catchLogin(UserResponse data) {
        if (!data.getError()) {
            if (data.getUser() != null)
                view.saveLogin(data.getUser());
        } else {
            if (data.getMessage_code() == -1)
                view.inCorrectPassword();
            else if (data.getMessage_code() == -2)
                view.emailNotValid();
        }
    }

    private void catchFacebook(UserResponse data) {
        if (!data.getError()) {
            if (data.getUser() != null)
                view.saveFacebook(data.getUser());
        } else {
            if (data.getMessage_code() == -1)
                view.inCorrectPassword();
            else if (data.getMessage_code() == -2)
                view.emailNotValid();
        }
    }

    private void catchGoogle(UserResponse data) {
        if (!data.getError()) {
            if (data.getUser() != null)
                view.saveGoogle(data.getUser());
        } else {
            if (data.getMessage_code() == -1)
                view.inCorrectPassword();
            else if (data.getMessage_code() == -2)
                view.emailNotValid();
        }
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
