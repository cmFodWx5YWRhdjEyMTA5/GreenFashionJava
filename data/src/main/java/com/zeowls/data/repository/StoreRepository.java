package com.zeowls.data.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import com.zeowls.data.entity.AddAddress_data;
import com.zeowls.data.entity.AddReview_data;
import com.zeowls.data.entity.AddressList_data;
import com.zeowls.data.entity.Brands_data;
import com.zeowls.data.entity.Categories_data;
import com.zeowls.data.entity.DeviceToken_data;
import com.zeowls.data.entity.FacebookLogin_data;
import com.zeowls.data.entity.FavCart_data;
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
import com.zeowls.data.mapper.Mapper;
import com.zeowls.data.source.local.database.Cart;
import com.zeowls.data.source.local.database.Favorite;
import com.zeowls.data.source.local.database.ProductRoomDatabase;
import com.zeowls.data.source.local.database.SearchEntity;
import com.zeowls.data.source.remote.CountingRequestBody;
import com.zeowls.data.source.remote.RemoteSource;
import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.entity.FavCart;
import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.entity.Ids;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.MainCat;
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
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@ApplicationScope
public class StoreRepository implements Repository {
    private Context context;
    private RemoteSource remoteSource;
    private Mapper<HomePage, HomePage_data> homeMapper;
    private Mapper<VersionValidation, VersionValidation_data> versionMapper;
    private Mapper<Suggestion, Suggestion_data> suggestionMapper;
    private Mapper<ProductDetails, ProductDetails_data> detailsMapper;
    private Mapper<ProductReview, ProductReview_data> productReviewMapper;
    private Mapper<Brands, Brands_data> brandsMapper;
    private Mapper<MainCats, MainCats_data> MainCatsMapper;
    private Mapper<Categories, Categories_data> categoriesMapper;
    private Mapper<Products, Products_data> productsMapper;
    private Mapper<Search, SearchEntity> searchMapper;
    private Mapper<Product, Cart> cartMapper;
    private Mapper<Product, Favorite> favoriteMapper;
    private Mapper<Ids, Ids_data> idsMapper;
    private Mapper<FilterRequest, FilterRequest_data> filterRequestMapper;
    private Mapper<UserRequest, UserRequest_data> userRequestMapper;
    private Mapper<UserResponse, UserResponse_data> userResponseMapper;
    private Mapper<Login, Login_data> loginMapper;
    private Mapper<FacebookLogin, FacebookLogin_data> facebookMapper;
    private Mapper<GoogleLogin, GoogleLogin_data> googleMapper;
    private Mapper<Signup, Signup_data> signupMapper;
    private Mapper<AddressList, AddressList_data> addressListMapper;
    private Mapper<Response, Response_data> responseMapper;
    private Mapper<AddAddress, AddAddress_data> addAddressMapper;
    private Mapper<Orders, Orders_data> ordersMapper;
    private Mapper<OrderDetails, OrderDetails_data> ordersDetailsMapper;
    private Mapper<AddReview, AddReview_data> addReviewMapper;
    private Mapper<FavCart, FavCart_data> favCartMapper;
    private Mapper<MakeOrder, MakeOrder_data> makeOrderMapper;
    private Mapper<DeviceToken, DeviceToken_data> tokenMapper;
    private Mapper<Geocoder, Geocoder_data> geocoderMapper;


    @Inject
    public StoreRepository(Context context, RemoteSource remoteSource, Mapper<HomePage, HomePage_data> homeMapper, Mapper<VersionValidation, VersionValidation_data> versionMapper, Mapper<Suggestion, Suggestion_data> suggestionMapper, Mapper<ProductDetails, ProductDetails_data> detailsMapper, Mapper<ProductReview, ProductReview_data> productReviewMapper, Mapper<Brands, Brands_data> brandsMapper, Mapper<MainCats, MainCats_data> MainCatsMapper, Mapper<Categories, Categories_data> categoriesMapper, Mapper<Products, Products_data> productsMapper, Mapper<Search, SearchEntity> searchMapper, Mapper<Product, Cart> cartMapper, Mapper<Product, Favorite> favoriteMapper, Mapper<Ids, Ids_data> idsMapper, Mapper<FilterRequest, FilterRequest_data> filterRequestMapper, Mapper<UserRequest, UserRequest_data> userRequestMapper, Mapper<UserResponse, UserResponse_data> userResponseMapper, Mapper<Login, Login_data> loginMapper, Mapper<FacebookLogin, FacebookLogin_data> facebookMapper, Mapper<GoogleLogin, GoogleLogin_data> googleMapper, Mapper<Signup, Signup_data> signupMapper, Mapper<AddressList, AddressList_data> addressListMapper, Mapper<Response, Response_data> responseMapper, Mapper<AddAddress, AddAddress_data> addAddressMapper, Mapper<Orders, Orders_data> ordersMapper, Mapper<OrderDetails, OrderDetails_data> ordersDetailsMapper, Mapper<AddReview, AddReview_data> addReviewMapper, Mapper<FavCart, FavCart_data> favCartMapper, Mapper<MakeOrder, MakeOrder_data> makeOrderMapper, Mapper<DeviceToken, DeviceToken_data> tokenMapper, Mapper<Geocoder, Geocoder_data> geocoderMapper) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.homeMapper = homeMapper;
        this.versionMapper = versionMapper;
        this.suggestionMapper = suggestionMapper;
        this.detailsMapper = detailsMapper;
        this.productReviewMapper = productReviewMapper;
        this.brandsMapper = brandsMapper;
        this.MainCatsMapper = MainCatsMapper;
        this.categoriesMapper = categoriesMapper;
        this.productsMapper = productsMapper;
        this.searchMapper = searchMapper;
        this.cartMapper = cartMapper;
        this.favoriteMapper = favoriteMapper;
        this.idsMapper = idsMapper;
        this.filterRequestMapper = filterRequestMapper;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.loginMapper = loginMapper;
        this.facebookMapper = facebookMapper;
        this.googleMapper = googleMapper;
        this.signupMapper = signupMapper;
        this.addressListMapper = addressListMapper;
        this.responseMapper = responseMapper;
        this.addAddressMapper = addAddressMapper;
        this.ordersMapper = ordersMapper;
        this.ordersDetailsMapper = ordersDetailsMapper;
        this.addReviewMapper = addReviewMapper;
        this.favCartMapper = favCartMapper;
        this.makeOrderMapper = makeOrderMapper;
        this.tokenMapper = tokenMapper;
        this.geocoderMapper = geocoderMapper;
    }

    @Override
    public Single<VersionValidation> getVersion() {
        return remoteSource.getVersion().map(versionMapper::map);
    }

    @Override
    public Single<HomePage> getHomePage() {
        return remoteSource.getHome().map(homeMapper::map);
    }

    @Override
    public Single<Products> getHot(int page) {
        return remoteSource.getHot(page).map(productsMapper::map);
    }

    @Override
    public Single<Products> getNew(int page) {
        return remoteSource.getnew(page).map(productsMapper::map);
    }

    @Override
    public Observable<Suggestion> getSuggestion() {
        return remoteSource.getSuggestion().map(suggestionMapper::map).doOnNext(this::saveSuggestion);
    }

    @Override
    public Single<ProductDetails> getDetails(int id) {
        return remoteSource.getDetails(id).map(detailsMapper::map);
    }

    @Override
    public Single<ProductReview> getProductReviews(int id) {
        return remoteSource.getItemReviews(id).map(productReviewMapper::map);
    }

    @Override
    public Single<Brands> getBrands(int page) {
        return remoteSource.getBrands(page).map(brandsMapper::map);
    }

    @Override
    public Single<MainCats> getMainCats(int page) {
        return remoteSource.getMainCats(page).map(MainCatsMapper::map);
    }

    @Override
    public Single<Categories> getCategories(int id, int page) {
        return remoteSource.getCategories(id, page).map(categoriesMapper::map);
    }

    @Override
    public Single<Products> getBrandProducts(int id, int page) {
        return remoteSource.getBrandProducts(id, page).map(productsMapper::map);
    }

    @Override
    public Single<Products> getSubCatBrandProducts(int brandId, int page, int subCatId) {
        return remoteSource.getsubCatBrandProducts(brandId, page, subCatId).map(productsMapper::map);
    }

    @Override
    public Single<Products> getMainCatProducts(int id, int page) {
        return remoteSource.getMainCatProducts(id, page).map(productsMapper::map);
    }

    @Override
    public Single<Products> getSubCatProducts(int id, int page) {
        return remoteSource.getSubCatProducts(id, page).map(productsMapper::map);
    }

    @Override
    public Observable<Products> getProducts(Ids ids, boolean isFavorite) {
        return remoteSource.getProducts(idsMapper.reverse(ids)).map(productsMapper::map)
                .doOnNext(data -> {
                    if (!data.getProduct().isEmpty()) {
                        if (isFavorite)
                            insertAllFavorite(data.getProduct());
                        else
                            insertAllCart(data.getProduct());
                    }
                });
    }


    @Override
    public Single<Products> getFilter(FilterRequest filter, int page) {
        return remoteSource.getFilter(filterRequestMapper.reverse(filter), page).map(productsMapper::map);
    }

    @Override
    public Single<UserResponse> editProfile(UserRequest body) {
        return remoteSource.editProfile(userRequestMapper.reverse(body)).map(userResponseMapper::map);
    }

    @Override
    public Completable saveDeviceToken(DeviceToken body) {
        return remoteSource.saveDeviceToked(tokenMapper.reverse(body));
    }

    @Override
    public Single<UserResponse> login(Login body) {
        return remoteSource.login(loginMapper.reverse(body)).map(userResponseMapper::map);
    }

    @Override
    public Single<UserResponse> facebookLogin(FacebookLogin body) {
        return remoteSource.facebookLogin(facebookMapper.reverse(body)).map(userResponseMapper::map);
    }

    @Override
    public Single<UserResponse> googleLogin(GoogleLogin body) {
        return remoteSource.googleLogin(googleMapper.reverse(body)).map(userResponseMapper::map);
    }

    @Override
    public Single<UserResponse> signup(Signup body) {
        return remoteSource.signup(signupMapper.reverse(body)).map(userResponseMapper::map);
    }

    @SuppressLint("CheckResult")
    @Override
    public Flowable<Double> uploadImage(String filePath, Integer id) {
        return Flowable.create(emitter -> {
            try {
                remoteSource.uploadImage(createMultipartBody(filePath, emitter), id).blockingGet();
                emitter.onComplete();
            } catch (Exception e) {
                emitter.tryOnError(e);
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Single<AddressList> getAddresses(int id) {
        return remoteSource.getAddresses(id).map(addressListMapper::map);
    }

    @Override
    public Single<Orders> getOrders(int id) {
        return remoteSource.getOrders(id).map(ordersMapper::map);
    }

    @Override
    public Single<OrderDetails> getOrderDetails(int id) {
        return remoteSource.getOrderDetails(id).map(ordersDetailsMapper::map);
    }

    @Override
    public Single<Response> addAddresses(AddAddress body) {
        return remoteSource.addAddresses(addAddressMapper.reverse(body)).map(responseMapper::map);
    }


    @Override
    public Single<Response> addReview(AddReview body) {
        return remoteSource.addReview(addReviewMapper.reverse(body)).map(responseMapper::map);
    }

    @Override
    public Single<Response> cancelOrder(int id) {
        return remoteSource.cancelOrder(id).map(responseMapper::map);
    }

    @Override
    public Single<Response> makeOrder(MakeOrder body) {
        return remoteSource.makeOrder(makeOrderMapper.reverse(body)).map(responseMapper::map);
    }

    @Override
    public Single<Geocoder> geocodeAddress(String latlng) {
        return service.geocodeAddress(latlng).map(geocoderMapper::map);
    }

    private void saveSuggestion(Suggestion data) {
        ArrayList arrayList = new ArrayList();
        for (Brand brand : data.getBrands()) {
            SearchEntity brands = new SearchEntity();
            brands.setId(brand.getId());
            brands.setName(brand.getName().trim());
            brands.setNameAr(brand.getNameAr().trim());
            brands.setType(2);
            arrayList.add(brands);
        }
        for (MainCat mainCat : data.getMainCats()) {
            SearchEntity mainCats = new SearchEntity();
            mainCats.setId(mainCat.getId());
            mainCats.setName(mainCat.getName().trim());
            mainCats.setNameAr(mainCat.getNameAr().trim());
            mainCats.setType(0);
            arrayList.add(mainCats);
        }
        for (SubCat subCat : data.getSubCats()) {
            SearchEntity subCats = new SearchEntity();
            subCats.setId(subCat.getId());
            subCats.setName(subCat.getName().trim());
            subCats.setNameAr(subCat.getNameAr().trim());
            subCats.setType(1);
            arrayList.add(subCats);
        }
        ProductRoomDatabase.getDatabase(this.context).searchDao().insertAll(arrayList);
    }


    @Override
    public Single<List<Search>> getLocalSuggestion() {
        return ProductRoomDatabase.getDatabase(context).searchDao().getSuggestions().map(searchMapper::map);
    }

    @Override
    public Flowable<List<Search>> getRecentSearch() {
        return ProductRoomDatabase.getDatabase(context).searchDao().getRecentSearch().map(searchMapper::map);
    }

    @Override
    public Completable addRecent(String name) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .searchDao()
                    .addRecent(name);
            e.onComplete();
        });
    }

    @Override
    public Completable deleteRecent(int id) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .searchDao()
                    .deleteRecent(id);
            e.onComplete();
        });
    }

    @Override
    public Completable deleteAllRecent() {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .searchDao()
                    .deleteAllRecent();
            e.onComplete();
        });
    }

    @Override
    public Observable<Products> getCartIds() {
        return ProductRoomDatabase.getDatabase(context).cartDao().getAllIds().toObservable().flatMap(data -> {
            Ids ids = new Ids();
            List<Integer> item = new ArrayList<>();
            for (Integer object : data) {
                item.add(object);
            }
            ids.setIds(item);
            return getProducts(ids, false);
        });
    }

    private void insertAllCart(List<Product> product) {
        ProductRoomDatabase.getDatabase(context)
                .cartDao()
                .insertAllCart(cartMapper.reverse(product));
    }

    private void insertAllFavorite(List<Product> product) {
        ProductRoomDatabase.getDatabase(context)
                .favouriteDao()
                .insertAllFavorite(favoriteMapper.reverse(product));
    }

    @Override
    public Flowable<List<Product>> getCart() {
        return ProductRoomDatabase.getDatabase(context).cartDao().getAllCart().map(cartMapper::map);
    }


    public Flowable getCount() {
        return ProductRoomDatabase.getDatabase(context).cartDao().getCount();
    }


    @Override
    public Completable addCart(Product product) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .cartDao()
                    .addCart(cartMapper.reverse(product));
            e.onComplete();
        });
    }

    @Override
    public Completable editCart(int id, int count) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .cartDao()
                    .edit(id, count);
            e.onComplete();
        });
    }

    @Override
    public Completable deleteCart(int id) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .cartDao()
                    .deleteCart(id);
            e.onComplete();
        });
    }

    @Override
    public Completable deleteAllCart() {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .cartDao()
                    .deleteAllCart();
            e.onComplete();
        });
    }

    @Override
    public Observable<Products> getFavIds() {
        return ProductRoomDatabase.getDatabase(context).favouriteDao().getAllIds().toObservable().flatMap(data -> {
            Ids ids = new Ids();
            List<Integer> item = new ArrayList<>();
            for (Integer object : data) {
                item.add(object);
            }
            ids.setIds(item);
            return getProducts(ids, true);
        });
    }


    @Override
    public Flowable<List<Integer>> findFavorite(int id) {
        return ProductRoomDatabase.getDatabase(context).favouriteDao().findFavorite(id);
    }

    @Override
    public Flowable<List<Product>> getFav() {
        return ProductRoomDatabase.getDatabase(context).favouriteDao().getFavCart().map(data -> {
            List<Product> products = new ArrayList<>();
            for (FavCart_data item : data) {
                Product product = new Product();
                if (item.getCartId() != null) {
                    product.setCart(true);
                }
                product.setId(item.getId());
                product.setCartQuantity(item.getCartQuantity());
                product.setActive(item.isActive());
                product.setCategory(item.getCategory());
                product.setCode(item.getCode());
                product.setDateAdd(item.getDateAdd());
                product.setDateUpd(item.getDateUpd());
                product.setHot(item.isHot());
                product.setNew(item.isNew());
                product.setMainImage(item.getMainImage());
                product.setNewCode(item.getNewCode());
                product.setName(item.getName());
                product.setNameAr(item.getNameAr());
                product.setOnSale(item.isOnSale());
                product.setOutOfStock(item.isOutOfStock());
                product.setPrice(item.getPrice());
                product.setReductionPrice(item.getReductionPrice());
                product.setWholesalePrice(item.getWholesalePrice());
                product.setQuantity(item.getQuantity());
                products.add(product);
            }
            return products;
        });
    }


    @Override
    public Completable addFavorite(Product product) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .favouriteDao()
                    .addFavorite(favoriteMapper.reverse(product));
            e.onComplete();
        });
    }

    @Override
    public Completable moveToFavorite(Product product) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .cartDao()
                    .moveToFavoriteInTransaction(favoriteMapper.reverse(product));
            e.onComplete();
        });
    }

    @Override
    public Completable deleteFavorite(int id) {
        return Completable.create(e -> {
            ProductRoomDatabase.getDatabase(context)
                    .favouriteDao()
                    .delete(id);
            e.onComplete();
        });
    }


    private MultipartBody.Part createMultipartBody(String filePath, FlowableEmitter<Double> emitter) {
        File file = new File(filePath);
        return MultipartBody.Part.createFormData("ProfileImage", file.getName(), createCountingRequestBody(file, emitter));
    }


    private RequestBody createRequestBody(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }

    private RequestBody createCountingRequestBody(File file, FlowableEmitter<Double> emitter) {
        RequestBody requestBody = createRequestBody(file);
        return new CountingRequestBody(requestBody, (bytesWritten, contentLength) -> {
            double progress = (1.0 * bytesWritten) / contentLength;
            emitter.onNext(progress);
        });
    }

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("http://maps.googleapis.com/")
            .build();

    RemoteSource service = retrofit.create(RemoteSource.class);
}

