package com.zeowls.domain.entity;


import java.util.List;

public class HomePage {

    private List<Brand> brands = null;
    private List<Category> categories = null;
    private boolean error;
    private Object message;
    private Object messageCode;
    private String newImage;
    private String hotImage;

    private List<Promotion> promotion = null;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

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

    public List<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<Promotion> promotionDomains) {
        this.promotion = promotionDomains;
    }

    public String getNewImage() {
        return "http://arafa.000webhostapp.com/Hyper/uploads/" + newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public String getHotImage() {
        return "http://arafa.000webhostapp.com/Hyper/uploads/" + hotImage;
    }

    public void setHotImage(String hotImage) {
        this.hotImage = hotImage;
    }
}
