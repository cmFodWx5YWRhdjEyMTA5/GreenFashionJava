package com.zeowls.store.greenfashion.ui.categories;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.interactor.MainCatProducts_interactor;
import com.zeowls.domain.interactor.SubCatProducts_interactor;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.categories.CategoriesAdapter;
import com.zeowls.store.greenfashion.ui.adapter.categories.HeaderAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.list.optionList;
import com.zeowls.store.greenfashion.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoriesFragment extends BaseFragment implements CategoriesContract.View, HeaderAdapter.onUseCase, optionList.Listener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    private CategoriesContract.Presenter presenter;
    private CategoriesAdapter mAdapter;
    private static String ID = "id";
    private static String TITLE = "title";
    private int id;
    private String title;
    @Inject
    MainCatProducts_interactor mainCatProducts;
    @Inject
    SubCatProducts_interactor subCatProducts;

    public CategoriesFragment() {
    }

    public static CategoriesFragment newInstance(int id, String title) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ID, -1);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_categories;
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
        toolbarTitle.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CategoriesAdapter(getActivity(), this, mainCatProducts, subCatProducts);
        mAdapter.setFragmentManager(getChildFragmentManager());
        recyclerView.setAdapter(mAdapter);
        back.setOnClickListener(view -> getActivity().onBackPressed());
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
        presenter.getFav();
        presenter.getData(id);
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull CategoriesContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(Categories data) {
        super.populate();
        isFetched = true;
        mAdapter.setHeader(data.getProduct());
        mAdapter.setData(data.getSubCat());
    }

    @Override
    public void showErrorMessage(String s) {

    }

    @Override
    public void showEmptyMessage(String s) {

    }

    @Override
    public void setFav(List<Product> products) {
        mAdapter.setFav(products);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void insertFavorite(Product product) {
        presenter.addFavorite(product);

    }

    @Override
    public void deleteFavorite(int id) {
        presenter.removeFavorite(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.dispose();
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
                break;
            case 2:
                share(id, mAdapter.getProduct(position));
                break;

            default:
        }
    }

    private void share(int id, Product product) {
        Uri myUri = ((MainActivity) getActivity()).createShareUri(id);
        Uri dynamicLinkUri = ((MainActivity) getActivity()).createDynamicUri(myUri, product);
        ((MainActivity) getActivity()).shortenLink(dynamicLinkUri);
    }
}
