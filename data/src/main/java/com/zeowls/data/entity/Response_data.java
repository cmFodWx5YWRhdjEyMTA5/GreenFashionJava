package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_data {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("messageCode")
    @Expose
    private Integer messageCode;
    @SerializedName("response")
    @Expose
    private Integer response;

    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean bool) {
        this.error = bool;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Integer getMessageCode() {
        return this.messageCode;
    }

    public void setMessageCode(Integer num) {
        this.messageCode = num;
    }

    public Integer getResponse() {
        return this.response;
    }

    public void setResponse(Integer num) {
        this.response = num;
    }
}
