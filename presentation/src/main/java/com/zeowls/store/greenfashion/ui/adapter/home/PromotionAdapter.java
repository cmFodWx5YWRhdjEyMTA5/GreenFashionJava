package com.zeowls.store.greenfashion.ui.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.Promotion;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.BRAND;
import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class PromotionAdapter extends PagerAdapter {

    private Context context;
    private List<Promotion> data;
    AppCompatActivity activity;
    private SingleInteractor<Products> hot_interactor;
    private SingleInteractor<Products> new_interactor;
    private SingleInteractor<Products> interactor;
    private SingleInteractor<Products> subCatProducts;
    private SingleInteractor<Products> subCatBrandProducts;


    PromotionAdapter(Context context, AppCompatActivity activity, SingleInteractor<Products> new_interactor, SingleInteractor<Products> hot_interactor, SingleInteractor<Products> interactor, SingleInteractor<Products> subCatProducts, SingleInteractor<Products> subCatBrandProducts
    ) {
        this.context = context;
        this.activity = activity;
        data = new ArrayList<>();
        this.new_interactor = new_interactor;
        this.hot_interactor = hot_interactor;
        this.interactor = interactor;
        this.subCatProducts = subCatProducts;
        this.subCatBrandProducts = subCatBrandProducts;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_ads, container, false);
        ImageView image = layout.findViewById(R.id.image);
        if (data.get(position).getType() != null) {
            switch (data.get(position).getType()) {
                case 0:
                    break;
                case 1:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(data.get(position).getIdProduct()), CHILD.name()).addToBackStack(null).commit());
                    break;
                case 2:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(hot_interactor, -1, false, false, context.getString(R.string.hot_product), null, -1), CHILD.name()).addToBackStack(null).commit());
                    break;
                case 3:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(new_interactor, -1, false, false, context.getString(R.string.new_arrival), null, -1), CHILD.name()).addToBackStack(null).commit());
                    break;
                case 4:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(subCatBrandProducts, data.get(position).getIdBrand(), false, false, context.getString(R.string.collection), null, data.get(position).getIdCategory()), CHILD.name()).addToBackStack(null).commit());
                    break;
                case 5:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(interactor, data.get(position).getIdBrand(), true, false, data.get(position).getBrandName(), BRAND.name(), -1), CHILD.name()).addToBackStack(null).commit());
                    break;
                case 6:
                    image.setOnClickListener(view -> activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(subCatProducts, data.get(position).getIdCategory(), true, true, data.get(position).getCategoryName(), CAT.name(), -1), CHILD.name()).addToBackStack(null).commit());
                    break;
            }
        }
        GlideApp.with(context).load(data.get(position).getImageUrl()).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
        container.addView(layout);
        return layout;
    }

    public void setData(List<Promotion> data) {
        if (data == null) return;
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}