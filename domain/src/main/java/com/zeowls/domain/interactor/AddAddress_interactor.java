package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class AddAddress_interactor extends MultiInteractor<Response, AddAddress> {
    private Repository repository;

    @Inject
    public AddAddress_interactor(BaseSchedulerProvider schedulerProvider,
                                 Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Response> singleUseCase(AddAddress body) {
        return repository.addAddresses(body);
    }
}
