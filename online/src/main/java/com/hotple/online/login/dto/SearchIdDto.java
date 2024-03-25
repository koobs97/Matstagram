package com.hotple.online.login.dto;

import lombok.Data;

@Data
public class SearchIdDto {
    private String seq;
    private String email;
    private String authCode;
    private String authGenTime;
    private String authYn;
}
