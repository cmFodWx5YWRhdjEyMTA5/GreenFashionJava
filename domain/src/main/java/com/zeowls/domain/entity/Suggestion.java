
package com.zeowls.domain.entity;

import java.util.List;

public class Suggestion {

    private List<Brand> brands = null;
    private boolean error;
    private List<MainCat> mainCats = null;
    private Object message;
    private Object messageCode;
    private List<SubCat> subCats = null;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MainCat> getMainCats() {
        return mainCats;
    }

    public void setMainCats(List<MainCat> mainCats) {
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

    public List<SubCat> getSubCats() {
        return subCats;
    }

    public void setSubCats(List<SubCat> subCats) {
        this.subCats = subCats;
    }

}
