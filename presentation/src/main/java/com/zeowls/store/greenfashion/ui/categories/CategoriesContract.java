package com.zeowls.store.greenfashion.ui.categories;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.Product;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface CategoriesContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setData(Categories data);

        void showErrorMessage(String s);

        void showEmptyMessage(String s);

        void setFav(List<Product> products);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData(int mainCatId);

        void getFav();

        void addCart(Product product);

        void addFavorite(Product product);

        void removeFavorite(int id);

        void dispose();

    }
}
