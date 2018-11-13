package com.zeowls.data.mapper;

import com.zeowls.data.entity.Review_data;
import com.zeowls.data.entity.User_data;
import com.zeowls.domain.entity.Review;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ReviewMapper extends Mapper<Review, Review_data> {
    private Mapper<User, User_data> userMapper;

    @Inject
    public ReviewMapper(Mapper<User, User_data> userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public Review map(Review_data data) {
        if (data == null) return null;
        Review entity = new Review();
        entity.setActive(data.isActive());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setProduct(data.getProduct());
        entity.setProductImage(data.getProductImage());
        entity.setProductName(data.getProductName());
        entity.setProductNameAr(data.getProductNameAr());
        entity.setRate(data.getRate());
        entity.setReview(data.getReview());
        entity.setUser(userMapper.map(data.getUser()));
        return entity;
    }

    @Override
    public Review_data reverse(Review review) {
        return null;
    }
}
