package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.source.local.database.Cart;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class CartMapper extends Mapper<Product, Cart> {
    private Context context;
    @Inject
    CartMapper(Context context) {
        this.context = context;
    }

    public Product map(Cart data) {
        if (data == null) return null;
        Product product = new Product();
        product.setId(data.getId());
        product.setCartQuantity(data.getCartQuantity());
        product.setActive(data.isActive());
        product.setCategory(data.getCategory());
        product.setCode(data.getCode());
        product.setDateAdd(data.getDateAdd());
        product.setDateUpd(data.getDateUpd());
        product.setHot(data.isHot());
        product.setNew(data.isNew());
        product.setMainImage(data.getMainImage());
        product.setNewCode(data.getNewCode());
        if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
            product.setName(data.getName());
        else
            product.setName(data.getNameAr());
        product.setNameAr(data.getNameAr());
        product.setOnSale(data.isOnSale());
        product.setOutOfStock(data.isOutOfStock());
        product.setPrice(data.getPrice());
        product.setReductionPrice(data.getReductionPrice());
        product.setWholesalePrice(data.getWholesalePrice());
        product.setQuantity(data.getQuantity());
        return product;
    }

    public Cart reverse(Product data) {
        if (data == null) return null;
        Cart cart = new Cart();
        cart.setId(data.getId());
        cart.setCartQuantity(data.getCartQuantity());
        cart.setActive(data.isActive());
        cart.setCategory(data.getCategory());
        cart.setCode(data.getCode());
        cart.setDateAdd(data.getDateAdd());
        cart.setDateUpd(data.getDateUpd());
        cart.setHot(data.isHot());
        cart.setNew(data.isNew());
        cart.setMainImage(data.getMainImage());
        cart.setNewCode(data.getNewCode());
        cart.setName(data.getName());
        cart.setNameAr(data.getNameAr());
        cart.setOnSale(data.isOnSale());
        cart.setOutOfStock(data.isOutOfStock());
        cart.setPrice(data.getPrice());
        cart.setReductionPrice(data.getReductionPrice());
        cart.setWholesalePrice(data.getWholesalePrice());
        cart.setQuantity(data.getQuantity());
        return cart;
    }
}
