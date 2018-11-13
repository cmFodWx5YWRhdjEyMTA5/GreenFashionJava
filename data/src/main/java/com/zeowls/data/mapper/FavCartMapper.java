package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.entity.FavCart_data;
import com.zeowls.domain.entity.FavCart;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class FavCartMapper extends Mapper<FavCart, FavCart_data> {
    private Context context;

    @Inject
    FavCartMapper(Context context) {
        this.context = context;
    }

    @Override
    public FavCart map(FavCart_data data) {
        if (data == null) return null;
        FavCart entity = new FavCart();
        entity.setActive(data.isActive());
        entity.setCartId(data.getCartId());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setHot(data.isHot());
        entity.setId(data.getId());
        entity.setMainImage(data.getMainImage());
        entity.setName(data.getName());
        entity.setNameAr(data.getNameAr());
        entity.setNew(data.isNew());
        entity.setNewCode(data.getNewCode());
        entity.setOnSale(data.isOnSale());
        entity.setOutOfStock(data.isOutOfStock());
        entity.setPrice(data.getPrice());
        entity.setQuantity(data.getQuantity());
        entity.setReductionPercent(data.getReductionPercent());
        entity.setReductionPrice(data.getReductionPrice());
        entity.setWholesalePrice(data.getWholesalePrice());
        return entity;
    }

    @Override
    public FavCart_data reverse(FavCart data) {
        if (data == null) return null;
        FavCart_data entity = new FavCart_data();
        entity.setActive(data.isActive());
        entity.setCartId(data.getCartId());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setHot(data.isHot());
        entity.setId(data.getId());
        entity.setMainImage(data.getMainImage());
        entity.setName(data.getName());
        entity.setNameAr(data.getNameAr());
        entity.setNew(data.isNew());
        entity.setNewCode(data.getNewCode());
        entity.setOnSale(data.isOnSale());
        entity.setOutOfStock(data.isOutOfStock());
        entity.setPrice(data.getPrice());
        entity.setQuantity(data.getQuantity());
        entity.setReductionPercent(data.getReductionPercent());
        entity.setReductionPrice(data.getReductionPrice());
        entity.setWholesalePrice(data.getWholesalePrice());
        return entity;
    }
}
