package com.zeowls.domain.entity;

import java.util.List;


public class Categories {

    private boolean error;
    private Object message;
    private Object messageCode;
    private List<Product> product = null;
    private List<SubCat> subCat = null;

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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<SubCat> getSubCat() {
        return subCat;
    }

    public void setSubCat(List<SubCat> subCat) {
        this.subCat = subCat;
    }

}
