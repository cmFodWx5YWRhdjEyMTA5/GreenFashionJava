package com.zeowls.data.mapper;

import com.zeowls.data.entity.ProductReview_data;
import com.zeowls.data.entity.Reviews_data;
import com.zeowls.domain.entity.ProductReview;
import com.zeowls.domain.entity.Reviews;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class ProductReviewMapper extends Mapper<ProductReview, ProductReview_data> {

    private Mapper<Reviews, Reviews_data> reviewsMapper;

    @Inject
    public ProductReviewMapper(Mapper<Reviews, Reviews_data> reviewsMapper) {
        this.reviewsMapper = reviewsMapper;
    }


    @Override
    public ProductReview map(ProductReview_data data) {
        if (data == null) return null;
        ProductReview entity = new ProductReview();
        entity.setError(data.isError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setReviews(reviewsMapper.map(data.getReviews()));
        return entity;
    }

    @Override
    public ProductReview_data reverse(ProductReview products) {
        return null;
    }
}
