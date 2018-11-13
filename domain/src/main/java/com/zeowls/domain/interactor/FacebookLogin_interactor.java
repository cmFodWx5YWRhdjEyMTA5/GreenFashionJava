package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class FacebookLogin_interactor extends MultiInteractor<UserResponse, FacebookLogin> {
    private Repository repository;

    @Inject
    public FacebookLogin_interactor(BaseSchedulerProvider schedulerProvider,
                                    Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<UserResponse> singleUseCase(FacebookLogin body) {
        return repository.facebookLogin(body);
    }
}
