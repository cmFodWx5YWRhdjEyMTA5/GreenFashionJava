package com.zeowls.store.greenfashion.ui.splash;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

public interface SplashContract {
    interface View extends BaseView<Presenter> {

        void attachPresenter(@NonNull Presenter presenter);

        void setData(Suggestion suggestion);

        void setVersion(VersionValidation version);

        void showErrorMessage(String s);

        void showEmptyMessage(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);
        void getData();
        void getVersion();
        void dispose();
    }
}
