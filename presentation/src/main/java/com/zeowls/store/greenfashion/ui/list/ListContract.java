package com.zeowls.store.greenfashion.ui.list;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface ListContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Products data);

        void showErrorMessage(String s);

        void showEmptyMessage(String s);

        void setHasFilter(Products data);

        void setMoreFilter(Products data);

        void setMore(Products data);

        void setFav(List<Product> products);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(SingleInteractor<Products> interactor, int id, int secId);

        void getFavorite();

        void getFilter(MultiInteractor<Products, FilterRequest> filterInteractor, FilterRequest body);

        void getMoreFilter(MultiInteractor<Products, FilterRequest> filterInteractor, FilterRequest body, int page);

        void getMore(SingleInteractor<Products> interactor, int id, int page);

        void addCart(Product product);

        void addFavorite(Product product);

        void removeFavorite(int id);

        void dispose(SingleInteractor<Products> interactor, MultiInteractor<Products, FilterRequest> filterInteractor);

    }
}
