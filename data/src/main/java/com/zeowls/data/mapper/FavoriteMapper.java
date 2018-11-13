package com.zeowls.data.mapper;

import android.content.Context;

import com.zeowls.data.LocaleManager;
import com.zeowls.data.source.local.database.Favorite;
import com.zeowls.domain.entity.Product;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class FavoriteMapper extends Mapper<Product, Favorite> {
    private Context context;
    @Inject
    public FavoriteMapper(Context context) {
        this.context = context;
    }

    public Product map(Favorite data) {
        if (data == null) {
            return null;
        }
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
        product.setName(data.getName());
        product.setNameAr(data.getNameAr());
        product.setOnSale(data.isOnSale());
        product.setOutOfStock(data.isOutOfStock());
        product.setPrice(data.getPrice());
        product.setReductionPrice(data.getReductionPrice());
        product.setWholesalePrice(data.getWholesalePrice());
        product.setQuantity(data.getQuantity());
        return product;
    }

    public Favorite reverse(Product data) {
        if (data == null) return null;
        Favorite product = new Favorite();
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
        product.setName(data.getName());
        product.setNameAr(data.getNameAr());
        product.setOnSale(data.isOnSale());
        product.setOutOfStock(data.isOutOfStock());
        product.setPrice(data.getPrice());
        product.setReductionPrice(data.getReductionPrice());
        product.setWholesalePrice(data.getWholesalePrice());
        product.setQuantity(data.getQuantity());
        return product;
    }
}
