package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter_data {

    @SerializedName("brands")
    @Expose
    private List<Brand_data> brands = null;
    @SerializedName("categories")
    @Expose
    private List<Category_data> categories = null;
    @SerializedName("max_price")
    @Expose
    private int maxPrice;
    @SerializedName("min_price")
    @Expose
    private int minPrice;

    public List<Brand_data> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand_data> brands) {
        this.brands = brands;
    }

    public List<Category_data> getCategories() {
        return categories;
    }

    public void setCategories(List<Category_data> categories) {
        this.categories = categories;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

}
