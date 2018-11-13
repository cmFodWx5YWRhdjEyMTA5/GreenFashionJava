package com.zeowls.data.mapper;

import com.zeowls.data.entity.User_data;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;


@ApplicationScope
public class UserMapper extends Mapper<User, User_data> {


    @Inject
    public UserMapper() {
    }

    @Override
    public User map(User_data data) {
        if (data == null) return null;
        User entity = new User();
        entity.setActive(data.isActive());
        entity.setBirthday(data.getBirthday());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setEmail(data.getEmail());
        entity.setFavLan(data.getFavLan());
        entity.setFbToken(data.getFbToken());
        entity.setFirstName(data.getFirstName());
        entity.setGender(data.getGender());
        entity.setGoogleId(data.getGoogleId());
        entity.setId(data.getId());
        entity.setLastName(data.getLastName());
        entity.setLatitude(data.getLatitude());
        entity.setLongitude(data.getLongitude());
        entity.setProfilePic(data.getProfilePic());
        return entity;
    }

    @Override
    public User_data reverse(User data) {
        return null;
    }
}
