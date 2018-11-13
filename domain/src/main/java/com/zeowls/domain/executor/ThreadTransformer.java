package com.zeowls.domain.executor;

import com.zeowls.domain.scope.ApplicationScope;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

@ApplicationScope
public class ThreadTransformer implements SingleTransformer, FlowableTransformer, CompletableTransformer, MaybeTransformer, ObservableTransformer {


    private BaseSchedulerProvider baseSchedulerProvider;

    @Inject
    public ThreadTransformer(BaseSchedulerProvider baseSchedulerProvider) {
        this.baseSchedulerProvider = baseSchedulerProvider;
    }


    @Override
    public SingleSource apply(@NonNull Single upstream) {
        return upstream.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui());
    }


    @Override
    public ObservableSource apply(@NonNull Observable upstream) {
        return upstream.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui());
    }

    @Override
    public Publisher apply(@NonNull Flowable upstream) {
        return upstream.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui());
    }

    @Override
    public CompletableSource apply(@NonNull Completable upstream) {
        return upstream.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui());
    }


    @Override
    public MaybeSource apply(Maybe upstream) {
        return upstream.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui());
    }
}