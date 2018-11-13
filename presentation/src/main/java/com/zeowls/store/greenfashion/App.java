package com.zeowls.store.greenfashion;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.zeowls.data.LocaleManager;
import com.zeowls.store.greenfashion.di.component.ApplicationComponent;
import com.zeowls.store.greenfashion.di.component.DaggerApplicationComponent;
import com.zeowls.store.greenfashion.di.module.ApplicationModule;
import com.zeowls.store.greenfashion.di.module.FragmentModule;
import com.zeowls.store.greenfashion.di.module.InteractorModule;
import com.zeowls.store.greenfashion.di.module.MapperModule;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class App extends Application {
    private static App INSTANCE;
    public ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        INSTANCE = this;
        initializeAppComponent();
        applicationComponent.inject(this);
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }


    private void initializeAppComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .mapperModule(new MapperModule())
                    .interactorModule(new InteractorModule())
                    .fragmentModule(new FragmentModule())
                    .build();
        }
    }

    @NonNull
    public static App appInstance() {
        return INSTANCE;
    }

    public ApplicationComponent appComponent() {
        return applicationComponent;
    }


//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleManager.setLocale(base));
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        LocaleManager.setLocale(this);
//    }
}
