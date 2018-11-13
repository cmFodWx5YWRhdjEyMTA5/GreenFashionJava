
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterRequest_data {

    @SerializedName("brands")
    @Expose
    private List<Integer> brands = null;
    @SerializedName("min_price")
    @Expose
    private int minPrice;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("max_price")
    @Expose
    private int maxPrice;
    @SerializedName("colors")
    @Expose
    private List<Integer> colors = null;
    @SerializedName("cats")
    @Expose
    private List<Integer> cats = null;

    public List<Integer> getBrands() {
        return brands;
    }

    public void setBrands(List<Integer> brands) {
        this.brands = brands;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getColors() {
        return colors;
    }

    public void setColors(List<Integer> colors) {
        this.colors = colors;
    }

    public List<Integer> getCats() {
        return cats;
    }

    public void setCats(List<Integer> cats) {
        this.cats = cats;
    }

}
