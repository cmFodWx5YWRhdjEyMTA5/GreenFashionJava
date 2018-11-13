package com.zeowls.domain.interactor.useCases;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.functions.Consumer;

public abstract class ProductCase<T> {

    public abstract void getAllProduct(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError);

    public abstract void getAllFavourite(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError);

    public abstract void getCount(Consumer<? super Integer> onSuccess, Consumer<? super Throwable> onError);

    public abstract void getCartIds(Consumer<? super Products> onSuccess, Consumer<? super Throwable> onError);

    public abstract void findFavorite(Consumer<? super List<Integer>> onSuccess, Consumer<? super Throwable> onError, int id);

    public abstract Completable addCart(Product product);

    public abstract Completable addFavourite(Product product);

    public abstract Completable editProduct(int id, int count);

    public abstract Completable moveToFavorite(Product product);

    public abstract Completable removeCart(int id);

    public abstract Completable removeFavourite(int id);

    public abstract Completable removeAllCart();

    public abstract void dispose();
}
