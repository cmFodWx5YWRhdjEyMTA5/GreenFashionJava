package com.zeowls.data.mapper;

import com.zeowls.data.entity.UserRequest_data;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class UserRequestMapper extends Mapper<UserRequest, UserRequest_data> {
    @Inject
    public UserRequestMapper() {
    }

    @Override
    public UserRequest map(UserRequest_data data) {
        if (data == null) return null;
        UserRequest entity = new UserRequest();
        entity.setEmail(data.getEmail());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setOldPassword(data.getOldPassword());
        entity.setPassword(data.getPassword());
        entity.setUserId(data.getUserId());
        return entity;
    }

    @Override
    public UserRequest_data reverse(UserRequest data) {
        if (data == null) return null;
        UserRequest_data entity = new UserRequest_data();
        entity.setEmail(data.getEmail());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setOldPassword(data.getOldPassword());
        entity.setPassword(data.getPassword());
        entity.setUserId(data.getUserId());
        return entity;
    }
}
