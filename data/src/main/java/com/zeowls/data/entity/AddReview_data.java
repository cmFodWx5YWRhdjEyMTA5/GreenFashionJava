package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddReview_data {

    @SerializedName("item_id")
    @Expose
    private Integer itemId;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("review")
    @Expose
    private String review;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
