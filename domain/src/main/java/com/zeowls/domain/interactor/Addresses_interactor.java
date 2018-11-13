package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Addresses_interactor extends SingleInteractor<AddressList> {

    private Repository repository;

    @Inject
    public Addresses_interactor(BaseSchedulerProvider schedulerProvider,
                                Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<AddressList> singleUseCase(int id) {
        return repository.getAddresses(id);
    }
}
