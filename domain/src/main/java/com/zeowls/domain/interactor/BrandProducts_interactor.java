package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Products;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class BrandProducts_interactor extends SingleInteractor<Products> {

    private Repository repository;

    @Inject
    public BrandProducts_interactor(BaseSchedulerProvider schedulerProvider,
                                    Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Products> singleUseCase(int id,int page) {
        return repository.getBrandProducts(id ,page);
    }
}
