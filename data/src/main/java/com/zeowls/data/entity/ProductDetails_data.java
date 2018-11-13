
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetails_data {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;
    @SerializedName("product")
    @Expose
    private Product_data product;
    @SerializedName("related")
    @Expose
    private List<Related_data> related = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
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

    public Product_data getProduct() {
        return product;
    }

    public void setProduct(Product_data product) {
        this.product = product;
    }

    public List<Related_data> getRelated() {
        return related;
    }

    public void setRelated(List<Related_data> related) {
        this.related = related;
    }

}
