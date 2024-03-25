package com.hotple.online.login.ivo;

import lombok.Data;

@Data
public class UserJoinIvo {
    private String userId;
    private String userName;
    private String userPasswd;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String genderCode;
    private String useYn;
    private String lockedYn;
    private String passwdChgDt;
    private String termsAgreYn;
    private String passwdHint;
    private String passwdHintAnswer;
}
