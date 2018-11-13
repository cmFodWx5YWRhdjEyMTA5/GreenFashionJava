package com.zeowls.data.mapper;

import com.zeowls.data.entity.Categories_data;
import com.zeowls.data.entity.Product_data;
import com.zeowls.data.entity.SubCat_data;
import com.zeowls.domain.entity.Categories;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class CategoriesMapper extends Mapper<Categories, Categories_data> {

    private Mapper<Product, Product_data> productMapper;
    private Mapper<SubCat, SubCat_data> subCatMapper;

    @Inject
    public CategoriesMapper(Mapper<Product, Product_data> productMapper, Mapper<SubCat, SubCat_data> subCatMapper) {

        this.productMapper = productMapper;
        this.subCatMapper = subCatMapper;
    }


    @Override
    public Categories map(Categories_data data) {
        if (data == null) return null;
        Categories entity = new Categories();
        entity.setProduct(productMapper.map(data.getProduct()));
        entity.setSubCat(subCatMapper.map(data.getSubCat()));
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public Categories_data reverse(Categories homePage_) {
        return null;
    }
}
