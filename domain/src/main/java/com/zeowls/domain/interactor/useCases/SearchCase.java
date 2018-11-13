package com.zeowls.domain.interactor.useCases;

import io.reactivex.Completable;
import io.reactivex.functions.Consumer;

public abstract class SearchCase<T> {

    public abstract void recent(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2);

    public abstract void suggestions(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2);

    public abstract Completable insert(String name);

    public abstract Completable deleteRecent(int id);

    public abstract Completable deleteAllRecent();

    public abstract void dispose();

}
