package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Orders;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Orders_interactor extends SingleInteractor<Orders> {

    private Repository repository;

    @Inject
    public Orders_interactor(BaseSchedulerProvider schedulerProvider,
                             Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Orders> singleUseCase(int id) {
        return repository.getOrders(id);
    }
}
