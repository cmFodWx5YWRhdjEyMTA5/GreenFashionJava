package com.zeowls.store.greenfashion.di.module;

import android.support.v4.app.FragmentActivity;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zeowls.domain.scope.ForLogin;
import com.zeowls.store.greenfashion.R;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private final GoogleApiClient.OnConnectionFailedListener listener;
    private final FragmentActivity activity;
    private int id;

    public LoginModule(GoogleApiClient.OnConnectionFailedListener listener, FragmentActivity activity, int id) {
        this.listener = listener;
        this.activity = activity;
        this.id = id;
    }

    @ForLogin
    @Provides
    GoogleApiClient provideGoogleApiClient(FragmentActivity activity) {
        final GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.client_id))
                .requestEmail()
                .requestProfile()
                .build();
        return new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity,id, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
    }

    @ForLogin
    @Provides
    FragmentActivity provideActivity() {
        return activity;
    }

    @ForLogin
    @Provides
    CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
    }
}
