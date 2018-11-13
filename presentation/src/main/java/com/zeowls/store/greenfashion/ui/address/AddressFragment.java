package com.zeowls.store.greenfashion.ui.address;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.City;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.entity.Response;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerLocationComponent;
import com.zeowls.store.greenfashion.di.module.LocationModule;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.main.MainActivity;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;
import com.zeowls.store.greenfashion.ui.view.MapViewV2;
import com.zeowls.store.greenfashion.ui.view.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.zeowls.store.greenfashion.ui.utils.Utils.convertFromJsonString;
import static com.zeowls.store.greenfashion.ui.utils.Utils.inputStreamToString;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidField;
import static com.zeowls.store.greenfashion.ui.utils.Utils.setInputTextLayoutColor;


public class AddressFragment extends BaseFragment implements OnMapReadyCallback, AddressContract.View {
    private static final int RC_GPS = 888;
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_ID = "id";
    private Address data;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> locationAdapter;
    private ArrayAdapter<String> timeAdapter;
    private ArrayAdapter<String> regionAdapter;
    private CompositeDisposable disposable;
    private boolean isAutomaticLocation;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.map)
    MapViewV2 mapView;
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.lastname)
    EditText lastname;
    @BindView(R.id.country)
    EditText country;
    @BindView(R.id.city)
    MaterialSpinner city;
    @BindView(R.id.area)
    MaterialSpinner area;
    @BindView(R.id.street)
    EditText street;
    @BindView(R.id.building)
    EditText building;
    @BindView(R.id.floor)
    EditText floor;
    @BindView(R.id.apartment)
    EditText apartment;
    @BindView(R.id.landmark)
    EditText landmark;
    @BindView(R.id.location_type)
    MaterialSpinner locationType;
    @BindView(R.id.preferred_time)
    MaterialSpinner preferredTime;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.landline)
    EditText landline;
    @BindView(R.id.notes)
    EditText notes;
    private String[] perms;
    GoogleMap gMap;
    double lat = 0;
    double lng = 0;

    @Inject
    LocationManager locationManager;
    private AddressContract.Presenter presenter;
    private Integer id;
    private AddAddress address;

    public AddressFragment() {
    }

    public static AddressFragment newInstance(String address, Integer id) {
        AddressFragment fragment = new AddressFragment();
        if (id != null) {
            Bundle args = new Bundle();
            args.putString(ARG_ADDRESS, address);
            args.putInt(ARG_ID, id);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        perms = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (getArguments() != null) {
            data = convertFromJsonString(getArguments().getString(ARG_ADDRESS), Address.class);
            id = getArguments().getInt(ARG_ID, -1);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_address;
    }

    @Override
    protected void initializeDependencies() {
        DaggerLocationComponent.builder()
                .presenterModule(new PresenterModule())
                .locationModule(new LocationModule(getActivity(), this::onSetLocation))
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    private void onSetLocation(LatLng latLng) {
        lat = latLng.latitude;
        lng = latLng.longitude;
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        if (isAutomaticLocation)
            presenter.getStreet(lat + "," + lng);
    }

    @Override
    protected void init() {
        if (data != null) {
            isAutomaticLocation = false;
            toolbarTitle.setText(R.string.edit_address);
            populateFields();
        } else {
            isAutomaticLocation = true;
            toolbarTitle.setText(R.string.add_address);
        }

        back.setOnClickListener(view -> getActivity().onBackPressed());

        populateSpinner(convertFromJsonString(inputStreamToString(getResources().openRawResource(R.raw.city_region)), new TypeToken<List<City>>() {
        }.getType()));

        observeFields();
        methodRequiresReadPermission();
    }

    private void populateFields() {
        firstname.setText(data.getFirstName());
        lastname.setText(data.getLastName());
        country.setText(data.getCountryName());
        street.setText(data.getStreetName());
        building.setText(data.getBuildingNum());
        floor.setText(data.getFloorNum());
        apartment.setText(data.getApartmentNum());
        landmark.setText(data.getLandmark());
        mobile.setText(data.getPhone().getMobile());
        landline.setText(data.getPhone().getLandline());
        notes.setText(data.getNotes());
    }

    private void populateSpinner(List<City> list) {
        cityAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locationAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.location_type));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.preferred_time));
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> cities = new ArrayList<>();
        for (City name : list) {
            cities.add(name.getName());
        }
        cityAdapter.addAll(cities);
        city.setAdapter(cityAdapter);
        city.setList(list);
        city.setHasArea(this::setArea);
        locationType.setAdapter(locationAdapter);
        preferredTime.setAdapter(timeAdapter);
        area.setAdapter(regionAdapter);
    }

    private void setArea(List<City> list, int position) {
        regionAdapter.clear();
        if (!list.get(position).getRegion().isEmpty()) {
            regionAdapter.addAll(list.get(position).getRegion());
            area.setVisibility(View.VISIBLE);
        } else {
            area.setVisibility(View.GONE);
        }
        area.setAdapter(regionAdapter);
    }


    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(RC_GPS)
    private void methodRequiresReadPermission() {
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location),
                    RC_GPS, perms);
        } else {
            if (hasLocationPermission()) {
                locationManager.startLocationUpdates();
            }
        }
    }

    private boolean hasLocationPermission() {
        return EasyPermissions.hasPermissions(getActivity(), perms);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        if (hasLocationPermission()) {
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onDestroyView() {
        mapView.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        safelyDispose(disposable);
        presenter.dispose();
    }

    private void safelyDispose(Disposable... disposableArr) {
        for (Disposable disposable : disposableArr) {
            if (!(disposable == null || disposable.isDisposed())) {
                disposable.dispose();
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (hasLocationPermission()) {
            gMap.setMyLocationEnabled(true);
            locationManager.startLocationUpdates();
            googleMap.setOnMyLocationButtonClickListener(() -> {
                locationManager.getLocation();
                return true;
            });
        }
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull AddressContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @OnClick(R.id.save)
    public void onSave() {
        ((MainActivity) getActivity()).hideKeyboard();
        if (isValidField(firstname, getActivity()) && isValidField(lastname, getActivity()) && city.getSelectedItemPosition() > -1 && isValidField(street, getActivity()) && isValidField(mobile, getActivity()) && locationType.getSelectedItemPosition() > -1) {
            address = new AddAddress();
            address.setFirstName(String.valueOf(firstname.getText()));
            address.setLastName(String.valueOf(lastname.getText()));
            address.setCountryName(String.valueOf(country.getText()));
            address.setCityName(String.valueOf(city.getSelectedItem()));
            address.setStreetName(String.valueOf(street.getText()));
            address.setLocationType(String.valueOf(locationType.getSelectedItem()));
            address.setMobile(String.valueOf(mobile.getText()));
            address.setLatitude(lat);
            address.setLongitude(lng);
            address.setAddressId(id);
            address.setUserId(LoggedData.getUserObject(getActivity()).getId());
            if (area.getSelectedItem() != null)
                address.setAreaName(String.valueOf(area.getSelectedItem()));
            if (building.getText().length() > 0)
                address.setBuildingNum(Integer.valueOf(String.valueOf(building.getText())));
            if (floor.getText().length() > 0)
                address.setFloorNum(Integer.valueOf(String.valueOf(floor.getText())));
            if (apartment.getText().length() > 0)
                address.setApartmentNum(Integer.valueOf(String.valueOf(apartment.getText())));
            if (landmark.getText().length() > 0)
                address.setLandmark(String.valueOf(landmark.getText()));
            if (preferredTime.getSelectedItem() != null)
                address.setPreferredTime(String.valueOf(preferredTime.getSelectedItem()));
            if (landline.getText().length() > 0)
                address.setLandline(String.valueOf(landline.getText()));
            if (notes.getText().length() > 0)
                address.setNotes(String.valueOf(notes.getText()));
            presenter.getData(address);
        } else {
            Toast.makeText(getActivity(), getString(R.string.required), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setData(Response data) {
        if (data.getResponse() > 0) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void showErrorMessage() {
    }

    @Override
    public void setStreet(Geocoder data) {
        street.setText(data.getResults().get(0).getFormattedAddress());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LocationManager.LOCATION_SERVICES_CODE:
                if (resultCode == RESULT_OK) {
                    locationManager.getLocation();
                }
                break;
        }
    }


    private void observeFields() {
        disposable.add(RxTextView.textChanges(firstname)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        firstname.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(firstname, colorState);
                    } else {
                        firstname.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(firstname, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(lastname)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        lastname.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(lastname, colorState);
                    } else {
                        lastname.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(lastname, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(street)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        street.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(street, colorState);
                    } else {
                        street.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(street, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(mobile)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        mobile.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(mobile, colorState);
                    } else {
                        mobile.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(mobile, colorState);
                    }
                }));
    }
}
