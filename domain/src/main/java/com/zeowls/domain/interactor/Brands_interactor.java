package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Brands_interactor extends SingleInteractor<Brands> {

    private Repository repository;

    @Inject
    public Brands_interactor(BaseSchedulerProvider schedulerProvider,
                             Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Brands> singleUseCase(int page) {
        return repository.getBrands(page);
    }
}
