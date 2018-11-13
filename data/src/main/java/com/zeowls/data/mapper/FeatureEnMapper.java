package com.zeowls.data.mapper;

import com.zeowls.data.entity.FeaturesEn_data;
import com.zeowls.domain.entity.FeaturesEn;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class FeatureEnMapper extends Mapper<FeaturesEn, FeaturesEn_data> {

    @Inject
    public FeatureEnMapper() {
    }

    @Override
    public FeaturesEn map(FeaturesEn_data data) {
        if (data == null) return null;
        FeaturesEn entity = new FeaturesEn();
        entity.setKey(data.key);
        entity.setValue(data.getValue());
        return entity;
    }

    @Override
    public FeaturesEn_data reverse(FeaturesEn featuresAr) {
        return null;
    }
}
