package com.zeowls.store.greenfashion.ui.adapter.submitAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Product;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitAdapter extends BaseAdapter<Product> {

    public SubmitAdapter(Context context) {
        super(context);
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartViewHolder(inflater.inflate(R.layout.item_submit, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public int orderPrice() {
        int total = 0;
        for (Product product : data) {
            total += product.getCartQuantity() * product.getPrice();
        }
        return total;
    }

    protected class CartViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.ex_price)
        TextView ex_price;
        @BindView(R.id.count)
        TextView count;
        DecimalFormat formatter;

        CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            formatter = new DecimalFormat("#,###,###");
            context = inflater.getContext();
        }

        @Override
        public void onBindData() {
            Product product = at(getAdapterPosition());
            GlideApp.with(context).load(product.getMainImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            title.setText(product.getName());
            count.setText(String.format(context.getString(R.string.quantity_tag), product.getCartQuantity()));
            configuration(product);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
        }

        private void configuration(Product product) {
            if (product.isOnSale() && product.getReductionPercent() != null) {
                ex_price.setVisibility(View.VISIBLE);
                price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                ex_price.setText(String.format(context.getString(R.string.strike_line), product.getWholesalePrice()));
            } else {
                ex_price.setVisibility(View.GONE);
                price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
            }
        }
    }
}
