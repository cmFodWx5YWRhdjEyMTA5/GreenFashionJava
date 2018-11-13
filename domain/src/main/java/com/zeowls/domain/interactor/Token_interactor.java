package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Completable;

@ApplicationScope
public class Token_interactor  {
    private Repository repository;
    private ThreadTransformer threadTransformer;

    @Inject
    public Token_interactor(Repository repository, ThreadTransformer threadTransformer) {
        this.repository = repository;
        this.threadTransformer = threadTransformer;
    }

    public Completable saveDeviceToken(DeviceToken token) {
        return repository.saveDeviceToken(token).compose(threadTransformer);
    }
}
