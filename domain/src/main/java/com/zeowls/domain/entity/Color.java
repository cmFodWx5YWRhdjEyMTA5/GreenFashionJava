package com.zeowls.domain.entity;


public class Color {
    private Color_Color color;
    private int dateAdd;
    private int dateUpd;
    private int id;
    private int idProduct;
    private Object model;
    private Object price;
    private Object quantity;

    public Color_Color getColor() {
        return color;
    }

    public void setColor(Color_Color color) {
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
