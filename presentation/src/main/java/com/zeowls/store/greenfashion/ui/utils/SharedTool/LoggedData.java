package com.zeowls.store.greenfashion.ui.utils.SharedTool;

import android.content.Context;

import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.User;


public class LoggedData {

    private static String TAG_SAVE_LOGIN_DATA = "login_data";
    private static String TAG_SAVE_USER_CART = "cart_data";
    private static String TAG_SAVE_CART = "cart_products";
    private static String TAG_LANGUAGE_SCREEN = "language";
    private static String TAG_SHOPPING_CART = "shopping_carts";
    private static String TAG_LOCALIZATION = "localization";
    private static String TAG_ADDRESS = "address";
    private static String TAG_PHONE = "phone";
    private static String TAG_NOTIFICATION_STATE = "notification_state";
    private static String TAG_CURRENCY = "Currency";
    private static String TAG_USER_CODE = "user_code";
    private static String TAG_USER_PASSWORD = "user_password";
    private static String TAG_REMEMBER_ME = "remember_me";
    private static String TAG_SAVE_PROFILE_INFO = "profile_info";
    private static String TAG_TOKEN = "token";
    private static Boolean TAG_TOKEN_INSERT_STATE = false;


    public static void saveUserObject(Context context, Object myObject, boolean rememberMe) {
        SharedPreferencesTool.saveObject(context, TAG_SAVE_LOGIN_DATA, myObject);
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static User getUserObject(Context context) {
        return SharedPreferencesTool.getObject(context, TAG_SAVE_LOGIN_DATA, User.class);
    }

    public static void saveUserCart(Context context, Object myObject, boolean rememberMe) {
        SharedPreferencesTool.saveObject(context, TAG_SAVE_USER_CART, myObject);
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static MakeOrder getUserCart(Context context) {
        return SharedPreferencesTool.getObject(context, TAG_SAVE_USER_CART, MakeOrder.class);
    }

    public static void saveUserCartProducts(Context context, Object myObject, boolean rememberMe) {
        SharedPreferencesTool.saveObject(context, TAG_SAVE_CART, myObject);
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static Products getUserCartProducts(Context context) {
        return SharedPreferencesTool.getObject(context, TAG_SAVE_CART, Products.class);
    }

    public static void saveToken(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_TOKEN, value);
    }

    public static String getToken(Context context) {
        return SharedPreferencesTool.getString(context, TAG_TOKEN);
    }


    public static boolean getRemmemberMe(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_REMEMBER_ME);
    }

    public static void saveRemmemberMe(Context context, boolean rememberMe) {
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static void saveLocalization(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_LOCALIZATION, value);
    }

    public static int getLocalization(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_LOCALIZATION);
    }


    public static void saveFavAddress(Context context, Object myObject, boolean rememberMe) {
        SharedPreferencesTool.saveObject(context, TAG_ADDRESS, myObject);
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static Address getFavAddress(Context context) {
        return SharedPreferencesTool.getObject(context, TAG_ADDRESS, Address.class);
    }

    public static void savePhone(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_PHONE, value);
    }

    public static int getPhone(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_PHONE);
    }

    public static void saveNotificationState(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_NOTIFICATION_STATE, value);
    }

    public static int getNotificationState(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_NOTIFICATION_STATE);
    }

    public static void saveUserCurrencyID(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_CURRENCY, value);
    }

    public static int getUserCurrencyID(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_CURRENCY);
    }

    public static void saveUserCode(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_USER_CODE, value);
    }

    public static String getUserCode(Context context) {
        return SharedPreferencesTool.getString(context, TAG_USER_CODE);
    }

    public static void saveUserPassword(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_USER_PASSWORD, value);
    }

    public static String getUserPassword(Context context) {
        return SharedPreferencesTool.getString(context, TAG_USER_PASSWORD);
    }

    public static void clearShared(Context context) {
        SharedPreferencesTool.clearObject(context);
    }

    public static void clearUser(Context context) {
        SharedPreferencesTool.clearObject(context, TAG_SAVE_LOGIN_DATA);
        SharedPreferencesTool.clearObject(context, TAG_SAVE_CART);
        SharedPreferencesTool.clearObject(context, TAG_SAVE_USER_CART);
        SharedPreferencesTool.clearObject(context, TAG_ADDRESS);
    }

    public static void setLanguageScreen(Context context, boolean isShown) {
        SharedPreferencesTool.setBoolean(context, isShown, TAG_LANGUAGE_SCREEN);
    }

    public static boolean isLanguageShown(Context context) {
        return SharedPreferencesTool.getBooleanLang(context, TAG_LANGUAGE_SCREEN);
    }

    public static void saveReadyShoppingCart(Context context, boolean isShown) {
        SharedPreferencesTool.setBoolean(context, isShown, TAG_SHOPPING_CART);
    }

    public static boolean getReadyShoppingCart(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_SHOPPING_CART);
    }
}
