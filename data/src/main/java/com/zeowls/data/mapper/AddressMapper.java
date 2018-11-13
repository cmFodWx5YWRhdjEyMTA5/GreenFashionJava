package com.zeowls.data.mapper;

import com.zeowls.data.entity.Address_data;
import com.zeowls.data.entity.Phone_data;
import com.zeowls.data.entity.User_data;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.Phone;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class AddressMapper extends Mapper<Address, Address_data> {
    private Mapper<Phone, Phone_data> phoneMapper;
    private Mapper<User, User_data> userMapper;

    @Inject
    public AddressMapper(Mapper<Phone, Phone_data> phoneMapper, Mapper<User, User_data> userMapper) {
        this.phoneMapper = phoneMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Address map(Address_data data) {
        if (data == null) return null;
        Address entity = new Address();
        entity.setActive(data.getActive());
        entity.setApartmentNum(data.getApartmentNum());
        entity.setBuildingNum(data.getBuildingNum());
        entity.setFloorNum(data.getFloorNum());
        entity.setAreaName(data.getAreaName());
        entity.setCityName(data.getCityName());
        entity.setCountryName(data.getCountryName());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setLandmark(data.getLandmark());
        entity.setLatitude(data.getLatitude());
        entity.setLongitude(data.getLongitude());
        entity.setLocationType(data.getLocationType());
        entity.setNotes(data.getNotes());
        entity.setPhone(phoneMapper.map(data.getPhone()));
        entity.setPreferredTime(data.getPreferredTime());
        entity.setStreetName(data.getStreetName());
        entity.setUser(userMapper.map(data.getUser()));
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        return entity;
    }

    @Override
    public Address_data reverse(Address brand_) {
        return null;
    }
}
