package com.zeowls.store.greenfashion.ui.adapter.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.brands.BrandsFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;
import com.zeowls.store.greenfashion.ui.view.AutoViewPager;
import com.zeowls.store.greenfashion.ui.view.BrandsDecorator;
import com.zeowls.store.greenfashion.ui.view.EdgeDecorator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.SEARCH;


public class Adapter extends BaseAdapter<HomePage> {
    private static final int AD = 0;
    private static final int CATEGORY = 1;
    private static final int HOT = 2;
    private static final int NEW = 3;
    private static final int COUNT = 4;
    private SingleInteractor<Products> interactor;
    private SingleInteractor<Products> hot_interactor;
    private SingleInteractor<Products> new_interactor;
    private SingleInteractor<Products> mainCatProducts;
    private SingleInteractor<Products> subCatBrandProducts;
    private final SearchFragment searchFragment;
    private HomePage data;
    AppCompatActivity activity;

    public Adapter(Context context, SingleInteractor<Products> interactor, SingleInteractor<Products> hot_interactor, SingleInteractor<Products> new_interactor, SingleInteractor<Products> mainCatProducts, SearchFragment searchFragment, SingleInteractor<Products> subCatBrandProducts) {
        super(context);
        this.interactor = interactor;
        this.hot_interactor = hot_interactor;
        this.new_interactor = new_interactor;
        this.mainCatProducts = mainCatProducts;
        this.subCatBrandProducts = subCatBrandProducts;
        this.searchFragment = searchFragment;
        this.data = new HomePage();
        this.activity = (AppCompatActivity) context;

    }


    public void setData(HomePage data) {
        if (data == null) return;
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case AD:
                return new AdsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.include_item_pager, parent, false));
            case CATEGORY:
                return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.include_item_category, parent, false));
            case HOT:
                return new HotViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false));
            case NEW:
                return new NewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionAD(position)) {
            return AD;
        } else if (isPositionCATEGORY(position)) {
            return CATEGORY;
        } else if (isPositionHOT(position)) {
            return HOT;
        } else if (isPositionNEW(position)) {
            return NEW;
        }
        return 0;
    }

    private boolean isPositionAD(int position) {
        return position == 0;
    }

    private boolean isPositionCATEGORY(int position) {
        return position == 1;
    }

    private boolean isPositionHOT(int position) {
        return position == 2;
    }

    private boolean isPositionNEW(int position) {
        return position == 3;
    }

    protected class AdsViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.pager)
        AutoViewPager viewPager;
        PromotionAdapter pagerAdapter;

        AdsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            pagerAdapter = new PromotionAdapter(context, activity, new_interactor, hot_interactor, interactor, mainCatProducts, subCatBrandProducts);
            viewPager.setAdapter(pagerAdapter);

        }

        @Override
        public void onBindData() {
            pagerAdapter.setData(data.getPromotion());
        }


        @Override
        public void onClick(View v) {

        }
    }

    protected class BrandViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.more)
        TextView more;
        @BindView(R.id.list)
        RecyclerView brandRecycler;
        BrandAdapter brandAdapter;

        BrandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            more.setOnClickListener(this);
            brandAdapter = new BrandAdapter(context, interactor);
            GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public boolean canScrollHorizontally() {
                    return true;
                }
            };
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int index = brandAdapter.getItemViewType(position);
                    if (index == 0 || index == 1) {
                        return 2;
                    } else {
                        return 1;
                    }
                }
            });
            brandRecycler.setAdapter(brandAdapter);
            brandRecycler.setLayoutManager(layoutManager);
            brandRecycler.setHasFixedSize(true);
            brandRecycler.setNestedScrollingEnabled(false);
            brandRecycler.addItemDecoration(new BrandsDecorator());
        }

        @Override
        public void onBindData() {
            brandAdapter.setData(data.getBrands());
        }


        @Override
        public void onClick(View v) {
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, BrandsFragment.newInstance(), CHILD.name()).addToBackStack(null).commit();
        }
    }

    protected class CategoryViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.list)
        RecyclerView categoryRecycler;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.more)
        TextView more;
        CategoryAdapter categoryAdapter;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            more.setOnClickListener(this);
            categoryAdapter = new CategoryAdapter(context, mainCatProducts);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public boolean canScrollHorizontally() {
                    return true;
                }
            };
            categoryRecycler.setAdapter(categoryAdapter);
            categoryRecycler.setLayoutManager(linearLayoutManager);
            categoryRecycler.setHasFixedSize(true);
            categoryRecycler.setNestedScrollingEnabled(false);
            categoryRecycler.addItemDecoration(new EdgeDecorator(32, true));
        }

        @Override
        public void onBindData() {
            categoryAdapter.setData(data.getCategories());
        }

        @Override
        public void onClick(View v) {
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, searchFragment, SEARCH.name()).addToBackStack(SEARCH.name()).commit();
        }
    }

    protected class HotViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;

        HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            imageView.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            title.setText(R.string.hot_product);
            GlideApp.with(context).load(data.getHotImage()).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(imageView);
        }

        @Override
        public void onClick(View v) {
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(hot_interactor, -1, false, false, "Hot Products", null, -1), CHILD.name()).addToBackStack(null).commit();
        }
    }

    protected class NewViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;

        NewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            imageView.setOnClickListener(this);

        }

        @Override
        public void onBindData() {
            title.setText(R.string.new_arrival);
            GlideApp.with(context).load(data.getNewImage()).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(imageView);
        }

        @Override
        public void onClick(View v) {
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(new_interactor, -1, false, false, "New Products", null, -1), CHILD.name()).addToBackStack(null).commit();

        }
    }
}
