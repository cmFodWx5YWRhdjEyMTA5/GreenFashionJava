package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.entity.Cart_data;
import com.zeowls.domain.entity.Cart;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class CartOrdersMapper extends Mapper<Cart, Cart_data> {
    private Context context;
    @Inject
    public CartOrdersMapper(Context context) {
        this.context = context;
    }


    @Override
    public Cart map(Cart_data data) {
        if (data == null) return null;
        Cart entity = new Cart();
        entity.setActive(data.getActive());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setIdOrder(data.getIdOrder());
        entity.setIdUser(data.getIdUser());
        entity.setIdProduct(data.getIdProduct());
        entity.setProductImage(data.getProductImage());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            entity.setProductName(data.getProductName());
        else
            entity.setProductName(data.getProductNameAr());
        entity.setProductNameAr(data.getProductNameAr());
        entity.setProductOnSale(data.getProductOnSale());
        entity.setProductOutOfStock(data.getProductOutOfStock());
        entity.setProductRate(data.getProductRate());
        entity.setProductReductionPercent(data.getProductReductionPercent());
        entity.setProductReductionPrice(data.getProductReductionPrice());
        entity.setProductWholesalePrice(data.getProductWholesalePrice());
        entity.setQuantity(data.getQuantity());
        return entity;
    }

    @Override
    public Cart_data reverse(Cart cart) {
        return null;
    }
}
