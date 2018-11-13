package com.zeowls.data.mapper;

import com.zeowls.data.entity.Item_data;
import com.zeowls.domain.entity.Item;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ItemMapper extends Mapper<Item, Item_data> {

    @Inject
    public ItemMapper() {
    }

    @Override
    public Item map(Item_data data) {
        if (data == null) return null;
        Item entity = new Item();
        entity.setItemId(data.getItemId());
        entity.setItemQuantity(data.getItemQuantity());
        return entity;
    }

    @Override
    public Item_data reverse(Item data) {
        if (data == null) return null;
        Item_data entity = new Item_data();
        entity.setItemId(data.getItemId());
        entity.setItemQuantity(data.getItemQuantity());
        return entity;
    }
}
