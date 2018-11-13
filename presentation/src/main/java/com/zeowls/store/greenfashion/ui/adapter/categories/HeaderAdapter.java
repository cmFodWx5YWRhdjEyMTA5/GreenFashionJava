package com.zeowls.store.greenfashion.ui.adapter.categories;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Product;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;
import com.zeowls.store.greenfashion.ui.list.optionList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class HeaderAdapter extends BaseAdapter<Product> {

    public onUseCase listener;
    private final FragmentManager fragmentManager;
    private List<Product> favoriteList;

    HeaderAdapter(Context context, onUseCase listener, FragmentManager fragmentManager) {
        super(context);
        this.listener = listener;
        this.fragmentManager = fragmentManager;
        this.favoriteList = new ArrayList<>();
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeaderViewHolder(inflater.inflate(R.layout.item_product_horz, parent, false));
    }

    public void setFav(List<Product> data) {
        if (data == null) return;
        this.favoriteList = data;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public Product getProduct(int position) {
        return at(position);
    }

    public interface onUseCase {
        void insertFavorite(Product product);

        void deleteFavorite(int id);
    }

    protected class HeaderViewHolder extends BaseAdapter<Product>.GenericViewHolder {
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
        @BindView(R.id.like)
        ImageView favorite;
        @BindView(R.id.hot)
        ImageView hot;
        @BindView(R.id.option)
        ImageView option;
        @BindView(R.id.click)
        View clicker;
        DecimalFormat formatter;
        private Product product;
        AppCompatActivity activity;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            clicker.setOnClickListener(this::onClick);
            favorite.setOnClickListener(this::onFavorite);
            option.setOnClickListener(this::onOption);
            formatter = new DecimalFormat("#,###,###");
            context = inflater.getContext();
            activity = (AppCompatActivity) context;
        }

        @Override
        public void onBindData() {
            product = at(getAdapterPosition());
            GlideApp.with(context).load(product.getMainImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
            title.setText(product.getName());
            configuration(product);
            favoriteSet(product);
        }


        private void onClick(View view) {
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(at(getAdapterPosition()).getId()), CHILD.name()).addToBackStack(null).commit();
        }

        void onFavorite(View v) {
            if (product.isFavorite()) {
                listener.deleteFavorite(product.getId());
            } else {
                listener.insertFavorite(at(getAdapterPosition()));
            }
        }

        void onOption(View v) {
            Log.d("HERE", CAT.name());
            optionList.newInstance(getAdapterPosition(), product.getId(), product.getIdMainCategory(), CAT.name(), product.getMainCategory()).show(fragmentManager, "options");
        }

        private void configuration(Product product) {
            if (product.isNew()) {
                hot.setVisibility(View.VISIBLE);
            } else {
                hot.setVisibility(View.GONE);
            }
            if (product.getQuantity() > 0) {
                if (product.isOnSale() && product.getReductionPercent() != null) {
                    sale.setVisibility(View.VISIBLE);
                    ex_price.setVisibility(View.VISIBLE);
                    sale.setText(String.format("%d %s", product.getReductionPercent().intValue(), context.getString(R.string.off)));
                    price.setText(String.format(context.getString(R.string.currency), product.getPrice()));
                    ex_price.setText(String.valueOf(product.getWholesalePrice()));
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

        private void favoriteSet(Product product) {
            for (Product item : favoriteList) {
                if (item.getId() == product.getId()) {
                    product.setFavorite(true);
                    break;
                } else {
                    product.setFavorite(false);
                }
            }
            if (product.isFavorite()) {
                favorite.setImageResource(R.drawable.ic_favorite_active);
            } else {
                favorite.setImageResource(R.drawable.ic_favorite_unactive);
            }
        }

    }
}
