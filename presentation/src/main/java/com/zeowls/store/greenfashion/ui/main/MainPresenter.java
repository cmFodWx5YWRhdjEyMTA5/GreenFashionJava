package com.zeowls.store.greenfashion.ui.main;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;

@ViewScope
public class MainPresenter implements MainContract.Presenter {
    private ProductCase<List<Product>> productCase;
    private MainContract.View view;



    @Inject
    public MainPresenter(ProductCase<List<Product>> productCase) {
        this.productCase = productCase;
    }


    public void dispose() {
        productCase.dispose();
    }


    @Override
    public void attachView(@NonNull MainContract.View view) {
        this.view = view;
    }

    public void cartCounter() {
        productCase.getCount(this::catchCounter,this::catchError);
    }

    private void catchCounter(Integer num) {
        if (num != null) {
            view.setCounter(num.intValue());
        } else {
            view.showEmptyMessage("Data Is null");
        }
    }

    private void catchError(Throwable th) {
        th.printStackTrace();
        this.view.showErrorMessage(th.getMessage());
    }
}
