package com.zeowls.data.mapper;

import com.zeowls.data.entity.FeaturesAr_data;
import com.zeowls.domain.entity.FeaturesAr;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class FeatureArMapper extends Mapper<FeaturesAr, FeaturesAr_data> {

    @Inject
    public FeatureArMapper() {
    }

    @Override
    public FeaturesAr map(FeaturesAr_data data) {
        if (data == null) return null;
        FeaturesAr entity = new FeaturesAr();
        entity.setKey(data.key);
        entity.setValue(data.getValue());
        return entity;
    }

    @Override
    public FeaturesAr_data reverse(FeaturesAr featuresAr) {
        return null;
    }
}
