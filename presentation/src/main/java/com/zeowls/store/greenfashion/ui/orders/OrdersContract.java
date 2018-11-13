package com.zeowls.store.greenfashion.ui.orders;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Orders;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface OrdersContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Orders data);

        void showErrorMessage(String s);

    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void dispose();

        void getData(int id);
    }
}
