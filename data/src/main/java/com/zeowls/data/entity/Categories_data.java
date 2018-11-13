package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories_data {

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
    private List<Product_data> product = null;
    @SerializedName("sub_cat")
    @Expose
    private List<SubCat_data> subCat = null;

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

    public List<Product_data> getProduct() {
        return product;
    }

    public void setProduct(List<Product_data> product) {
        this.product = product;
    }

    public List<SubCat_data> getSubCat() {
        return subCat;
    }

    public void setSubCat(List<SubCat_data> subCat) {
        this.subCat = subCat;
    }

}
