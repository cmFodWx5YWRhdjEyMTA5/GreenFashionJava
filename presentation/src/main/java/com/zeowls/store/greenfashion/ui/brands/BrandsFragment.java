package com.zeowls.store.greenfashion.ui.brands;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.interactor.BrandProducts_interactor;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.brands.BrandsAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.view.EndlessRecycler;

import javax.inject.Inject;

import butterknife.BindView;

public class BrandsFragment extends BaseFragment implements BrandsContract.View {
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private BrandsContract.Presenter presenter;
    private BrandsAdapter mAdapter;
    @Inject
    BrandProducts_interactor interactor;

    public BrandsFragment() {
    }

    public static BrandsFragment newInstance() {
        BrandsFragment fragment = new BrandsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_brands;
    }

    @Override
    protected void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull BrandsContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    protected void init() {
        toolbarTitle.setText(R.string.brands);
        mAdapter = new BrandsAdapter(getContext(), interactor);
        LinearLayoutManager layoutManager;
        mRecyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecycler(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                fireMoreCall(current_page);
            }
        });
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
        presenter.getData();
    }


    @Override
    public void setData(Brands data) {
        super.populate();
        isFetched = true;
        mAdapter.setData(data.getBrand());
    }

    @Override
    public void showErrorMessage(String s) {

    }

    @Override
    public void appendMore(Brands data) {
        mAdapter.appendData(data.getBrand());

    }

    private void fireMoreCall(int pageCount) {
        presenter.getMore(pageCount);
    }
}
