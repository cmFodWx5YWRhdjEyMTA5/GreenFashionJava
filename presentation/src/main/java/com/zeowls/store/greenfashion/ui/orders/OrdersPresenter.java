package com.zeowls.store.greenfashion.ui.orders;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Orders;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;

@ViewScope
public class OrdersPresenter implements OrdersContract.Presenter {

    private SingleInteractor<Orders> interactor;
    private OrdersContract.View view;

    @Inject
    public OrdersPresenter(SingleInteractor<Orders> catsUseCase) {
        this.interactor = catsUseCase;
    }

    @Override
    public void attachView(@NonNull OrdersContract.View view) {
        this.view = view;
    }


    @Override
    public void dispose() {
        interactor.dispose();
    }

    @Override
    public void getData(int id) {
        interactor.execute(this::catchData, this::catchError, id);
    }

    private void catchData(Orders data) {
        if (!data.getError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
        view.showErrorMessage(ex.getMessage());
    }
}
