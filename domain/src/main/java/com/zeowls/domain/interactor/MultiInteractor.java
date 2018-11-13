package com.zeowls.domain.interactor;

import com.zeowls.domain.executor.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class MultiInteractor<T, T1> {

    private BaseSchedulerProvider schedulerProvider;
    private CompositeDisposable disposables;

    MultiInteractor(BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.disposables = new CompositeDisposable();
    }

    public Single<T> singleUseCase(T1 object) {
        return null;
    }

    public Single<T> singleUseCase(T1 object, int page) {
        return null;
    }

    public Single<T> singleUseCase(int id, int page) {
        return null;
    }

    public Observable<T> observableUseCase() {
        return null;
    }

    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, T1 object) {
        Single<T> single = singleUseCase(object);
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void execute(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError, T1 object, int page) {
        Single<T> single = singleUseCase(object, page);
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

    public void executeObs(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        Observable<T> single = observableUseCase();
        if (single != null) {
            single = single.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui());
            disposables.add(single.subscribe(onSuccess, onError));
        }
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.clear();
        }
    }
}
