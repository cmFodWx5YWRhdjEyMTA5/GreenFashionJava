package com.zeowls.store.greenfashion.di.component;

import com.zeowls.domain.scope.ViewScope;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookFragment;
import com.zeowls.store.greenfashion.ui.brands.BrandsFragment;
import com.zeowls.store.greenfashion.ui.cart.CartFragment;
import com.zeowls.store.greenfashion.ui.categories.CategoriesFragment;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;
import com.zeowls.store.greenfashion.ui.edit.EditFragment;
import com.zeowls.store.greenfashion.ui.favorite.FavoriteFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsFragment;
import com.zeowls.store.greenfashion.ui.orders.OrdersFragment;
import com.zeowls.store.greenfashion.ui.review.ReviewFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;
import com.zeowls.store.greenfashion.ui.signup.SignupFragment;
import com.zeowls.store.greenfashion.ui.splash.SplashActivity;
import com.zeowls.store.greenfashion.ui.submit.SubmitFragment;

import dagger.Component;

@ViewScope
@Component(dependencies = ApplicationComponent.class,
        modules = {PresenterModule.class})
public interface ViewComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(HomeFragment fragment);

    void inject(FavoriteFragment fragment);

    void inject(SearchFragment fragment);

    void inject(CartFragment fragment);

    void inject(BrandsFragment fragment);

    void inject(CategoriesFragment fragment);

    void inject(ListFragment fragment);

    void inject(DetailsFragment fragment);

    void inject(ReviewFragment fragment);

    void inject(EditFragment fragment);

    void inject(AddressBookFragment fragment);

    void inject(OrdersFragment fragment);

    void inject(OrdersDetailsFragment fragment);

    void inject(SubmitFragment fragment);

    void inject(SignupFragment fragment);


}
