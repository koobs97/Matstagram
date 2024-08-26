package com.hotple.online.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotple.online.apis.ivo.NaverOpenApiIvo;
import com.hotple.online.apis.service.NaverOpenApiService;

@RestController
@RequestMapping("/api/naver")
public class NaverOpenApi {

    @Autowired
    NaverOpenApiService svc;

    @PostMapping("/blog")
    public Object searchBlog(@RequestBody NaverOpenApiIvo ivo) {
        System.out.println(ivo.toString());
        return svc.searchBlog(ivo);
    }

}
