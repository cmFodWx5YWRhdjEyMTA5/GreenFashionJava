package com.zeowls.data.mapper;

import com.zeowls.data.entity.Image_data;
import com.zeowls.domain.entity.Image;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ImageMapper extends Mapper<Image, Image_data> {
    @Inject
    public ImageMapper() {
    }

    @Override
    public Image map(Image_data data) {
        if (data == null) return null;
        Image entity = new Image();
        entity.setActive(data.isActive());
        entity.setIdColor(data.getIdColor());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setIdImage(data.getIdImage());
        entity.setIdProduct(data.getIdProduct());
        entity.setImageUrl(data.getImageUrl());
        entity.setProduct(data.getProduct());
        return entity;
    }

    @Override
    public Image_data reverse(Image brand_) {
        return null;
    }
}
