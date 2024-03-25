package com.hotple.online.login.ivo;

import lombok.Data;

@Data
public class SearchIdIvo {
    private String userName;
    private String email;
    private String authCode;
    private String authYn;
    private String timeEndYn;
}
