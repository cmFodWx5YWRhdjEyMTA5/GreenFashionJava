package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Color_Color_data;
import com.zeowls.domain.entity.Color_Color;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class Color_Mapper extends Mapper<Color_Color, Color_Color_data> {
    private Context context;

    @Inject
    public Color_Mapper(Context context) {
        this.context = context;
    }

    @Override
    public Color_Color map(Color_Color_data data) {
        if (data == null) return null;
        Color_Color entity = new Color_Color();
        entity.setActive(data.isActive());
        entity.setCode(data.getCode());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setName(data.getName());
        else
            entity.setName(data.getNameAr());
        entity.setNameAr(data.getNameAr());
        return entity;
    }

    @Override
    public Color_Color_data reverse(Color_Color category_) {
        return null;
    }

}
