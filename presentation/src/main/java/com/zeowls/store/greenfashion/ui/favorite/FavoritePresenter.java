package com.zeowls.store.greenfashion.ui.favorite;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class FavoritePresenter implements FavoriteContract.Presenter {
    private final ProductCase<List<Product>> productCase;
    private FavoriteContract.View view;

    @Inject
    public FavoritePresenter(ProductCase<List<Product>> productCase) {
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void renewFav() {
        productCase.getCartIds(this::catchSign, this::catchError);
    }

    @Override
    public void getData() {
        productCase.getAllFavourite(this::catchProducts, this::catchError);
    }

    @Override
    public void addCart(Product product) {
        productCase.addCart(product).subscribe();
    }

    @Override
    public void doRemoveCart(int id) {
        productCase.removeCart(id).subscribe();
    }

    @Override
    public void doRemoveFavorite(int id) {
        productCase.removeFavourite(id).subscribe();
    }

    private void catchProducts(List<Product> data) {
        if (!data.isEmpty()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchSign(Products data) {
        if (!data.isError()) {
            view.getFavorite(data);
        } else {
            view.showErrorMessage();
        }
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
