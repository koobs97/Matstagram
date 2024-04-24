<!--
 * [아이디 찾기 화면]
 * 이메일 인증으로 
 * 
 * @Class   SearchUserPw.vue
 * @Author  Koo Bon Sang
 * @Date    2024.03.29
 * @Version 1.1.0
 * 
-->

<template>

    <!-- 전체 form -->
    <el-form label-width="120px">
        <el-form-item>
            <br><br>
        </el-form-item>

        <div class="container_header">
            <el-button color="#4527A0" class="top_button" :plain="plainId">아이디 찾기</el-button>
            <el-button color="#4527A0" class="top_button" :plain="plainPasswd" @click="onClickToSearchPw">비밀번호 찾기</el-button>
        </div>

        <!-- container 영역 -->
        <div class="container">
            <el-card shadow="never" style="height: 80%; width: 40%;">

                <el-form label-width="120px" label-position="left">
                    <el-row :gutter="0">

                        <el-col :span="24" style="text-align: left;">
                            <el-text v-model="AuthType[0]" style="font-size: 16px; font-weight: bold;">
                                <el-icon style="font-size: 13px;"><Message /></el-icon>
                                이메일 인증
                            </el-text>
                            <el-divider style="margin-top: 7px; margin-bottom: 7px;" />
                            <p style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;계정의 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.
                            </p>
                        </el-col>

                        <el-col :span="12" style="text-align: left; height: 40px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사용자명">
                                <el-input ref="userName" v-model="state.ivo.userName" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="12"/>

                        <el-col :span="12" style="text-align: left; height: 40px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이메일주소">
                                <el-input ref="email" v-model="state.ivo.email" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align: left; height: 40px; padding-left: 5px;">
                            <el-button type="info" plain :icon="Message" @click="onClickSendMail">인증번호 보내기</el-button>
                        </el-col>
                        <el-col :span="6"/>

                        <el-col :span="12" style="text-align: left; height: 20px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;인증번호">
                                <el-input v-model="state.ivo.authCode" :disabled="state.isDisabledCertificat" ref="authCode" placeholder="인증번호 6자리" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6" style="text-align: left; height: 40px; padding-left: 5px; padding-top: 5px;">
                            <el-text class="mx-1" style="text-align: left; font-weight: bold; font-size: 12px; color: crimson;">
                                <el-icon style="margin-right: 1px;"><Timer /></el-icon>
                                {{ state.time }}</el-text>
                        </el-col>
                        <el-col :span="6"/>

                        <el-col :span="4"/>
                        <el-col :span="6" style="text-align: left; height: 20px; margin-top: 1px;" >
                            <el-text type="info" style="font-size: 13px;">혹시 이메일이 오지 않는다면?</el-text>
                        </el-col>
                        <el-col :span="12" style="text-align: left; margin-bottom: 7px;">
                            <el-popover placement="right" :width="380" trigger="hover">
                                <template #reference>
                                    <el-button style="height: 8px; width: 8px; padding: 0px;" :icon="QuestionFilled"></el-button>
                                </template>
                                <div>
                                    <el-col :span="24" style="margin-bottom: 4px; margin-top: 4px;">
                                        <el-text style="font-weight: bold;">
                                            <el-icon style="margin-right: 1px;">
                                                <InfoFilled />
                                            </el-icon>
                                            확인해주세요
                                        </el-text>
                                    </el-col>
                                    <el-divider style="margin-bottom: 0px; margin-top: 0px;"></el-divider>
                                    <el-col :span="24">
                                        <el-tag style="margin-bottom: 4px; margin-top: 4px; width: 355px; justify-content: left; color: #4527A0;">1. 입력한 이메일 주소가 맞는지 확인해주세요.</el-tag>
                                        <el-tag style="margin-bottom: 4px; width: 355px; justify-content: left; color: #4527A0;">2. koobs970729@gmail.com의 차단여부를 확인해주세요.</el-tag>
                                        <el-tag style="margin-bottom: 4px; width: 355px; justify-content: left; color: #4527A0;">3. 인터넷 연결이 원활한지 확인해주세요.</el-tag>
                                    </el-col>
                                    <el-divider style="margin-bottom: 0px; margin-top: 0px;"></el-divider>
                                    <el-tag style="margin-bottom: 4px; margin-top: 4px; width: 355px; justify-content: left; background-color: #F5F5F5; color: #212121;">확인 후 다시 한 번 '인증번호 받기'를 눌러주세요.</el-tag>
                                </div>
                            </el-popover>
                        </el-col>

                    </el-row>
                </el-form>

            </el-card>
        </div>
        <!-- container 영역 -->

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button :icon="ArrowLeft" @click="onClickToLogin">이전</el-button>
            <el-button @click="onClickChkAuth">다음<el-icon class="el-icon--right"><ArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
    <!-- 전체 form -->

</template>
<script lang="ts" setup>
import { ArrowLeft, ArrowRight, Message, QuestionFilled, Timer, InfoFilled } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { onMounted, reactive, ref, watch } from 'vue';
import { onBeforeRouteLeave, useRouter } from 'vue-router';
import { Api } from '../common/common';
import { userStore } from '../store/userStore';
import { SearchUserId } from '../vo/ivo/SearchUserId';

const router        = useRouter()   // router
const userStoreObj  = userStore()   // 유저스토어

const plainId       = ref(false)    // 찾기 구분 선택
const plainPasswd   = ref(true)     // 찾기 구분 선택

const userName  = ref()             // focus
const email     = ref()             // focus
const authCode  = ref()             // focus
const AuthType  = ref(['1', '2'])   // 인증타입

/* state reactive */
const state = reactive({
    ivo: new SearchUserId(),    // 화면 ivo
    ivo2: new SearchUserId(),   // 인증완료 시 store에 넘겨줄 ivo
    time: '00:00',              // 인증 타이머
    isDisabledCertificat: true, // 인증번호 입력 활성화여부
})

/* 화면진입 시 */
onMounted(() => {
    userName.value.focus()
})

/* 뒤로가기/앞으로가기 시 실행할 작업 */
onBeforeRouteLeave(async(to, from, next) => {

    /* 인증 전에 페이지 이탈 시 초기화 */
    if(state.isDisabledCertificat == false) {
        state.isDisabledCertificat = true
        state.time = '00:00'
        state.ivo.authCode = ''
        state.ivo.timeEndYn = 'Y'

        await Api.post("/api/search/chkAuthCode", state.ivo)
    }
    
    next(); // 다음 단계로 진행
})

/* 인증시간 세팅 */
const onClickSendMail = async () => {

    if(state.ivo.userName.trim() == '') {
        ElMessage({
            type: 'error',
            message: '사용자명을 입력하세요.',
        })
        userName.value.focus()
        return
    }
    else if(state.ivo.email.trim() == '') {
        ElMessage({
            type: 'error',
            message: '이메일을 입력하세요.',
        })
        email.value.focus()
        return
    }

    /* 이메일 인증번호 발송 */
    const retData = await Api.post("/api/search/searchId", state.ivo, true)
    
    if(retData.data.chkUserEmail != '1') {
        ElMessage({
            type: 'error',
            message: '사용자명 및 이메일주소를 확인해주세요.',
        })
    }
    else if(retData.data.chkUserEmail == '1') {

        state.ivo2.userName = state.ivo.userName
        state.ivo2.email = state.ivo.email

        ElMessage({
            type: 'success',
            message: '인증번호가 발송되었습니다.',
        })
        authCode.value.focus()

        /* 인증시간 계산 */
        state.isDisabledCertificat = false
        let minutes = 3
        let seconds = 60
        for (let i = 0; i < 181; i++) {
            setTimeout(()=> {

                if(i == 0) {
                    minutes = minutes - 1
                }

                if(seconds == 60) {
                    seconds = seconds - 1
                }
                state.time = '0' + minutes + ':' + seconds.toString().padStart(2, '0')

                if(seconds == 0) {
                    seconds = 60
                    minutes = minutes - 1
                }
                else {
                    seconds = seconds - 1
                }
                if(i == 180 && state.isDisabledCertificat == false) {
                    state.isDisabledCertificat = true
                    state.time = '00:00'
                    state.ivo.authCode = ''
                    state.ivo.timeEndYn = 'Y'

                    /* 인증결과 등록 */
                    Api.post("/api/search/chkAuthCode", state.ivo)

                    ElMessage({
                        type: 'error',
                        message: '인증시간이 초과되었습니다.',
                    })
                    return
                }

            }, i * 1000)
        }
    }

    /* 이메일 인증번호 입력 시간 계산 */
    watch(
        () => state.time,
        () => {
            state.time = state.time + ''
        }
    )
}

/* 인증하기 */
const onClickChkAuth = async () => {

    if(state.isDisabledCertificat == true) {
        ElMessage({
            type: 'error',
            message: '인증번호를 전송 후 진행해주세요.',
        })
        return
    }

    /* 인증번호 전송 */
    const retData = await Api.post("/api/search/chkAuthCode", state.ivo, true)

    /* 인증 완료 시 */
    if(retData.data.certificatRslt == 'Y') {

        /* 인증시계 초기화 */
        state.isDisabledCertificat = true
        state.time = '00:00'
        state.ivo.authCode = ''
        state.ivo.timeEndYn = 'Y'

        /* 유저인증 세팅 */
        userStoreObj.authentication(state.ivo2.userName, state.ivo2.email, true)
        router.push('/login/showId')
    }
    else {
        ElMessage({
            type: 'error',
            message: '인증번호를 확인해주세요.',
        })
        return
    }

}

/* 비밀번호 찾기 페이지로 이동 */
const onClickToSearchPw = () => {
    router.push('/login/pwSearch')
}

/* 로그인 페이지로 이동 */
const onClickToLogin = () => {
    router.push('/')
}

</script>

<style scoped>

.container_header {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 2px;
}
.container {
    display: flex;
    justify-content: center;
    align-items: center;
}

.top_button {
    color: "#626aef";
    height: 40px;
    width: 20%;
    margin-right: 1px; 
    margin-left: 1px;
}

.el-button+.el-button {
    margin-left: 1px;
}

.text-container {
    position: fixed;
    bottom: 2%;
    left: 0;
    right: 0;
    text-align: center;
}

/* 버튼 색상 */
.el-button {
    --el-button-font-weight: var(--el-font-weight-primary);
    --el-button-border-color: var(--el-border-color);
    --el-button-bg-color: var(--el-fill-color-blank);
    --el-button-text-color: var(--el-text-color-regular);
    --el-button-disabled-text-color: var(--el-disabled-text-color);
    --el-button-disabled-bg-color: var(--el-fill-color-blank);
    --el-button-disabled-border-color: var(--el-border-color-light);
    --el-button-divide-border-color: rgba(255, 255, 255, 0.5);
    --el-button-hover-text-color: #4527A0;                              /* 마우스 올렸을 때 글씨 색 */
    --el-button-hover-bg-color: #EDE7F6;                                /* 마우스 올렸을 때 배경 색 */
    --el-button-hover-border-color: #7C4DFF;                            /* 마우스 올렸을 때 테두리 색 */
    --el-button-active-text-color: var(--el-button-hover-text-color);
    --el-button-active-border-color: #9575CD;
    --el-button-active-bg-color: var(--el-button-hover-bg-color);
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-hover-link-text-color: var(--el-color-info);
    --el-button-active-color: var(--el-text-color-primary);
}

/* input 색상 조정 */
.el-input {
    --el-input-text-color: var(--el-text-color-regular);
    --el-input-border: var(--el-border);
    --el-input-hover-border: var(--el-border-color-hover);
    --el-input-focus-border: var(--el-color-primary);
    --el-input-transparent-border: 0 0 0 1px transparent inset;
    --el-input-border-color: var(--el-border-color);
    --el-input-border-radius: var(--el-border-radius-base);
    --el-input-bg-color: var(--el-fill-color-blank);
    --el-input-icon-color: var(--el-text-color-placeholder);
    --el-input-placeholder-color: var(--el-text-color-placeholder);
    --el-input-hover-border-color: var(--el-border-color-hover);
    --el-input-clear-hover-color: var(--el-text-color-secondary);
    --el-input-focus-border-color: #9575CD;
    --el-input-width: 100%;
}

</style>
