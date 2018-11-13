package com.zeowls.store.greenfashion.ui.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingSettings;
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.strategy.WalledGardenInternetObservingStrategy;
import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.BuildConfig;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.view.ViewDialog;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity implements SplashContract.View, ViewDialog.Action {
    private SplashContract.Presenter presenter;
    private InternetObservingSettings settings;
    private final String APP_PNAME = "com.zeowls.store.greenfashion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencies();
        settings = InternetObservingSettings.builder().initialInterval(2000).interval(2000).timeout(2000).strategy(new WalledGardenInternetObservingStrategy())
                .build();
        checkInternet();
    }

    private void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    private void handleEvent(Class<?> o) {
        startActivity(new Intent(this, o));
    }

    private void onError() {
        handleEvent(MainActivity.class);
    }

    private void onSuccess() {
        handleEvent(MainActivity.class);
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull SplashContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(Suggestion suggestion) {
        onSuccess();
    }

    @Override
    public void setVersion(VersionValidation version) {
        if (version.getCritical()) {
            if (version.getVersionCode() > BuildConfig.VERSION_CODE) {
                new ViewDialog().showDialog(this, getString(R.string.update), this);
            } else {
                presenter.getData();
            }
        } else {
            presenter.getData();
        }
    }

    @Override
    public void showErrorMessage(String s) {
        onError();
    }

    @Override
    public void showEmptyMessage(String s) {

    }

    @SuppressLint("CheckResult")
    public void checkInternet() {
        Single<Boolean> single = ReactiveNetwork.checkInternetConnectivity(settings);
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToTheInternet -> {
                    if (isConnectedToTheInternet) {
                        presenter.getData();
                    } else {
                        SplashActivity.this.onError();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void onConfirm() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
    }
}
