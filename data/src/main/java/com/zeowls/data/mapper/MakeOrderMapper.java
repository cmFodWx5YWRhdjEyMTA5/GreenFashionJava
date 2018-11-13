package com.zeowls.data.mapper;

import com.zeowls.data.entity.Item_data;
import com.zeowls.data.entity.MakeOrder_data;
import com.zeowls.domain.entity.Item;
import com.zeowls.domain.entity.MakeOrder;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class MakeOrderMapper extends Mapper<MakeOrder, MakeOrder_data> {

    private Mapper<Item, Item_data> itemMapper;

    @Inject
    public MakeOrderMapper(Mapper<Item, Item_data> itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public MakeOrder map(MakeOrder_data data) {
        if (data == null) return null;
        MakeOrder entity = new MakeOrder();
        entity.setAddressId(data.getAddressId());
        entity.setUserId(data.getUserId());
        entity.setItems(itemMapper.map(data.getItems()));
        return entity;
    }

    @Override
    public MakeOrder_data reverse(MakeOrder data) {
        if (data == null) return null;
        MakeOrder_data entity = new MakeOrder_data();
        entity.setAddressId(data.getAddressId());
        entity.setUserId(data.getUserId());
        entity.setItems(itemMapper.reverse(data.getItems()));
        return entity;
    }
}
