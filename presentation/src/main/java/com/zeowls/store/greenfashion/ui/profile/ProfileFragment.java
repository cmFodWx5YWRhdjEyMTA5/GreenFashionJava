package com.zeowls.store.greenfashion.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zeowls.data.LocaleManager;
import com.zeowls.domain.entity.Profile;
import com.zeowls.domain.entity.User;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerLoginComponent;
import com.zeowls.store.greenfashion.di.module.LoginModule;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.adapter.profile.ProfileAdapter;
import com.zeowls.store.greenfashion.ui.addressBook.AddressBookFragment;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.edit.EditFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.installment.InstallmentFragment;
import com.zeowls.store.greenfashion.ui.login.LoginFragment;
import com.zeowls.store.greenfashion.ui.orders.OrdersFragment;
import com.zeowls.store.greenfashion.ui.policy.PolicyFragment;
import com.zeowls.store.greenfashion.ui.splash.SplashActivity;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;
import com.zeowls.store.greenfashion.ui.view.ViewDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;

public class ProfileFragment extends BaseFragment implements ProfileAdapter.onProfileActive, ProfileAdapter.onProfile, ViewDialog.Action {

    private final String APP_PNAME = "com.zeowls.store.greenfashion";
    private User user;

    @Inject
    GoogleApiClient mGoogleApiClient;
    @Inject
    HomeFragment homeFragment;

    @Inject
    public ProfileFragment() {
    }

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.profile_image)
    ImageView imageView;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.username)
    TextView username;
    @Nullable
    @BindView(R.id.email)
    TextView email;
    ProfileAdapter mAdapter;

    @Override
    protected int layoutId() {
        if (LoggedData.getUserObject(getActivity()) == null)
            return R.layout.fragment_profile_guest;
        else
            return R.layout.fragment_profile_active;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initializeDependencies() {
        DaggerLoginComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .loginModule(new LoginModule(this::onConnectionFailed, getActivity(), 0))
                .build().inject(this);
    }

    private void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void init() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
        }
        mGoogleApiClient.connect();
        user = LoggedData.getUserObject(getActivity());
        imageView.getBackground().setLevel(5000);
        recyclerView.setHasFixedSize(true);
        if (user == null)
            guest();
        else
            logged(user);
    }

    private void guest() {
        toolbarTitle.setText(R.string.settings);
        String[] title = getResources().getStringArray(R.array.guest_title);
        int[] images = {R.drawable.ic_rate_us, R.drawable.share, R.drawable.ic_policy};
        List<Profile> profileList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            Profile profile = new Profile();
            profile.setImage(images[i]);
            profile.setTitle(title[i]);
            profileList.add(profile);
        }
        GlideApp.with(getActivity()).load(R.drawable.ic_profile_pic).apply(RequestOptions.circleCropTransform()).into(imageView);
        mAdapter = new ProfileAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(profileList);
    }

    @OnClick(R.id.language)
    public void onLanguage(View view) {
        new ViewDialog().showLanguageDialog(getActivity(), getString(R.string.change_language_content), this);
    }

    private void logged(User user) {
        toolbarTitle.setText(R.string.profile);
        username.setText(LoggedData.getUserObject(getActivity()).getUsername());
        email.setText(LoggedData.getUserObject(getActivity()).getEmail());

        int[] images = {R.drawable.ic_my_order, R.drawable.ic_edit, R.drawable.ic_address_book, R.drawable.ic_rate_us, R.drawable.share, R.drawable.ic_policy, R.drawable.ic_installment, R.drawable.ic_logout};
        String[] title = getResources().getStringArray(R.array.profile_title);
        List<Profile> profileList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            Profile profile = new Profile();
            profile.setImage(images[i]);
            profile.setTitle(title[i]);
            profileList.add(profile);
        }
        Log.d("HERE", user.getProfilePic());
        GlideApp.with(Objects.requireNonNull(getActivity())).load(user.getProfilePic()).placeholder(R.drawable.ic_profile_pic).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).into(imageView);
        mAdapter = new ProfileAdapter(getActivity(), this, true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(profileList);
    }

    @Optional
    @OnClick(R.id.login)
    public void onLogin() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, LoginFragment.newInstance()).addToBackStack(null).commit();
    }


    @Override
    public void onOrders() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, OrdersFragment.newInstance(), CHILD.name()).addToBackStack(null).commit();
    }

    @Override
    public void onEdit() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, EditFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void onAddressBook() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, AddressBookFragment.newInstance(user.getId(), false), CHILD.name()).addToBackStack(null).commit();
    }

    @Override
    public void onRateUs() {
        getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
    }

    @Override
    public void onReview() {

    }

    @Override
    public void onShare() {
        share();
    }

    @Override
    public void onReturnPolicy() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, PolicyFragment.newInstance()).addToBackStack(null).commit();

    }

    @Override
    public void onInstallment() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, InstallmentFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void onLogout() {
        SignOut();
    }

    private void SignOut() {
        LoggedData.clearUser(getActivity());
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
        disconnectFromFacebook();
        redirect();
    }

    public void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE,
                graphResponse -> LoginManager.getInstance().logOut()).executeAsync();
    }

    private void redirect() {
        if (!getActivity().getSupportFragmentManager().popBackStackImmediate(HOME.name(), 0)) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME.name()).addToBackStack(HOME.name()).commit();
        }
    }


    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey, check out Hyper Techno at: https://play.google.com/store/apps/details?id=com.zeowls.store.greenfashion");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onDestroyView() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
            mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
        super.onDestroyView();
    }

    @Override
    public void onConfirm() {
        if (LocaleManager.getLanguage(getActivity()).equals(LocaleManager.LANGUAGE_ENGLISH))
            LocaleManager.setNewLocale(getContext(), LocaleManager.LANGUAGE_ARABIC);
        else
            LocaleManager.setNewLocale(getContext(), LocaleManager.LANGUAGE_ENGLISH);
        getActivity().overridePendingTransition(0, 0);
        ActivityCompat.finishAffinity(getActivity());
        getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
    }
}

