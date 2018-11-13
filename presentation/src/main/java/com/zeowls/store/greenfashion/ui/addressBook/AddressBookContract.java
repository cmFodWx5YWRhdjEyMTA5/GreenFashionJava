package com.zeowls.store.greenfashion.ui.addressBook;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddressList;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface AddressBookContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(AddressList data);

        void showErrorMessage();

    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(int id);

        void dispose();

    }
}
