package com.zeowls.domain.entity;

import java.util.List;
public class MainCats {

    private List<Category> category = null;
    private int count;
    private boolean error;
    private Object message;
    private Object messageCode;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
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
