package com.hotple.online.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotple.online.login.ivo.UserLoginIvo;
import com.hotple.online.login.mapper.UserLoginMapper;
import com.hotple.online.login.ovo.UserLoginOvo;


@Service
public class UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;
    
    public UserLoginOvo selectUser(UserLoginIvo ivo) {

        UserLoginOvo ovo = new UserLoginOvo();
        ovo.setLonginRslt(userLoginMapper.selectUser(ivo));

        return ovo;
    }
    
}
