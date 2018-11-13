package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Details_interactor extends SingleInteractor<ProductDetails> {

    private Repository repository;

    @Inject
    public Details_interactor(BaseSchedulerProvider schedulerProvider,
                              Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<ProductDetails> singleUseCase(int id) {
        return repository.getDetails(id);
    }
}
