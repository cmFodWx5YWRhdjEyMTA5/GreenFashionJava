package com.zeowls.store.greenfashion.ui.adapter.cart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Item;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class CartAdapter extends BaseAdapter<Product> {
    private AppCompatActivity activity;
    private final onProductCase listener;

    public CartAdapter(Context context, onProductCase listener) {
        super(context);
        this.listener = listener;
        this.activity = (AppCompatActivity) context;
    }


    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CartViewHolder(inflater.inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public int orderPrice() {
        int total = 0;
        for (Product product : data) {
            if (product.getQuantity() > 0)
                total += product.getCartQuantity() * product.getPrice();
        }
        return total;
    }

    public Products getProducts() {
        Products products = new Products();
        List<Product> stockProducts = new ArrayList<>();
        for (Product product : data) {
            if (product.getQuantity() > 0)
                stockProducts.add(product);
        }
        products.setProduct(stockProducts);
        return products;
    }

    public List<Item> orderData() {
        List<Item> items = new ArrayList<>();
        for (Product product : data) {
            if (product.getQuantity() > 0) {
                Item item = new Item();
                item.setItemId(product.getId());
                item.setItemQuantity(product.getCartQuantity());
                items.add(item);
            }
        }
        return items;
    }

    public interface onProductCase {
        void move(Product product);

        void edit(int id, int count);

        void remove(int id);

        void rePricing();

        void showError(String max_quantity);
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
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.plus)
        ImageView plus;
        @BindView(R.id.minus)
        ImageView minus;
        @BindView(R.id.move)
        TextView move;
        @BindView(R.id.remove)
        TextView remove;
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
            Product product = at(getAdapterPosition());
            GlideApp.with(context).load(product.getMainImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            title.setText(product.getName());
            count.setText(String.valueOf(product.getCartQuantity()));
            configuration(product);
        }

        @OnClick(R.id.plus)
        public void onPlus(View view) {
            int position = getAdapterPosition();
            if (at(position).getCartQuantity() < at(position).getQuantity()) {
                listener.edit(at(position).getId(), at(position).getCartQuantity() + 1);
                listener.rePricing();
            } else {
                listener.showError(context.getString(R.string.limit));
            }
        }

        @OnClick(R.id.minus)
        public void onMinus(View view) {
            int position = getAdapterPosition();
            if (at(position).getCartQuantity() > 1) {
                listener.edit(at(position).getId(), at(position).getCartQuantity() - 1);
                listener.rePricing();
            }
        }

        @OnClick(R.id.move)
        public void onMove(View view) {
            int position = getAdapterPosition();
            listener.move(at(position));
            listener.rePricing();
        }

        @OnClick(R.id.remove)
        public void onRemove(View view) {
            int position = getAdapterPosition();
            listener.remove(at(position).getId());
            listener.rePricing();
        }

        @OnClick(R.id.click)
        public void onClick(View view) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(at(position).getId()), CHILD.name()).addToBackStack(null).commit();
        }

        private void configuration(Product product) {
            if (product.getQuantity() > 0) {
                if (product.isOnSale() && product.getReductionPercent() != null) {
                    ex_price.setVisibility(View.VISIBLE);
                    price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                    ex_price.setText(String.format(context.getString(R.string.strike_line), product.getWholesalePrice()));
                } else {
                    ex_price.setVisibility(View.GONE);
                    price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                }
            } else {
                ex_price.setVisibility(View.GONE);
                price.setText(context.getString(R.string.sold_out));
            }
        }
    }
}
