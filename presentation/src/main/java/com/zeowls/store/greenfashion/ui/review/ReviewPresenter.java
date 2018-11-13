package com.zeowls.store.greenfashion.ui.review;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.ProductReview;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;


@ViewScope
public class ReviewPresenter implements ReviewContract.Presenter {
    private ReviewContract.View view;
    private SingleInteractor<ProductReview> interactor;

    @Inject
    public ReviewPresenter(SingleInteractor<ProductReview> interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(@NonNull ReviewContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(int id) {
        interactor.execute(this::catchProducts, this::catchError, id);
    }


    private void catchProducts(ProductReview data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
