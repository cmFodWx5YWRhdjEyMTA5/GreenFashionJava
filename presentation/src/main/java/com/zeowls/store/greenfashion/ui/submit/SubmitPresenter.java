package com.zeowls.store.greenfashion.ui.submit;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class SubmitPresenter implements SubmitContract.Presenter {
    private SubmitContract.View view;
    private MultiInteractor<Response, MakeOrder> interactor;
    private ProductCase<List<Product>> productCase;

    @Inject
    public SubmitPresenter(MultiInteractor<Response, MakeOrder> interactor, ProductCase<List<Product>> productCase) {
        this.interactor = interactor;
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull SubmitContract.View view) {
        this.view = view;
    }

    @Override
    public void makeOrder(MakeOrder body) {
        interactor.execute(this::catchResponse, this::catchError, body);
    }

    @Override
    public void clearCart() {
        productCase.removeAllCart().subscribe();
    }

    @Override
    public void dispose() {
        interactor.dispose();
        productCase.dispose();
    }

    @Override
    public void getCart() {
        productCase.getAllProduct(this::catchProducts, this::catchError);
    }


    private void catchResponse(Response data) {
        if (!data.getError()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }


    private void catchProducts(List<Product> data) {
        if (!data.isEmpty()) {
            view.setCart(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
