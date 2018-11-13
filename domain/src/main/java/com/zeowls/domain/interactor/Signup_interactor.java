package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Signup_interactor extends MultiInteractor<UserResponse, Signup> {
    private Repository repository;

    @Inject
    public Signup_interactor(BaseSchedulerProvider schedulerProvider,
                             Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<UserResponse> singleUseCase(Signup body) {
        return repository.signup(body);
    }
}
