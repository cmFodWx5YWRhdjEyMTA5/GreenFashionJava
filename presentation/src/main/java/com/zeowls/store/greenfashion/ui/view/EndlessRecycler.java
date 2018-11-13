package com.zeowls.store.greenfashion.ui.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

public abstract class EndlessRecycler extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecycler.class.getSimpleName();
    private int totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold;
    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    private int height;
    private ConstraintLayout view;
    private int Y;

    public EndlessRecycler(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.visibleThreshold = 5;
    }

    public EndlessRecycler(GridLayoutManager gridLayoutManager, ConstraintLayout view) {
        this.mGridLayoutManager = gridLayoutManager;
        this.view = view;
        this.visibleThreshold = 10;
        this.height = view.getMeasuredHeight();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Y = dy;
        int visibleItemCount = recyclerView.getChildCount();

        int firstVisibleItem;
        if (mGridLayoutManager != null) {
            totalItemCount = mGridLayoutManager.getItemCount();
            firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
        } else {
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold) && totalItemCount >= 20) {

            current_page++;
            onLoadMore(current_page);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (RecyclerView.SCROLL_AXIS_VERTICAL == newState) {
            if (view != null) {
                if (Y > 0 && totalItemCount > 4) {
                    slideUp(view);
                } else if (Y < 0) {
                    slideDown(view);
                }
            }
        }


        super.onScrollStateChanged(recyclerView, newState);
    }

    public abstract void onLoadMore(int current_page);

    public void reset() {
        this.previousTotal = 0;
        this.loading = true;
        current_page = 1;
    }

    private void slideUp(ConstraintLayout child) {
        child.clearAnimation();
        child.animate().translationY(-height).setDuration(200).setInterpolator(new AnticipateInterpolator());
        child.setVisibility(View.GONE);
    }

    private void slideDown(ConstraintLayout child) {
        child.clearAnimation();
        child.setVisibility(View.VISIBLE);
        child.animate().translationY(0).setDuration(200).setInterpolator(new AnticipateInterpolator());
    }
}