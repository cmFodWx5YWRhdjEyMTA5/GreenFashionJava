package com.zeowls.store.greenfashion.ui.addressBook;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;


@ViewScope
public class AddressBookPresenter implements AddressBookContract.Presenter {
    private AddressBookContract.View view;
    private SingleInteractor<AddressList> interactor;

    @Inject
    public AddressBookPresenter(SingleInteractor<AddressList> interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(@NonNull AddressBookContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(int id) {
        interactor.execute(this::catchProducts, this::catchError, id);
    }

    @Override
    public void dispose() {
        interactor.dispose();
    }


    private void catchProducts(AddressList data) {
        if (!data.getError()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
