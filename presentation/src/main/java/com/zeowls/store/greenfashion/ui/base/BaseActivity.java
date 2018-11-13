package com.zeowls.store.greenfashion.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.zeowls.data.LocaleManager;
import com.zeowls.store.greenfashion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private Animation slide_down, slide_up;
    private CompositeDisposable internetDisposable;
    @Nullable
    @BindView(R.id.merlin_container)
    ViewGroup noInternet;

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LocaleManager.setLocale(newBase));
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initializeDependencies();
        internetDisposable = new CompositeDisposable();
        bind();
        layoutConfig();
        init();
    }

    @LayoutRes
    protected abstract int layoutId();

    protected void bind() {
        unbinder = ButterKnife.bind(this);
    }

    public abstract void initializeDependencies();

    public abstract void init();

    protected abstract void triggerPresenter();

    protected abstract void connectionError();

    public void layoutConfig() {
        slide_down = AnimationUtils.loadAnimation(this,
                R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(this,
                R.anim.slide_up);
        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                noInternet.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                noInternet.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                noInternet.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void stalkingInternet() {
        internetDisposable.add(ReactiveNetwork
                .observeNetworkConnectivity(getApplicationContext())
                .distinctUntilChanged()
                .flatMapSingle(connectivity -> ReactiveNetwork.checkInternetConnectivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    if (isConnected) {
                        triggerPresenter();
                        if (noInternet.getVisibility() == View.VISIBLE) {
                            noInternet.clearAnimation();
                            noInternet.startAnimation(slide_down);
                        }
                    } else {
                        connectionError();
                        if (noInternet.getVisibility() == View.INVISIBLE) {
                            noInternet.clearAnimation();
                            noInternet.startAnimation(slide_up);
                        }
                    }
                }));
    }

    public void dispose() {
        if (internetDisposable != null) {
            safelyDispose(internetDisposable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    private void safelyDispose(CompositeDisposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
        }
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleManager.setLocale(base));
//    }

}
