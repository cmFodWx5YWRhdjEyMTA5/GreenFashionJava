package com.zeowls.store.greenfashion.ui.detail;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class DetailsPresenter implements DetailsContract.Presenter {
    private ProductCase<List<Product>> productCase;
    private SingleInteractor<ProductDetails> detailsInteractor;
    private DetailsContract.View view;

    @Inject
    public DetailsPresenter(SingleInteractor<ProductDetails> detailsInteractor, ProductCase<List<Product>> productCase) {
        this.detailsInteractor = detailsInteractor;
        this.productCase = productCase;
    }

    @Override
    public void attachView(@NonNull DetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(int id) {
        detailsInteractor.execute(this::catchDetails, this::catchError, id);
    }

    @Override
    public void getFavorite(int id) {
        productCase.findFavorite(this::catchFav, this::catchError, id);
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
    public void doRemoveFavorite(int id) {
        productCase.removeFavourite(id).subscribe();
    }

    @Override
    public void dispose() {
        productCase.dispose();
        detailsInteractor.dispose();
    }

    private void catchDetails(ProductDetails data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage();
        }
    }

    private void catchFav(List<Integer> id) {
        view.setFav(id);
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
