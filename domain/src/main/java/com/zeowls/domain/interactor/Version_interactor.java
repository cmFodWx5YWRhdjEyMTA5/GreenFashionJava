package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Version_interactor extends SingleInteractor<VersionValidation> {

    private Repository repository;

    @Inject
    public Version_interactor(BaseSchedulerProvider schedulerProvider,
                                 Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<VersionValidation> singleUseCase() {
        return repository.getVersion();
    }
}
