package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class OrderDetails_interactor extends SingleInteractor<OrderDetails> {

    private Repository repository;

    @Inject
    public OrderDetails_interactor(BaseSchedulerProvider schedulerProvider,
                                   Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<OrderDetails> singleUseCase(int id) {
        return repository.getOrderDetails(id);
    }
}
