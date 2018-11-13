package com.zeowls.store.greenfashion.ui.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static com.zeowls.store.greenfashion.ui.utils.Utils.dpToPx;

public class BrandsDecorator extends RecyclerView.ItemDecoration {

    public BrandsDecorator() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemCount = state.getItemCount();

        final int itemPosition = parent.getChildAdapterPosition(view);

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // first item
        if (itemPosition == 0) {
            outRect.set(32,dpToPx(4, parent.getContext()), dpToPx(2, parent.getContext()), dpToPx(8, parent.getContext()));
        }
        // last item
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(dpToPx(2, parent.getContext()), dpToPx(4, parent.getContext()), 32,dpToPx(4, parent.getContext()));
        }
        // every other item
        else {
            if (itemPosition % 2 == 1) {
                outRect.set(dpToPx(2, parent.getContext()), dpToPx(4, parent.getContext()), dpToPx(2, parent.getContext()), dpToPx(4, parent.getContext()));
            } else {
                outRect.set(dpToPx(2, parent.getContext()), view.getPaddingTop(), dpToPx(2, parent.getContext()), dpToPx(8, parent.getContext()));
            }
        }
    }
}