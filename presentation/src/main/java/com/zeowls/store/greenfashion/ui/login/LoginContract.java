package com.zeowls.store.greenfashion.ui.login;

import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.User;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void showErrorMessage();

        void emailNotValid();

        void inCorrectPassword();

        void saveLogin(User user);

        void saveFacebook(User user);

        void saveGoogle(User user);

    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void Login(Login user);

        void FacebookLogin(Profile profile, AccessToken accessToken);

        void GoogleLogin(Task<GoogleSignInAccount> task);

        void saveToken(DeviceToken token);

        void dispose();

    }
}
