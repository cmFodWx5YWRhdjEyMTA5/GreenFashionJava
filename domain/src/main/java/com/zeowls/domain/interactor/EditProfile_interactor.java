package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class EditProfile_interactor extends MultiInteractor<UserResponse, UserRequest> {
    private Repository repository;

    @Inject
    public EditProfile_interactor(BaseSchedulerProvider schedulerProvider,
                                  Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<UserResponse> singleUseCase(UserRequest body) {
        return repository.editProfile(body);
    }
}
