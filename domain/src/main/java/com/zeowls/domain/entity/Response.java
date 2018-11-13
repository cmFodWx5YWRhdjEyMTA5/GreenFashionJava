package com.zeowls.domain.entity;

public class Response {
    private Boolean error;
    private String message;
    private Integer messageCode;
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
