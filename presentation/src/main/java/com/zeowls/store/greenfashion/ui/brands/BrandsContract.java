package com.zeowls.store.greenfashion.ui.brands;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Brands;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface BrandsContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Brands data);

        void showErrorMessage(String s);

        void appendMore(Brands data);

    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void dispose();

        void getData();

        void getMore(int page);
    }
}
