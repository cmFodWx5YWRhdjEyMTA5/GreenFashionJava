package com.zeowls.store.greenfashion.ui.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

import timber.log.Timber;

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try { 
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi 
                item.setShifting(false);
                // set once again checked value, so view will be updated 
                //noinspection RestrictedApi 
                item.setChecked(item.getItemData().isChecked());
            } 
        } catch (NoSuchFieldException e) {
            Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Timber.e(e, "Unable to change value of shift mode");
        } 
    } 
} 