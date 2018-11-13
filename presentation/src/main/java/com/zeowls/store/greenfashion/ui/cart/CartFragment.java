package com.zeowls.store.greenfashion.ui.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.cart.CartAdapter;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookFragment;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.login.LoginFragment;
import com.zeowls.store.greenfashion.ui.submit.SubmitFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.SUBMIT;
import static com.zeowls.store.greenfashion.ui.utils.Utils.convertToJsonString;

@ApplicationScope
public class CartFragment extends BaseFragment implements CartContract.View, CartAdapter.onProductCase {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.price)
    TextView totalPrice;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.error)
    ViewGroup error;
    @BindView(R.id.footer)
    ViewGroup footer;
    private CartAdapter mAdapter;
    private CartContract.Presenter presenter;
    private User user;
    private boolean warn;

    @Inject
    public CartFragment() {
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = LoggedData.getUserObject(getActivity());
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_cart;
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
        toolbarTitle.setText(R.string.cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CartAdapter(getActivity(), this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        warn = false;
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
        presenter.reNewCart();
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull CartContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(List<Product> products) {
        super.populate();
        for (Product product : products) {
            if (product.getQuantity() < 1) {
                warn = true;
            }
        }
        mAdapter.setData(products);
        totalPrice.setText(String.format(getString(R.string.currency), mAdapter.orderPrice()));
        footer.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
        if (warn) {
            Toast.makeText(getActivity(), getString(R.string.sold_out_warn), Toast.LENGTH_SHORT).show();
            warn = false;
        }
    }

    @Override
    public void showErrorMessage() {
        super.populate();
        mAdapter.clearData();
        footer.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.checkout)
    public void onCheckOut() {
        if (user != null) {
            MakeOrder order = new MakeOrder();
            order.setItems(mAdapter.orderData());
            order.setUserId(String.valueOf(user.getId()));
            order.setOrderprice(mAdapter.orderPrice());
            LoggedData.saveUserCart(getActivity(), order, true);
            LoggedData.saveUserCartProducts(getActivity(), mAdapter.getProducts(), true);
            if (LoggedData.getFavAddress(getActivity()) != null)
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, SubmitFragment.newInstance(convertToJsonString(LoggedData.getFavAddress(getActivity()), Address.class)), SUBMIT.name()).addToBackStack(SUBMIT.name()).commit();
            else
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, AddressBookFragment.newInstance(user.getId(), true), CHILD.name()).addToBackStack(null).commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, LoginFragment.newInstance(), CHILD.name()).addToBackStack(null).commit();
        }

    }

    @Override
    public void getCart(Products data) {
        presenter.getData();
    }

    @Override
    public void move(Product product) {
        presenter.doMove(product);
    }

    @Override
    public void edit(int id, int count) {
        presenter.doEdit(id, count);
    }

    @Override
    public void remove(int id) {
        presenter.doRemove(id);
    }

    @Override
    public void rePricing() {
        totalPrice.setText(String.valueOf(mAdapter.orderPrice()));
    }

    @Override
    public void showError(String max_quantity) {
        Toast.makeText(getContext(), max_quantity, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
