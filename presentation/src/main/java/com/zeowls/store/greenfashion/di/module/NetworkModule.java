package com.zeowls.store.greenfashion.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.BuildConfig;
import com.zeowls.store.greenfashion.ui.utils.MyDateTypeAdapter;

import java.io.File;
import java.util.Date;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((String message) -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);  //10M cache
    }

    @Provides
    @ApplicationScope
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "okhttp_cashe");
    }

    @Provides
    @ApplicationScope
    Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new MyDateTypeAdapter());
        return new GsonBuilder().registerTypeAdapter(Date.class, new MyDateTypeAdapter()).setDateFormat("dd/MM/yyyy hh:mm a").create();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        chain -> {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .addHeader("Authorization", BuildConfig.API_KEY);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                .cache(cache)
                .build();
    }
}
