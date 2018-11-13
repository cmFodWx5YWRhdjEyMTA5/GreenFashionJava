
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetails_data {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;
    @SerializedName("orders")
    @Expose
    private Order_data orders;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Object messageCode) {
        this.messageCode = messageCode;
    }

    public Order_data getOrders() {
        return orders;
    }

    public void setOrders(Order_data orders) {
        this.orders = orders;
    }

}
