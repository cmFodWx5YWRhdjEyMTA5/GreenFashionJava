package com.zeowls.store.greenfashion.di.module;

import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.ui.cart.CartFragment;
import com.zeowls.store.greenfashion.ui.favorite.FavoriteFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.profile.ProfileFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    @Provides
    @ApplicationScope
    HomeFragment homeFragment(){
        return new HomeFragment();
    }

    @Provides
    @ApplicationScope
    FavoriteFragment favoriteFragment(){
        return new FavoriteFragment();
    }

    @Provides
    @ApplicationScope
    SearchFragment searchFragment(){
        return new SearchFragment();
    }

    @Provides
    @ApplicationScope
    ProfileFragment profileFragment(){
        return new ProfileFragment();
    }

    @Provides
    @ApplicationScope
    CartFragment cartFragment(){
        return new CartFragment();
    }
}
