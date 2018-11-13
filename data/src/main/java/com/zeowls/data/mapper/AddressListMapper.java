package com.zeowls.data.mapper;


import com.zeowls.data.entity.AddressList_data;
import com.zeowls.data.entity.Address_data;
import com.zeowls.domain.entity.Address;
import com.zeowls.domain.entity.AddressList;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class AddressListMapper extends Mapper<AddressList, AddressList_data> {

    private Mapper<Address, Address_data> addressMapper;


    @Inject
    public AddressListMapper(Mapper<Address, Address_data> addressMapper) {
        this.addressMapper = addressMapper;
    }


    @Override
    public AddressList map(AddressList_data data) {
        if (data == null) return null;
        AddressList entity = new AddressList();
        entity.setAddresses(addressMapper.map(data.getAddresses()));
        entity.setError(data.getError());
        entity.setMessageCode(data.getMessageCode());
        return entity;
    }

    @Override
    public AddressList_data reverse(AddressList data) {
        return null;
    }
}
