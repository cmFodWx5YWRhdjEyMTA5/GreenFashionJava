package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookLogin_data {

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fb_id")
    @Expose
    private String fbId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("fb_token")
    @Expose
    private String fbToken;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

}
