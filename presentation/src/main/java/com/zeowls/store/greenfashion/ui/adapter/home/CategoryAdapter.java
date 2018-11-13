package com.zeowls.store.greenfashion.ui.adapter.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.categories.CategoriesFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class CategoryAdapter extends BaseAdapter<Category> {
    private SingleInteractor<Products> subCatProducts;


    CategoryAdapter(Context context, SingleInteractor<Products> subCatProducts) {
        super(context);
        this.subCatProducts = subCatProducts;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(inflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    protected class CategoryViewHolder extends BaseAdapter<Category>.GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.click)
        View clicker;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            clicker.setOnClickListener(this);
            context = inflater.getContext();

        }

        @Override
        public void onBindData() {
            Category category = at(getAdapterPosition());
            title.setText(category.getName());
            GlideApp.with(context).load(category.getImage()).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            AppCompatActivity activity = (AppCompatActivity) context;
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(subCatProducts, at(position).getId(), true, true, at(position).getName(), CAT.name(), -1), CHILD.name()).addToBackStack(null).commit();
        }
    }
}
