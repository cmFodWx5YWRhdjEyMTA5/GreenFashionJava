
package com.zeowls.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggestion_data {

    @SerializedName("brands")
    @Expose
    private List<Brand_data> brands = null;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("main_cats")
    @Expose
    private List<MainCat_data> mainCats = null;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("message_code")
    @Expose
    private Object messageCode;
    @SerializedName("sub_cats")
    @Expose
    private List<SubCat_data> subCats = null;

    public List<Brand_data> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand_data> brands) {
        this.brands = brands;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MainCat_data> getMainCats() {
        return mainCats;
    }

    public void setMainCats(List<MainCat_data> mainCats) {
        this.mainCats = mainCats;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Object messageCode) {
        this.messageCode = messageCode;
    }

    public List<SubCat_data> getSubCats() {
        return subCats;
    }

    public void setSubCats(List<SubCat_data> subCats) {
        this.subCats = subCats;
    }

}
