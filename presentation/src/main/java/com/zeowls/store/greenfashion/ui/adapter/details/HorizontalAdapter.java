package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalAdapter extends BaseAdapter<String> {

    private int mSelectedItem = -1;
    private OnImgClick onImgClick;

    public HorizontalAdapter(Context context, OnImgClick onImgClick) {
        super(context);
        this.onImgClick = onImgClick;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryViewHolder(inflater.inflate(R.layout.item_gallery_horz, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    protected class GalleryViewHolder extends BaseAdapter<String>.GenericViewHolder {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        ColorMatrix matrix;
        ColorMatrixColorFilter filter;

        GalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            matrix = new ColorMatrix();
            filter = new ColorMatrixColorFilter(matrix);
        }

        @Override
        public void onBindData() {
            String product = at(getAdapterPosition());
            GlideApp.with(context).load(product).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            if (mSelectedItem != getAdapterPosition()) {
                matrix.setSaturation(0);
                image.setColorFilter(filter);
                image.setAlpha(0.5f);
            } else {
                matrix.setSaturation(1);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                image.setColorFilter(filter);
                image.setAlpha(1f);
            }
            image.setOnClickListener(view -> onImgClick.onClick(getAdapterPosition()));
        }
    }

    public void setSelectedItem(int position) {
        if (position >= data.size()) return;
        mSelectedItem = position;
        notifyDataSetChanged();
    }

    public interface OnImgClick {
        void onClick(int pos);
    }

}
