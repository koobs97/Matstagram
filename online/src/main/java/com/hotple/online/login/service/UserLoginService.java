package com.hotple.online.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotple.online.login.dto.UserLoginDto;
import com.hotple.online.login.ivo.UserLoginIvo;
import com.hotple.online.login.mapper.UserLoginMapper;
import com.hotple.online.login.ovo.UserLoginOvo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;
    
    public UserLoginDto selectUser(UserLoginIvo ivo) {

        UserLoginOvo ovo = new UserLoginOvo();
        UserLoginDto dto = new UserLoginDto();

        ovo.setLonginRslt(userLoginMapper.selectUser(ivo));

        if(ovo.getLonginRslt().equals("1")) {
            dto = userLoginMapper.selectUserInfo(ivo);

            List<UserLoginDto> testList = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                UserLoginDto testRow = new UserLoginDto();
                testRow = userLoginMapper.selectUserInfo(ivo);
                testRow.setChgDttm(Integer.toString(i));
                testList.add(testRow);
            }

            log.info("================================");
            log.info("================================");
            log.info("================================");
            log.info("testList : " + testList.toString());

        }

        return dto;
    }
    
}
