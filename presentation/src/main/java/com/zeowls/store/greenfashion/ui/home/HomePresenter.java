package com.zeowls.store.greenfashion.ui.home;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;


@ViewScope
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private SingleInteractor<HomePage> homeInteractor;

    @Inject
    public HomePresenter(SingleInteractor<HomePage> useCase) {
        this.homeInteractor = useCase;
    }

    @Override
    public void attachView(@NonNull HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        homeInteractor.execute(this::catchData, this::catchError);
    }

    private void catchData(HomePage data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchError(Throwable ex) {
        view.showErrorMessage(ex.getMessage());

    }

    @Override
    public void dispose() {
        homeInteractor.dispose();
    }
}
