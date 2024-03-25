package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.ivo.UserLoginIvo;
import com.hotple.online.login.ovo.UserLoginOvo;
import com.hotple.online.login.service.UserLoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserLoginService svc;
 
    @PostMapping("/login")
    public UserLoginOvo selectUser(@RequestBody UserLoginIvo ivo) {
        
        UserLoginOvo ovo = svc.selectUser(ivo);

        return ovo;
    }

}
