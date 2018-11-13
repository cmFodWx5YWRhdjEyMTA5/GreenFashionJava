package com.zeowls.store.greenfashion.ui.orderDetails;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;

@ViewScope
public class OrdersDetailsPresenter implements OrdersDetailsContract.Presenter {

    private SingleInteractor<OrderDetails> interactor;
    private SingleInteractor<Response> interactorCancel; //TODO  - > completable  not single
    private MultiInteractor<Response, AddReview> interactorReview; //TODO - > completable  not single
    private OrdersDetailsContract.View view;

    @Inject
    public OrdersDetailsPresenter(SingleInteractor<OrderDetails> catsUseCase, SingleInteractor<Response> interactorCancel, MultiInteractor<Response, AddReview> interactorReview) {
        this.interactor = catsUseCase;
        this.interactorCancel = interactorCancel;
        this.interactorReview = interactorReview;
    }

    @Override
    public void attachView(@NonNull OrdersDetailsContract.View view) {
        this.view = view;
    }


    @Override
    public void dispose() {
        interactor.dispose();
        interactorCancel.dispose();
        interactorReview.dispose();
    }

    @Override
    public void getData(int id) {
        interactor.execute(this::catchData, this::catchError, id);
    }

    @Override
    public void cancelOrder(int id) {
        interactorCancel.execute(this::CancelData, this::catchError, id);
    }

    @Override
    public void addReview(AddReview body) {
        interactorReview.execute(this::CancelData, this::catchError, body);
    }

    private void catchData(OrderDetails data) {
        if (!data.getError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void CancelData(Response data) {
        view.cancelData(data);
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
        view.showErrorMessage(ex.getMessage());
    }
}
