package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.MainCat_data;
import com.zeowls.domain.entity.MainCat;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class MainCatMapper extends Mapper<MainCat, MainCat_data> {
    private Context context;

    @Inject
    public MainCatMapper(Context context) {
        this.context = context;
    }

    @Override
    public MainCat map(MainCat_data data) {
        if (data == null) return null;
        MainCat entity = new MainCat();
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
    public MainCat_data reverse(MainCat category_) {
        return null;
    }

}
