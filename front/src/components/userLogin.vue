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
                                            <el-divider style="margin-bottom: 0px; margin-top: 0px;"></el-divider>
                                            <el-text style="font-size: 12px; font-weight: bold;">주제 : </el-text> <el-text>개인 공부를 위한 맛집 플랫폼 웹서비스 구현</el-text>
                                            <div>
                                                <el-row>
                                                    <el-col :span="4" style="height: 20px;">
                                                        <el-text style="font-size: 12px; font-weight: bold;">사용기술 : </el-text>
                                                    </el-col>
                                                    <el-col :span="19" style="height: 20px; margin-left: 4px;">
                                                        <el-checkbox style="height: 22px;" label="Vue3" />
                                                    </el-col>
                                                    <el-col :span="4" />
                                                    <el-col :span="19" style="height: 20px; margin-left: 4px;">
                                                        <el-checkbox style="height: 22px;" label="Spring-Boot3"/>
                                                    </el-col>
                                                    <el-col :span="4" />
                                                    <el-col :span="19" style="height: 20px; margin-left: 4px;">
                                                        <el-checkbox style="height: 22px;" label="Oracle21"/>
                                                    </el-col>
                                                </el-row>
                                                
                                                
                                                
                                                
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
                                        :suffix-icon="View"
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

import { Hide, Memo, View } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { Api, Common } from '../common/common'
import { userStore } from '../store/userStore'
import { UserLoginIvo } from '../vo/ivo/UserLoginIvo'

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

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

@font-face {
    font-family: 'dancing';
    src:url('../../public/fonts/DancingScript-VariableFont_wght.ttf')
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

</style>
