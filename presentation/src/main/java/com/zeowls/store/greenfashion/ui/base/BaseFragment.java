package com.zeowls.store.greenfashion.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zeowls.store.greenfashion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;
    public boolean isFetched;
    public FragmentEvents fragmentEvents;
    @BindView(R.id.container)
    View container;
    @Nullable
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @Nullable
    @BindView(R.id.connection)
    View connection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencies();
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

    public void fireCall() {
        if (progressBar != null && connection != null) {
            progressBar.setVisibility(View.VISIBLE);
            connection.setVisibility(View.GONE);
        }
    }

    public void fireConnectionError() {
        if (progressBar != null || connection != null) {
            progressBar.setVisibility(View.GONE);
            connection.setVisibility(View.VISIBLE);
        }
    }

    protected void vanish() {
        if (progressBar !=null && container !=null) {
            progressBar.setVisibility(View.VISIBLE);
            container.setVisibility(View.INVISIBLE);
        }
    }

    protected void populate() {
        fragmentEvents.onDispose();
        if (progressBar !=null && container !=null) {
            progressBar.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
        }
    }


    @LayoutRes
    protected abstract int layoutId();

    protected abstract void initializeDependencies();

    protected abstract void init();


    public interface FragmentEvents {
        void onCheckConnection();

        void onDispose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentEvents = (FragmentEvents) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentEvents");
        }
    }

    public void fetchData() {
        fragmentEvents.onCheckConnection();
    }

    public void disposeFetch() {
        fragmentEvents.onDispose();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}