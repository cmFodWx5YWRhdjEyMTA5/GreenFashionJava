package com.zeowls.store.greenfashion.ui.list;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeowls.domain.entity.Filter;
import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.Filter_interactor;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.list.ListAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.view.EndlessRecycler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zeowls.store.greenfashion.ui.utils.Utils.convertToJsonString;

public class ListFragment extends BaseFragment implements ListContract.View, ListAdapter.onUseCase, FiltersFragment.Listener, optionList.Listener {
    private static String ID = "id";
    private static String SEC_ID = "sec_id";
    private static String FILTER = "filter";
    private static String SUB = "sub";
    private static String TITLE = "title";
    private static String CHOICE = "choice";
    private SingleInteractor<Products> interactor;
    private ListContract.Presenter presenter;
    private ListAdapter mAdapter;
    private int id, secId;
    private boolean hasFilter, isSub;
    private String title, choice;
    private ArrayList cats, brands;

    @Inject
    Filter_interactor filterInteractor;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.filter)
    Button filter;
    @BindView(R.id.counter)
    TextView count;
    @BindView(R.id.main)
    TextView main;
    @BindView(R.id.sub)
    TextView sub;
    @BindView(R.id.header)
    ConstraintLayout header;
    private int min, max;
    private EndlessRecycler scrollListener;
    private Products baseData;
    private FilterRequest filterBody;
    private boolean isFilter;
    private int filterCurrent_page;
    private int Current_page;
    private Filter filterData;

    public ListFragment() {
    }

    public static ListFragment newInstance(SingleInteractor<Products> interactor, int id, boolean filter, boolean sub, String title, String choice, int secId) {
        ListFragment fragment = new ListFragment();
        fragment.interactor = interactor;
        Bundle args = new Bundle();
        args.putInt(ID, id);
        args.putInt(SEC_ID, secId);
        args.putBoolean(FILTER, filter);
        args.putBoolean(SUB, sub);
        args.putString(TITLE, title);
        args.putString(CHOICE, choice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ID, -1);
            secId = getArguments().getInt(SEC_ID, -1);
            hasFilter = getArguments().getBoolean(FILTER, false);
            isSub = getArguments().getBoolean(SUB, false);
            title = getArguments().getString(TITLE);
            choice = getArguments().getString(CHOICE, "-1");
        }
        Current_page = 1;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_list;
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
        toolbarTitle.setText(R.string.products);
        back.setOnClickListener(view -> getActivity().onBackPressed());
        cats = new ArrayList();
        brands = new ArrayList();
        GridLayoutManager layoutManager;
        recyclerView.setLayoutManager(layoutManager = new GridLayoutManager(getContext(), 2));
        mAdapter = new ListAdapter(getContext(), this, getChildFragmentManager());
        mAdapter.setChoice(choice);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.grid_layout_animation_from_bottom));
        recyclerView.addOnScrollListener(scrollListener = new EndlessRecycler(layoutManager, header) {
            @Override
            public void onLoadMore(int current_page) {
                fireMoreCall();
            }
        });
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onResume();
        } else {
            onPause();
        }
    }

    @Override
    public void fireCall() {
        super.fireCall();
        presenter.getFavorite();
        presenter.getData(interactor, id, secId);
    }

    private void fireMoreCall() {
        if (isFilter)
            presenter.getMoreFilter(filterInteractor, filterBody, ++filterCurrent_page);
        else
            presenter.getMore(interactor, id, ++Current_page);

    }

    @Inject
    @Override
    public void attachPresenter(@NonNull ListContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(Products data) {
        super.populate();
        isFetched = true;
        baseData = data;
        mAdapter.setData(data.getProduct());
        filterData = data.getFilter();
        setNavigation(data, true);
    }

    private void setNavigation(Products data, boolean isHeaded) {
        count.setVisibility(View.VISIBLE);
        count.setText(String.format("%d %s", data.getCount(), getString(R.string.product)));
        if (hasFilter) {
            filter.setVisibility(View.VISIBLE);
            filter.setOnClickListener(view -> FiltersFragment.newInstance(convertToJsonString(filterData, Filter.class), choice, cats, brands).show(getChildFragmentManager(), "filter"));
        } else {
            filter.setVisibility(View.GONE);
        }
        if (isHeaded) {
            if (isSub) {
                main.setVisibility(View.VISIBLE);
                sub.setVisibility(View.VISIBLE);
                main.setText(String.format("%s %s ", data.getProduct().get(0).getMainCategory(), getString(R.string.toward)));
                sub.setText(title);
            } else {
                main.setVisibility(View.VISIBLE);
                main.setText(title);
            }
        }
    }

    @Override
    public void showErrorMessage(String s) {
    }

    @Override
    public void showEmptyMessage(String s) {

    }

    @Override
    public void setHasFilter(Products data) {
        super.populate();
        setNavigation(data, false);
        mAdapter.setData(data.getProduct());
        recyclerView.scheduleLayoutAnimation();
        filter.setClickable(true);
        filter.setOnClickListener(v -> FiltersFragment.newInstance(convertToJsonString(filterData, Filter.class), choice, cats, brands).show(getChildFragmentManager(), "filter"));
    }

    @Override
    public void setMoreFilter(Products data) {
        mAdapter.appendData(data.getProduct());
    }

    @Override
    public void setMore(Products data) {
        mAdapter.appendData(data.getProduct());
    }

    @Override
    public void setFav(List<Product> products) {
        if (products.isEmpty()) {
            Product emptyProduct = new Product();
            emptyProduct.setId(-1);
            products.add(emptyProduct);
            mAdapter.setFavorite(products);
        } else {
            mAdapter.setFavorite(products);
        }
    }

    @Override
    public void addFavorite(Product product) {
        presenter.addFavorite(product);
    }

    @Override
    public void removeFavorite(int id) {
        presenter.removeFavorite(id);
    }

    @Override
    public void onFiltersClicked(HashMap<String, Integer> mapBrand, HashMap<String, Integer> mapCategory, int minPrice, int maxPrice) {
        super.vanish();
        filterCurrent_page = 1;
        cats.clear();
        brands.clear();
        min = minPrice;
        max = maxPrice;
        for (Object key : mapCategory.keySet()) {
            int value = mapCategory.get(key);
            cats.add(value);
        }
        for (Object key : mapBrand.keySet()) {
            int value = mapBrand.get(key);
            brands.add(value);
        }
        isFilter = true;
        filterBody = new FilterRequest();
        filterBody.setMinPrice(min);
        filterBody.setMaxPrice(max);
        filterBody.setCats(cats);
        filterBody.setBrands(brands);
        presenter.getFilter(filterInteractor, filterBody);
        scrollListener.reset();
    }

    @Override
    public void onFilterClear() {
        isFilter = false;
        Current_page = 1;
        cats.clear();
        brands.clear();
        count.setVisibility(View.VISIBLE);
        count.setText(String.valueOf(baseData.getCount()));
        mAdapter.setData(baseData.getProduct());
        recyclerView.scheduleLayoutAnimation();
        filter.setClickable(true);
    }

    @Override
    public void onOptionClicked(int item, int position, int id, int parentId, String title) {
        switch (item) {
            case 0:
                if (mAdapter.getProduct(position).getQuantity() > 0)
                    presenter.addCart(mAdapter.getProduct(position));
                else
                    Toast.makeText(getContext(), getString(R.string.sold_out), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                share(id, mAdapter.getProduct(position));
                break;
            default:
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose(interactor, filterInteractor);
    }


    private void share(int id, Product product) {
        Uri myUri = ((MainActivity) getActivity()).createShareUri(id);
        Uri dynamicLinkUri = ((MainActivity) getActivity()).createDynamicUri(myUri, product);
        ((MainActivity) getActivity()).shortenLink(dynamicLinkUri);
    }
}
