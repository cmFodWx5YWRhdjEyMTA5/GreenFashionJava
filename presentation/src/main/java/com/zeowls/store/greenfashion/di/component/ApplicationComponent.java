package com.zeowls.store.greenfashion.di.component;

import android.content.Context;

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
import com.zeowls.domain.interactor.ProductReview_interactor;
import com.zeowls.domain.interactor.Signup_interactor;
import com.zeowls.domain.interactor.SubCatBrandProducts;
import com.zeowls.domain.interactor.SubCatProducts_interactor;
import com.zeowls.domain.interactor.Suggestion_interactor;
import com.zeowls.domain.interactor.Token_interactor;
import com.zeowls.domain.interactor.UploadImage_Interactor;
import com.zeowls.domain.interactor.Version_interactor;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.di.module.ApplicationModule;
import com.zeowls.store.greenfashion.di.module.FragmentModule;
import com.zeowls.store.greenfashion.di.module.InteractorModule;
import com.zeowls.store.greenfashion.di.module.MapperModule;
import com.zeowls.store.greenfashion.ui.base.BaseActivity;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.cart.CartFragment;
import com.zeowls.store.greenfashion.ui.favorite.FavoriteFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.profile.ProfileFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;
import com.zeowls.store.greenfashion.ui.utils.FileResolver;

import dagger.Component;

@ApplicationScope
@Component(modules = {MapperModule.class,
        InteractorModule.class,
        ApplicationModule.class,
        FragmentModule.class,})
public interface ApplicationComponent {
    void inject(App app);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    FileResolver fileResolver();

    Repository repository();

    ThreadTransformer threadTransformer();

    Context context();

    HomeFragment homeFragment();

    FavoriteFragment favoriteFragment();

    SearchFragment searchFragment();

    ProfileFragment profileFragment();

    CartFragment cartFragment();

    Version_interactor getVersion();

    Home_interactor HomeInteractor();

    Hot_interactor HotInteractor();

    New_interactor NewInteractor();

    Suggestion_interactor SuggestionInteractor();

    Details_interactor DetailsInteractor();

    ProductReview_interactor ProductReviewInteractor();

    Brands_interactor BrandsInteractor();

    MainCats_interactor MainCatsInteractor();

    Categories_interactor CategoriesInteractor();

    BrandProducts_interactor getBrandProducts();

    SubCatBrandProducts getSubCatBrandProducts();

    MainCatProducts_interactor getMainCatProducts();

    SubCatProducts_interactor getSubCatProducts();

    Filter_interactor getFilter();

    UploadImage_Interactor doUploadImage();

    EditProfile_interactor doEditProfile();

    Login_interactor login();

    FacebookLogin_interactor facebookLogin();

    GoogleLogin_interactor googleLogin();

    Signup_interactor signup();

    Addresses_interactor addresses();

    AddAddress_interactor addAddress();

    Orders_interactor getOrders();

    OrderDetails_interactor getOrderDetails();

    CancelOrder_interactor cancelOrder();

    AddReview_interactor addReview();

    MakeOrder_interactor makeOrder();

    Token_interactor saveDeviceToken();

    Geocode_interactor geocode_interactor();
}
