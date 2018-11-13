package com.zeowls.store.greenfashion.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.zeowls.domain.entity.Product;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.base.BaseActivity;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.cart.CartFragment;
import com.zeowls.store.greenfashion.ui.detail.DetailsFragment;
import com.zeowls.store.greenfashion.ui.favorite.FavoriteFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.profile.ProfileFragment;
import com.zeowls.store.greenfashion.ui.search.SearchFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CART;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.FAVORITE;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.PROFILE;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.SEARCH;

public class MainActivity extends BaseActivity implements BaseFragment.FragmentEvents, MainContract.View {
    private FragmentManager fragmentManager;
    private String QUERY_PARAM_ID = "ProductId";
    private String DYNAMIC_LINK_DOMAIN = "vs99h.app.goo.gl";

    @Inject
    HomeFragment homeFragment;
    @Inject
    FavoriteFragment favoriteFragment;
    @Inject
    SearchFragment searchFragment;
    @Inject
    ProfileFragment profileFragment;
    @Inject
    CartFragment cartFragment;
    @BindView(R.id.navigation)
    public BottomNavigationView navigation;
    BaseFragment baseFragment;
    private ArrayList<FragmentList> fragments;
    TextView textCartItemCount;
    private MainContract.Presenter presenter;
    private boolean isDeepLinking;


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
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
        fragments = new ArrayList<>();
        initFragment();
        fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        BottomNavigationViewHelper.disableShiftMode(navigation);
        fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment(), HOME.name()).addToBackStack(HOME.name()).commit();
        fragmentManager.addOnBackStackChangedListener(this::reOrder);

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) navigation.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(4);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.custom_action_item_layout, bottomNavigationMenuView, false);
        itemView.addView(badge);
        textCartItemCount = badge.findViewById(R.id.cart_badge);
        setupBadge(0);
        presenter.cartCounter();

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        isDeepLinking = true;
                        deepLink = pendingDynamicLinkData.getLink();
                        deepLink.getQueryParameter(QUERY_PARAM_ID);
                        getSupportFragmentManager().beginTransaction().add(R.id.container, DetailsFragment.newInstance(Integer.valueOf(deepLink.getQueryParameter(QUERY_PARAM_ID))), CHILD.name()).addToBackStack(null).commit();
                    }

                })
                .addOnFailureListener(this, e -> {
                });

    }

    @Override
    protected void triggerPresenter() {
        baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        baseFragment.fireCall();
    }

    @Override
    protected void connectionError() {
        baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        baseFragment.fireConnectionError();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchFragment(0);
                return true;
            case R.id.navigation_favorite:
                switchFragment(1);
                return true;
            case R.id.navigation_search:
                switchFragment(2);
                return true;
            case R.id.navigation_profile:
                switchFragment(3);
                return true;
            case R.id.navigation_cart:
                switchFragment(4);
                return true;
        }
        return false;
    };


    private void reOrder() {
        if (fragmentManager.findFragmentById(R.id.container) != null && fragmentManager.findFragmentById(R.id.container).getTag() != null) {
            navigationState whichView = navigationState.valueOf(fragmentManager.findFragmentById(R.id.container).getTag());
            switch (whichView) {
                case HOME:
                    if (isDeepLinking)
                        fragmentManager.findFragmentById(R.id.container).onResume();
                    isDeepLinking = false;
                    navigation.getMenu().getItem(0).setChecked(true);
                    break;
                case FAVORITE:
                    navigation.getMenu().getItem(1).setChecked(true);
                    fragmentManager.findFragmentById(R.id.container).onResume();
                    break;
                case SEARCH:
                    navigation.getMenu().getItem(2).setChecked(true);
                    fragmentManager.findFragmentById(R.id.container).onResume();
                    break;
                case PROFILE:
                    navigation.getMenu().getItem(3).setChecked(true);
                    break;
                case CART:
                    navigation.getMenu().getItem(4).setChecked(true);
                    fragmentManager.findFragmentById(R.id.container).onResume();
                    break;
                case CHILD:
                    fragmentManager.findFragmentById(R.id.container).onResume();
                default:
                    break;

            }
        }
    }

    private void initialFragment() {
        String tag = fragments.get(0).getTag();
        fragmentManager.beginTransaction().add(R.id.container, fragments.get(0).getFragment(), tag).commit();
    }

    private void switchFragment(int index) {
        hideKeyboard();
        String tag = fragments.get(index).getTag();
        Fragment fragment = fragments.get(index).getFragment();
        if (fragmentManager.findFragmentById(R.id.container) != fragment) {
            boolean isPopped = fragmentManager.popBackStackImmediate(tag, 0);
            if (!isPopped && fragmentManager.findFragmentByTag(tag) == null) {
                fragmentManager.beginTransaction().add(R.id.container, fragment, tag).addToBackStack(tag).commit();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onCheckConnection() {
        super.stalkingInternet();
    }

    @Override
    public void onDispose() {
        super.dispose();
    }

    private void setupBadge(int value) {

        if (textCartItemCount != null) {
            if (value == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(value, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initFragment() {
        FragmentList home = new FragmentList();
        home.setFragment(homeFragment);
        home.setTag(HOME.name());
        fragments.add(home);

        FragmentList favorite = new FragmentList();
        favorite.setFragment(favoriteFragment);
        favorite.setTag(FAVORITE.name());
        fragments.add(favorite);

        FragmentList search = new FragmentList();
        search.setFragment(searchFragment);
        search.setTag(SEARCH.name());
        fragments.add(search);

        FragmentList profile = new FragmentList();
        profile.setFragment(profileFragment);
        profile.setTag(PROFILE.name());
        fragments.add(profile);

        FragmentList cart = new FragmentList();
        cart.setFragment(cartFragment);
        cart.setTag(CART.name());
        fragments.add(cart);
    }

    @Override
    public void setCounter(int i) {
        setupBadge(i);
    }

    @Override
    public void showEmptyMessage(String str) {

    }

    @Override
    public void showErrorMessage(String str) {

    }

    @Inject
    @Override
    public void attachPresenter(@NonNull MainContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    public enum navigationState {
        HOME, FAVORITE, SEARCH, PROFILE, CART, CHILD, SUBMIT
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            supportFinishAfterTransition();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentManager.removeOnBackStackChangedListener(this::reOrder);
    }


    public Uri createShareUri(int deepId) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(getString(R.string.config_scheme))
                .authority(getString(R.string.config_host))
                .appendPath(getString(R.string.config_path_deep))
                .appendQueryParameter(QUERY_PARAM_ID, String.valueOf(deepId));
        return builder.build();
    }

    public void shortenLink(Uri linkUri) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(linkUri)
                .buildShortDynamicLink()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Uri shortLink = task.getResult().getShortLink();
                        String msg = String.format(getString(R.string.proposal), shortLink);
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });
    }

    public Uri createDynamicUri(Uri myUri, Product product) {
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(myUri)
                .setDynamicLinkDomain(DYNAMIC_LINK_DOMAIN)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder()
                        .build())
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.zeowls.hypertech").build())
                .setSocialMetaTagParameters(new DynamicLink.SocialMetaTagParameters.Builder()
                        .setTitle(product.getName())
                        .setDescription(product.getDescription())
                        .setImageUrl(Uri.parse(product.getMainImage()))
                        .build())
                .buildDynamicLink();
        return dynamicLink.getUri();
    }

    public void hideKeyboard() {
        View view = this.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
