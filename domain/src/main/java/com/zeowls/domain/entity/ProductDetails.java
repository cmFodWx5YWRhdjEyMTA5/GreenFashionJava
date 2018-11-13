
package com.zeowls.domain.entity;

import java.util.List;

public class ProductDetails {

    private boolean error;
    private Object message;
    private Object messageCode;
    private Product product;
    private List<Related> related = null;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Related> getRelated() {
        return related;
    }

    public void setRelated(List<Related> related) {
        this.related = related;
    }

}
