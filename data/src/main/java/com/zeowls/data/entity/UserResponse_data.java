package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse_data {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Integer message_code;
    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("user")
    @Expose
    private User_data user;

    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean bool) {
        this.error = bool;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(Object obj) {
        this.message = obj;
    }

    public User_data getUser() {
        return this.user;
    }

    public void setUser(User_data userRequest) {
        this.user = userRequest;
    }

    public Integer getResponse() {
        return this.response;
    }

    public void setResponse(Integer num) {
        this.response = num;
    }

    public Integer getMessage_code() {
        return this.message_code;
    }

    public void setMessage_code(Integer num) {
        this.message_code = num;
    }
}
