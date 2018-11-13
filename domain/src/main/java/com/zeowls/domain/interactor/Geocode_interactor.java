package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class Geocode_interactor extends SingleInteractor<Geocoder> {

    private Repository repository;

    @Inject
    public Geocode_interactor(BaseSchedulerProvider schedulerProvider,
                              Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<Geocoder> singleUseCase(String latlng) {
        return repository.geocodeAddress(latlng);
    }
}
