package com.zeowls.store.greenfashion.di.component;

import com.zeowls.domain.scope.LocationScope;
import com.zeowls.store.greenfashion.di.module.LocationModule;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.address.AddressFragment;

import dagger.Component;

@LocationScope
@Component(dependencies = ApplicationComponent.class,
        modules = {PresenterModule.class, LocationModule.class})
public interface LocationComponent {

    void inject(AddressFragment fragment);

}
