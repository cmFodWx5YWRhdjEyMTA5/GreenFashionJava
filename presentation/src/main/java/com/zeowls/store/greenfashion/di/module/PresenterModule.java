package com.zeowls.store.greenfashion.di.module;

import com.facebook.CallbackManager;
import com.zeowls.domain.interactor.AddAddress_interactor;
import com.zeowls.domain.interactor.AddReview_interactor;
import com.zeowls.domain.interactor.Addresses_interactor;
import com.zeowls.domain.interactor.Brands_interactor;
import com.zeowls.domain.interactor.CancelOrder_interactor;
import com.zeowls.domain.interactor.Categories_interactor;
import com.zeowls.domain.interactor.Details_interactor;
import com.zeowls.domain.interactor.EditProfile_interactor;
import com.zeowls.domain.interactor.FacebookLogin_interactor;
import com.zeowls.domain.interactor.Geocode_interactor;
import com.zeowls.domain.interactor.GoogleLogin_interactor;
import com.zeowls.domain.interactor.Home_interactor;
import com.zeowls.domain.interactor.Login_interactor;
import com.zeowls.domain.interactor.MainCats_interactor;
import com.zeowls.domain.interactor.MakeOrder_interactor;
import com.zeowls.domain.interactor.OrderDetails_interactor;
import com.zeowls.domain.interactor.Orders_interactor;
import com.zeowls.domain.interactor.ProductDao_interactor;
import com.zeowls.domain.interactor.ProductReview_interactor;
import com.zeowls.domain.interactor.SearchDao_interactor;
import com.zeowls.domain.interactor.Signup_interactor;
import com.zeowls.domain.interactor.Suggestion_interactor;
import com.zeowls.domain.interactor.Token_interactor;
import com.zeowls.domain.interactor.UploadImage_Interactor;
import com.zeowls.domain.interactor.Version_interactor;
import com.zeowls.domain.scope.ForLogin;
import com.zeowls.domain.scope.LocationScope;
import com.zeowls.domain.scope.ViewScope;
import com.zeowls.store.greenfashion.ui.address.AddressContract;
import com.zeowls.store.greenfashion.ui.address.AddressPresenter;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookContract;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookPresenter;
import com.zeowls.store.greenfashion.ui.brands.BrandsContract;
import com.zeowls.store.greenfashion.ui.brands.BrandsPresenter;
import com.zeowls.store.greenfashion.ui.cart.CartContract;
import com.zeowls.store.greenfashion.ui.cart.CartPresenter;
import com.zeowls.store.greenfashion.ui.categories.CategoriesContract;
import com.zeowls.store.greenfashion.ui.categories.CategoriesPresenter;
import com.zeowls.store.greenfashion.ui.detail.DetailsContract;
import com.zeowls.store.greenfashion.ui.detail.DetailsPresenter;
import com.zeowls.store.greenfashion.ui.edit.EditContract;
import com.zeowls.store.greenfashion.ui.edit.EditPresenter;
import com.zeowls.store.greenfashion.ui.favorite.FavoriteContract;
import com.zeowls.store.greenfashion.ui.favorite.FavoritePresenter;
import com.zeowls.store.greenfashion.ui.home.HomeContract;
import com.zeowls.store.greenfashion.ui.home.HomePresenter;
import com.zeowls.store.greenfashion.ui.list.ListContract;
import com.zeowls.store.greenfashion.ui.list.ListPresenter;
import com.zeowls.store.greenfashion.ui.login.LoginContract;
import com.zeowls.store.greenfashion.ui.login.LoginPresenter;
import com.zeowls.store.greenfashion.ui.main.MainContract;
import com.zeowls.store.greenfashion.ui.main.MainPresenter;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsContract;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsPresenter;
import com.zeowls.store.greenfashion.ui.orders.OrdersContract;
import com.zeowls.store.greenfashion.ui.orders.OrdersPresenter;
import com.zeowls.store.greenfashion.ui.review.ReviewContract;
import com.zeowls.store.greenfashion.ui.review.ReviewPresenter;
import com.zeowls.store.greenfashion.ui.search.SearchContract;
import com.zeowls.store.greenfashion.ui.search.SearchPresenter;
import com.zeowls.store.greenfashion.ui.signup.SignUpContract;
import com.zeowls.store.greenfashion.ui.signup.SignUpPresenter;
import com.zeowls.store.greenfashion.ui.splash.SplashContract;
import com.zeowls.store.greenfashion.ui.splash.SplashPresenter;
import com.zeowls.store.greenfashion.ui.submit.SubmitContract;
import com.zeowls.store.greenfashion.ui.submit.SubmitPresenter;
import com.zeowls.store.greenfashion.ui.utils.FileResolver;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @ViewScope
    @Provides
    SplashContract.Presenter splashPresenter(Suggestion_interactor getSuggestion, Version_interactor version_interactor) {
        return new SplashPresenter(getSuggestion, version_interactor);
    }

    @ViewScope
    @Provides
    MainContract.Presenter mainPresenter(ProductDao_interactor productDao) {
        return new MainPresenter(productDao);
    }

    @ViewScope
    @Provides
    HomeContract.Presenter homePresenter(Home_interactor getHome) {
        return new HomePresenter(getHome);
    }

    @ViewScope
    @Provides
    SearchContract.Presenter searchPresenter(MainCats_interactor getCategories, SearchDao_interactor searchDoDao) {
        return new SearchPresenter(getCategories, searchDoDao);
    }

    @ViewScope
    @Provides
    BrandsContract.Presenter brandsPresenter(Brands_interactor getBrands) {
        return new BrandsPresenter(getBrands);
    }

    @ViewScope
    @Provides
    CategoriesContract.Presenter categoriesPresenter(Categories_interactor getCategories, ProductDao_interactor productDao) {
        return new CategoriesPresenter(getCategories, productDao);
    }

    @ViewScope
    @Provides
    ListContract.Presenter listPresenter(ProductDao_interactor productDao) {
        return new ListPresenter(productDao);
    }

    @ViewScope
    @Provides
    CartContract.Presenter cartPresenter(ProductDao_interactor productDao) {
        return new CartPresenter(productDao);
    }

    @ViewScope
    @Provides
    FavoriteContract.Presenter favoritePresenter(ProductDao_interactor productDao) {
        return new FavoritePresenter(productDao);
    }

    @ViewScope
    @Provides
    DetailsContract.Presenter detailsPresenter(Details_interactor getDetails, ProductDao_interactor productDao) {
        return new DetailsPresenter(getDetails, productDao);
    }

    @ViewScope
    @Provides
    ReviewContract.Presenter reviewPresenter(ProductReview_interactor getReviews) {
        return new ReviewPresenter(getReviews);
    }

    @ForLogin
    @Provides
    LoginContract.Presenter loginPresenter(CallbackManager callbackManager, Login_interactor login, FacebookLogin_interactor facebookLogin, GoogleLogin_interactor googleLogin, Token_interactor tokenInteractor) {
        return new LoginPresenter(callbackManager, login, facebookLogin, googleLogin, tokenInteractor);
    }

    @ViewScope
    @Provides
    SignUpContract.Presenter signupPresenter(Signup_interactor signup, Token_interactor tokenInteractor) {
        return new SignUpPresenter(signup, tokenInteractor);
    }

    @ViewScope
    @Provides
    EditContract.Presenter editPresenter(UploadImage_Interactor upload, FileResolver fileResolver, EditProfile_interactor editProfileInteractor) {
        return new EditPresenter(upload, fileResolver, editProfileInteractor);
    }

    @ViewScope
    @Provides
    AddressBookContract.Presenter addressbookPresenter(Addresses_interactor addresses) {
        return new AddressBookPresenter(addresses);
    }

    @LocationScope
    @Provides
    AddressContract.Presenter addressPresenter(AddAddress_interactor address, Geocode_interactor geocode) {
        return new AddressPresenter(address, geocode);
    }

    @ViewScope
    @Provides
    OrdersContract.Presenter getOrders(Orders_interactor orders) {
        return new OrdersPresenter(orders);
    }

    @ViewScope
    @Provides
    OrdersDetailsContract.Presenter getOrderDetails(OrderDetails_interactor orders, CancelOrder_interactor cancel, AddReview_interactor addReview) {
        return new OrdersDetailsPresenter(orders, cancel,addReview);
    }

    @ViewScope
    @Provides
    SubmitContract.Presenter submitOrder(MakeOrder_interactor order, ProductDao_interactor productDao) {
        return new SubmitPresenter(order, productDao);
    }

}
