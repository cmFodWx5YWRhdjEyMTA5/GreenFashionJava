package com.zeowls.data.mapper;


import com.zeowls.data.entity.FilterRequest_data;
import com.zeowls.domain.entity.FilterRequest;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class FilterListMapper extends Mapper<FilterRequest, FilterRequest_data> {


    @Inject
    public FilterListMapper(){
    }


    @Override
    public FilterRequest map(FilterRequest_data data) {
        if (data == null) return null;
        FilterRequest entity = new FilterRequest();
        entity.setBrands((data.getBrands()));
        entity.setCats(data.getCats());
        entity.setColors(data.getColors());
        entity.setMinPrice(data.getMinPrice());
        entity.setMaxPrice(data.getMaxPrice());
        entity.setQuery(data.getQuery());
        return entity;
    }

    @Override
    public FilterRequest_data reverse(FilterRequest data) {
        if (data == null) return null;
        FilterRequest_data entity = new FilterRequest_data();
        entity.setBrands((data.getBrands()));
        entity.setCats(data.getCats());
        entity.setColors(data.getColors());
        entity.setMinPrice(data.getMinPrice());
        entity.setMaxPrice(data.getMaxPrice());
        entity.setQuery(data.getQuery());
        return entity;
    }
}
