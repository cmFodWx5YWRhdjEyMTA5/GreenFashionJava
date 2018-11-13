package com.zeowls.data.mapper;


import com.zeowls.data.entity.Brand_data;
import com.zeowls.data.entity.Category_data;
import com.zeowls.data.entity.HomePage_data;
import com.zeowls.data.entity.Promotion_data;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Category;
import com.zeowls.domain.entity.HomePage;
import com.zeowls.domain.entity.Promotion;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class HomeMapper extends Mapper<HomePage, HomePage_data> {

    private Mapper<Brand, Brand_data> brandMapper;
    private Mapper<Category, Category_data> categoryMapper;
    private Mapper<Promotion, Promotion_data> promotionMapper;

    @Inject
    public HomeMapper(Mapper<Brand, Brand_data> brandMapper,
                      Mapper<Category, Category_data> categoryMapper,
                      Mapper<Promotion, Promotion_data> promotionMapper) {
        this.brandMapper = brandMapper;
        this.categoryMapper = categoryMapper;
        this.promotionMapper = promotionMapper;
    }


    @Override
    public HomePage map(HomePage_data data) {
        if (data == null) return null;
        HomePage entity = new HomePage();
        entity.setBrands(brandMapper.map(data.getBrands()));
        entity.setCategories(categoryMapper.map(data.getCategories()));
        entity.setPromotion(promotionMapper.map(data.getPromotions()));
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setNewImage(data.getNewImage());
        entity.setHotImage(data.getHotImage());
        return entity;
    }

    @Override
    public HomePage_data reverse(HomePage homePage_) {
        return null;
    }
}
