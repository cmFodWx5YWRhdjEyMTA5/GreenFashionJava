
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentState_data {

    @SerializedName("active")
    @Expose
    private Object active;
    @SerializedName("date_add")
    @Expose
    private Integer dateAdd;
    @SerializedName("date_upd")
    @Expose
    private Integer dateUpd;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Object getActive() {
        return active;
    }

    public void setActive(Object active) {
        this.active = active;
    }

    public Integer getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Integer dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Integer getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Integer dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
