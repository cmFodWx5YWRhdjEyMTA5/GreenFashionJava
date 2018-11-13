package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Brand_data;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class BrandMapper extends Mapper<Brand, Brand_data> {
    private Context context;

    @Inject
    public BrandMapper(Context context) {
        this.context = context;
    }

    @Override
    public Brand map(Brand_data data) {
        if (data == null) return null;
        Brand entity = new Brand();
        entity.setActive(data.isActive());
        entity.setCode(data.getCode());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setImage(data.getImage());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setName(data.getName());
        else
            entity.setName(data.getNameAr());
        entity.setNameAr(data.getNameAr());
        return entity;
    }

    @Override
    public Brand_data reverse(Brand brand_) {
        return null;
    }
}
