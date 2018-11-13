package com.zeowls.store.greenfashion.di.component;

import com.zeowls.domain.scope.ForLogin;
import com.zeowls.store.greenfashion.di.module.LoginModule;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.login.LoginFragment;
import com.zeowls.store.greenfashion.ui.profile.ProfileFragment;

import dagger.Component;

@ForLogin
@Component(dependencies = ApplicationComponent.class,
        modules = {PresenterModule.class, LoginModule.class})
public interface LoginComponent {
    void inject(LoginFragment fragment);

    void inject(ProfileFragment fragment);
}