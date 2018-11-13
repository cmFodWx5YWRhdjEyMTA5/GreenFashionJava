package com.zeowls.store.greenfashion.di.module;

import com.zeowls.data.entity.AddAddress_data;
import com.zeowls.data.entity.AddReview_data;
import com.zeowls.data.entity.AddressList_data;
import com.zeowls.data.entity.Address_data;
import com.zeowls.data.entity.Brand_data;
import com.zeowls.data.entity.Brands_data;
import com.zeowls.data.entity.Cart_data;
import com.zeowls.data.entity.Categories_data;
import com.zeowls.data.entity.Category_data;
import com.zeowls.data.entity.Color_Color_data;
import com.zeowls.data.entity.Color_data;
import com.zeowls.data.entity.CurrentState_data;
import com.zeowls.data.entity.DeviceToken_data;
import com.zeowls.data.entity.FacebookLogin_data;
import com.zeowls.data.entity.FavCart_data;
import com.zeowls.data.entity.FeaturesAr_data;
import com.zeowls.data.entity.FeaturesEn_data;
import com.zeowls.data.entity.FilterRequest_data;
import com.zeowls.data.entity.Filter_data;
import com.zeowls.data.entity.Geocode_data;
import com.zeowls.data.entity.Geocoder_data;
import com.zeowls.data.entity.GoogleLogin_data;
import com.zeowls.data.entity.HomePage_data;
import com.zeowls.data.entity.Ids_data;
import com.zeowls.data.entity.Image_data;
import com.zeowls.data.entity.Item_data;
import com.zeowls.data.entity.Login_data;
import com.zeowls.data.entity.MainCat_data;
import com.zeowls.data.entity.MainCats_data;
import com.zeowls.data.entity.MakeOrder_data;
import com.zeowls.data.entity.OrderDetails_data;
import com.zeowls.data.entity.Order_data;
import com.zeowls.data.entity.Orders_data;
import com.zeowls.data.entity.Phone_data;
import com.zeowls.data.entity.ProductDetails_data;
import com.zeowls.data.entity.ProductReview_data;
import com.zeowls.data.entity.Product_data;
import com.zeowls.data.entity.Products_data;
import com.zeowls.data.entity.Promotion_data;
import com.zeowls.data.entity.Related_data;
import com.zeowls.data.entity.Response_data;
import com.zeowls.data.entity.Review_data;
import com.zeowls.data.entity.Reviews_data;
import com.zeowls.data.entity.Signup_data;
import com.zeowls.data.entity.SubCat_data;
import com.zeowls.data.entity.Suggestion_data;
import com.zeowls.data.entity.UserRequest_data;
import com.zeowls.data.entity.UserResponse_data;
import com.zeowls.data.entity.User_data;
import com.zeowls.data.entity.VersionValidation_data;
import com.zeowls.data.mapper.AddAddressMapper;
import com.zeowls.data.mapper.AddReviewMapper;
import com.zeowls.data.mapper.AddressListMapper;
import com.zeowls.data.mapper.AddressMapper;
import com.zeowls.data.mapper.BrandMapper;
import com.zeowls.data.mapper.BrandsMapper;
import com.zeowls.data.mapper.CartMapper;
import com.zeowls.data.mapper.CartOrdersMapper;
import com.zeowls.data.mapper.CategoriesMapper;
import com.zeowls.data.mapper.CategoryMapper;
import com.zeowls.data.mapper.ColorMapper;
import com.zeowls.data.mapper.Color_Mapper;
import com.zeowls.data.mapper.FacebookLoginMapper;
import com.zeowls.data.mapper.FavCartMapper;
import com.zeowls.data.mapper.FavoriteMapper;
import com.zeowls.data.mapper.FeatureArMapper;
import com.zeowls.data.mapper.FeatureEnMapper;
import com.zeowls.data.mapper.FilterListMapper;
import com.zeowls.data.mapper.FilterMapper;
import com.zeowls.data.mapper.GeocodeMapper;
import com.zeowls.data.mapper.GeocoderMapper;
import com.zeowls.data.mapper.GoogleLoginMapper;
import com.zeowls.data.mapper.HomeMapper;
import com.zeowls.data.mapper.IdsMapper;
import com.zeowls.data.mapper.ImageMapper;
import com.zeowls.data.mapper.ItemMapper;
import com.zeowls.data.mapper.LoginMapper;
import com.zeowls.data.mapper.MainCatMapper;
import com.zeowls.data.mapper.MainCatsMapper;
import com.zeowls.data.mapper.MakeOrderMapper;
import com.zeowls.data.mapper.Mapper;
import com.zeowls.data.mapper.OrderDetailsMapper;
import com.zeowls.data.mapper.OrderMapper;
import com.zeowls.data.mapper.OrdersMapper;
import com.zeowls.data.mapper.PhoneMapper;
import com.zeowls.data.mapper.ProductMapper;
import com.zeowls.data.mapper.ProductReviewMapper;
import com.zeowls.data.mapper.ProductsDetailsMapper;
import com.zeowls.data.mapper.ProductsMapper;
import com.zeowls.data.mapper.PromotionMapper;
import com.zeowls.data.mapper.RelatedMapper;
import com.zeowls.data.mapper.ResponseMapper;
import com.zeowls.data.mapper.ReviewMapper;
import com.zeowls.data.mapper.ReviewsMapper;
import com.zeowls.data.mapper.SearchMapper;
import com.zeowls.data.mapper.SignupMapper;
import com.zeowls.data.mapper.StateMapper;
import com.zeowls.data.mapper.SubCatMapper;
import com.zeowls.data.mapper.SuggestionMapper;
import com.zeowls.data.mapper.TokenMapper;
import com.zeowls.data.mapper.UserMapper;
import com.zeowls.data.mapper.UserRequestMapper;
import com.zeowls.data.mapper.UserResponseMapper;
import com.zeowls.data.mapper.VersionValidationMapper;
import com.zeowls.data.source.local.database.Cart;
import com.zeowls.data.source.local.database.Favorite;
import com.zeowls.data.source.local.database.SearchEntity;
import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.entity.Color;
import com.zeowls.domain.entity.Color_Color;
import com.zeowls.domain.entity.CurrentState;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.entity.FavCart;
import com.zeowls.domain.entity.FeaturesAr;
import com.zeowls.domain.entity.FeaturesEn;
import com.zeowls.domain.entity.Filter;
import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Geocode;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.entity.Ids;
import com.zeowls.domain.entity.Image;
import com.zeowls.domain.entity.Item;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.entity.MainCat;
import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Order;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.entity.Orders;
import com.zeowls.domain.entity.Phone;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.entity.ProductReview;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.Promotion;
import com.zeowls.domain.entity.Related;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.entity.Review;
import com.zeowls.domain.entity.Reviews;
import com.zeowls.domain.entity.Search;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.domain.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @ApplicationScope
    @Provides
    Mapper<VersionValidation, VersionValidation_data> VersionMapper(VersionValidationMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<HomePage, HomePage_data> homeMapper(HomeMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Brand, Brand_data> brandMapper(BrandMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Category, Category_data> categoryMapper(CategoryMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Promotion, Promotion_data> promotionMapper(PromotionMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Suggestion, Suggestion_data> suggestionMapper(SuggestionMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<MainCat, MainCat_data> mainCatMapper(MainCatMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<SubCat, SubCat_data> subCatMapper(SubCatMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Brands, Brands_data> brandsMapper(BrandsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<MainCats, MainCats_data> MainCatsMapper(MainCatsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Categories, Categories_data> CategoriesMapper(CategoriesMapper mapper) {
        return mapper;
    }


    @ApplicationScope
    @Provides
    Mapper<Product, Product_data> productMapper(ProductMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Color, Color_data> colorMapper(ColorMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Color_Color, Color_Color_data> color_Mapper(Color_Mapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Image, Image_data> ImageMapper(ImageMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Products, Products_data> productsMapper(ProductsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Filter, Filter_data> filterMapper(FilterMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Search, SearchEntity> searchMapper(SearchMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Product, Cart> cartMapper(CartMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Product, Favorite> favoriteMapper(FavoriteMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Ids, Ids_data> idsMapper(IdsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Related, Related_data> relatedMapper(RelatedMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<ProductDetails, ProductDetails_data> detailsMapper(ProductsDetailsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<ProductReview, ProductReview_data> productReviewMapper(ProductReviewMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Reviews, Reviews_data> reviewsMapper(ReviewsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Review, Review_data> reviewMapper(ReviewMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<User, User_data> userMapper(UserMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<FilterRequest, FilterRequest_data> filterRequestMapper(FilterListMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<UserRequest, UserRequest_data> userRequestMapper(UserRequestMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<UserResponse, UserResponse_data> userResponseMapper(UserResponseMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Login, Login_data> loginMapper(LoginMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Signup, Signup_data> signUpMapper(SignupMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<FacebookLogin, FacebookLogin_data> facebookLoginMapper(FacebookLoginMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<GoogleLogin, GoogleLogin_data> googleLoginMapper(GoogleLoginMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Address, Address_data> addressMapper(AddressMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Phone, Phone_data> phoneMapper(PhoneMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<AddressList, AddressList_data> addressListMapper(AddressListMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Response, Response_data> responseMapper(ResponseMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<AddAddress, AddAddress_data> addAddress(AddAddressMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Orders, Orders_data> getOrders(OrdersMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Order, Order_data> getOrder(OrderMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<com.zeowls.domain.entity.Cart, Cart_data> getOrderCart(CartOrdersMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<OrderDetails, OrderDetails_data> getOrderDetails(OrderDetailsMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<AddReview, AddReview_data> addReview(AddReviewMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<CurrentState, CurrentState_data> stateMapper(StateMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<FavCart, FavCart_data> favCartMapper(FavCartMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Item, Item_data> itemMapper(ItemMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<MakeOrder, MakeOrder_data> makeOrderMapper(MakeOrderMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<DeviceToken, DeviceToken_data> tokenMapper(TokenMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Geocode, Geocode_data> geocodeMapper(GeocodeMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<Geocoder, Geocoder_data> geocoderMapper(GeocoderMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<FeaturesAr, FeaturesAr_data> featuresArMapper(FeatureArMapper mapper) {
        return mapper;
    }

    @ApplicationScope
    @Provides
    Mapper<FeaturesEn, FeaturesEn_data> featureEnMapper(FeatureEnMapper mapper) {
        return mapper;
    }
}
