package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Color_data;
import com.zeowls.data.entity.Image_data;
import com.zeowls.data.entity.Related_data;
import com.zeowls.domain.entity.Color;
import com.zeowls.domain.entity.Image;
import com.zeowls.domain.entity.Related;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class RelatedMapper extends Mapper<Related, Related_data> {

    private Mapper<Color, Color_data> colorMapper;
    private Mapper<Image, Image_data> imageMapper;
    private Context context;


    @Inject
    public RelatedMapper(Mapper<Color, Color_data> colorMapper, Mapper<Image, Image_data> imageMapper, Context context) {
        this.colorMapper = colorMapper;
        this.imageMapper = imageMapper;
        this.context = context;
    }


    @Override
    public Related map(Related_data data) {
        if (data == null) return null;
        Related entity = new Related();
        entity.setActive(data.isActive());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setBoxContent(data.getBoxContent());
        else
            entity.setBoxContent(data.getBoxContentAr());
        entity.setBoxContentAr(data.getBoxContentAr());
        entity.setCatPriority(data.getCatPriority());
        entity.setCategory(data.getCategory());
        entity.setCode(data.getCode());
        entity.setColors(colorMapper.map(data.getColors()));
        entity.setCountry(data.getCountry());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setDescription(data.getDescription());
        else
            entity.setDescription(data.getDescriptionAr());
        entity.setDescriptionAr(data.getDescriptionAr());
        entity.setDimensions(data.getDimensions());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setHighlights(data.getHighlights());
        else
            entity.setHighlights(data.getHighlightsAr());
        entity.setHighlightsAr(data.getHighlightsAr());
        entity.setHotPriority(data.getHotPriority());
        entity.setId(data.getId());
        entity.setIdCategory(data.getIdCategory());
        entity.setIdMainCategory(data.getIdMainCategory());
        entity.setIdMainColor(data.getIdMainColor());
        entity.setIdManufacturer(data.getIdManufacturer());
        entity.setIdSupplier(data.getIdSupplier());
        entity.setImages(imageMapper.map(data.getImages()));
        entity.setHot(data.isHot());
        entity.setNew(data.isNew());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setMainCategory(data.getMainCategory());
        else
            entity.setMainCategory(data.getMainCategoryAr());
        entity.setMainCategoryAr(data.getMainCategoryAr());
        entity.setMainColor(data.getMainColor());
        entity.setMainImage(data.getMainImage());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setMainMaterial(data.getMainMaterial());
        else
            entity.setMainMaterial(data.getMainMaterialAr());
        entity.setMainMaterialAr(data.getMainMaterialAr());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setManufacturer(data.getManufacturer());
        else
            entity.setManufacturer(data.getManufacturerAr());
        entity.setManufacturerAr(data.getManufacturerAr());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setName(data.getName());
        else
            entity.setName(data.getNameAr());
        entity.setNameAr(data.getNameAr());
        entity.setNewCode(data.getNewCode());
        entity.setOnSale(data.isOnSale());
        entity.setOutOfStock(data.isOutOfStock());
        entity.setPrice(data.getPrice());
        entity.setQuantity(data.getQuantity());
        entity.setRate(data.getRate());
        entity.setReductionFrom(data.getReductionFrom());
        entity.setReductionPercent(data.getReductionPercent());
        entity.setReductionPrice(data.getReductionPrice());
        entity.setReductionTo(data.getReductionTo());
        entity.setWarranty(data.getWarranty());
        entity.setWeight(data.getWeight());
        entity.setWholesalePrice(data.getWholesalePrice());
        return entity;
    }

    @Override
    public Related_data reverse(Related data) {
        return null;
    }
}
