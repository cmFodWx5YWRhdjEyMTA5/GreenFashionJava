
package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviews_data {

    @SerializedName("rate")
    @Expose
    private double rate;
    @SerializedName("reviews")
    @Expose
    private List<Review_data> reviews = null;
    @SerializedName("reviews_count")
    @Expose
    private int reviewsCount;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Review_data> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review_data> reviews) {
        this.reviews = reviews;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

}
