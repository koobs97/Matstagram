<!--
 * [로그인 화면]
 * 
 * @Class   userLogin.vue
 * @Author  Koo Bon Sang
 * @Date    2024.03.29
 * @Version 1.1.0
 * 
-->

<template>
    <el-form label-width="120px">
        <el-form-item>
            <br><br><br><br>
        </el-form-item>
        <el-row :gutter="0">
            <el-col :span="24">
                <div class="container">
                    <el-card shadow="never" style="height: 80%; width: 19%;">
                        <el-form label-width="120px">
                            <el-row :gutter="0">

                                <el-col :span="24">
                                    <p class="title">Matstagram</p>
                                </el-col>
                                <el-col :span="24" style="margin-bottom: 4px; text-align: right;">
                                    <el-popover
                                        ref="popover"
                                        placement="right"
                                        :width="400"
                                        :visible="projectDetailView"
                                    >
                                        <template #reference>
                                            <el-switch v-model="projectDetailView"></el-switch>
                                        </template>
                                        <div>
                                            <el-text style="font-weight: bold;">
                                                <el-icon style="margin-right: 1px;">
                                                    <Memo />
                                                </el-icon>
                                                [개인 프로젝트]
                                            </el-text>
                                            <el-divider style="margin-bottom: 4px; margin-top: 4px;"></el-divider>
                                            <el-descriptions :column="1" size="small" border>
                                                <el-descriptions-item label-align="left" align="left">
                                                    <template #label>
                                                        <div class="cell-item">
                                                            주제
                                                        </div>
                                                    </template>
                                                    <el-text style="font-size: 12px;">개인 공부를 위한 맛집 플랫폼 웹서비스 구현</el-text>
                                                </el-descriptions-item>
                                                <el-descriptions-item label-align="left" align="left">
                                                    <template #label>
                                                        <div class="cell-item">
                                                            GitHub
                                                        </div>
                                                    </template>
                                                    <div style="display: flex;">
                                                        <div style="width: 92%;">
                                                            <el-link type="info" style="font-size: 12px;" href="https://github.com/koobs97/Matstagram" target="_blank">https://github.com/koobs97/Matstagram</el-link>
                                                        </div>
                                                        <div style="width: 8%;">
                                                            <el-button :icon="CopyDocument" style="width: 8px; height: 23px; font-size: 11px; padding-left: 12px; padding-right: 12px;" type="primary" @click="onClickCopyUrl" />
                                                        </div>
                                                    </div>
                                                </el-descriptions-item>
                                                <el-descriptions-item label-align="left" align="left">
                                                    <template #label>
                                                        <div class="cell-item">
                                                            환경
                                                        </div>
                                                    </template>
                                                    <div style="display: flex;">
                                                        <div style="width: 50%; text-align: left;">
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="Vue3" />
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="jdk 17" />
                                                        </div>
                                                        <div style="width: 50%; text-align: left;">
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="TypeScript" />
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="Spring-Boot3"/>
                                                        </div>
                                                        <div style="width: 50%; text-align: left;">
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="Oracle21"/>
                                                            <el-checkbox checked style="height: 22px; font-size: 12px;" label="MariaDb"/>
                                                        </div>
                                                    </div>
                                                </el-descriptions-item>
                                                <el-descriptions-item label-align="left" align="left">
                                                    <template #label>
                                                        <div class="cell-item">
                                                            목표
                                                        </div>
                                                    </template>
                                                    <el-tag style="margin-bottom: 4px; margin-top: 4px; padding: 0 2px; width: 310px; justify-content: left; color: #4527A0;">
                                                        <el-icon style="margin-right: 1px;"><CaretRight /></el-icon>각종 오픈소스 API 적용해보기</el-tag>
                                                    <el-col :span="4" /><el-tag style="margin-bottom: 4px; padding: 0 2px; width: 310px; justify-content: left; color: #4527A0;">
                                                        <el-icon style="margin-right: 1px;"><CaretRight /></el-icon>프론트, 백엔드, 배치 서버를 분리/병합한 소스 구현</el-tag>
                                                    <el-col :span="4" /><el-tag style="margin-bottom: 4px; padding: 0 2px; width: 310px; justify-content: left; color: #4527A0;">
                                                        <el-icon style="margin-right: 1px;"><CaretRight /></el-icon>업무에서 해보지 못하는 기능 구현</el-tag>
                                                    <el-col :span="4" /><el-tag style="margin-bottom: 4px; padding: 0 2px; width: 310px; justify-content: left; color: #4527A0;">
                                                        <el-icon style="margin-right: 1px;"><CaretRight /></el-icon>프로젝트 개발에 재미 키우기</el-tag>
                                                </el-descriptions-item>
                                            </el-descriptions>
                                            <div>
                                                <el-col :span="4" style="height: 20px; margin-top: 4px; margin-bottom: 4px;">
                                                    <el-text style="font-size: 12px; font-weight: bold;">[특이사항]</el-text>
                                                </el-col>
                                                <div style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">
                                                    <el-card shadow="never" class="custom-card">
                                                        <el-tag type="info" effect="plain" style="font-size: 12px; margin-bottom: 4px;">
                                                            {{ '이메일 인증' }}
                                                        </el-tag><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- 구글의 이메일 인증 API 사용</el-text><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- 휴대폰 인증은 유료로 개발하지 않음</el-text><br>
                                                        <el-tag type="info" effect="plain" style="font-size: 12px; margin-top: 10px; margin-bottom: 4px;">
                                                            {{ 'DB 이중화' }}
                                                        </el-tag><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- 두 가지 DB에 데이터 적재</el-text><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- 배치를 통한 데이터 이중관리</el-text><br>
                                                        <el-tag type="info" effect="plain" style="font-size: 12px; margin-top: 10px; margin-bottom: 4px;">
                                                            {{ 'UI Framework' }}
                                                        </el-tag><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- Element Plus 사용</el-text><br>
                                                        <el-text style="font-size: 12px; margin-bottom: 4px;">- 구글 색상표의 보라색 계열 참조</el-text><br>
                                                    </el-card>
                                                </div>
                                            </div>
                                        </div>
                                    </el-popover>
                                </el-col>
                                <el-col :span="24">
                                    <el-input
                                        v-model="state.ivo.userId"
                                        ref="id"
                                        @keyup.enter="onClickLogin"
                                        style="height: 40px; font-size: 15px; margin-bottom: 8px;"
                                        placeholder="사용자ID 또는 이메일"
                                    />
                                </el-col>

                                <!-- 비밀번호 감추는 영역 -->
                                <el-col :span="24" :hidden="state.isHidden.HideIcon">
                                    <el-input
                                        v-model="state.ivo.userPasswd"
                                        ref="passwd"
                                        @keyup.enter="onClickLogin"
                                        style="height: 40px; font-size: 15px; margin-bottom: 16px;"
                                        type="password"
                                        placeholder="비밀번호"
                                        >
                                        <template #append>
                                            <el-button :icon="Hide" @click="onClickHideIcon" />
                                        </template>
                                    </el-input>
                                </el-col>

                                <!-- 보여지는 비밀번호 영역 -->
                                <el-col :span="24" :hidden="state.isHidden.ViewIcon">
                                    <el-input
                                        v-model="state.ivo.userPasswd"
                                        ref="passwd"
                                        @keyup.enter="onClickLogin"
                                        style="height: 40px; font-size: 15px; margin-bottom: 16px;"
                                        type="text"
                                        placeholder="비밀번호"
                                        @click="onClickViewIcon"
                                        >
                                        <template #append>
                                            <el-button :icon="View" @click="onClickViewIcon" />
                                        </template>
                                    </el-input>
                                </el-col>

                                <el-col :span="24">
                                    <el-button color="#311B92" class="login" @click="onClickLogin" style=" margin-bottom: 16px;">로그인</el-button>
                                </el-col>

                                <el-col :span="24">
                                    <el-button type="info" link @click="onClickSearchId">아이디 찾기</el-button>
                                    <el-button type="info" link @click="onClickSearchPw">비밀번호 찾기</el-button>
                                </el-col>

                            </el-row> 
                        </el-form>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="24">
                <el-form-item/>
                <div class="container">
                    <el-card shadow="never" style="width: 19%; height: 60px;">
                        <el-button type="primary" style="font-weight: bold; color: #9575CD;" link @click="onClickGoJoinPage">회원가입</el-button>
                    </el-card>
                </div>
            </el-col>
        </el-row>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>

<script lang="ts" setup>

import { CaretRight, CopyDocument, Hide, Memo, View } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { Api, Common } from '../../common/common'
import { userStore } from '../../store/userStore'
import { UserLoginIvo } from '../../vo/ivo/UserLoginIvo'

const router        = useRouter()
const userStoreObj  = userStore()

const id        = ref()
const passwd    = ref()

const projectDetailView = ref(false)

// 유저정보
interface userState {
    userId      : string,
    userName    : string,
    email       : String,
    phoneNumber : string,
    birthDate   : string,
    genderCode  : string,
}

const state = reactive({
    ivo: new UserLoginIvo(),
    ivoParam: new UserLoginIvo(),
    isHidden: {
        HideIcon: false,
        ViewIcon: true,
    },
})

// 화면진입 시
onMounted(() => {
    id.value.focus()

    setTimeout(()=>{ projectDetailView.value = true }, 300)
})

// 패스워드 숨기기 아이콘 클릭
const onClickHideIcon = () => {
    state.isHidden.HideIcon = true
    state.isHidden.ViewIcon = false
}

// 패스워드 보기 아이콘 클릭
const onClickViewIcon = () => {
    state.isHidden.HideIcon = false
    state.isHidden.ViewIcon = true
}

// 로그인
const onClickLogin = async () => {
    
    if(state.ivo.userId == '') {
        ElMessage({
            type: 'error',
            message: '사용자ID 또는 이메일을 입력하세요.',
        })
        id.value.focus()
    }
    else if(state.ivo.userPasswd == '') {
        ElMessage({
            type: 'error',
            message: '비밀번호를 입력하세요.',
        })
        passwd.value.focus()
    }
    else{

        /* 넘겨줄 user 정보 세팅 */
        state.ivoParam.userId = state.ivo.userId
        state.ivoParam.userPasswd = Common.encypt(state.ivo.userPasswd) // 패스워드 암호화

        let retData = await Api.post("/api/login", state.ivoParam)

        if(retData.data.userId != null) {

            // 유저 정보 세팅할 파라미터
            const userInfo = {
                userId      : retData.data.userId,
                userName    : retData.data.userName,
                email       : retData.data.email,
                phoneNumber : retData.data.phoneNumber,
                birthDate   : retData.data.birthDate,
                genderCode  : retData.data.genderCode,
            } as userState

            // 유저 정보 세팅
            userStoreObj.setUserInfo(userInfo)

            // 로그인 성공
            router.push('/')
        }
        else if(retData.data.userId == null) {
            ElMessage({
                type: 'error',
                message: '사용자ID 또는 비밀번호를 확인하세요.',
            })
        }
    }
}

// 회원가입 페이지 이동
const onClickGoJoinPage = () => {
    router.push('/login/join')
}

// 아이디 찾기 페이지 이동
const onClickSearchId = () => {
    router.push('/login/idSearch')
}

// 아이디 찾기 페이지 이동
const onClickSearchPw = () => {
    router.push('/login/pwSearch')
}

/* 깃 url 복사 */
const onClickCopyUrl = () => {
    navigator.clipboard.writeText('https://github.com/koobs97/Matstagram')
        .then(() => {
            ElMessage({
                type: 'success',
                grouping: true,
                message: 'url이 클립보드에 복사되었습니다',
            })
        })
        .catch(err => {
            console.error('클립보드 복사 실패:', err);
        })
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

@font-face {
    font-family: 'dancing';
    src:url('../../../public/fonts/DancingScript-VariableFont_wght.ttf')
}

.title {
    font-size: 40px;
    font-weight: bold;
    font-family: 'dancing';
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
}

.login {
    width: 100%;
    height: 45px; 
    font-weight: bold; 
    font-size: 16px;
}

.text-container {
    position: fixed;
    bottom: 2%;
    left: 0;
    right: 0;
    text-align: center;
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

.el-switch {
    --el-switch-on-color: #9575CD;
    --el-switch-off-color: var(--el-border-color);
}
/* 체크박스 색상 */
.el-checkbox {
    --el-checkbox-checked-text-color: #7C4DFF;
    --el-checkbox-checked-input-border-color: #70039b;
    --el-checkbox-checked-bg-color: #70039b;
}

/* 체크박스 색상 */
.el-checkbox.is-bordered.is-checked {
    border-color: #7C4DFF;
}

.custom-card {
    --el-card-border-color: var(--el-border-color-light);
    --el-card-border-radius: 4px;
    --el-card-padding: 12px;
    --el-card-bg-color: var(--el-fill-color-blank);
}

/* 버튼 색상 */
.el-button--primary {
    --el-button-text-color: var(--el-color-white);
    --el-button-bg-color: #673AB7;
    --el-button-border-color: #673AB7;
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-active-color: var(--el-color-primary-dark-2);
    --el-button-hover-text-color: var(--el-color-white);
    --el-button-hover-link-text-color: var(--el-color-primary-light-5);
    --el-button-hover-bg-color: #7C4DFF;
    --el-button-hover-border-color: #B388FF;
    --el-button-active-bg-color: var(--el-color-primary-dark-2);
    --el-button-active-border-color: var(--el-color-primary-dark-2);
    --el-button-disabled-text-color: var(--el-color-white);
    --el-button-disabled-bg-color: var(--el-color-primary-light-5);
    --el-button-disabled-border-color: var(--el-color-primary-light-5);
}

</style>
