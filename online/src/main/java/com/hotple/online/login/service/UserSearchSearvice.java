package com.hotple.online.login.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotple.online.common.service.MailService;
import com.hotple.online.login.dto.SearchIdDto;
import com.hotple.online.login.ivo.SearchIdIvo;
import com.hotple.online.login.mapper.UserSearchMapper;
import com.hotple.online.login.ovo.SearchIdOvo;
import com.hotple.online.login.ovo.ShowIdOvo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserSearchSearvice {

    @Autowired
    UserSearchMapper userSearchMapper;

    private final MailService mailService;

    /**
     * 이메일 인증번호 발송
     * @param ivo
     * @return SearchIdOvo
     */
    public SearchIdOvo searchId(SearchIdIvo ivo) {

        SearchIdOvo ovo = new SearchIdOvo();

        // 사용자명/이메일 검증
        ovo.setChkUserEmail(userSearchMapper.chkUserEmail(ivo));

        if(ovo.getChkUserEmail().equals("1")) {

            String title = "이메일 인증 번호";      // 메일 제목
            String authCode = createCode();        // 인증코드 6자리

            // 메일 내용
            StringBuilder appendText = new StringBuilder();
            appendText.append("인증번호 : " + authCode + "\n");
            appendText.append("안녕하세요. " + ivo.getUserName() + "님\n");
            appendText.append("계정 본인 확인용 이메일입니다. 해당 인증코드를 입력하여 본인 소유임을 인증하여 주시기 바랍니다. \n");
            appendText.append("해당 인증번호는 3분 뒤에 만료됩니다. 3분 내로 입력하여 주시기 바랍니다. \n");
            appendText.append("감사합니다! \n");

            mailService.sendEmail(ivo.getEmail(), title, appendText.toString());

            // 인증코드 저장
            ivo.setAuthCode(authCode);
            userSearchMapper.saveAuthCode(ivo);
            
        }
        else {
            return ovo;
        }

        return ovo;
    }

    /**
     * 이메일 인증번호 체크
     * @param ivo
     * @return SearchIdOvo
     */
    public SearchIdOvo chkAuthCode(SearchIdIvo ivo) {

        SearchIdOvo ovo = new SearchIdOvo();
        SearchIdDto dto = new SearchIdDto();

        // 시간이 끝나면 인증 종료
        if(ivo.getTimeEndYn().equals("Y")) {
            ivo.setAuthYn("N");
            userSearchMapper.updateAuthYn(ivo);

            return ovo;
        }
        else {
            dto = userSearchMapper.chkEmailAuth(ivo);

            // 인증코드 인증 시
            if(dto.getAuthCode().equals(ivo.getAuthCode())) {
                ivo.setAuthYn("Y");
                userSearchMapper.updateAuthYn(ivo);
                ovo.setCertificatRslt("Y");
            }
            else {
                ovo.setCertificatRslt("N");
            }

            return ovo;
        }
        
    }

    /**
     * 사용자 id 조회
     * @param ivo
     * @return ShowIdOvo
     */
    public ShowIdOvo showUserId(SearchIdIvo ivo) {

        ShowIdOvo ovo = new ShowIdOvo();
        ovo.setResult(userSearchMapper.showUserId(ivo));

        return ovo;
    }


    /**
     * 이메일 인증문자열 6자리 생성
     * @return code
     */
    private String createCode() {

        // 랜덤한 숫자와 영문을 생성할 문자열
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // 랜덤 객체 생성
        Random random = new Random();

        // 6자리 랜덤 코드 생성
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // 문자열에서 랜덤한 인덱스를 선택하여 해당 문자를 코드에 추가
            int randomIndex = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        // 생성된 랜덤 코드 출력
        String randomCode = codeBuilder.toString();

        return randomCode;
    }
    
}