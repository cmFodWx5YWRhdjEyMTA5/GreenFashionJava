package com.zeowls.store.greenfashion.ui.cart;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class CartPresenter implements CartContract.Presenter {

    private CartContract.View view;
    private ProductCase<List<Product>> productCase;

    @Inject
    public CartPresenter(ProductCase<List<Product>> productCase) {
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull CartContract.View view) {
        this.view = view;
    }

    @Override
    public void reNewCart() {
        productCase.getCartIds(this::catchSign,this::catchError);
    }

    @Override
    public void getData() {
        productCase.getAllProduct(this::catchProducts, this::catchError);
    }

    @Override
    public void doEdit(int id, int count) {
        productCase.editProduct(id, count).subscribe();
    }

    @Override
    public void doMove(Product product) {
        productCase.moveToFavorite(product).subscribe();
    }

    @Override
    public void doRemove(int id) {
        productCase.removeCart(id).subscribe();
    }

    @Override
    public void dispose() {
        productCase.dispose();
    }

    private void catchSign(Products data) {
        if (!data.isError()) {
            view.getCart(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchProducts(List<Product> data) {
        if (!data.isEmpty()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
