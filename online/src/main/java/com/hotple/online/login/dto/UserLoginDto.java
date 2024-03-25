package com.hotple.online.login.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String userId;
    private String userName;
    private String userPasswd;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String genderCode;
    private String useYn;
    private String passwdChgDt;
    private String regDttm;
    private String chgDttm;
}
