package com.zeowls.store.greenfashion.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.entity.Search;
import com.zeowls.domain.interactor.BrandProducts_interactor;
import com.zeowls.domain.interactor.MainCatProducts_interactor;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.search.MainCatsAdapter;
import com.zeowls.store.greenfashion.ui.adapter.search.SearchAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.list.ListFragment;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.view.ClearFocusEditText;
import com.zeowls.store.greenfashion.ui.view.EndlessRecycler;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

@ApplicationScope
public class SearchFragment extends BaseFragment implements SearchContract.View, SearchAdapter.AdapterListener {

    @BindView(R.id.search_container)
    ConstraintLayout constraintLayout;
    @BindView(R.id.search_box)
    ConstraintLayout searchBox;
    @BindView(R.id.search)
    ClearFocusEditText etSearch;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_empty)
    ViewGroup searchEmpty;
    @BindView(R.id.recent)
    TextView recent;
    @BindView(R.id.delete)
    TextView delete;
    private ConstraintSet setCollapse, setExpand, constraint;
    private SearchContract.Presenter presenter;
    private MainCatsAdapter catsAdapter;
    private SearchAdapter searchAdapter;
    private boolean isRecent;
    private CompositeDisposable disposable;
    private LinearLayoutManager layoutManager;

    @Inject
    public SearchFragment() {
    }

    @Inject
    MainCatProducts_interactor mainCatProducts;

    @Inject
    BrandProducts_interactor brandProducts;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search;
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
    public void attachPresenter(@NonNull SearchContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void init() {
        disposable = new CompositeDisposable();
        catsAdapter = new MainCatsAdapter(getActivity());
        searchAdapter = new SearchAdapter(getActivity(), this);
        recent.setText(R.string.recent_search);
        recent.setVisibility(View.VISIBLE);
        delete.setVisibility(View.GONE);
        delete.setOnClickListener(this::deleteAll);
        etSearch.setText(R.string.search);
        isRecent = false;
        mRecyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(catsAdapter);

        mRecyclerView.addOnScrollListener(new EndlessRecycler(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                fireMoreCall(current_page);
            }
        });

        mRecyclerView.setOnTouchListener((view, motionEvent) -> {
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });

        initSearch();

        back.setOnClickListener(view -> {
            etSearch.clearFocus();
            ((MainActivity) getActivity()).hideKeyboard();
        });
        clear.setOnClickListener(view -> etSearch.setText(""));
    }

    private void deleteAll(View view) {
        presenter.deleteAllRecent();
    }

    private void initSearch() {
        setCollapse = new ConstraintSet();
        setCollapse.clone(constraintLayout);
        setExpand = new ConstraintSet();
        setExpand.clone(constraintLayout);
        setExpand.connect(searchBox.getId(), ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        setExpand.connect(searchBox.getId(), ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        setExpand.connect(searchBox.getId(), ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        setExpand.connect(searchBox.getId(), ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

        disposable.add(RxView.focusChanges(etSearch)
                .subscribe(isFocused -> {
                    if (isFocused) {
                        mRecyclerView.setAdapter(searchAdapter);
                        searchAdapter.notifyDataSetChanged();
                        isRecent = true;
                        presenter.getRecent();
                        searchBoxExpand();
                    } else {
                        searchBoxCollapse();
                        isRecent = false;
                        recent.setVisibility(View.GONE);
                        delete.setVisibility(View.GONE);
                        searchEmpty.setVisibility(View.GONE);
                        mRecyclerView.setAdapter(catsAdapter);
                        catsAdapter.notifyDataSetChanged();
                    }
                }));


        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSearch.getText().length() > 0 && etSearch.hasFocus()) {
                    clear.setVisibility(View.VISIBLE);
                    isRecent = false;
                    presenter.getSuggestion();
                } else {
                    clear.setVisibility(View.GONE);
                    isRecent = false;
                    if (mRecyclerView.getAdapter() == searchAdapter) {
                        isRecent = true;
                        presenter.getRecent();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
    public void setCats(MainCats data) {
        super.populate();
        catsAdapter.setData(data.getCategory());

    }

    @Override
    public void showErrorMessage(String s) {

    }


    @Override
    public void appendMore(MainCats data) {
        catsAdapter.appendData(data.getCategory());
    }

    @Override
    public void setRecent(List<Search> list) {
        if (isRecent) {
            recent.setVisibility(View.VISIBLE);
            recent.setText(R.string.recent_search);
            delete.setVisibility(View.VISIBLE);
            searchAdapter.isRecent(true);
            searchEmpty.setVisibility(View.GONE);
            searchAdapter.setData(list);
        }
    }

    @Override
    public void setSuggestion(List<Search> list) {
        searchEmpty.setVisibility(View.GONE);
        recent.setVisibility(View.VISIBLE);
        recent.setText(R.string.suggested_searches);
        delete.setVisibility(View.GONE);
        searchAdapter.isRecent(isRecent);
        searchAdapter.setSuggestion(list);
        searchAdapter.getFilter().filter(etSearch.getText().toString());
    }

    @Override
    public void showEmptyMessage() {
        searchEmpty.setVisibility(View.VISIBLE);
    }


    private void fireMoreCall(int pageCount) {
        presenter.getMore(pageCount);
    }

    private void searchBoxExpand() {
        constraint = setExpand;
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                etSearch.setGravity(Gravity.CENTER_VERTICAL);
                etSearch.setText("");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                expand();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.go(new Scene(constraintLayout), transition);
        constraint.applyTo(constraintLayout);
    }

    private void searchBoxCollapse() {
        constraint = setCollapse;
        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                collapse();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.go(new Scene(constraintLayout), transition);
        constraint.applyTo(constraintLayout);
    }

    private void expand() {
        back.setVisibility(View.VISIBLE);
        searchBox.setBackground(getResources().getDrawable(R.drawable.search_full));
    }

    private void collapse() {
        if (clear != null) {
            clear.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            searchBox.setBackground(getResources().getDrawable(R.drawable.search_short));
            etSearch.setGravity(Gravity.CENTER);
            etSearch.setText(R.string.search);
        }
    }

    @Override
    public void onSelected(Search search) {
        etSearch.setText(search.getName());
        ((MainActivity) getActivity()).hideKeyboard();
        presenter.addRecent(search.getName());
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(mainCatProducts, search.getId(), true, true, search.getName(), CAT.name(), -1), CHILD.name()).addToBackStack(null).commit();
    }

    @Override
    public void onDelete(int id) {
        presenter.deleteRecent(id);
    }


    @Override
    public void onDestroyView() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        presenter.dispose();
        super.onDestroyView();

    }
}
