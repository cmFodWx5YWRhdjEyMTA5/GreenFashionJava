package com.zeowls.store.greenfashion.ui.splash;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.scope.ViewScope;

import javax.inject.Inject;


@ViewScope
public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View view;
    private SingleInteractor<Suggestion> interactor;
    private SingleInteractor<VersionValidation> Version_interactor;

    @Inject
    public SplashPresenter(SingleInteractor<Suggestion> useCase, SingleInteractor<VersionValidation> versionUseCase) {
        this.interactor = useCase;
        this.Version_interactor = versionUseCase;
    }

    @Override
    public void attachView(@NonNull SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        interactor.executeObs(this::catchData, this::catchError);
    }

    @Override
    public void getVersion() {
        Version_interactor.execute(this::catchVersion,this::catchError);
    }

    private void catchData(Suggestion data) {
        if (!data.isError()) {
            view.setData(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchVersion(VersionValidation data) {
        if (!data.getError()) {
            view.setVersion(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchError(Throwable ex) {
        view.showErrorMessage(ex.getMessage());

    }

    @Override
    public void dispose() {
        interactor.dispose();
    }
}
