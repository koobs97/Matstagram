package com.hotple.online.login.dto;

import lombok.Data;

@Data
public class SearchPwDto {
    private String passwdHint;
    private String passwdHintAnswer;
    private String authYn;
}
