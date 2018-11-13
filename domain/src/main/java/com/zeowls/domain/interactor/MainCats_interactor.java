package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class MainCats_interactor extends SingleInteractor<MainCats> {

    private Repository repository;

    @Inject
    public MainCats_interactor(BaseSchedulerProvider schedulerProvider,
                               Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<MainCats> singleUseCase(int page) {
        return repository.getMainCats(page);
    }
}
