package com.zeowls.store.greenfashion.di.module;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.SettingsClient;
import com.zeowls.domain.scope.LocationScope;
import com.zeowls.store.greenfashion.ui.address.LocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {
    private Activity activity;
    private final LocationManager.OnLocationFound OnLocationFound;

    public LocationModule(Activity activity, LocationManager.OnLocationFound onLocationFound) {
        this.activity = activity;
        OnLocationFound = onLocationFound;
    }

    @Provides
    @LocationScope
    LocationManager providesLocationHelper(FusedLocationProviderClient fusedLocationProviderClient,
                                           SettingsClient settingsClient) {
        return new LocationManager(fusedLocationProviderClient, settingsClient).getActivity(activity).enableLocationCallback(OnLocationFound);
    }

    @Provides
    @LocationScope
    FusedLocationProviderClient providesFusedClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }

    @Provides
    @LocationScope
    SettingsClient providesSettingsClient(Context context) {
        return LocationServices.getSettingsClient(context);
    }

}