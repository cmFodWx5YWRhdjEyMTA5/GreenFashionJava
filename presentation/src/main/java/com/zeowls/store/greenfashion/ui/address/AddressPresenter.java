package com.zeowls.store.greenfashion.ui.address;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.LocationScope;

import javax.inject.Inject;


@LocationScope
public class AddressPresenter implements AddressContract.Presenter {
    private AddressContract.View view;
    private MultiInteractor<Response, AddAddress> interactor;
    private SingleInteractor<Geocoder> geocode_ineractor;

    @Inject
    public AddressPresenter(MultiInteractor<Response, AddAddress> interactor, SingleInteractor<Geocoder> geocode_ineractor) {
        this.interactor = interactor;
        this.geocode_ineractor = geocode_ineractor;
    }

    @Override
    public void attachView(@NonNull AddressContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(AddAddress body) {
        interactor.execute(this::catchProducts, this::catchError, body);
    }

    @Override
    public void getStreet(String latlng) {
        geocode_ineractor.execute(this::catchStreet, this::catchError, latlng);
    }

    @Override
    public void dispose() {
        interactor.dispose();
        geocode_ineractor.dispose();
    }


    private void catchProducts(Response data) {
        if (!data.getError()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchStreet(Geocoder data) {
        if (!data.getResults().isEmpty()) {
            view.setStreet(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
