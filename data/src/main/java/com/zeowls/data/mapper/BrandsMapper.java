package com.zeowls.data.mapper;


import com.zeowls.data.entity.Brand_data;
import com.zeowls.data.entity.Brands_data;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Brands;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class BrandsMapper extends Mapper<Brands, Brands_data> {

    private Mapper<Brand, Brand_data> brandMapper;


    @Inject
    public BrandsMapper(Mapper<Brand, Brand_data> brandMapper) {
        this.brandMapper = brandMapper;
    }


    @Override
    public Brands map(Brands_data data) {
        if (data == null) return null;
        Brands entity = new Brands();
        entity.setBrand(brandMapper.map(data.getBrand()));
        entity.setCount(data.getCount());
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public Brands_data reverse(Brands data) {
        return null;
    }
}
