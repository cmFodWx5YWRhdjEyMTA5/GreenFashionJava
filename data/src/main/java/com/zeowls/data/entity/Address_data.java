
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address_data {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("apartment_num")
    @Expose
    private String apartmentNum;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("building_num")
    @Expose
    private String buildingNum;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("date_add")
    @Expose
    private Integer dateAdd;
    @SerializedName("date_upd")
    @Expose
    private Integer dateUpd;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("floor_num")
    @Expose
    private String floorNum;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("phone")
    @Expose
    private Phone_data phone;
    @SerializedName("preferred_time")
    @Expose
    private String preferredTime;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("user")
    @Expose
    private User_data user;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(String apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
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

    public Integer getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Integer dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Integer getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Integer dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Phone_data getPhone() {
        return phone;
    }

    public void setPhone(Phone_data phone) {
        this.phone = phone;
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

    public User_data getUser() {
        return user;
    }

    public void setUser(User_data user) {
        this.user = user;
    }

}
