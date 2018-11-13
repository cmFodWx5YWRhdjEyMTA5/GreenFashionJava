package com.zeowls.data.mapper;

import com.zeowls.data.entity.DeviceToken_data;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class TokenMapper extends Mapper<DeviceToken, DeviceToken_data> {


    @Inject
    public TokenMapper() {
    }

    @Override
    public DeviceToken map(DeviceToken_data data) {
        if (data == null) return null;
        DeviceToken entity = new DeviceToken();
        entity.setDeviceToken(data.getDeviceToken());
        entity.setUserId(data.getUserId());
        return entity;
    }

    @Override
    public DeviceToken_data reverse(DeviceToken data) {
        if (data == null) return null;
        DeviceToken_data entity = new DeviceToken_data();
        entity.setDeviceToken(data.getDeviceToken());
        entity.setUserId(data.getUserId());
        return entity;
    }
}
