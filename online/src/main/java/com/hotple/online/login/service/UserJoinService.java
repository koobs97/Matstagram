package com.hotple.online.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotple.online.login.ivo.UserJoinIvo;
import com.hotple.online.login.mapper.UserJoinMapper;
import com.hotple.online.login.ovo.UserJoinOvo;

@Service
public class UserJoinService {

    @Autowired
    UserJoinMapper userJoinMapper;
    
    /**
     * ID 중복 확인
     * @param ivo
     * @return UserJoinOvo
     */
    public UserJoinOvo chkUserIdDupYn(UserJoinIvo ivo) {
        UserJoinOvo ovo = new UserJoinOvo();
        ovo.setIdDupYn(userJoinMapper.selectUserId(ivo));
        return ovo;
    }

    /**
     * 회원가입
     * @param ivo
     * @return UserJoinOvo
     */
    public UserJoinOvo joinUser(UserJoinIvo ivo) {
        UserJoinOvo ovo = new UserJoinOvo();
        try {

            ovo.setUserDupYn(userJoinMapper.selectChkUser(ivo));

            if(ovo.getUserDupYn().equals("Y")) {
                return ovo;
            }
            else {
                ovo.setJoinResult(userJoinMapper.joinUser(ivo));
            }
            
        } catch (Exception e) {

            ovo.setJoinResult(-1);

            e.printStackTrace();
            throw(e);
        }
        return ovo;
    }
}
