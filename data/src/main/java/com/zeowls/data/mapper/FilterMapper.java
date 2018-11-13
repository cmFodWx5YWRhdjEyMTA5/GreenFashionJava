package com.zeowls.data.mapper;

import com.zeowls.data.entity.Brand_data;
import com.zeowls.data.entity.Category_data;
import com.zeowls.data.entity.Filter_data;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.entity.Filter;
import com.zeowls.domain.scope.ApplicationScope;
import javax.inject.Inject;

@ApplicationScope
public class FilterMapper extends Mapper<Filter, Filter_data> {
    private Mapper<Brand, Brand_data> brandMapper;
    private Mapper<Category, Category_data> categoryMapper;

    @Inject
    public FilterMapper(Mapper<Brand, Brand_data> brandMapper, Mapper<Category, Category_data> categoryMapper) {
        this.brandMapper = brandMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Filter map(Filter_data data) {
        if (data == null) return null;
        Filter entity = new Filter();
        entity.setBrands(brandMapper.map(data.getBrands()));
        entity.setCategories(categoryMapper.map(data.getCategories()));
        entity.setMinPrice(data.getMinPrice());
        entity.setMaxPrice(data.getMaxPrice());
        return entity;
    }

    @Override
    public Filter_data reverse(Filter filter) {
        return null;
    }
}
