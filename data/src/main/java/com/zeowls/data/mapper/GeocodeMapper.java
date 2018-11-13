package com.zeowls.data.mapper;


import com.zeowls.data.entity.Geocode_data;
import com.zeowls.domain.entity.Geocode;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class GeocodeMapper extends Mapper<Geocode, Geocode_data> {


    @Inject
    public GeocodeMapper() {
    }

    @Override
    public Geocode map(Geocode_data data) {
        if (data == null) return null;
        Geocode entitiy = new Geocode();
        entitiy.setFormattedAddress(data.getFormattedAddress());
        return entitiy;
    }

    @Override
    public Geocode_data reverse(Geocode data) {
        if (data == null) return null;
        Geocode_data entitiy = new Geocode_data();
        entitiy.setFormattedAddress(data.getFormattedAddress());
        return entitiy;    }
}
