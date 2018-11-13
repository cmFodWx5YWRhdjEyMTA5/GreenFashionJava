package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Response;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class CancelOrder_interactor extends SingleInteractor<Response> {

    private Repository repository;

    @Inject
    public CancelOrder_interactor(BaseSchedulerProvider schedulerProvider,
                                  Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Response> singleUseCase(int id) {
        return repository.cancelOrder(id);
    }
}
