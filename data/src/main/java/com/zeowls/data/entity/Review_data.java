package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Review_data {

    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("date_add")
    @Expose
    private Date dateAdd;
    @SerializedName("date_upd")
    @Expose
    private Date dateUpd;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("product")
    @Expose
    private int product;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_name_ar")
    @Expose
    private String productNameAr;
    @SerializedName("rate")
    @Expose
    private double rate;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("user")
    @Expose
    private User_data user;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameAr() {
        return productNameAr;
    }

    public void setProductNameAr(String productNameAr) {
        this.productNameAr = productNameAr;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User_data getUser() {
        return user;
    }

    public void setUser(User_data user) {
        this.user = user;
    }

}
