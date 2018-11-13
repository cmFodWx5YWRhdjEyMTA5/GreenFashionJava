package com.zeowls.data.mapper;

import com.zeowls.data.entity.Review_data;
import com.zeowls.data.entity.Reviews_data;
import com.zeowls.domain.entity.Review;
import com.zeowls.domain.entity.Reviews;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class ReviewsMapper extends Mapper<Reviews, Reviews_data> {

    private Mapper<Review, Review_data> reviewMapper;


    @Inject
    public ReviewsMapper(Mapper<Review, Review_data> reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public Reviews map(Reviews_data data) {
        if (data == null) return null;
        Reviews entity = new Reviews();
        entity.setRate(data.getRate());
        entity.setReviews(reviewMapper.map(data.getReviews()));
        entity.setReviewsCount(data.getReviewsCount());
        return entity;
    }

    @Override
    public Reviews_data reverse(Reviews reviews) {
        return null;
    }
}
