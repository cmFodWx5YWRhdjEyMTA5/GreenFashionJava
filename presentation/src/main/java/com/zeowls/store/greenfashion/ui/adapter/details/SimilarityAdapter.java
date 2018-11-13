package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Related;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class SimilarityAdapter extends BaseAdapter<Related> {
    private AppCompatActivity activity;

    public SimilarityAdapter(Context context) {
        super(context);
        this.activity = (AppCompatActivity) context;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimilarityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similarity, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }


    protected class SimilarityViewHolder extends GenericViewHolder {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.sale)
        TextView sale;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.ex_price)
        TextView ex_price;
        @BindView(R.id.hot)
        ImageView hot;
        @BindView(R.id.click)
        View clicker;
        DecimalFormat formatter;

        SimilarityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            formatter = new DecimalFormat("#,###,###");

        }

        @Override
        public void onBindData() {
            Related data = at(getAdapterPosition());
            GlideApp.with(clicker).load(data.getMainImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            title.setText(data.getName());
            configuration(data);
        }

        @OnClick(R.id.click)
        public void onClick(View v) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(at(position).getId()), CHILD.name()).addToBackStack(null).commit();

        }

        private void configuration(Related product) {
            if (product.isNew()) {
                hot.setVisibility(View.VISIBLE);
            } else {
                hot.setVisibility(View.GONE);
            }
            if (product.getQuantity() > 0) {
                if (product.isOnSale() && product.getReductionPercent() != null) {
                    sale.setVisibility(View.VISIBLE);
                    ex_price.setVisibility(View.VISIBLE);
                    sale.setText(String.valueOf(String.format("%s %s", product.getReductionPercent(), context.getString(R.string.off))));
                    price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                    ex_price.setText(String.format(context.getString(R.string.strike_line), product.getWholesalePrice()));
                    ex_price.setPaintFlags(ex_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    sale.setVisibility(View.GONE);
                    ex_price.setVisibility(View.GONE);
                    price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                }
            } else {
                sale.setVisibility(View.GONE);
                ex_price.setVisibility(View.GONE);
                price.setText(context.getString(R.string.sold_out));
            }
        }
    }
}
