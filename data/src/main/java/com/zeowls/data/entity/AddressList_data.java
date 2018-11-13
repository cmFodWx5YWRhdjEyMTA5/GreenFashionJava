
package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressList_data {

    @SerializedName("addresses")
    @Expose
    private List<Address_data> addresses = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;

    public List<Address_data> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address_data> addresses) {
        this.addresses = addresses;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Object getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Object messageCode) {
        this.messageCode = messageCode;
    }

}
