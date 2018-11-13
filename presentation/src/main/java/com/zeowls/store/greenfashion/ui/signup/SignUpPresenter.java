package com.zeowls.store.greenfashion.ui.signup;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.Token_interactor;
import com.zeowls.domain.scope.ForLogin;

import javax.inject.Inject;


@ForLogin
public class SignUpPresenter implements SignUpContract.Presenter {
    private MultiInteractor<UserResponse, Signup> signup;
    private Token_interactor tokenInteractor;
    private SignUpContract.View view;

    @Inject
    public SignUpPresenter(MultiInteractor<UserResponse, Signup> signup, Token_interactor tokenInteractor) {
        this.signup = signup;
        this.tokenInteractor = tokenInteractor;
    }

    @Override
    public void attachView(@NonNull SignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void signup(Signup user) {
        signup.execute(this::catchSignup, this::catchError, user);
    }

    @Override
    public void saveToken(DeviceToken token) {
        tokenInteractor.saveDeviceToken(token).subscribe();
    }

    @Override
    public void dispose() {
        signup.dispose();
    }


    private void catchSignup(UserResponse data) {
        if (!data.getError()) {
            if (data.getUser() != null) {
                view.saveSignup(data.getUser());
            }
        } else {
            view.duplicatedEmail();
        }
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
