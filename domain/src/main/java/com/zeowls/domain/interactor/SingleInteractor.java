package com.zeowls.domain.interactor;

import com.zeowls.domain.executor.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class SingleInteractor<T> {

    private BaseSchedulerProvider schedulerProvider;
    private CompositeDisposable disposables;

    public SingleInteractor(BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.disposables = new CompositeDisposable();
    }

    public Single<T> singleUseCase() {
        return null;
    }

    public Single<T> singleUseCase(int page) {
        return null;
    }

    public Single<T> singleUseCase(String page) {
        return null;
    }

    public Single<T> singleUseCase(int id, int page) {
        return null;
    }

    public Single<T> singleUseCase(int id, int page, int secId) {
        return null;
    }

    public Observable<T> observableUseCase() {
        return null;
    }

    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        Single<T> single = singleUseCase();
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, int id) {
        Single<T> single = singleUseCase(id);
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }


    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, String data) {
        Single<T> single = singleUseCase(data);
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }


    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, int id, int page) {
        Single<T> single = singleUseCase(id, page);
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, int id, int page, int secId) {
        Single<T> single = singleUseCase(id, page, secId);
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void executeObs(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        Observable<T> single = observableUseCase();
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void dispose() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.clear();
        }
    }
}
