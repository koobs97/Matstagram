package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.dto.UserLoginDto;
import com.hotple.online.login.ivo.UserLoginIvo;
import com.hotple.online.login.service.UserLoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserLoginService svc;

    @PostMapping("/login")
    public UserLoginDto selectUser(@RequestBody UserLoginIvo ivo) {
        
        UserLoginDto dto = svc.selectUser(ivo);

        return dto;
    }

}
