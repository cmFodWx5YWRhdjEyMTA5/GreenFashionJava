
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart_data {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("date_add")
    @Expose
    private Integer dateAdd;
    @SerializedName("date_upd")
    @Expose
    private Integer dateUpd;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_order")
    @Expose
    private Integer idOrder;
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_name_ar")
    @Expose
    private String productNameAr;
    @SerializedName("product_on_sale")
    @Expose
    private Boolean productOnSale;
    @SerializedName("product_out_of_stock")
    @Expose
    private Boolean productOutOfStock;
    @SerializedName("product_rate")
    @Expose
    private Double productRate;
    @SerializedName("product_reduction_percent")
    @Expose
    private Object productReductionPercent;
    @SerializedName("product_reduction_price")
    @Expose
    private Integer productReductionPrice;
    @SerializedName("product_wholesale_price")
    @Expose
    private Integer productWholesalePrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Integer dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Integer getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Integer dateUpd) {
        this.dateUpd = dateUpd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameAr() {
        return productNameAr;
    }

    public void setProductNameAr(String productNameAr) {
        this.productNameAr = productNameAr;
    }

    public Boolean getProductOnSale() {
        return productOnSale;
    }

    public void setProductOnSale(Boolean productOnSale) {
        this.productOnSale = productOnSale;
    }

    public Boolean getProductOutOfStock() {
        return productOutOfStock;
    }

    public void setProductOutOfStock(Boolean productOutOfStock) {
        this.productOutOfStock = productOutOfStock;
    }

    public Double getProductRate() {
        return productRate;
    }

    public void setProductRate(Double productRate) {
        this.productRate = productRate;
    }

    public Object getProductReductionPercent() {
        return productReductionPercent;
    }

    public void setProductReductionPercent(Object productReductionPercent) {
        this.productReductionPercent = productReductionPercent;
    }

    public Integer getProductReductionPrice() {
        return productReductionPrice;
    }

    public void setProductReductionPrice(Integer productReductionPrice) {
        this.productReductionPrice = productReductionPrice;
    }

    public Integer getProductWholesalePrice() {
        return productWholesalePrice;
    }

    public void setProductWholesalePrice(Integer productWholesalePrice) {
        this.productWholesalePrice = productWholesalePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
