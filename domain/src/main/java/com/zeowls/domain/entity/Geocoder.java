
package com.zeowls.domain.entity;

import java.util.List;

public class Geocoder {

    private List<Geocode> results = null;
    private String status;

    public List<Geocode> getResults() {
        return results;
    }

    public void setResults(List<Geocode> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
