package com.zeowls.store.greenfashion.ui.brands;

import android.support.annotation.NonNull;


import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;


@ViewScope
public class BrandsPresenter implements BrandsContract.Presenter {

    private SingleInteractor<Brands> interactor;
    private BrandsContract.View view;


    @Inject
    public BrandsPresenter(SingleInteractor<Brands> catsUseCase) {
        this.interactor = catsUseCase;
    }

    @Override
    public void attachView(@NonNull BrandsContract.View view) {
        this.view = view;
    }


    @Override
    public void dispose() {
        interactor.dispose();
    }

    @Override
    public void getData() {
        interactor.execute(this::catchData, this::catchError, 1);
    }

    @Override
    public void getMore(int page) {
        interactor.execute(this::catchMore, this::catchError, page);
    }

    private void catchData(Brands data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchMore(Brands data) {
        if (!data.isError()) {
            view.appendMore(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
        view.showErrorMessage(ex.getMessage());
    }
}
