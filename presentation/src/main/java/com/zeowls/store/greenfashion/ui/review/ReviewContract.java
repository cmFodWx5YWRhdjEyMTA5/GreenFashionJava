package com.zeowls.store.greenfashion.ui.review;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.ProductReview;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface ReviewContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(ProductReview data);

        void showErrorMessage();

    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(int id);

    }
}
