
package com.zeowls.domain.entity;



public class Cart {


    private Boolean active;

    private Integer dateAdd;

    private Integer dateUpd;

    private Integer id;

    private Integer idOrder;

    private Integer idProduct;

    private Integer idUser;

    private String productImage;

    private String productName;

    private String productNameAr;

    private Boolean productOnSale;

    private Boolean productOutOfStock;

    private Double productRate;

    private Object productReductionPercent;

    private Integer productReductionPrice;

    private Integer productWholesalePrice;

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
        return "http://arafa.000webhostapp.com/Hyper/uploads/" + productImage;
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
