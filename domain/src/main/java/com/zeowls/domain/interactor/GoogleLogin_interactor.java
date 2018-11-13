package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class GoogleLogin_interactor extends MultiInteractor<UserResponse, GoogleLogin> {
    private Repository repository;

    @Inject
    public GoogleLogin_interactor(BaseSchedulerProvider schedulerProvider,
                                  Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<UserResponse> singleUseCase(GoogleLogin body) {
        return repository.googleLogin(body);
    }
}
