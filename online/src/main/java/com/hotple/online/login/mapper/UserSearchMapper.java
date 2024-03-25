package com.hotple.online.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hotple.online.login.dto.SearchIdDto;
import com.hotple.online.login.dto.ShowIdDto;
import com.hotple.online.login.ivo.SearchIdIvo;

@Mapper
public interface UserSearchMapper {
    public String chkUserEmail(SearchIdIvo ivo);
    public SearchIdDto chkEmailAuth(SearchIdIvo ivo);
    public int saveAuthCode(SearchIdIvo ivo);
    public int updateAuthYn(SearchIdIvo ivo);
    public ShowIdDto showUserId (SearchIdIvo ivo);
}
