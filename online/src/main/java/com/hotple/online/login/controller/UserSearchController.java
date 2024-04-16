package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.dto.SearchPwDto;
import com.hotple.online.login.ivo.SearchIdIvo;
import com.hotple.online.login.ivo.SearchPwIvo;
import com.hotple.online.login.ovo.SearchIdOvo;
import com.hotple.online.login.ovo.ShowIdOvo;
import com.hotple.online.login.service.UserSearchSearvice;

/**
 * UserSearchController 
 * 아이디/비밀번호 찾기
 * 
 * @Class   UserSearchController
 * @Author  Koo Bon Sang
 * @Date    2024.03.25
 * @Version 1.1.0
 * 
 */

@RestController
@RequestMapping("/api/search")
public class UserSearchController {

    @Autowired
    UserSearchSearvice svc;

    @PostMapping("/searchId")
    public SearchIdOvo searchId(@RequestBody SearchIdIvo ivo) {
        
        SearchIdOvo ovo = svc.searchId(ivo);

        return ovo;
    }

    @PostMapping("/chkAuthCode")
    public SearchIdOvo chkAuthCode(@RequestBody SearchIdIvo ivo) {
        
        SearchIdOvo ovo = svc.chkAuthCode(ivo);

        return ovo;
    }

    @PostMapping("/showId")
    public ShowIdOvo showId(@RequestBody SearchIdIvo ivo) {
        
        ShowIdOvo ovo = svc.showUserId(ivo);

        return ovo;
    }

    @PostMapping("/searchUserPw")
    public SearchPwDto searchUserPw(@RequestBody SearchPwIvo ivo) {
        
        SearchPwDto dto = svc.searchUserPw(ivo);

        return dto;
    }

    @PostMapping("/searchPwAnswer")
    public SearchPwDto searchPwAnswer(@RequestBody SearchPwIvo ivo) {
        
        SearchPwDto dto = svc.searchPwAnswer(ivo);

        return dto;
    }

}
