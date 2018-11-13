
package com.zeowls.domain.entity;

import java.util.List;

public class FilterRequest {

    private List<Integer> brands = null;
    private int minPrice;
    private String query;
    private int maxPrice;
    private List<Integer> colors = null;
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
