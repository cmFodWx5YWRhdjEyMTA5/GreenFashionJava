package com.zeowls.data.mapper;


import com.zeowls.data.entity.Brand_data;
import com.zeowls.data.entity.MainCat_data;
import com.zeowls.data.entity.SubCat_data;
import com.zeowls.data.entity.Suggestion_data;
import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.MainCat;
import com.zeowls.domain.entity.SubCat;
import com.zeowls.domain.entity.Suggestion;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class SuggestionMapper extends Mapper<Suggestion, Suggestion_data> {

    private Mapper<Brand, Brand_data> brandMapper;
    private Mapper<MainCat, MainCat_data> mainMapper;
    private Mapper<SubCat, SubCat_data> subMapper;

    @Inject
    public SuggestionMapper(Mapper<Brand, Brand_data> brandMapper, Mapper<MainCat, MainCat_data> mainMapper, Mapper<SubCat, SubCat_data> subMapper) {
        this.brandMapper = brandMapper;
        this.mainMapper = mainMapper;
        this.subMapper = subMapper;
    }


    @Override
    public Suggestion map(Suggestion_data data) {
        if (data == null) return null;
        Suggestion entity = new Suggestion();
        entity.setBrands(brandMapper.map(data.getBrands()));
        entity.setMainCats(mainMapper.map(data.getMainCats()));
        entity.setSubCats(subMapper.map(data.getSubCats()));
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public Suggestion_data reverse(Suggestion homePage_) {
        return null;
    }
}
