package com.hotple.online.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotple.online.login.dto.UserLoginDto;
import com.hotple.online.login.ivo.UserLoginIvo;
import com.hotple.online.login.mapper.UserLoginMapper;
import com.hotple.online.login.ovo.UserLoginOvo;


@Service
public class UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;
    
    public UserLoginDto selectUser(UserLoginIvo ivo) {

        UserLoginOvo ovo = new UserLoginOvo();
        UserLoginDto dto = new UserLoginDto();

        ovo.setLonginRslt(userLoginMapper.selectUser(ivo));

        if(ovo.getLonginRslt().equals("1")) {
            dto = userLoginMapper.selectUserInfo(ivo);
        }

        return dto;
    }
    
}
