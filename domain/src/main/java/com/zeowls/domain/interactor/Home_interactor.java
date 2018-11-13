package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Home_interactor extends SingleInteractor<HomePage> {

    private Repository repository;

    @Inject
    public Home_interactor(BaseSchedulerProvider schedulerProvider,
                           Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<HomePage> singleUseCase() {
        return repository.getHomePage();
    }
}
