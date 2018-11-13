package com.zeowls.domain.entity;



public class VersionValidation {

    private Boolean critical;
    private Boolean error;
    private Object message;
    private Object messageCode;
    private Integer versionCode;
    private Double versionName;

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
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

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Double getVersionName() {
        return versionName;
    }

    public void setVersionName(Double versionName) {
        this.versionName = versionName;
    }

}
