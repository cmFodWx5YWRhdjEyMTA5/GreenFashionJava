package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Category_data;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class CategoryMapper extends Mapper<Category, Category_data> {
    private Context context;

    @Inject
    public CategoryMapper(Context context) {
        this.context = context;
    }

    @Override
    public Category map(Category_data data) {
        if (data == null) return null;
        Category entity = new Category();
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
    public Category_data reverse(Category category_) {
        return null;
    }

}
