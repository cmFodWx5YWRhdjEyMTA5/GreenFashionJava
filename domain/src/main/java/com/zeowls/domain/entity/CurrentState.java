
package com.zeowls.domain.entity;

public class CurrentState {


    private Object active;

    private Integer dateAdd;

    private Integer dateUpd;

    private String description;

    private Integer id;

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
