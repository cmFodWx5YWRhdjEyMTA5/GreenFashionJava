package com.zeowls.store.greenfashion.ui.home;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.HomePage;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        void attachPresenter(@NonNull Presenter presenter);

        void setData(HomePage home);

        void showErrorMessage(String s);

        void showEmptyMessage(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);
        void getData();
        void dispose();

    }
}
