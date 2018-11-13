package com.zeowls.store.greenfashion.ui.categories;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;


@ViewScope
public class CategoriesPresenter implements CategoriesContract.Presenter {

    private CategoriesContract.View view;
    private SingleInteractor<Categories> interactor;
    private ProductCase<List<Product>> productCase;

    public CategoriesPresenter(SingleInteractor<Categories> interactor, ProductCase<List<Product>> productCase) {
        this.interactor = interactor;
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull CategoriesContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(int mainCatId) {
        interactor.execute(this::catchData, this::catchError, mainCatId, 1);
    }

    @Override
    public void getFav() {
        productCase.getAllFavourite(this::catchFav,this::catchError);
    }

    @Override
    public void addCart(Product product) {
        productCase.addCart(product).subscribe();
    }

    @Override
    public void addFavorite(Product product) {
        productCase.addFavourite(product).subscribe();
    }

    @Override
    public void removeFavorite(int id) {
        productCase.removeFavourite(id).subscribe();
    }


    @Override
    public void dispose() {
        interactor.dispose();
        productCase.dispose();
    }

    private void catchData(Categories data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchFav(List<Product> data) {
        view.setFav(data);
    }

    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
