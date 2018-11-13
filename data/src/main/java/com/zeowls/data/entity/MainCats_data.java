package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCats_data {

    @SerializedName("category")
    @Expose
    private List<Category_data> category = null;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;

    public List<Category_data> getCategory() {
        return category;
    }

    public void setCategory(List<Category_data> category) {
        this.category = category;
    }

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

}
