package com.zeowls.store.greenfashion.ui.detail;

import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zeowls.domain.entity.Color;
import com.zeowls.domain.entity.Image;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.entity.Related;
import com.zeowls.domain.interactor.SubCatProducts_interactor;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.details.ColorAdapter;
import com.zeowls.store.greenfashion.ui.adapter.details.PagerVeiwAdapter;
import com.zeowls.store.greenfashion.ui.adapter.details.SimilarityAdapter;
import com.zeowls.store.greenfashion.ui.adapter.details.WrappingFragmentPagerAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.view.ClickableViewPager;
import com.zeowls.store.greenfashion.ui.view.EdgeDecorator;
import com.zeowls.store.greenfashion.ui.view.WrappingViewPager;
import com.zeowls.store.greenfashion.ui.view.ZoomOutPageTransformer;
import com.zeowls.store.greenfashion.ui.view.pageIndicator.PageIndicatorView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;
import static com.zeowls.store.greenfashion.ui.utils.Utils.convertToJsonString;

public class DetailsFragment extends BaseFragment implements DetailsContract.View, ColorAdapter.colorPicker {
    @BindView(R.id.container)
    ConstraintLayout constraintLayout;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.favorite)
    ImageView favorite;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.new_product)
    ImageView new_product;
    @BindView(R.id.sale)
    TextView sale;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.ex_price)
    TextView ex_price;
    @BindView(R.id.colors_list)
    RecyclerView colorRecyclerView;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.similar_items)
    TextView similar_items;
    @BindView(R.id.list_similar)
    RecyclerView similarRecyclerView;
    @BindView(R.id.pager)
    ClickableViewPager viewPager;
    @BindView(R.id.indicator)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.tabs_pager)
    WrappingViewPager tabsPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.cart)
    Button cart;
    @BindView(R.id.rating)
    RatingBar rate;
    @BindView(R.id.color)
    TextView color;
    private List<Image> collection;

    @Inject
    SubCatProducts_interactor similarProducts;

    private DetailsContract.Presenter presenter;
    private static final String ARG_ID = "id";
    private int id;
    private ArrayList<String> images, arrayList;
    private PagerVeiwAdapter adapter;
    private ColorAdapter colorAdapter;
    private SimilarityAdapter similarityAdapter;
    private DecimalFormat formatter;
    private WrappingFragmentPagerAdapter tabsAdapter;
    private Product product;


    @Inject
    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(int id) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID, 0);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_details;
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
        back.setOnClickListener(view -> getActivity().onBackPressed());

        images = new ArrayList<>();
        arrayList = new ArrayList<>();

        adapter = new PagerVeiwAdapter(getContext());
        viewPager.setAdapter(adapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setDynamicCount(true);

        colorAdapter = new ColorAdapter(getContext(), this);
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        colorRecyclerView.setAdapter(colorAdapter);

        similarityAdapter = new SimilarityAdapter(getContext());
        similarRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        similarRecyclerView.setAdapter(similarityAdapter);
        similarRecyclerView.addItemDecoration(new EdgeDecorator(16, false));
        formatter = new DecimalFormat("#,###,###");
    }

    @Override
    public void fireCall() {
        super.fireCall();
        presenter.getData(id);
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


    @Inject
    @Override
    public void attachPresenter(@NonNull DetailsContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(ProductDetails data) {
        super.populate();
        isFetched = true;
        product = data.getProduct();
        presenter.getFavorite(id);
        title.setText(product.getName());
        arrayList.add(data.getProduct().getMainImage());
        for (Image image1 : data.getProduct().getImages()) {
            arrayList.add(image1.getImageUrl());
        }
        collection = data.getProduct().getImages();
        adapter.setData(arrayList);
        tabsAdapter = new WrappingFragmentPagerAdapter(getActivity(), getChildFragmentManager(), data);
        tabsPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(tabsPager);
        tabsPager.setPageTransformer(true, new ZoomOutPageTransformer());
        configuration(product);
        setSimilarity(data.getRelated());
        viewPager.setOnItemClickListener(position -> {
            GalleryFragment mDialog = GalleryFragment.newInstance(position, convertToJsonString(arrayList, new TypeToken<List<String>>() {
            }.getType()));
            mDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.GalleryTheme);
            mDialog.show(getActivity().getSupportFragmentManager(), "DialogFragment");
        });

        favorite.setOnClickListener(view -> {
            if (product.isFavorite()) {
                presenter.doRemoveFavorite(product.getId());
            } else {
                presenter.addFavorite(product);
            }
        });
    }

    private void configuration(Product product) {
        if (product.isNew()) {
            new_product.setVisibility(View.VISIBLE);
        } else {
            new_product.setVisibility(View.GONE);
        }

        if (product.getQuantity() > 0) {
            if (product.isOnSale() && product.getReductionPercent() != null) {
                sale.setVisibility(View.VISIBLE);
                ex_price.setVisibility(View.VISIBLE);
                sale.setText(String.format("%d %s", product.getReductionPercent().intValue(), getString(R.string.off)));
                price.setText(String.format(getString(R.string.currency), product.getPrice()));
                ex_price.setText(String.valueOf(product.getWholesalePrice()));
                ex_price.setPaintFlags(ex_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            } else {
                sale.setVisibility(View.GONE);
                ex_price.setVisibility(View.GONE);
                price.setText(String.format(getString(R.string.currency), product.getPrice()));
            }
        }else {
            sale.setVisibility(View.GONE);
            ex_price.setVisibility(View.GONE);
            price.setText(getString(R.string.sold_out));
        }

        if (product.getRate() != null)
            this.rate.setRating(product.getRate());
        else
            this.rate.setRating(0.0f);

        setColor(product.getColors());
        cart.setOnClickListener(view -> {
            if (product.getQuantity() > 0)
                presenter.addCart(product);
            else
                Toast.makeText(getContext(), getString(R.string.sold_out), Toast.LENGTH_SHORT).show();
        });
    }

    private void setColor(List<Color> list) {
        if (!list.isEmpty()) {
            color.setVisibility(View.VISIBLE);
            colorRecyclerView.setVisibility(View.VISIBLE);
            colorAdapter.setData(list);
        } else {
            color.setVisibility(View.GONE);
            colorRecyclerView.setVisibility(View.GONE);
        }
    }

    private void setSimilarity(List<Related> list) {
        if (!list.isEmpty()) {
            more.setVisibility(View.VISIBLE);
            similar_items.setVisibility(View.VISIBLE);
            similarityAdapter.setData(list);
            more.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(similarProducts, list.get(0).getIdCategory(), true, true, list.get(0).getCategory(), CAT.name(), -1), CHILD.name()).addToBackStack(null).commit());
        } else {
            more.setVisibility(View.GONE);
            similar_items.setVisibility(View.GONE);
        }
    }


    @Override
    public void showErrorMessage() {

    }

    @Override
    public void setFav(List<Integer> products) {
        if (products.isEmpty()) {
            product.setFavorite(false);
            favorite.setImageResource(R.drawable.ic_favorite_details);
        } else {
            product.setFavorite(true);
            favorite.setImageResource(R.drawable.ic_favorite_filled_details);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }


    @Override
    public void oPickColor(int color) {
        if (color == -1) {
            images.clear();
            images.addAll(arrayList);

        } else {
            images.clear();
            for (Image image : collection) {
                if (color == image.getIdColor())
                    images.add(image.getImageUrl());
            }
        }
        adapter.setData(images);
    }

    @OnClick(R.id.share)
    public void share() {
        Uri myUri = ((MainActivity) getActivity()).createShareUri(id);
        Uri dynamicLinkUri = ((MainActivity) getActivity()).createDynamicUri(myUri, product);
        ((MainActivity) getActivity()).shortenLink(dynamicLinkUri);
    }
}
