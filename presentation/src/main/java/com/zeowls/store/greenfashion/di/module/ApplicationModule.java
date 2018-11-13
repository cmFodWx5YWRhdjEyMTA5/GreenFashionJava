package com.zeowls.store.greenfashion.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.zeowls.data.repository.StoreRepository;
import com.zeowls.data.source.remote.RemoteSource;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.executor.ThreadTransformer;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;
import com.zeowls.store.greenfashion.BuildConfig;
import com.zeowls.store.greenfashion.executor.SchedulerProvider;
import com.zeowls.store.greenfashion.ui.utils.FileResolver;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    Context provideContext() {
        return context;
    }

    @ApplicationScope
    @Provides
    Repository repository(StoreRepository repository) {
        return repository;
    }

    @Provides
    @ApplicationScope
    public RemoteSource storeService(Retrofit retrofit) {
        return retrofit.create(RemoteSource.class);
    }

    @ApplicationScope
    @Provides
    FileResolver fileResolver(Context context) {
        return new FileResolver(context.getContentResolver());
    }


    @ApplicationScope
    @Provides
    BaseSchedulerProvider schedulerProvider() {
        return new SchedulerProvider();
    }


    @Provides
    @ApplicationScope
    ThreadTransformer provideThreadTransformer(BaseSchedulerProvider schedulerProvider) {
        return new ThreadTransformer(schedulerProvider);
    }

    @ApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }
}
