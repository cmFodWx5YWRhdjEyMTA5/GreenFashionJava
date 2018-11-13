package com.zeowls.store.greenfashion.ui.adapter.favorite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.data.LocaleManager;
import com.zeowls.domain.entity.Product;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class FavoriteAdapter extends BaseAdapter<Product> {

    onProductCase listener;
    private AppCompatActivity activity;

    public FavoriteAdapter(Context context, onProductCase listener) {
        super(context);
        this.listener = listener;
        this.activity = (AppCompatActivity) context;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(inflater.inflate(R.layout.item_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public interface onProductCase {
        void addCart(Product product);

        void removeCart(int id);

        void remove(int id);
    }

    protected class FavoriteViewHolder extends BaseAdapter<Product>.GenericViewHolder {
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
        @BindView(R.id.cart)
        ImageView cart;
        @BindView(R.id.hot)
        ImageView hot;
        @BindView(R.id.remove)
        ImageView remove;
        @BindView(R.id.click)
        View clicker;
        DecimalFormat formatter;

        FavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            formatter = new DecimalFormat("#,###,###");
            context = inflater.getContext();
        }

        @Override
        public void onBindData() {
            Product product = at(getAdapterPosition());
            GlideApp.with(context).load(product.getMainImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
                title.setText(product.getName());
            else
                title.setText(product.getNameAr());
            configuration(product);
        }

        @OnClick(R.id.cart)
        public void onCart(View v) {
            int position = getAdapterPosition();
            if (at(position).isCart()) {
                listener.removeCart(at(position).getId());
            } else {
                if (at(position).getQuantity() > 0)
                    listener.addCart(at(position));
                else
                    Toast.makeText(context, context.getString(R.string.sold_out), Toast.LENGTH_SHORT).show();
            }
        }

        @OnClick(R.id.remove)
        public void onRemove(View v) {
            int position = getAdapterPosition();
            listener.remove(at(position).getId());
        }

        @OnClick(R.id.click)
        public void onClick(View v) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(at(position).getId()), CHILD.name()).addToBackStack(null).commit();
        }

        private void configuration(Product product) {
            if (product.isCart()) {
                cart.setSelected(true);
            } else {
                cart.setSelected(false);
            }

//            if (product.isNew()) {
//                hot.setVisibility(View.VISIBLE);
//            } else {
//                hot.setVisibility(View.GONE);
//            }
//            if (product.isOnSale() && product.getReductionPercent() != null) {
//                sale.setVisibility(View.VISIBLE);
//                ex_price.setVisibility(View.VISIBLE);
//                sale.setText(String.format("%d %s", product.getReductionPercent().intValue(), context.getString(R.string.off)));
//                price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
//                ex_price.setText(String.valueOf(product.getWholesalePrice()));
//                ex_price.setPaintFlags(ex_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//            } else {
//                sale.setVisibility(View.GONE);
//                ex_price.setVisibility(View.GONE);
//                price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
//            }
        }
    }
}
