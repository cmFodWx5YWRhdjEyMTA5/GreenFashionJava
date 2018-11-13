
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promotion_data {

    @SerializedName("date_add")
    @Expose
    private int dateAdd;
    @SerializedName("date_upd")
    @Expose
    private int dateUpd;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_image")
    @Expose
    private int idImage;
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("quantity")
    @Expose
    private Object quantity;
    @SerializedName("id_brand")
    @Expose
    private Integer idBrand;
    @SerializedName("id_category")
    @Expose
    private Integer idCategory;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("brand_nameAr")
    @Expose
    private String brandNameAr;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_nameAr")
    @Expose
    private String categoryNameAr;

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

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Integer getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandNameAr() {
        return brandNameAr;
    }

    public void setBrandNameAr(String brandNameAr) {
        this.brandNameAr = brandNameAr;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameAr() {
        return categoryNameAr;
    }

    public void setCategoryNameAr(String categoryNameAr) {
        this.categoryNameAr = categoryNameAr;
    }
}
