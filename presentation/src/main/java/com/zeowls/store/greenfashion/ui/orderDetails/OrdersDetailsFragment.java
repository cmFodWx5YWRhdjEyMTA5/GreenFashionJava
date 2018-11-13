package com.zeowls.store.greenfashion.ui.orderDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Cart;
import com.zeowls.domain.entity.Order;
import com.zeowls.domain.entity.OrderDetails;
import com.zeowls.domain.entity.Response;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.orderDetails.OrderDetailsAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.view.ViewDialog;
import com.zeowls.store.greenfashion.ui.view.stepview.StepView;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;

public class OrdersDetailsFragment extends BaseFragment implements OrdersDetailsContract.View, OrderDetailsAdapter.orderEvents {

    private static String ARG_ID = "id";

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.action)
    TextView action;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.deliverd_date)
    TextView deliveryDate;
    @BindView(R.id.shipment_no)
    TextView shipment_no;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.street)
    TextView street;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.shipping_fee)
    TextView shippingFee;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.back)
    ImageView back;
    private OrdersDetailsContract.Presenter presenter;
    private OrderDetailsAdapter mAdapter;
    private int id;
    private SimpleDateFormat dateFormatter;


    public OrdersDetailsFragment() {
    }

    public static OrdersDetailsFragment newInstance(int id) {
        OrdersDetailsFragment fragment = new OrdersDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID, -1);
        }
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_order_details;
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
    public void attachPresenter(@NonNull OrdersDetailsContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    protected void init() {
        super.vanish();
        toolbarTitle.setText(R.string.order_details);
        back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        action.setVisibility(View.GONE);
        mAdapter = new OrderDetailsAdapter(getActivity(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
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
        presenter.getData(id);
    }


    @Override
    public void setData(OrderDetails data) {
        super.populate();
        isFetched = true;
        configurationSet(data);
        userDetails(data.getOrders());
        paymentDetails(data.getOrders());
        if (data.getOrders().getCurrentState().getId() > 0) {
            stepView.done(true);
            stepView.go(data.getOrders().getCurrentState().getId() - 1, true);
        } else {
            stepView.done(false);
        }
        mAdapter.setData(data.getOrders().getCart());
    }

    private void paymentDetails(Order orders) {
        int price = 0;
        int fee = 0;
        int totalValue;
        for (Cart product : orders.getCart()) {
            if (product.getProductOnSale() != null && product.getProductOnSale()) {
                price += product.getProductReductionPrice();
            } else {
                price += product.getProductWholesalePrice();
            }
        }
        orderPrice.setText(String.format(getString(R.string.order_price_tag), price, getString(R.string.LE)));
        if (orders.getShippingFee() != null) {
            fee = orders.getShippingFee();
            shippingFee.setText(String.format(getString(R.string.shipping_fee_tag), orders.getShippingFee()));
        } else {
            shippingFee.setText(String.format("%s %s", getString(R.string.shipping_fee), getString(R.string.not_specified)));
        }
        totalValue = price + fee;
        total.setText(String.format(getString(R.string.total_tag), totalValue, getString(R.string.LE)));
    }

    private void userDetails(Order orders) {
        name.setText(orders.getAddress().getUsername());
        street.setText(orders.getAddress().getStreetName());
        phone.setText(orders.getAddress().getPhone().getMobile());
    }

    private void configurationSet(OrderDetails data) {
        Order orders = data.getOrders();

        if (data.getOrders().getCurrentState().getId() > 0 && data.getOrders().getCurrentState().getId() < 3) {
            action.setVisibility(View.VISIBLE);
            action.setText(R.string.cancel);
            action.setOnClickListener(this::onCancel);
        } else {
            action.setVisibility(View.GONE);
        }

        if (data.getOrders().getCurrentState().getId() == 4) {
            mAdapter.setIsDelivered(true);
        } else {
            mAdapter.setIsDelivered(false);
        }

        shipment_no.setText(String.format("%s %d", getString(R.string.shipping_no), orders.getId()));
        date.setText(dateFormatter.format(orders.getDateAdd()));
        if (orders.getShippingDate() != null) {
            deliveryDate.setText(dateFormatter.format(orders.getShippingDate()));
        } else {
            deliveryDate.setText(R.string.not_specified);
        }
    }

    private void onCancel(View view) {
        ViewDialog alert = new ViewDialog();
        alert.showConfirmationDialog(getActivity(), id, presenter);
    }

    @Override
    public void showErrorMessage(String s) {

    }

    @Override
    public void cancelData(Response data) {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onRate(int id) {
        ViewDialog alert = new ViewDialog();
        alert.showRateDialog(getActivity(), id, presenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
