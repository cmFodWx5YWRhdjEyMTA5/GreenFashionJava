package com.zeowls.store.greenfashion.ui.favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.favorite.FavoriteAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@ApplicationScope
public class FavoriteFragment extends BaseFragment implements FavoriteContract.View, FavoriteAdapter.onProductCase {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.error)
    ViewGroup error;
    FavoriteAdapter mAdapter;
    private FavoriteContract.Presenter presenter;

    @Inject
    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    @Override
    public void init() {
        super.vanish();
        toolbarTitle.setText(R.string.favorite);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new FavoriteAdapter(getContext(), this);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.grid_layout_animation_from_bottom));
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isFetched)
            super.fetchData();
    }


    @Override
    public void onPause() {
        super.onPause();
        super.disposeFetch();
    }

    @Override
    public void fireCall() {
        super.fireCall();
        presenter.renewFav();
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull FavoriteContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(List<Product> products) {
        super.populate();
        mAdapter.setData(products);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        super.populate();
        mAdapter.clearData();
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void getFavorite(Products data) {
        presenter.getData();
    }

    @Override
    public void addCart(Product product) {
        if (product.getQuantity() > 0)
            presenter.addCart(product);
        else
            Toast.makeText(getContext(), getString(R.string.sold_out), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeCart(int id) {
        presenter.doRemoveCart(id);
    }

    @Override
    public void remove(int id) {
        presenter.doRemoveFavorite(id);
    }
}
