package com.hotple.online.login.dto;

import lombok.Data;

@Data
public class SearchPwDto {
    private String userId;
    private String userName;
    private String phoneNumber;
    private String birthDate;
    private String email;
    private String passwdHint;
    private String passwdHintAnswer;
    private String authYn;
}
