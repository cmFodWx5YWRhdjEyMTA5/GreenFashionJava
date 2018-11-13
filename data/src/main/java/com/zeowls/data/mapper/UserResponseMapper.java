package com.zeowls.data.mapper;

import com.zeowls.data.entity.UserResponse_data;
import com.zeowls.data.entity.User_data;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class UserResponseMapper extends Mapper<UserResponse, UserResponse_data> {
    private Mapper<User, User_data> userMapper;

    @Inject
    public UserResponseMapper(Mapper<User, User_data> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse map(UserResponse_data data) {
        if (data == null) return null;
        UserResponse entity = new UserResponse();
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setMessage_code(data.getMessage_code());
        entity.setResponse(data.getResponse());
        entity.setUser(userMapper.map(data.getUser()));
        return entity;
    }

    @Override
    public UserResponse_data reverse(UserResponse data) {
        UserResponse_data entity = new UserResponse_data();
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setMessage_code(data.getMessage_code());
        entity.setResponse(data.getResponse());
        entity.setUser(userMapper.reverse(data.getUser()));
        return entity;
    }
}
