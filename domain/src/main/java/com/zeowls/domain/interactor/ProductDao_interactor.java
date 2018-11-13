package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.interactor.useCases.ProductCase;
import com.zeowls.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProductDao_interactor extends ProductCase<List<Product>> {
    private CompositeDisposable disposables = new CompositeDisposable();
    private Repository repository;
    private ThreadTransformer threadTransformer;

    @Inject
    public ProductDao_interactor(Repository repository, ThreadTransformer threadTransformer) {
        this.repository = repository;
        this.threadTransformer = threadTransformer;
    }


    @Override
    public void getAllProduct(Consumer<? super List<Product>> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getCart().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    @Override
    public void getAllFavourite(Consumer<? super List<Product>> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getFav().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    @Override
    public void getCount(Consumer<? super Integer> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getCount().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    @Override
    public void getCartIds(Consumer<? super Products> onSuccess, Consumer<? super Throwable> onError) {
        disposables.add(repository.getCartIds().compose(threadTransformer).subscribe(onSuccess, onError));
    }

    @Override
    public void findFavorite(Consumer<? super List<Integer>> onSuccess, Consumer<? super Throwable> onError, int id) {
        disposables.add(repository.findFavorite(id).compose(threadTransformer).subscribe(onSuccess, onError));
    }


    @Override
    public Completable addCart(Product product) {
        return repository.addCart(product).compose(threadTransformer);
    }


    @Override
    public Completable addFavourite(Product product) {
        return repository.addFavorite(product).compose(threadTransformer);
    }

    @Override
    public Completable editProduct(int id, int count) {
        return repository.editCart(id, count).compose(threadTransformer);
    }

    @Override
    public Completable moveToFavorite(Product product) {
        return repository.moveToFavorite(product).compose(threadTransformer);
    }


    @Override
    public Completable removeCart(int id) {
        return repository.deleteCart(id).compose(threadTransformer);

    }

    @Override
    public Completable removeFavourite(int id) {
        return repository.deleteFavorite(id).compose(threadTransformer);
    }

    @Override
    public Completable removeAllCart() {
        return repository.deleteAllCart().compose(threadTransformer);
    }

    public void dispose() {
        if (!this.disposables.isDisposed()) {
            this.disposables.clear();
        }
    }
}
