package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color_data {

    @SerializedName("color")
    @Expose
    private Color_Color_data color;
    @SerializedName("date_add")
    @Expose
    private int dateAdd;
    @SerializedName("date_upd")
    @Expose
    private int dateUpd;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_product")
    @Expose
    private int idProduct;
    @SerializedName("model")
    @Expose
    private Object model;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("quantity")
    @Expose
    private Object quantity;

    public Color_Color_data getColor() {
        return color;
    }

    public void setColor(Color_Color_data color) {
        this.color = color;
    }

    public int getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(int dateAdd) {
        this.dateAdd = dateAdd;
    }

    public int getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(int dateUpd) {
        this.dateUpd = dateUpd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

}
