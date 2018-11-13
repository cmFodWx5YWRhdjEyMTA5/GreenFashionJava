package com.zeowls.domain.interactor;

import com.zeowls.domain.entity.ProductReview;
import com.zeowls.domain.executor.BaseSchedulerProvider;
import com.zeowls.domain.repository.Repository;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ApplicationScope
public class ProductReview_interactor extends SingleInteractor<ProductReview> {

    private Repository repository;

    @Inject
    public ProductReview_interactor(BaseSchedulerProvider schedulerProvider,
                                    Repository repository) {
        super(schedulerProvider);
        this.repository = repository;
    }

    @Override
    public Single<ProductReview> singleUseCase(int id) {
        return repository.getProductReviews(id);
    }
}
