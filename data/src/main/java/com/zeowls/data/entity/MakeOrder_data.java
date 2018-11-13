
package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MakeOrder_data {

    @SerializedName("address_id")
    @Expose
    private String addressId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("items")
    @Expose
    private List<Item_data> items = null;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Item_data> getItems() {
        return items;
    }

    public void setItems(List<Item_data> items) {
        this.items = items;
    }

}
