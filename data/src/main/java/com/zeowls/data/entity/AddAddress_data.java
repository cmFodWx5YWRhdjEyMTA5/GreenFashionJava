
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAddress_data {

    @SerializedName("floor_num")
    @Expose
    private Integer floorNum;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("apartment_num")
    @Expose
    private Integer apartmentNum;
    @SerializedName("landline")
    @Expose
    private String landline;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("building_num")
    @Expose
    private Integer buildingNum;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("preferred_time")
    @Expose
    private String preferredTime;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("last_name")
    @Expose
    private String lastName;

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Integer getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(Integer apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
