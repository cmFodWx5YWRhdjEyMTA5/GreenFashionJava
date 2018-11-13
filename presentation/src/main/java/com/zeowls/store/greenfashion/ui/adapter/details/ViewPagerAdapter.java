package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.view.photoview.PhotoView;
import com.zeowls.store.greenfashion.ui.view.photoview.PhotoViewAttacher;

import java.util.List;

/**
 * The type View pager adapter.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> mDataSet;
    private boolean isShowing = true;
    private RecyclerView imagesHorizontalList;
    private PhotoView imageView;


    public ViewPagerAdapter(Context context, List<String> dataSet, RecyclerView imagesHorizontalList) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDataSet = dataSet;
        this.imagesHorizontalList = imagesHorizontalList;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_gallery, container, false);
        String image = mDataSet.get(position);
        imageView = itemView.findViewById(R.id.iv);
        GlideApp.with(context).load(image).transition(GenericTransitionOptions.with(R.anim.fade_in)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                onTap();
                return false;
            }
        }).into(imageView);


        container.addView(itemView);

        return itemView;
    }


    private void onTap() {
        PhotoViewAttacher mPhotoViewAttacher = new PhotoViewAttacher(imageView);

        mPhotoViewAttacher.setOnPhotoTapListener((view, x, y) -> {
            if (isShowing) {
                isShowing = false;
                imagesHorizontalList.animate().translationY(imagesHorizontalList.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
            } else {
                isShowing = true;
                imagesHorizontalList.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
            }
        });
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
