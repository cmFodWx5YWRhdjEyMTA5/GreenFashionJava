package com.zeowls.store.greenfashion.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class ClickableViewPager extends ViewPager {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    private class TapGestureListener extends SimpleOnGestureListener {
        private TapGestureListener() {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getCurrentItem());
            }
            return true;
        }
    }

    public ClickableViewPager(Context context) {
        super(context);
        setup();
    }

    public ClickableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setup();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new TapGestureListener());
        setOnTouchListener((view, motionEvent) -> {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
