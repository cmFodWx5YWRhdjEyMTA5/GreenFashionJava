package com.zeowls.store.greenfashion.ui.submit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.entity.User;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.submitAdapter.SubmitAdapter;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookFragment;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.SUBMIT;
import static com.zeowls.store.greenfashion.ui.utils.Utils.convertFromJsonString;

public class SubmitFragment extends BaseFragment implements SubmitContract.View {
    private static String ARG_ADDRESS = "address_data";
    @Inject
    HomeFragment homeFragment;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.street)
    TextView street;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.back)
    ImageView back;
    private Address address;
    int addressId;
    private MakeOrder makeOrder;

    private SubmitContract.Presenter presenter;
    private User user;

    public SubmitFragment() {
    }

    public static SubmitFragment newInstance(String address) {
        SubmitFragment fragment = new SubmitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ADDRESS, address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = convertFromJsonString(getArguments().getString(ARG_ADDRESS), Address.class);
        }
        user = LoggedData.getUserObject(getActivity());
        makeOrder = LoggedData.getUserCart(getActivity());
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_submit;
    }

    @Override
    protected void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    @Override
    protected void init() {
        super.populate();
        toolbarTitle.setText(R.string.order_summary);
        back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        addressId = address.getId();
        edit.setOnClickListener(view -> getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, AddressBookFragment.newInstance(user.getId(), true), SUBMIT.name()).addToBackStack(null).commit());
        configuration(address);
        SubmitAdapter adapter = new SubmitAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setData(LoggedData.getUserCartProducts(getActivity()).getProduct());
    }

    private void configuration(Address address) {
        name.setText(address.getUsername());
        street.setText(address.getStreetName());
        phone.setText(address.getPhone().getMobile());
        orderPrice.setText(String.format(getString(R.string.order_price_tag), makeOrder.getOrderprice(), getString(R.string.LE)));
        total.setText(String.format(getString(R.string.total_tag), makeOrder.getOrderprice(), getString(R.string.LE)));
    }

    private void configuration(AddAddress address) {
        name.setText(address.getUsername());
        street.setText(address.getStreetName());
        phone.setText(address.getMobile());
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull SubmitContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(Response data) {
        if (data.getResponse() > 0) {
            presenter.clearCart();
            redirect(data.getResponse());
        }
    }

    @Override
    public void showErrorMessage() {
    }

    @Override
    public void setCart(List<Product> products) {


    }

    private void redirect(int id) {
        getActivity().getSupportFragmentManager().popBackStack(HOME.name(), 0);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, OrdersDetailsFragment.newInstance(id)).addToBackStack(null).commit();
    }

    @OnClick(R.id.submit)
    public void onSubmit() {
        makeOrder.setAddressId(String.valueOf(addressId));
        presenter.makeOrder(makeOrder);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }
}
