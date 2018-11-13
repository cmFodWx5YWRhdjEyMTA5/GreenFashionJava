package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Response;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.interactor.useCases.UploadCase;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Flowable;

@ApplicationScope
public class UploadImage_Interactor extends UploadCase<Response, String, Integer> {
    private Repository repository;
    private ThreadTransformer threadTransformer;

    @Inject
    public UploadImage_Interactor(Repository repository, ThreadTransformer threadTransformer) {
        this.repository = repository;
        this.threadTransformer = threadTransformer;
    }

    public Flowable<Double> uploadImage(String str, Integer num) {
        return repository.uploadImage(str, num).compose(threadTransformer);
    }
}
