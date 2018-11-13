package com.zeowls.store.greenfashion.ui.favorite;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface FavoriteContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(List<Product> data);

        void showErrorMessage();

        void getFavorite(Products data);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void renewFav();

        void getData();

        void addCart(Product product);

        void doRemoveCart(int id);

        void doRemoveFavorite(int id);
    }
}
