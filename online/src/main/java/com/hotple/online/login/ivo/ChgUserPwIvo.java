package com.hotple.online.login.ivo;

import lombok.Data;

/**
 * 비밀번호 변경
 * 
 * @Class   ChgUserPwIvo
 * @Author  Koo Bon Sang
 * @Date    2024.04.20
 * @Version 1.1.0
 * 
 */

@Data
public class ChgUserPwIvo {
    private String userName;        // 사용자명
    private String email;           // 이메일
    private String userId;          // 아이디
    private String birthDate;       // 생년월일
    private String phoneNumber;     // 전화번호
    private String userPasswd;      // 비밀번호
}
