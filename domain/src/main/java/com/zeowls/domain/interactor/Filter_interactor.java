package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Filter_interactor extends MultiInteractor<Products, FilterRequest> {
    private Repository repository;

    @Inject
    public Filter_interactor(BaseSchedulerProvider schedulerProvider,
                             Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Products> singleUseCase(FilterRequest body, int page) {
        return repository.getFilter(body, page);
    }
}
