package com.zeowls.store.greenfashion.ui.review;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zeowls.domain.entity.ProductReview;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.review.ReviewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ReviewFragment extends Fragment implements ReviewContract.View {

    private static final String ARG_ID = "id";
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.no_reviews)
    TextView no_product;
    @BindView(R.id.more_reviews)
    TextView moreReviews;
    @BindView(R.id.container)
    View container;
    @Nullable
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    private int id;
    private ReviewAdapter mAdapter;
    private ReviewContract.Presenter presenter;
    private Unbinder unbinder;

    public ReviewFragment() {
    }

    public static ReviewFragment newInstance(int id) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initializeDependencies();
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID, 0);
        }
    }

    public void bind(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        init();
    }

    protected int layoutId() {
        return R.layout.fragment_review;
    }

    protected void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    protected void init() {
        vanish();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ReviewAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        presenter.getData(id);
    }


    @Inject
    @Override
    public void attachPresenter(@NonNull ReviewContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(ProductReview data) {
        populate();
        if (data.getReviews() != null) {
            no_product.setVisibility(View.GONE);
            mAdapter.setData(data.getReviews().getReviews());
            moreReviews.setVisibility(View.VISIBLE);
        } else {
            no_product.setVisibility(View.VISIBLE);
            moreReviews.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage() {

    }

    protected void vanish() {
        progressBar.setVisibility(View.VISIBLE);
        container.setVisibility(View.INVISIBLE);
    }

    protected void populate() {
        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
