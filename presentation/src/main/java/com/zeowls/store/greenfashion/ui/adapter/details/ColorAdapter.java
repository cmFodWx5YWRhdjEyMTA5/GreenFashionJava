package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zeowls.domain.entity.Color;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdapter extends BaseAdapter<Color> {


    private int lastCheckedPosition = -1;

    private colorPicker colorPicker;
    private Color colorData;

    public ColorAdapter(Context context, colorPicker colorPicker) {
        super(context);
        this.colorPicker = colorPicker;
    }


    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ColorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public interface colorPicker {
        void oPickColor(int color);
    }

    protected class ColorViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.color)
        ImageView color;

        ColorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            color.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            colorData = at(getAdapterPosition());
            GradientDrawable bgShape = (GradientDrawable) color.getBackground();
            if (colorData.getColor().getCode().contains("#"))
                bgShape.setColor(android.graphics.Color.parseColor(colorData.getColor().getCode()));
            if (getAdapterPosition() == lastCheckedPosition)
                color.setImageResource(R.drawable.circle_selected);
            else
                color.setImageResource(0);
        }


        @Override
        public void onClick(View v) {
            if (lastCheckedPosition == getAdapterPosition()) {
                colorPicker.oPickColor(-1);
                lastCheckedPosition = -1;
                notifyDataSetChanged();
            } else {
                lastCheckedPosition = getAdapterPosition();
                notifyDataSetChanged();
                colorPicker.oPickColor(colorData.getColor().getId());
            }
        }
    }
}
