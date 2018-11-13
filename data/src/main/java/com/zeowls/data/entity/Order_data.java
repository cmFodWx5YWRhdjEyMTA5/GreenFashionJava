
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Order_data {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("address")
    @Expose
    private Address_data address;
    @SerializedName("cart")
    @Expose
    private List<Cart_data> cart = null;
    @SerializedName("current_state")
    @Expose
    private CurrentState_data currentState;
    @SerializedName("date_add")
    @Expose
    private Date dateAdd;
    @SerializedName("date_upd")
    @Expose
    private Date dateUpd;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("phone")
    @Expose
    private Phone_data phone;
    @SerializedName("shipping_date")
    @Expose
    private Object shippingDate;
    @SerializedName("shipping_fee")
    @Expose
    private Integer shippingFee;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Address_data getAddress() {
        return address;
    }

    public void setAddress(Address_data address) {
        this.address = address;
    }

    public List<Cart_data> getCart() {
        return cart;
    }

    public void setCart(List<Cart_data> cart) {
        this.cart = cart;
    }

    public CurrentState_data getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CurrentState_data currentState) {
        this.currentState = currentState;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Phone_data getPhone() {
        return phone;
    }

    public void setPhone(Phone_data phone) {
        this.phone = phone;
    }

    public Object getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Object shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Integer shippingFee) {
        this.shippingFee = shippingFee;
    }

}
