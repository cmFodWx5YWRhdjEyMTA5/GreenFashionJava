
package com.zeowls.data.entity;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePage_data {

    @SerializedName("brands")
    @Expose
    private List<Brand_data> brands = null;
    @SerializedName("categories")
    @Expose
    private List<Category_data> categories = null;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;
    @SerializedName("new_image")
    @Expose
    private String newImage;
    @SerializedName("hot_image")
    @Expose
    private String hotImage;
    @SerializedName("promotions")
    @Expose
    private List<Promotion_data> promotions = null;

    public List<Brand_data> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand_data> brands) {
        this.brands = brands;
    }

    public List<Category_data> getCategories() {
        return categories;
    }

    public void setCategories(List<Category_data> categories) {
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

    public List<Promotion_data> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion_data> promotions) {
        this.promotions = promotions;
    }


    public String getNewImage() {
        return newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public String getHotImage() {
        return hotImage;
    }

    public void setHotImage(String hotImage) {
        this.hotImage = hotImage;
    }
}
