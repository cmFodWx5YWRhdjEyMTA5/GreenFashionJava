package com.zeowls.data.mapper;

import com.zeowls.data.entity.AddAddress_data;
import com.zeowls.domain.entity.AddAddress;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class AddAddressMapper extends Mapper<AddAddress, AddAddress_data> {
    @Inject
    public AddAddressMapper() {
    }

    @Override
    public AddAddress map(AddAddress_data data) {
        if (data == null) return null;
        AddAddress entity = new AddAddress();
        entity.setUserId(data.getUserId());
        entity.setAddressId(data.getAddressId());
        entity.setStreetName(data.getStreetName());
        entity.setApartmentNum(data.getApartmentNum());
        entity.setAreaName(data.getAreaName());
        entity.setBuildingNum(data.getBuildingNum());
        entity.setCityName(data.getCityName());
        entity.setCountryName(data.getCountryName());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setFloorNum(data.getFloorNum());
        entity.setLandline(data.getLandline());
        entity.setLandmark(data.getLandmark());
        entity.setMobile(data.getMobile());
        entity.setLatitude(data.getLatitude());
        entity.setLongitude(data.getLongitude());
        entity.setNotes(data.getNotes());
        entity.setPreferredTime(data.getPreferredTime());
        entity.setLocationType(data.getLocationType());
        return entity;
    }

    @Override
    public AddAddress_data reverse(AddAddress data) {
        if (data == null) return null;
        AddAddress_data entity = new AddAddress_data();
        entity.setUserId(data.getUserId());
        entity.setStreetName(data.getStreetName());
        entity.setAddressId(data.getAddressId());
        entity.setApartmentNum(data.getApartmentNum());
        entity.setAreaName(data.getAreaName());
        entity.setBuildingNum(data.getBuildingNum());
        entity.setCityName(data.getCityName());
        entity.setCountryName(data.getCountryName());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setFloorNum(data.getFloorNum());
        entity.setLandline(data.getLandline());
        entity.setLandmark(data.getLandmark());
        entity.setMobile(data.getMobile());
        entity.setLatitude(data.getLatitude());
        entity.setLongitude(data.getLongitude());
        entity.setNotes(data.getNotes());
        entity.setPreferredTime(data.getPreferredTime());
        entity.setLocationType(data.getLocationType());
        return entity;
    }
}
