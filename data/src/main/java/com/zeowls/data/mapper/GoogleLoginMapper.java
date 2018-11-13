package com.zeowls.data.mapper;

import com.zeowls.data.entity.GoogleLogin_data;
import com.zeowls.domain.entity.GoogleLogin;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class GoogleLoginMapper extends Mapper<GoogleLogin, GoogleLogin_data> {
    @Inject
    public GoogleLoginMapper() {
    }

    @Override
    public GoogleLogin map(GoogleLogin_data data) {
        if (data == null) return null;
        GoogleLogin entity = new GoogleLogin();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setGoogleId(data.getGoogleId());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPicture(data.getPicture());
        return entity;
    }

    @Override
    public GoogleLogin_data reverse(GoogleLogin data) {
        if (data == null) return null;
        GoogleLogin_data entity = new GoogleLogin_data();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setGoogleId(data.getGoogleId());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPicture(data.getPicture());
        return entity;
    }
}
