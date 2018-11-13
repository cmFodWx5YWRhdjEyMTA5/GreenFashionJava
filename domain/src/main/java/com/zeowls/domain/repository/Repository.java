package com.zeowls.domain.repository;


import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.entity.Ids;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.entity.Orders;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.entity.ProductReview;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.entity.Search;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.entity.VersionValidation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Repository {
    Single<VersionValidation> getVersion();

    Single<HomePage> getHomePage();

    Single<Products> getHot(int page);

    Single<Products> getNew(int page);

    Observable<Suggestion> getSuggestion();

    Single<ProductDetails> getDetails(int id);

    Single<ProductReview> getProductReviews(int id);

    Single<Brands> getBrands(int page);

    Single<MainCats> getMainCats(int page);

    Single<Categories> getCategories(int id, int page);

    Single<Products> getBrandProducts(int id, int page);

    Single<Products> getSubCatBrandProducts(int brandId, int page, int subCatId);

    Single<Products> getMainCatProducts(int id, int page);

    Single<Products> getSubCatProducts(int id, int page);

    Observable<Products> getProducts(Ids ids, boolean isFavorite);

    Single<Products> getFilter(FilterRequest filter, int page);

    Single<UserResponse> editProfile(UserRequest body);

    Completable saveDeviceToken(DeviceToken body);

    Single<UserResponse> login(Login body);

    Single<UserResponse> facebookLogin(FacebookLogin body);

    Single<UserResponse> googleLogin(GoogleLogin body);

    Single<UserResponse> signup(Signup body);

    Flowable<Double> uploadImage(String str, Integer num);

    Single<AddressList> getAddresses(int id);

    Single<Orders> getOrders(int id);

    Single<OrderDetails> getOrderDetails(int id);

    Single<Response> addAddresses(AddAddress body);

    Single<Response> addReview(AddReview body);

    Single<Response> cancelOrder(int id);

    Single<Response> makeOrder(MakeOrder body);

    Single<Geocoder> geocodeAddress(String latlng);



    //SEARCH

    Single<List<Search>> getLocalSuggestion();

    Flowable<List<Search>> getRecentSearch();

    Completable addRecent(String name);

    Completable deleteRecent(int id);

    Completable deleteAllRecent();

    //CART

    Observable<Products> getCartIds();

    Flowable<List<Product>> getCart();

    Flowable getCount();

    Completable addCart(Product product);

    Completable editCart(int id, int count);

    Completable deleteCart(int id);

    Completable deleteAllCart();


    //FAVORITE

    Observable<Products> getFavIds();

    Flowable<List<Integer>> findFavorite(int id);

    Flowable<List<Product>> getFav();

    Completable addFavorite(Product product);

    Completable moveToFavorite(Product product);

    Completable deleteFavorite(int id);

}
