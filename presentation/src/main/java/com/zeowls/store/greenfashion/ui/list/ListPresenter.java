package com.zeowls.store.greenfashion.ui.list;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class ListPresenter implements ListContract.Presenter {

    private ListContract.View view;
    private ProductCase<List<Product>> productCase;

    @Inject
    public ListPresenter(ProductCase<List<Product>> productCase) {
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull ListContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(SingleInteractor<Products> interactor, int id, int secId) {
        if (id != -1) {
            if (secId != -1) {
                interactor.execute(this::catchData, this::catchError, id, 1, secId);
            } else {
                interactor.execute(this::catchData, this::catchError, id, 1);
            }
        } else {
            interactor.execute(this::catchData, this::catchError, 1);

        }
    }

    @Override
    public void getFavorite() {
        productCase.getAllFavourite(this::catchFav, this::catchError);
    }

    @Override
    public void getMore(SingleInteractor<Products> interactor, int id, int page) {
        if (id != -1)
            interactor.execute(this::catchMore, this::catchError, id, page);
        else
            interactor.execute(this::catchMore, this::catchError, page);
    }

    @Override
    public void getFilter(MultiInteractor<Products, FilterRequest> filterInteractor, FilterRequest body) {
        filterInteractor.execute(this::catchFilter, this::catchError, body, 1);
    }

    @Override
    public void getMoreFilter(MultiInteractor<Products, FilterRequest> filterInteractor, FilterRequest body, int page) {
        filterInteractor.execute(this::catchMoreFilter, this::catchError, body, page);
    }


    @Override
    public void addCart(Product product) {
        productCase.addCart(product).subscribe();
    }

    @Override
    public void addFavorite(Product product) {
        productCase.addFavourite(product).subscribe();
    }

    @Override
    public void removeFavorite(int id) {
        productCase.removeFavourite(id).subscribe();
    }


    private void catchData(Products data) {
        if (!data.isError()) {
            if (!data.getProduct().isEmpty())
                view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchFav(List<Product> data) {
        view.setFav(data);
    }

    private void catchFilter(Products data) {
        if (!data.isError()) {
            view.setHasFilter(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchMoreFilter(Products data) {
        if (!data.isError()) {
            view.setMoreFilter(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }


    private void catchMore(Products data) {
        if (!data.isError()) {
            view.setMore(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }

    @Override
    public void dispose(SingleInteractor<Products> interactor, MultiInteractor<Products, FilterRequest> filterInteractor) {
        interactor.dispose();
        productCase.dispose();
        filterInteractor.dispose();
    }
}
