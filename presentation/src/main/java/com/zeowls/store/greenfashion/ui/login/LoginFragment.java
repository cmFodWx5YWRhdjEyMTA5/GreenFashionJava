package com.zeowls.store.greenfashion.ui.login;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ForLogin;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerLoginComponent;
import com.zeowls.store.greenfashion.di.module.LoginModule;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.signup.SignupFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidEmail;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidPassword;
import static com.zeowls.store.greenfashion.ui.utils.Utils.setInputTextLayoutColor;

@ForLogin
public class LoginFragment extends BaseFragment implements LoginContract.View {

    private static final int RC_SIGN_IN = 9001;

    @Inject
    HomeFragment homeFragment;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    private LoginContract.Presenter presenter;

    @Inject
    CallbackManager callbackManager;
    @Inject
    GoogleApiClient mGoogleApiClient;
    private CompositeDisposable disposable;
    private DeviceToken token;

    @Inject
    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initializeDependencies() {
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this::onConnectionFailed, getActivity(), 1))
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    private void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void init() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
        }
        mGoogleApiClient.connect();
        token = new DeviceToken();
        token.setDeviceToken(LoggedData.getToken(getActivity()));
        password.setTransformationMethod(new PasswordTransformationMethod());
        observeFields();
    }


    @Inject
    @Override
    public void attachPresenter(@NonNull LoginContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }


    private void observeFields() {
        disposable.add(RxTextView.textChanges(email)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (isValidEmail(email)) {
                        email.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(email, colorState);
                    } else {
                        email.setError(getString(R.string.email_validation));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(email, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(password)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (isValidPassword(string)) {
                        password.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(password, colorState);
                    } else {
                        password.setError(getString(R.string.password_limit));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(password, colorState);
                    }
                }));
    }

    @OnClick(R.id.sign_in)
    public void Login() {
        if (isValidEmail(email) && isValidPassword(password.getText())) {
            Login login = new Login();
            login.setEmail(email.getText().toString());
            login.setPassword(password.getText().toString());
            presenter.Login(login);
        }
    }

    @OnClick(R.id.google)
    public void GoogleLogin() {
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), 9001);
    }

    @OnClick(R.id.facebook)
    public void FacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

    @OnClick(R.id.sign_up)
    public void singUp() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, SignupFragment.newInstance()).addToBackStack(null).commit();
    }


    @Override
    public void showErrorMessage() {

    }

    @Override
    public void emailNotValid() {
        Toast.makeText(getActivity(), R.string.incorrect_email, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void inCorrectPassword() {
        Toast.makeText(getActivity(), R.string.incorrect_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveLogin(User user) {
        user.setUserLogin(User.UserLogin.EDITABLE);
        LoggedData.saveUserObject(getActivity(), user, true);
        token.setUserId(user.getId());
        presenter.saveToken(token);
        redirect();
    }

    @Override
    public void saveFacebook(User user) {
        user.setUserLogin(User.UserLogin.NON_EDITABLE);
        LoggedData.saveUserObject(getActivity(), user, true);
        token.setUserId(user.getId());
        presenter.saveToken(token);
        redirect();
    }

    @Override
    public void saveGoogle(User user) {
        user.setUserLogin(User.UserLogin.NON_EDITABLE);
        LoggedData.saveUserObject(getActivity(), user, true);
        token.setUserId(user.getId());
        presenter.saveToken(token);
        redirect();
    }

    private void redirect() {
        if (!getActivity().getSupportFragmentManager().popBackStackImmediate(HOME.name(), 0)) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME.name()).addToBackStack(HOME.name()).commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }
        }
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> result = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (result.isSuccessful())
                presenter.GoogleLogin(result);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroyView() {
        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected())
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        safelyDispose(disposable);
        presenter.dispose();
    }

    private void safelyDispose(Disposable... disposableArr) {
        for (Disposable disposable : disposableArr) {
            if (!(disposable == null || disposable.isDisposed())) {
                disposable.dispose();
            }
        }
    }
}
