package com.zeowls.domain.interactor.useCases;

import io.reactivex.Flowable;

public abstract class UploadCase<T, T1, T2> {
    public abstract Flowable<Double> uploadImage(T1 str, T2 num);
}
