package com.zeowls.store.greenfashion.ui.adapter.orderDetails;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Cart;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class OrderDetailsAdapter extends BaseAdapter<Cart> {
    private AppCompatActivity activity;
    private orderEvents orderEvents;
    private boolean isDelivered;


    public OrderDetailsAdapter(Context context, orderEvents orderEvents) {
        super(context);
        this.orderEvents = orderEvents;
        this.activity = (AppCompatActivity) context;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartViewHolder(inflater.inflate(R.layout.item_order_details, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }


    public interface orderEvents {
        void onRate(int id);
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    protected class CartViewHolder extends GenericViewHolder {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.ex_price)
        TextView ex_price;
        @BindView(R.id.counter)
        TextView count;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.rate)
        Button rate;
        @BindView(R.id.click)
        View clicker;
        DecimalFormat formatter;

        CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            formatter = new DecimalFormat("#,###,###");
            context = inflater.getContext();
        }

        @Override
        public void onBindData() {
            Cart product = at(getAdapterPosition());
            GlideApp.with(context).load(product.getProductImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            title.setText(product.getProductName());
            count.setText(String.valueOf(product.getQuantity()));
            configuration(product);
        }

        @OnClick(R.id.click)
        public void onClick(View v) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(at(position).getIdProduct()), CHILD.name()).addToBackStack(null).commit();
        }

        @OnClick(R.id.rate)
        void onRate(View v) {
            int position = getAdapterPosition();
            orderEvents.onRate(at(position).getId());
        }

        private void configuration(Cart product) {
            if (product.getProductOnSale() != null && product.getProductReductionPercent() != null && product.getProductOnSale()) {
                ex_price.setVisibility(View.VISIBLE);
                price.setText(String.format(context.getString(R.string.currency), product.getProductReductionPrice()));
                ex_price.setText(String.format(context.getString(R.string.strike_line), product.getProductWholesalePrice()));
            } else {
                ex_price.setVisibility(View.GONE);
                price.setText(String.format(context.getString(R.string.currency), product.getProductWholesalePrice()));
            }
            if (product.getProductRate() != null) {
                ratingBar.setRating(product.getProductRate().floatValue());
            }
            if (isDelivered) {
                rate.setVisibility(View.VISIBLE);
            } else {
                rate.setVisibility(View.GONE);
            }
        }
    }
}
