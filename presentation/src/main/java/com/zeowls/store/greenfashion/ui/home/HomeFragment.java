package com.zeowls.store.greenfashion.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.interactor.BrandProducts_interactor;
import com.zeowls.domain.interactor.Hot_interactor;
import com.zeowls.domain.interactor.MainCatProducts_interactor;
import com.zeowls.domain.interactor.New_interactor;
import com.zeowls.domain.interactor.SubCatBrandProducts;
import com.zeowls.domain.interactor.SubCatProducts_interactor;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.home.Adapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private HomeContract.Presenter presenter;
    private Adapter mAdapter;
    @Inject
    BrandProducts_interactor brand_interactor;
    @Inject
    Hot_interactor hot_interactor;
    @Inject
    New_interactor new_interactor;
    @Inject
    MainCatProducts_interactor mainCatProducts;
    @Inject
    SubCatBrandProducts subCatBrandProducts;

    @Inject
    SearchFragment searchFragment;

    public HomeFragment() {
    }

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull HomeContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }


    @Override
    public void init() {
        toolbarTitle.setText(R.string.app_name);
        mAdapter = new Adapter(getContext(), brand_interactor, hot_interactor, new_interactor, mainCatProducts,searchFragment, subCatBrandProducts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
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
    public void setData(HomePage data) {
        super.populate();
        isFetched = true;
        mAdapter.setData(data);
    }

    @Override
    public void showErrorMessage(String s) {

    }

    @Override
    public void showEmptyMessage(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
