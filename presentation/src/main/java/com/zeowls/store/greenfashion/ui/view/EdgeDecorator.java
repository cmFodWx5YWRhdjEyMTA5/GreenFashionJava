package com.zeowls.store.greenfashion.ui.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class EdgeDecorator extends RecyclerView.ItemDecoration {

    private int edgePadding;
    private boolean usePadding;

    public EdgeDecorator(int edgePadding, boolean usePadding) {
        this.edgePadding = edgePadding;
        this.usePadding = usePadding;
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
            outRect.set(edgePadding, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        }
        // last item
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(view.getPaddingStart(), view.getPaddingTop(), edgePadding, view.getPaddingBottom());
        }
        // every other item
        else {
            if (usePadding) {
                if (itemPosition == 1) {
                    outRect.set(16, view.getPaddingTop(), 8, view.getPaddingBottom());
                } else if (itemCount > 0 && itemPosition == itemCount - 2) {
                    outRect.set(8, view.getPaddingTop(), 16, view.getPaddingBottom());
                } else {
                    outRect.set(8, view.getPaddingTop(), 8, view.getPaddingBottom());
                }
            } else
                outRect.set(view.getPaddingStart(), view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        }
    }
}