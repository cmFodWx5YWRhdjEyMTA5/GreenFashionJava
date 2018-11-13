package com.zeowls.data.mapper;


import com.zeowls.data.entity.Geocode_data;
import com.zeowls.data.entity.Geocoder_data;
import com.zeowls.domain.entity.Geocode;
import com.zeowls.domain.entity.Geocoder;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class GeocoderMapper extends Mapper<Geocoder, Geocoder_data> {

    private Mapper<Geocode, Geocode_data> geocodeMapper;

    @Inject
    public GeocoderMapper(Mapper<Geocode, Geocode_data> geocodeMapper) {

        this.geocodeMapper = geocodeMapper;
    }


    @Override
    public Geocoder map(Geocoder_data data) {
        if (data == null) return null;
        Geocoder entity = new Geocoder();
        entity.setResults(geocodeMapper.map(data.getResults()));
        entity.setStatus(data.getStatus());
        return entity;
    }

    @Override
    public Geocoder_data reverse(Geocoder data) {
        if (data == null) return null;
        Geocoder_data entity = new Geocoder_data();
        entity.setResults(geocodeMapper.reverse(data.getResults()));
        entity.setStatus(data.getStatus());
        return entity;
    }
}
