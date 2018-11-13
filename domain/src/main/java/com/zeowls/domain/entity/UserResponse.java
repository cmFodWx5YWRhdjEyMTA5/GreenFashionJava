package com.zeowls.domain.entity;

public class UserResponse {
    private Boolean error;
    private Object message;
    private Integer message_code;
    private Integer response;
    private User user;

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
