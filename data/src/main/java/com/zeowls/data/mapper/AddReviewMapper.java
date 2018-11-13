package com.zeowls.data.mapper;

import com.zeowls.data.entity.AddReview_data;
import com.zeowls.domain.entity.AddReview;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class AddReviewMapper extends Mapper<AddReview, AddReview_data> {

    @Inject
    public AddReviewMapper() {
    }

    @Override
    public AddReview map(AddReview_data data) {
        if (data == null) return null;
        AddReview entity = new AddReview();
        entity.setItemId(data.getItemId());
        entity.setRate(data.getRate());
        entity.setReview(data.getReview());
        entity.setUserId(data.getUserId());
        return entity;
    }

    @Override
    public AddReview_data reverse(AddReview data) {
        if (data == null) return null;
        AddReview_data entity = new AddReview_data();
        entity.setItemId(data.getItemId());
        entity.setRate(data.getRate());
        entity.setReview(data.getReview());
        entity.setUserId(data.getUserId());
        return entity;
    }
}
