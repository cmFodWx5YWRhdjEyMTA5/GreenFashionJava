package com.zeowls.store.greenfashion.ui.adapter.categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.categories.CategoriesFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;
import com.zeowls.store.greenfashion.ui.view.EdgeDecorator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class CategoriesAdapter extends BaseAdapter<SubCat> {
    private final int TOP_ITEMS = 0;
    private CategoriesFragment fragment;
    private final SingleInteractor<Products> mainCatProducts;
    private final SingleInteractor<Products> subCatProducts;
    private List<Product> products;
    private List<Product> favorite;
    HeaderAdapter headerAdapter;
    private FragmentManager fragmentManager;


    public CategoriesAdapter(Context context, CategoriesFragment fragment, SingleInteractor<Products> mainCatProducts, SingleInteractor<Products> subCatProducts) {
        super(context);
        this.fragment = fragment;
        this.mainCatProducts = mainCatProducts;
        this.subCatProducts = subCatProducts;
        this.products = new ArrayList<>();
        this.favorite = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return data.size() != 0 ? data.size() + 1 : 0;
    }

    public void setHeader(List<Product> data) {
        if (data == null) return;
        this.products = data;
    }

    public void setFav(List<Product> data) {
        favorite.clear();
        if (data == null) return;
        this.favorite = data;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        if (fragmentManager == null) return;
        this.fragmentManager = fragmentManager;
    }

    public Product getProduct(int position) {
        return headerAdapter.getProduct(position);
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TOP_ITEMS)
            return new HeaderViewHolder(inflater.inflate(R.layout.include_categories_header, parent, false));
        else
            return new CategoriesViewHolder(inflater.inflate(R.layout.item_single_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TOP_ITEMS;
        else
            return position;
    }

    protected class HeaderViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.list)
        RecyclerView recyclerView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.more)
        TextView more;
        @BindView(R.id.title_shop)
        TextView title_shop;
        AppCompatActivity activity;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            activity = (AppCompatActivity) context;
            more.setOnClickListener(this);
            headerAdapter = new HeaderAdapter(context, fragment, fragmentManager);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.addItemDecoration(new EdgeDecorator(32, false));
            recyclerView.setAdapter(headerAdapter);

            if (products.size() > 0) {
                title.setVisibility(View.VISIBLE);
                more.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
                more.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }

            if (data.size() > 0) {
                title_shop.setVisibility(View.VISIBLE);
            } else {
                title_shop.setVisibility(View.GONE);
            }

        }

        @Override
        public void onBindData() {
            headerAdapter.setData(products);
            headerAdapter.setFav(favorite);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(mainCatProducts, at(position).getIdParent(), false, false, products.get(0).getMainCategory(), null, -1), CHILD.name()).addToBackStack(null).commit();
        }
    }

    protected class CategoriesViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.title)
        TextView title;
        AppCompatActivity activity;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            activity = (AppCompatActivity) context;
            title.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            SubCat cats = at(getAdapterPosition() - 1);
            title.setText(cats.getName().trim());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition() - 1;
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(subCatProducts, at(position).getId(), true, true, at(position).getName(), CAT.name(), -1), CHILD.name()).addToBackStack(null).commit();
        }
    }
}
