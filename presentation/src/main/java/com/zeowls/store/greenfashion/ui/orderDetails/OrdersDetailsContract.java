package com.zeowls.store.greenfashion.ui.orderDetails;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.entity.Response;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface OrdersDetailsContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(OrderDetails data);

        void showErrorMessage(String s);

        void cancelData(Response data);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void dispose();

        void getData(int id);

        void cancelOrder(int id);

        void addReview(AddReview body);
    }
}
