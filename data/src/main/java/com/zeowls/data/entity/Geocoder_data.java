
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geocoder_data {

    @SerializedName("results")
    @Expose
    private List<Geocode_data> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Geocode_data> getResults() {
        return results;
    }

    public void setResults(List<Geocode_data> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
