package com.zeowls.data.mapper;

import com.zeowls.data.entity.Login_data;
import com.zeowls.domain.entity.Login;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class LoginMapper extends Mapper<Login, Login_data> {
    @Inject
    public LoginMapper() {
    }

    @Override
    public Login map(Login_data data) {
        if (data == null) return null;
        Login entity = new Login();
        entity.setEmail(data.getEmail());
        entity.setPassword(data.getPassword());
        return entity;
    }

    @Override
    public Login_data reverse(Login data) {
        if (data == null) return null;
        Login_data entity = new Login_data();
        entity.setEmail(data.getEmail());
        entity.setPassword(data.getPassword());
        return entity;
    }
}
