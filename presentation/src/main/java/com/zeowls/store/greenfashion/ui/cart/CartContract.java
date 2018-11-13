package com.zeowls.store.greenfashion.ui.cart;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface CartContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);
        void setData(List<Product> products);
        void showErrorMessage();

        void getCart(Products data);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);
        void reNewCart();
        void getData();
        void doEdit(int id, int count);
        void doMove(Product product);
        void doRemove(int id);
        void dispose();

    }
}
