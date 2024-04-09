package com.hotple.online.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hotple.online.login.dto.SearchIdDto;
import com.hotple.online.login.dto.SearchPwDto;
import com.hotple.online.login.dto.ShowIdDto;
import com.hotple.online.login.ivo.SearchIdIvo;
import com.hotple.online.login.ivo.SearchPwIvo;

@Mapper
public interface UserSearchMapper {
    public String chkUserEmail(SearchIdIvo ivo);            // 이메일 정합성 체크
    public SearchIdDto chkEmailAuth(SearchIdIvo ivo);       // 이메일 인증번호 체크
    public int saveAuthCode(SearchIdIvo ivo);               // 인증번호 저장
    public int updateAuthYn(SearchIdIvo ivo);               // 인증여부 업데이트
    public ShowIdDto showUserId(SearchIdIvo ivo);           // 유저ID 조회

    public SearchPwDto searchUserPw(SearchPwIvo ivo);       // 유저 힌트 조회
}
