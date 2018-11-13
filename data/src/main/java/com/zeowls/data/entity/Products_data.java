
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products_data {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("filter")
    @Expose
    private Filter_data filter;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;
    @SerializedName(value = "product", alternate = "products")
    @Expose
    private List<Product_data> product = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Filter_data getFilter() {
        return filter;
    }

    public void setFilter(Filter_data filter) {
        this.filter = filter;
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

    public List<Product_data> getProduct() {
        return product;
    }

    public void setProduct(List<Product_data> product) {
        this.product = product;
    }

}
