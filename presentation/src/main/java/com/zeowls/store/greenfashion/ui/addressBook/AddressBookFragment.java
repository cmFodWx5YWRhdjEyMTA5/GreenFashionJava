package com.zeowls.store.greenfashion.ui.addressBook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.AddressList;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.addressBook.AddressAdapter;
import com.zeowls.store.greenfashion.ui.address.AddressFragment;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.submit.SubmitFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.SUBMIT;
import static com.zeowls.store.greenfashion.ui.utils.Utils.convertToJsonString;


public class AddressBookFragment extends BaseFragment implements AddressBookContract.View, AddressAdapter.AddressEvents {

    private static final String ARG_ID = "id";
    private static final String ARG_CHECKOUT = "checkout";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    private int id;
    private AddressBookContract.Presenter presenter;
    private AddressAdapter mAdapter;
    private boolean isCheckout;

    public AddressBookFragment() {
    }

    public static AddressBookFragment newInstance(int id, boolean isCheckout) {
        AddressBookFragment fragment = new AddressBookFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putBoolean(ARG_CHECKOUT, isCheckout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID, 0);
            isCheckout = getArguments().getBoolean(ARG_CHECKOUT, false);
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_address_book;
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
        super.vanish();
        toolbarTitle.setText(R.string.address_book);
        mAdapter = new AddressAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
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
        presenter.getData(id);
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull AddressBookContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void setData(AddressList data) {
        super.populate();
        mAdapter.setData(data.getAddresses());
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void onFavorite(Address address) {
        LoggedData.saveFavAddress(getActivity(), address, true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEdit(Address address) {
        if (isCheckout) {
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(SUBMIT.name());
            if (fragment != null) {
                getActivity().getSupportFragmentManager().popBackStack(SUBMIT.name(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, SubmitFragment.newInstance(convertToJsonString(address, Address.class)), SUBMIT.name()).addToBackStack(SUBMIT.name()).commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, AddressFragment.newInstance(convertToJsonString(address, Address.class), address.getId())).addToBackStack(null).commit();
        }
    }

    @OnClick(R.id.fab)
    public void AddAddress() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, AddressFragment.newInstance(null, null)).addToBackStack(null).commit();
    }
}
