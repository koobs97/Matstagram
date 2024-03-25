package com.hotple.online.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hotple.online.login.ivo.UserLoginIvo;

@Mapper
public interface UserLoginMapper {
    public String selectUser(UserLoginIvo ivo);
}
