package com.zeowls.store.greenfashion.ui.signup;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.User;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface SignUpContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void saveSignup(User user);

        void duplicatedEmail();

        void showErrorMessage();
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void signup(Signup user);

        void saveToken(DeviceToken token);

        void dispose();
    }
}
