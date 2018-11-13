package com.zeowls.data.mapper;

import com.zeowls.data.entity.Color_Color_data;
import com.zeowls.data.entity.Color_data;
import com.zeowls.domain.entity.Color;
import com.zeowls.domain.entity.Color_Color;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ColorMapper extends Mapper<Color, Color_data> {

    private Mapper<Color_Color,Color_Color_data> color_Mapper;

    @Inject
    public ColorMapper(Mapper<Color_Color, Color_Color_data> color_mapper) {
        color_Mapper = color_mapper;
    }

    @Override
    public Color map(Color_data data) {
        if (data == null) return null;
        Color entity = new Color();
        entity.setColor(color_Mapper.map(data.getColor()));
        entity.setIdProduct(data.getIdProduct());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setModel(data.getModel());
        entity.setPrice(data.getPrice());
        entity.setQuantity(data.getQuantity());
        return entity;
    }

    @Override
    public Color_data reverse(Color category_) {
        return null;
    }

}
