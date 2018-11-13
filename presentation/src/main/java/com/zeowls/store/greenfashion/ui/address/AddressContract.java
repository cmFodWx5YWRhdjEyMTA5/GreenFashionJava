package com.zeowls.store.greenfashion.ui.address;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.Response;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface AddressContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Response data);

        void showErrorMessage();

        void setStreet(Geocoder data);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(AddAddress body);

        void getStreet(String latlng);

        void dispose();

    }
}
