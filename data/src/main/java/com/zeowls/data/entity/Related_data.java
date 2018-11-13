
package com.zeowls.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Related_data {

    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("box_content")
    @Expose
    private String boxContent;
    @SerializedName("box_content_ar")
    @Expose
    private String boxContentAr;
    @SerializedName("cat_priority")
    @Expose
    private int catPriority;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("colors")
    @Expose
    private List<Color_data> colors = null;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("date_add")
    @Expose
    private int dateAdd;
    @SerializedName("date_upd")
    @Expose
    private int dateUpd;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("descriptionAr")
    @Expose
    private String descriptionAr;
    @SerializedName("dimensions")
    @Expose
    private String dimensions;
    @SerializedName("highlights")
    @Expose
    private String highlights;
    @SerializedName("highlightsAr")
    @Expose
    private String highlightsAr;
    @SerializedName("hot_priority")
    @Expose
    private int hotPriority;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_category")
    @Expose
    private int idCategory;
    @SerializedName("id_main_category")
    @Expose
    private int idMainCategory;
    @SerializedName("id_main_color")
    @Expose
    private Object idMainColor;
    @SerializedName("id_manufacturer")
    @Expose
    private int idManufacturer;
    @SerializedName("id_supplier")
    @Expose
    private Object idSupplier;
    @SerializedName("images")
    @Expose
    private List<Image_data> images = null;
    @SerializedName("is_hot")
    @Expose
    private boolean isHot;
    @SerializedName("is_new")
    @Expose
    private boolean isNew;
    @SerializedName("main_category")
    @Expose
    private String mainCategory;
    @SerializedName("main_category_ar")
    @Expose
    private String mainCategoryAr;
    @SerializedName("main_color")
    @Expose
    private Object mainColor;
    @SerializedName("main_image")
    @Expose
    private String mainImage;
    @SerializedName("main_material")
    @Expose
    private String mainMaterial;
    @SerializedName("main_material_ar")
    @Expose
    private String mainMaterialAr;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("manufacturer_ar")
    @Expose
    private String manufacturerAr;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("new_code")
    @Expose
    private String newCode;
    @SerializedName("new_priority")
    @Expose
    private int newPriority;
    @SerializedName("on_sale")
    @Expose
    private boolean onSale;
    @SerializedName("out_of_stock")
    @Expose
    private boolean outOfStock;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("rate")
    @Expose
    private Object rate;
    @SerializedName("reduction_from")
    @Expose
    private Object reductionFrom;
    @SerializedName("reduction_percent")
    @Expose
    private Object reductionPercent;
    @SerializedName("reduction_price")
    @Expose
    private Object reductionPrice;
    @SerializedName("reduction_to")
    @Expose
    private Object reductionTo;
    @SerializedName("warranty")
    @Expose
    private String warranty;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("wholesale_price")
    @Expose
    private int wholesalePrice;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBoxContent() {
        return boxContent;
    }

    public void setBoxContent(String boxContent) {
        this.boxContent = boxContent;
    }

    public String getBoxContentAr() {
        return boxContentAr;
    }

    public void setBoxContentAr(String boxContentAr) {
        this.boxContentAr = boxContentAr;
    }

    public int getCatPriority() {
        return catPriority;
    }

    public void setCatPriority(int catPriority) {
        this.catPriority = catPriority;
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

    public List<Color_data> getColors() {
        return colors;
    }

    public void setColors(List<Color_data> colors) {
        this.colors = colors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getHighlightsAr() {
        return highlightsAr;
    }

    public void setHighlightsAr(String highlightsAr) {
        this.highlightsAr = highlightsAr;
    }

    public int getHotPriority() {
        return hotPriority;
    }

    public void setHotPriority(int hotPriority) {
        this.hotPriority = hotPriority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdMainCategory() {
        return idMainCategory;
    }

    public void setIdMainCategory(int idMainCategory) {
        this.idMainCategory = idMainCategory;
    }

    public Object getIdMainColor() {
        return idMainColor;
    }

    public void setIdMainColor(Object idMainColor) {
        this.idMainColor = idMainColor;
    }

    public int getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public Object getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Object idSupplier) {
        this.idSupplier = idSupplier;
    }

    public List<Image_data> getImages() {
        return images;
    }

    public void setImages(List<Image_data> images) {
        this.images = images;
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

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getMainCategoryAr() {
        return mainCategoryAr;
    }

    public void setMainCategoryAr(String mainCategoryAr) {
        this.mainCategoryAr = mainCategoryAr;
    }

    public Object getMainColor() {
        return mainColor;
    }

    public void setMainColor(Object mainColor) {
        this.mainColor = mainColor;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(String mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    public String getMainMaterialAr() {
        return mainMaterialAr;
    }

    public void setMainMaterialAr(String mainMaterialAr) {
        this.mainMaterialAr = mainMaterialAr;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerAr() {
        return manufacturerAr;
    }

    public void setManufacturerAr(String manufacturerAr) {
        this.manufacturerAr = manufacturerAr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public int getNewPriority() {
        return newPriority;
    }

    public void setNewPriority(int newPriority) {
        this.newPriority = newPriority;
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

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public Object getReductionFrom() {
        return reductionFrom;
    }

    public void setReductionFrom(Object reductionFrom) {
        this.reductionFrom = reductionFrom;
    }

    public Object getReductionPercent() {
        return reductionPercent;
    }

    public void setReductionPercent(Object reductionPercent) {
        this.reductionPercent = reductionPercent;
    }

    public Object getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(Object reductionPrice) {
        this.reductionPrice = reductionPrice;
    }

    public Object getReductionTo() {
        return reductionTo;
    }

    public void setReductionTo(Object reductionTo) {
        this.reductionTo = reductionTo;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(int wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

}
