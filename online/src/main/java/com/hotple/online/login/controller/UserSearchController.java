package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.ivo.SearchIdIvo;
import com.hotple.online.login.ovo.SearchIdOvo;
import com.hotple.online.login.ovo.ShowIdOvo;
import com.hotple.online.login.service.UserSearchSearvice;

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

}