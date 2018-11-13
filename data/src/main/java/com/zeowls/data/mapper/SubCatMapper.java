package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.SubCat_data;
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class SubCatMapper extends Mapper<SubCat, SubCat_data> {
    private Context context;
    @Inject
    public SubCatMapper(Context context) {
        this.context = context;
    }

    @Override
    public SubCat map(SubCat_data data) {
        if (data == null) return null;
        SubCat entity = new SubCat();
        entity.setActive(data.isActive());
        entity.setCode(data.getCode());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setIdParent(data.getIdParent());
        entity.setImage(data.getImage());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setName(data.getName());
        else
            entity.setName(data.getNameAr());
        entity.setNameAr(data.getNameAr());
        return entity;
    }

    @Override
    public SubCat_data reverse(SubCat category_) {
        return null;
    }

}
