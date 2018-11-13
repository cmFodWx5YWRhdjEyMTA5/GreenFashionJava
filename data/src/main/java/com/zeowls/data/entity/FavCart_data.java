package com.zeowls.data.entity;

import android.arch.persistence.room.ColumnInfo;

public class FavCart_data {
    private int cartQuantity = 1;

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "cartId")
    public Integer cartId;

    private boolean active;
    private String category;
    private String code;
    private int dateAdd;
    private int dateUpd;
    private boolean isHot;
    private boolean isNew;
    private String mainImage;
    private String name;
    private String nameAr;
    private String newCode;
    private boolean onSale;
    private boolean outOfStock;
    private int price;
    private int quantity;
    private Double reductionPercent;
    private Integer reductionPrice;
    private int wholesalePrice;

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }


    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getReductionPercent() {
        return reductionPercent;
    }

    public void setReductionPercent(Double reductionPercent) {
        this.reductionPercent = reductionPercent;
    }

    public Integer getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(Integer reductionPrice) {
        this.reductionPrice = reductionPrice;
    }

    public int getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(int wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
