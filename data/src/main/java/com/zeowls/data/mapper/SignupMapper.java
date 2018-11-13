package com.zeowls.data.mapper;

import com.zeowls.data.entity.Signup_data;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class SignupMapper extends Mapper<Signup, Signup_data> {
    @Inject
    public SignupMapper() {
    }

    @Override
    public Signup map(Signup_data data) {
        if (data == null) return null;
        Signup entity = new Signup();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPassword(data.getPassword());
        return entity;
    }

    @Override
    public Signup_data reverse(Signup data) {
        if (data == null) return null;
        Signup_data entity = new Signup_data();
        entity.setEmail(data.getEmail());
        entity.setBirthday(data.getBirthday());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setGender(data.getGender());
        entity.setPassword(data.getPassword());
        return entity;
    }
}
