package com.zeowls.data.mapper;

import com.zeowls.data.entity.FacebookLogin_data;
import com.zeowls.domain.entity.FacebookLogin;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class FacebookLoginMapper extends Mapper<FacebookLogin, FacebookLogin_data> {
    @Inject
    public FacebookLoginMapper() {
    }

    @Override
    public FacebookLogin map(FacebookLogin_data data) {
        if (data == null) return null;
        FacebookLogin entity = new FacebookLogin();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setFbId(data.getFbId());
        entity.setFbToken(data.getFbToken());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPicture(data.getPicture());
        return entity;
    }

    @Override
    public FacebookLogin_data reverse(FacebookLogin data) {
        if (data == null) return null;
        FacebookLogin_data entity = new FacebookLogin_data();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setFbId(data.getFbId());
        entity.setFbToken(data.getFbToken());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPicture(data.getPicture());
        return entity;
    }
}
