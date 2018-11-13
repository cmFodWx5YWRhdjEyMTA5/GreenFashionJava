package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;

import java.util.ArrayList;
import java.util.List;

public class PagerVeiwAdapter extends PagerAdapter {

    private final LayoutInflater mInflater;
    public Context context;
    private List<String> viewList;

    public PagerVeiwAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.viewList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mInflater.inflate(R.layout.image_layout, container, false);
        ImageView imageView = itemView.findViewById(R.id.image_);
        GlideApp.with(context).load(viewList.get(position)).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @NonNull
    List<String> getData() {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }

        return viewList;
    }

    public void setData(@Nullable List<String> list) {
        this.viewList.clear();
        if (list != null && !list.isEmpty()) {
            this.viewList.addAll(list);
        }

        notifyDataSetChanged();
    }
}