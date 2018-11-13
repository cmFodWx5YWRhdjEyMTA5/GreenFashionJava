package com.zeowls.data.mapper;

import com.zeowls.data.entity.Phone_data;
import com.zeowls.domain.entity.Phone;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class PhoneMapper extends Mapper<Phone, Phone_data> {
    @Inject
    public PhoneMapper() {
    }

    @Override
    public Phone map(Phone_data data) {
        if (data == null) return null;
        Phone entity = new Phone();
        entity.setActive(data.getActive());
        entity.setIdUser(data.getIdUser());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setLandline(data.getLandline());
        entity.setMobile(data.getMobile());
        return entity;
    }

    @Override
    public Phone_data reverse(Phone brand_) {
        return null;
    }
}
