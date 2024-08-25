package com.hotple.online.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.login.service.NaverOpenApiService;

@RestController
@RequestMapping("/api/naver")
public class NaverOpenApi {

    @Autowired
    NaverOpenApiService svc;

    @PostMapping("/blog")
    public Object searchBlog(@RequestBody String weather) {
        
        return svc.searchBlog(weather);
    }

}
