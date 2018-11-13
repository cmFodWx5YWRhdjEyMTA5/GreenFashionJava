package com.zeowls.store.greenfashion.ui.submit;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Response;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface SubmitContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Response data);

        void showErrorMessage();

        void setCart(List<Product> products);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void makeOrder(MakeOrder body);

        void clearCart();

        void dispose();

        void getCart();
    }
}
