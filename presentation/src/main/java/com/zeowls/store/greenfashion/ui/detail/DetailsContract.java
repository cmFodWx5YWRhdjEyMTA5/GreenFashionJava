package com.zeowls.store.greenfashion.ui.detail;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface DetailsContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(ProductDetails data);

        void showErrorMessage();

        void setFav(List<Integer> products);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(int id);

        void getFavorite(int id);

        void addCart(Product product);

        void addFavorite(Product product);

        void doRemoveFavorite(int id);

        void dispose();
    }
}
