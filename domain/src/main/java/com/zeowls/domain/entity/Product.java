package com.zeowls.domain.entity;

import java.util.List;


public class Product {
    private boolean active;
    private String boxContent;
    private String boxContentAr;
    private int catPriority;
    private String category;
    private String code;
    private List<Color> colors = null;
    private String country;
    private int dateAdd;
    private int dateUpd;
    private String description;
    private String descriptionAr;
    private String dimensions;
    private String highlights;
    private String highlightsAr;
    private int hotPriority;
    private int id;
    private int idCategory;
    private int idMainCategory;
    private Object idMainColor;
    private int idManufacturer;
    private Object idSupplier;
    private List<Image> images = null;
    private boolean isHot;
    private boolean isNew;
    private String mainCategory;
    private String mainCategoryAr;
    private Object mainColor;
    private String mainImage;
    private String mainMaterial;
    private String mainMaterialAr;
    private String manufacturer;
    private String manufacturerAr;
    private String name;
    private String nameAr;
    private String newCode;
    private int newPriority;
    private boolean onSale;
    private boolean outOfStock;
    private int price;
    private int quantity;
    private Float rate;
    private Object reductionFrom;
    private Double reductionPercent;
    private Integer reductionPrice;
    private Object reductionTo;
    private String warranty;
    private String weight;
    private int wholesalePrice;
    private int cartQuantity;
    private boolean cart;
    private boolean favorite;
    private List<FeaturesAr> featuresAr = null;
    private List<FeaturesEn> featuresEn = null;

    public List<FeaturesAr> getFeaturesAr() {
        return featuresAr;
    }

    public void setFeaturesAr(List<FeaturesAr> featuresAr) {
        this.featuresAr = featuresAr;
    }

    public List<FeaturesEn> getFeaturesEn() {
        return featuresEn;
    }

    public void setFeaturesEn(List<FeaturesEn> featuresEn) {
        this.featuresEn = featuresEn;
    }

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

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
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

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Object getReductionFrom() {
        return reductionFrom;
    }

    public void setReductionFrom(Object reductionFrom) {
        this.reductionFrom = reductionFrom;
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

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
