package com.zeowls.data.source.remote;

import com.zeowls.data.entity.AddAddress_data;
import com.zeowls.data.entity.AddReview_data;
import com.zeowls.data.entity.AddressList_data;
import com.zeowls.data.entity.Brands_data;
import com.zeowls.data.entity.Categories_data;
import com.zeowls.data.entity.DeviceToken_data;
import com.zeowls.data.entity.FacebookLogin_data;
import com.zeowls.data.entity.FilterRequest_data;
import com.zeowls.data.entity.Geocoder_data;
import com.zeowls.data.entity.GoogleLogin_data;
import com.zeowls.data.entity.HomePage_data;
import com.zeowls.data.entity.Ids_data;
import com.zeowls.data.entity.Login_data;
import com.zeowls.data.entity.MainCats_data;
import com.zeowls.data.entity.MakeOrder_data;
import com.zeowls.data.entity.OrderDetails_data;
import com.zeowls.data.entity.Orders_data;
import com.zeowls.data.entity.ProductDetails_data;
import com.zeowls.data.entity.ProductReview_data;
import com.zeowls.data.entity.Products_data;
import com.zeowls.data.entity.Response_data;
import com.zeowls.data.entity.Signup_data;
import com.zeowls.data.entity.Suggestion_data;
import com.zeowls.data.entity.UserRequest_data;
import com.zeowls.data.entity.UserResponse_data;
import com.zeowls.data.entity.VersionValidation_data;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteSource {
    @GET("User/check_version")
    Single<VersionValidation_data> getVersion();

    @GET("General/home_page")
    Single<HomePage_data> getHome();

    @GET("General/get_hot_items")
    Single<Products_data> getHot(@Query("Page") int page);

    @GET("General/get_new_items")
    Single<Products_data> getnew(@Query("Page") int page);

    @GET("General/get_suggestions")
    Observable<Suggestion_data> getSuggestion();

    @GET("General/get_item_details_by_id")
    Single<ProductDetails_data> getDetails(@Query("ProductId") int id);

    @GET("General/get_item_review")
    Single<ProductReview_data> getItemReviews(@Query("ItemID") int id);

    @GET("Brand/get_all_brands")
    Single<Brands_data> getBrands(@Query("Page") int page);

    @GET("Category/get_main_cats")
    Single<MainCats_data> getMainCats(@Query("Page") int page);

    @GET("Category/get_top_items_by_main_category_id")
    Single<Categories_data> getCategories(@Query("MainCategoryID") int id, @Query("Page") int page);

    @GET("Brand/get_brand_items_by_id")
    Single<Products_data> getBrandProducts(@Query("BrandId") int id, @Query("Page") int page);

    @GET("Category/get_items_by_sub_category_brand_id")
    Single<Products_data> getsubCatBrandProducts(@Query("BrandId") int brandId, @Query("Page") int page, @Query("SubCategoryID") int subCatId);

    @GET("Category/get_main_cat_items_by_id")
    Single<Products_data> getMainCatProducts(@Query("SubCatID") int id, @Query("Page") int page); // SubCatID - > MainCatID Backend

    @GET("Category/get_sub_cat_items_by_id")
    Single<Products_data> getSubCatProducts(@Query("SubCatID") int id, @Query("Page") int page);

    @POST("Eslam/fav_cart")
    Observable<Products_data> getProducts(@Body Ids_data ids);

    @POST("User/register_device")
    Completable saveDeviceToked(@Body DeviceToken_data ids);

    @POST("General/search")
    Single<Products_data> getFilter(@Body FilterRequest_data data, @Query("Page") int page);

    @POST("User/edit_profile")
    Single<UserResponse_data> editProfile(@Body UserRequest_data data);

    @POST("User/user_login")
    Single<UserResponse_data> login(@Body Login_data data);

    @POST("User/facebook_login")
    Single<UserResponse_data> facebookLogin(@Body FacebookLogin_data data);

    @POST("User/google_login")
    Single<UserResponse_data> googleLogin(@Body GoogleLogin_data data);

    @POST("User/user_signup")
    Single<UserResponse_data> signup(@Body Signup_data data);

    @POST("User/edit_user_image")
    @Multipart
    Single<ResponseBody> uploadImage(@Part MultipartBody.Part part, @Query("UserID") int id);

    @POST("Address/get_user_addresses")
    Single<AddressList_data> getAddresses(@Query("UserID") int id);

    @GET("Order/get_user_orders")
    Single<Orders_data> getOrders(@Query("UserID") int id);

    @GET("Order/get_order_by_id")
    Single<OrderDetails_data> getOrderDetails(@Query("OrderID") int id);

    @GET("Order/cancel_order")
    Single<Response_data> cancelOrder(@Query("OrderID") int id);

    @POST("Address/add_edit_user_address")
    Single<Response_data> addAddresses(@Body AddAddress_data data);

    @POST("Order/AddReview")
    Single<Response_data> addReview(@Body AddReview_data data);

    @POST("Order/make_order")
    Single<Response_data> makeOrder(@Body MakeOrder_data data);

    @GET("maps/api/geocode/json?sensor=false")
    Single<Geocoder_data> geocodeAddress(@Query("latlng") String latlng);
}
