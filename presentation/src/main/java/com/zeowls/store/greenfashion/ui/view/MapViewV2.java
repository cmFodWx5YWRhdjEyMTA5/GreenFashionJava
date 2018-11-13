package com.zeowls.store.greenfashion.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class MapViewV2 extends MapView {
    private ViewParent mViewParent;

    public MapViewV2(Context context) {
        super(context);
    }

    public MapViewV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MapViewV2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MapViewV2(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
    }

    public void setViewParent(@Nullable ViewParent viewParent) {
        this.mViewParent = viewParent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
