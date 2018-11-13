package com.zeowls.store.greenfashion.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.zeowls.store.greenfashion.ui.utils.Utils;


public class RecyclerviewMaxHeight extends RecyclerView {
    private Context context;

    public RecyclerviewMaxHeight(Context context) {
        super(context);
        this.context = context;
    }

    public RecyclerviewMaxHeight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RecyclerviewMaxHeight(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int maxHeight = 240;
        int hSize = MeasureSpec.getSize(heightSpec);
        int hMode = MeasureSpec.getMode(heightSpec);

        switch (hMode) {
            case MeasureSpec.AT_MOST:
                heightSpec = MeasureSpec.makeMeasureSpec(Math.min(hSize, Utils.dpToPx(maxHeight, context)), MeasureSpec.AT_MOST);
                break;
            case MeasureSpec.UNSPECIFIED:
                heightSpec = MeasureSpec.makeMeasureSpec(Utils.dpToPx((maxHeight), context), MeasureSpec.AT_MOST);
                break;
            case MeasureSpec.EXACTLY:
                heightSpec = MeasureSpec.makeMeasureSpec(Math.min(hSize, Utils.dpToPx((maxHeight), context)), MeasureSpec.EXACTLY);
                break;
        }
        super.onMeasure(widthSpec, heightSpec);
    }


}
