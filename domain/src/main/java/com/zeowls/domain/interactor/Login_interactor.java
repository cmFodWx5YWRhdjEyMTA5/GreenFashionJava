package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Login_interactor extends MultiInteractor<UserResponse, Login> {
    private Repository repository;

    @Inject
    public Login_interactor(BaseSchedulerProvider schedulerProvider,
                            Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<UserResponse> singleUseCase(Login body) {
        return repository.login(body);
    }
}
