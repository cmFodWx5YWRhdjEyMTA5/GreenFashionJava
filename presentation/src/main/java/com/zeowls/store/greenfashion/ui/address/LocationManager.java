package com.zeowls.store.greenfashion.ui.address;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Looper;
import android.provider.Settings;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

public class LocationManager {
    private long UPDATE_INTERVAL;
    private long FASTEST_INTERVAL;
    public static final int LOCATION_SERVICES_CODE = 350;

    private FusedLocationProviderClient locationFetcher;
    private SettingsClient settingsClient;
    private Activity activity;
    private LocationCallback locationCallback;
    private OnLocationFound onLocationFound;
    private LocationRequest mLocationRequest;

    @Inject
    public LocationManager(FusedLocationProviderClient locationFetcher, SettingsClient settingsClient) {
        this.locationFetcher = locationFetcher;
        this.settingsClient = settingsClient;
        UPDATE_INTERVAL = 10 * 1000;
        FASTEST_INTERVAL = 2000;
    }

    public interface OnLocationFound {
        void onSetLocation(LatLng latLng);
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.setAlwaysShow(true);
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        settingsClient.checkLocationSettings(locationSettingsRequest);

        settingsClient.checkLocationSettings(builder.build())
                .addOnSuccessListener(locationSettingsResponse ->
                        getLocation())
                .addOnFailureListener(exception -> {
                    if (exception instanceof ResolvableApiException) {
                        askForLocationServices(builder.build(), LOCATION_SERVICES_CODE);
                    }
                });

    }

    @SuppressLint("MissingPermission")
    public void getLocation() {
        locationFetcher.requestLocationUpdates(mLocationRequest, locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        locationFetcher.removeLocationUpdates(locationCallback);
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    private void onLocationChanged(Location location) {
        // New location has now been determined
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        onLocationFound.onSetLocation(latLng);
    }

    public LocationManager enableLocationCallback(OnLocationFound onLocationFound) {
        this.onLocationFound = onLocationFound;
        return this;
    }

    public LocationManager getActivity(Activity activity) {
        this.activity = activity;
        return this;
    }


    void askForLocationServices(LocationSettingsRequest builder, final int requestCode) {
        Task<LocationSettingsResponse> result =
                settingsClient.checkLocationSettings(builder);

        result.addOnCompleteListener(task -> {
            try {
                task.getResult(ApiException.class);
            } catch (ApiException exception) {
                switch (exception.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            // Show dialog to turn on location services
                            resolvable.startResolutionForResult(activity, requestCode);
                        } catch (IntentSender.SendIntentException | ClassCastException ignored) {
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        openLocationSettings();
                        break;
                }
            }
        });
    }

    // Get location services the old fashioned way
    protected void openLocationSettings() {
        activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }
}
