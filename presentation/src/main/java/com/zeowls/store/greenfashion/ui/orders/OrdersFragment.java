package com.zeowls.store.greenfashion.ui.orders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zeowls.domain.entity.Orders;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.orders.OrdersAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class OrdersFragment extends BaseFragment implements OrdersContract.View, OrdersAdapter.OrdersEvents {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    private OrdersContract.Presenter presenter;
    private OrdersAdapter mAdapter;


    public OrdersFragment() {
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_orders;
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
    public void attachPresenter(@NonNull OrdersContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    protected void init() {
        toolbarTitle.setText(R.string.orders);
        mAdapter = new OrdersAdapter(getContext(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        presenter.getData(LoggedData.getUserObject(getActivity()).getId());
    }


    @Override
    public void setData(Orders data) {
        super.populate();
        mAdapter.setData(data.getOrders());
    }

    @Override
    public void showErrorMessage(String s) {

    }

    @Override
    public void openDetails(Integer id) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, OrdersDetailsFragment.newInstance(id), CHILD.name()).addToBackStack(null).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
