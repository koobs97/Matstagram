package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.ivo.UserJoinIvo;
import com.hotple.online.login.ovo.UserJoinOvo;
import com.hotple.online.login.service.UserJoinService;

@RestController
@RequestMapping("/api/join")
public class UserJoinController {

    @Autowired
    UserJoinService svc;
 
    @PostMapping("/chkUserId")
    public UserJoinOvo chkUserId(@RequestBody UserJoinIvo ivo) {

        UserJoinOvo ovo = svc.chkUserIdDupYn(ivo);

        return ovo;
    }

    @PostMapping("/joinUser")
    public UserJoinOvo joinUser(@RequestBody UserJoinIvo ivo) {

        UserJoinOvo ovo = svc.joinUser(ivo);

        return ovo;
    }

}
