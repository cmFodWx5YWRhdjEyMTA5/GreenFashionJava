package com.zeowls.data.mapper;

import com.zeowls.data.entity.VersionValidation_data;
import com.zeowls.domain.entity.VersionValidation;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class VersionValidationMapper extends Mapper<VersionValidation, VersionValidation_data> {
    @Inject
    public VersionValidationMapper() {
    }

    @Override
    public VersionValidation map(VersionValidation_data data) {
        if (data == null) return null;
        VersionValidation entity = new VersionValidation();
        entity.setCritical(data.getCritical());
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setVersionCode(data.getVersionCode());
        entity.setVersionName(data.getVersionName());
        return entity;
    }

    @Override
    public VersionValidation_data reverse(VersionValidation data) {
        if (data == null) return null;
        VersionValidation_data entity = new VersionValidation_data();
        return entity;
    }
}
