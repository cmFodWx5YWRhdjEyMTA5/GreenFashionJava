package com.zeowls.store.greenfashion.ui.main;

import android.support.annotation.NonNull;

import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

public interface MainContract {

    interface Presenter extends BasePresenter<View> {

        void attachView(@NonNull View view);

        void cartCounter();

        void dispose();
    }

    interface View extends BaseView<Presenter> {
        void setCounter(int i);

        void showEmptyMessage(String str);

        void showErrorMessage(String str);
    }
}
