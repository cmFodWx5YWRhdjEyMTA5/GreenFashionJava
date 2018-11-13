package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Categories_interactor extends SingleInteractor<Categories> {

    private Repository repository;

    @Inject
    public Categories_interactor(BaseSchedulerProvider schedulerProvider,
                                 Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Categories> singleUseCase(int id ,int page) {
        return repository.getCategories(id, page);
    }
}
