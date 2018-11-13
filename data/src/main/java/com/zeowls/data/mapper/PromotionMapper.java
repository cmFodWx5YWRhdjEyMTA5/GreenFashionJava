package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Promotion_data;
import com.zeowls.domain.entity.Promotion;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class PromotionMapper extends Mapper<Promotion, Promotion_data> {
    private Context context;

    @Inject
    public PromotionMapper(Context context) {
        this.context = context;
    }

    @Override
    public Promotion map(Promotion_data data) {
        if (data == null) return null;
        Promotion entity = new Promotion();
        entity.setIdImage(data.getIdImage());
        entity.setIdProduct(data.getIdProduct());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setPrice(data.getPrice());
        entity.setQuantity(data.getQuantity());
        entity.setImageUrl(data.getImageUrl());
        entity.setIdBrand(data.getIdBrand());
        entity.setIdCategory(data.getIdCategory());
        entity.setType(data.getType());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setBrandName(data.getBrandName());
        else
            entity.setBrandName(data.getBrandNameAr());

        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setCategoryName(data.getCategoryName());
        else
            entity.setCategoryName(data.getCategoryNameAr());
        return entity;
    }

    @Override
    public Promotion_data reverse(Promotion promotion_) {
        return null;
    }


}
