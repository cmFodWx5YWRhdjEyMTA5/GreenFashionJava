
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ids_data {

    @SerializedName("ids")
    @Expose
    private List<Integer> ids;

    public Ids_data() {
    }

    public Ids_data(List<Integer> ids) {

        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

}
