package com.zeowls.store.greenfashion.ui.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zeowls.store.greenfashion.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class Utils {
    public static <T> T convertFromJsonString(String jsonString, Type type) {
        if (jsonString == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    public static String convertToJsonString(Object object, Type type) {
        if (object == null) return null;
        Gson gson = new Gson();
        return gson.toJson(object, type);
    }

    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static boolean isValidEmail(EditText editText) {
        return !TextUtils.isEmpty(editText.getText().toString()) && android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString()).matches();
    }

    public static boolean isValidField(EditText editText, Context context) {
        if (TextUtils.isEmpty(editText.getText().toString()))
            editText.setError(context.getString(R.string.required));
        else
            editText.setError(null);
        return !TextUtils.isEmpty(editText.getText().toString()) && editText.getError() == null;
    }

    public static boolean isValidPassword(CharSequence charSequence) {
        return Pattern.compile(".{8,16}$").matcher(charSequence).matches();
    }

    public static void setInputTextLayoutColor(EditText editText, ColorStateList colorState) {
        TextInputLayout textInputLayout = (TextInputLayout) editText.getParent().getParent();
        try {
            ViewCompat.setBackgroundTintList(editText, colorState);
            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(textInputLayout, colorState);


            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            fFocusedTextColor.setAccessible(true);
            fFocusedTextColor.set(textInputLayout, colorState);

            Method method = TextInputLayout.class.getDeclaredMethod("updateLabelState", boolean.class);
            method.setAccessible(true);
            method.invoke(textInputLayout, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
}
