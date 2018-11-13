package com.zeowls.domain.entity;


public class User {
    public enum UserLogin {
        EDITABLE, NON_EDITABLE
    }

    private boolean active;

    private Object birthday;

    private int dateAdd;

    private int dateUpd;

    private String email;

    private Object favLan;

    private Object fbToken;

    private String firstName;

    private Object gender;

    private String googleId;

    private int id;

    private String lastName;

    private Object latitude;

    private Object longitude;

    private String profilePic;

    public UserLogin userLogin;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public int getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(int dateAdd) {
        this.dateAdd = dateAdd;
    }

    public int getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(int dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getFavLan() {
        return favLan;
    }

    public void setFavLan(Object favLan) {
        this.favLan = favLan;
    }

    public Object getFbToken() {
        return fbToken;
    }

    public void setFbToken(Object fbToken) {
        this.fbToken = fbToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public String getProfilePic() {
        return "http://arafa.000webhostapp.com/Hyper/uploads/"+profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public String getUsername() {
        return String.format("%s %s", firstName, lastName);
    }
}
