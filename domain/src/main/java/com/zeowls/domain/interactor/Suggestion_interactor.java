package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Observable;

@ApplicationScope
public class Suggestion_interactor extends SingleInteractor<Suggestion> {

    private Repository repository;

    @Inject
    public Suggestion_interactor(BaseSchedulerProvider schedulerProvider,
                                 Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Observable<Suggestion> observableUseCase() {
        return repository.getSuggestion();
    }
}
