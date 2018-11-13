package com.zeowls.data.mapper;

import com.zeowls.data.entity.ProductDetails_data;
import com.zeowls.data.entity.Product_data;
import com.zeowls.data.entity.Related_data;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.domain.entity.Related;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class ProductsDetailsMapper extends Mapper<ProductDetails, ProductDetails_data> {

    private Mapper<Product, Product_data> productMapper;
    private Mapper<Related, Related_data> relatedMapper;

    @Inject
    public ProductsDetailsMapper(Mapper<Product, Product_data> productMapper, Mapper<Related, Related_data> relatedMapper) {
        this.productMapper = productMapper;
        this.relatedMapper = relatedMapper;
    }


    @Override
    public ProductDetails map(ProductDetails_data data) {
        if (data == null) return null;
        ProductDetails entity = new ProductDetails();
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setProduct(productMapper.map(data.getProduct()));
        entity.setRelated(relatedMapper.map(data.getRelated()));
        return entity;
    }

    @Override
    public ProductDetails_data reverse(ProductDetails products) {
        return null;
    }
}
