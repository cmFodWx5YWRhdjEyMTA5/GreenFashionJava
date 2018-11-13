package com.zeowls.data.mapper;

import com.zeowls.data.entity.Filter_data;
import com.zeowls.data.entity.Product_data;
import com.zeowls.data.entity.Products_data;
import com.zeowls.domain.entity.Filter;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class ProductsMapper extends Mapper<Products, Products_data> {

    private Mapper<Filter, Filter_data> filterMapper;
    private Mapper<Product, Product_data> productMapper;

    @Inject
    public ProductsMapper(Mapper<Filter, Filter_data> filterMapper, Mapper<Product, Product_data> productMapper) {
        this.filterMapper = filterMapper;
        this.productMapper = productMapper;
    }


    @Override
    public Products map(Products_data data) {
        if (data == null) return null;
        Products entity = new Products();
        entity.setCount(data.getCount());
        entity.setError(data.isError());
        entity.setFilter(filterMapper.map(data.getFilter()));
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setProduct(productMapper.map(data.getProduct()));
        return entity;
    }

    @Override
    public Products_data reverse(Products products) {
        return null;
    }
}
