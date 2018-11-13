package com.zeowls.domain.entity;

import java.util.List;

public class City {
    private String name;
    private List<String> region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }
}
