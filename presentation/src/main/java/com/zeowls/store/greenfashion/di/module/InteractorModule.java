package com.zeowls.store.greenfashion.di.module;

import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.interactor.AddAddress_interactor;
import com.zeowls.domain.interactor.AddReview_interactor;
import com.zeowls.domain.interactor.Addresses_interactor;
import com.zeowls.domain.interactor.BrandProducts_interactor;
import com.zeowls.domain.interactor.Brands_interactor;
import com.zeowls.domain.interactor.CancelOrder_interactor;
import com.zeowls.domain.interactor.Categories_interactor;
import com.zeowls.domain.interactor.Details_interactor;
import com.zeowls.domain.interactor.EditProfile_interactor;
import com.zeowls.domain.interactor.FacebookLogin_interactor;
import com.zeowls.domain.interactor.Filter_interactor;
import com.zeowls.domain.interactor.Geocode_interactor;
import com.zeowls.domain.interactor.GoogleLogin_interactor;
import com.zeowls.domain.interactor.Home_interactor;
import com.zeowls.domain.interactor.Hot_interactor;
import com.zeowls.domain.interactor.Login_interactor;
import com.zeowls.domain.interactor.MainCatProducts_interactor;
import com.zeowls.domain.interactor.MainCats_interactor;
import com.zeowls.domain.interactor.MakeOrder_interactor;
import com.zeowls.domain.interactor.New_interactor;
import com.zeowls.domain.interactor.OrderDetails_interactor;
import com.zeowls.domain.interactor.Orders_interactor;
import com.zeowls.domain.interactor.ProductDao_interactor;
import com.zeowls.domain.interactor.ProductReview_interactor;
import com.zeowls.domain.interactor.SearchDao_interactor;
import com.zeowls.domain.interactor.Signup_interactor;
import com.zeowls.domain.interactor.SubCatBrandProducts;
import com.zeowls.domain.interactor.SubCatProducts_interactor;
import com.zeowls.domain.interactor.Suggestion_interactor;
import com.zeowls.domain.interactor.Token_interactor;
import com.zeowls.domain.interactor.UploadImage_Interactor;
import com.zeowls.domain.interactor.Version_interactor;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.domain.scope.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @ApplicationScope
    @Provides
    Version_interactor getVersion(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Version_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Home_interactor getHome(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Home_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Suggestion_interactor getSuggestion(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Suggestion_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Hot_interactor getHot(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Hot_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    New_interactor getNew(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new New_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Details_interactor getDetails(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Details_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    ProductReview_interactor getProductReview(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new ProductReview_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Brands_interactor getBrands(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Brands_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    MainCats_interactor getMainCats(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new MainCats_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Categories_interactor getCategories(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Categories_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    BrandProducts_interactor getBrandProducts(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new BrandProducts_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    SubCatBrandProducts getSubCatBrandProducts(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new SubCatBrandProducts(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    MainCatProducts_interactor getMainCatProducts(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new MainCatProducts_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    SubCatProducts_interactor getubCatProducts(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new SubCatProducts_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Filter_interactor getFilter(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Filter_interactor(schedulerProvider, repository);
    }

    @ViewScope
    @Provides
    SearchDao_interactor searchDao(Repository repository, ThreadTransformer schedulerProvider) {
        return new SearchDao_interactor(repository, schedulerProvider);
    }

    @ViewScope
    @Provides
    ProductDao_interactor productDao(Repository repository, ThreadTransformer schedulerProvider) {
        return new ProductDao_interactor(repository, schedulerProvider);
    }


    @ApplicationScope
    @Provides
    UploadImage_Interactor uploadImageCase(Repository repository, ThreadTransformer threadTransformer) {
        return new UploadImage_Interactor(repository, threadTransformer);
    }

    @ApplicationScope
    @Provides
    EditProfile_interactor editProfile(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new EditProfile_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Login_interactor login(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Login_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    FacebookLogin_interactor facebookLogin(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new FacebookLogin_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    GoogleLogin_interactor googleLogin(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new GoogleLogin_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Signup_interactor signup(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Signup_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Addresses_interactor addresses(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Addresses_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    AddAddress_interactor addAddress(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new AddAddress_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Orders_interactor getOrders(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Orders_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    OrderDetails_interactor getOrderDetails(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new OrderDetails_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    CancelOrder_interactor cancelOrder(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new CancelOrder_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    AddReview_interactor addReview(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new AddReview_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    MakeOrder_interactor makeOrder(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new MakeOrder_interactor(schedulerProvider, repository);
    }

    @ApplicationScope
    @Provides
    Token_interactor saveDeviceToken(Repository repository, ThreadTransformer schedulerProvider) {
        return new Token_interactor(repository, schedulerProvider);
    }

    @ApplicationScope
    @Provides
    Geocode_interactor geocode_interactor(Repository repository, BaseSchedulerProvider schedulerProvider) {
        return new Geocode_interactor(schedulerProvider, repository);
    }
}
