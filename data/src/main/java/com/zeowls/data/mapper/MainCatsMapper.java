package com.zeowls.data.mapper;

import com.zeowls.data.entity.Category_data;
import com.zeowls.data.entity.MainCats_data;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class MainCatsMapper extends Mapper<MainCats, MainCats_data> {

    private Mapper<Category, Category_data> categoryMapper;


    @Inject
    public MainCatsMapper(Mapper<Category, Category_data> categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @Override
    public MainCats map(MainCats_data data) {
        if (data == null) return null;
        MainCats entity = new MainCats();
        entity.setCategory(categoryMapper.map(data.getCategory()));
        entity.setCount(data.getCount());
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public MainCats_data reverse(MainCats data) {
        return null;
    }
}
