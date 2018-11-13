package com.zeowls.store.greenfashion.ui.adapter.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.list.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.BRAND;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class BrandAdapter extends BaseAdapter<Brand> {
    private final int VIEW_TYPE_BIG = 0;
    private final int VIEW_TYPE_DEFAULT = 2;
    private final SingleInteractor<Products> interactor;

    public BrandAdapter(Context context, SingleInteractor<Products> interactor) {
        super(context);
        this.interactor = interactor;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_BIG:
                return new BrandViewHolder(inflater.inflate(R.layout.item_brand_big, parent, false));
            case VIEW_TYPE_DEFAULT:
                return new BrandViewHolder(inflater.inflate(R.layout.item_brand_small, parent, false));
            default:
                return new BrandViewHolder(inflater.inflate(R.layout.item_brand_small, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    protected class BrandViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView Image;
        @BindView(R.id.click)
        View clickReceiver;
        private Brand brand;
        AppCompatActivity activity;

        BrandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            clickReceiver.setOnClickListener(this);
            context = inflater.getContext();
            activity = (AppCompatActivity) context;
        }

        @Override
        public void onBindData() {
            brand = at(getAdapterPosition());
            GlideApp.with(context).load(brand.getImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(Image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(interactor, at(position).getId(), true, false, brand.getName(), BRAND.name(), -1), CHILD.name()).addToBackStack(null).commit();
        }
    }
}
