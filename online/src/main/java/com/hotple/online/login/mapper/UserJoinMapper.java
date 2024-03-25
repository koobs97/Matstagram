package com.hotple.online.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hotple.online.login.ivo.UserJoinIvo;

@Mapper
public interface UserJoinMapper {
    public String selectUserId(UserJoinIvo ivo);
    public String selectChkUser(UserJoinIvo ivo);
    public int joinUser(UserJoinIvo ivo);
}
