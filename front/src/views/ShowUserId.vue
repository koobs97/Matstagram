<template>
    <el-form label-width="120px">
        <el-form-item>
            <br><br>
        </el-form-item>

        <div class="container_header">
            <el-space direction="vertical">
                <el-text style="font-size: 25px; font-weight: bold;">아이디 찾기</el-text> 
                <el-text style="font-size: 22px; font-weight: bold; color:cornflowerblue">아이디 찾기가 완료되었습니다.</el-text>
            </el-space>
        </div>

        <div class="container">
            <el-card shadow="never" style="height: 70%; width: 30%;">
                <template #header>
                    <div class="card-header" style="text-align: left;">

                        <el-button style="height: 8px; width: 8px; padding: 0px;" :icon="InfoFilled"></el-button>
                        <el-text style="margin-left: 15px;">저희 사이트를 사용해주셔서 감사합니다.</el-text>

                        <div style="display: flex; text-align: left; padding-top: 5px;">
                            <el-text style="margin-left: 22px;">인증된 정보로 가입된 아이디가 있습니다.</el-text>
                        </div>
                        
                    </div>
                </template>

                <el-form label-position="top">
                    <el-row :gutter="0">
                        <el-col :span="24" style="text-align: left;">
                            <el-descriptions :column="2" border>
                                <el-descriptions-item label-align="left" align="left">
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon><User /></el-icon>
                                            사용자명
                                        </div>
                                    </template>
                                    {{ state.userName }}
                                </el-descriptions-item>
                                <el-descriptions-item label-align="center" align="center">
                                </el-descriptions-item>
                                <el-descriptions-item label-align="left" align="left">
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon><Phone /></el-icon>
                                            전화번호
                                        </div>
                                    </template>
                                    {{ state.phoneNumber }}
                                </el-descriptions-item>
                                <el-descriptions-item label-align="center" align="center">
                                    <template #label>
                                        <div class="cell-item">
                                            성별
                                        </div>
                                    </template>
                                    <el-tag plain style="color: #4527A0;">
                                        {{ state.genderCode }}
                                    </el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item label-align="left" align="left">
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon><UserFilled /></el-icon>
                                            아이디
                                        </div>
                                    </template>
                                    <div style="display: flex;">
                                        <div style="width: 86%;">
                                            {{ state.userId }}
                                        </div>
                                        <div style="width: 14%;">
                                            <el-button :icon="CopyDocument" style="width: 12px; height: 23px; font-size: 12px;" type="primary" @click="onClickCopyId" />
                                        </div>
                                    </div>
                                </el-descriptions-item>
                            </el-descriptions>
                        </el-col>
                    </el-row>
                </el-form>
            </el-card>
        </div>

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button @click="onClickToLogin">로그인페이지<el-icon class="el-icon--right"><ArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>

<script lang="ts" setup>
import { ArrowRight, InfoFilled, UserFilled, User, Phone, CopyDocument } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { onMounted, reactive, watch } from 'vue';
import { onBeforeRouteLeave, useRouter } from 'vue-router';
import { Api } from '../common/common';
import { userStore } from '../store/userStore';
import { SearchUserId } from '../vo/ivo/SearchUserId';

const router        = useRouter()
const userStoreObj  = userStore()

/* state reactive */
const state = reactive({
    ivo: new SearchUserId(),
    userName: '',
    phoneNumber: '',
    userId: '',
    genderCode: ''
})

/* 뒤로가기/앞으로가기 시 실행할 작업 */
onBeforeRouteLeave(async(to, from, next) => {

    userStoreObj.delAuthentication()    // 유저인증정보 초기화
    next();                             // 다음 단계로 진행

})

/* 화면진입 시 */
onMounted( async () => {

    state.ivo.userName = userStoreObj.getAuthenticated['username']
    state.ivo.email = userStoreObj.getAuthenticated['email']

    const retData = await Api.post("/api/search/showId", state.ivo)

    state.userName = retData.data.result.userName
    state.phoneNumber = retData.data.result.phoneNumber
    state.userId = retData.data.result.userId

    if(retData.data.result.genderCode == 'M') {
        state.genderCode = '남'
    }
    else {
        state.genderCode = '여'
    }

})

/* 전화번호 포맷 */
watch(
    () => state.phoneNumber,
    (newValue) => {
        const formattedPhnum = newValue.replace(/\D/g, '').replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3')
        state.phoneNumber = formattedPhnum  // 포맷된 전화번호를 상태에 설정
    }
)

/* 아이디 복사하기 */
const onClickCopyId = () => {
    navigator.clipboard.writeText(state.userId)
        .then(() => {
            ElMessage({
                type: 'success',
                grouping: true,
                message: '아이디가 클립보드에 복사되었습니다',
            })
        })
        .catch(err => {
            console.error('클립보드 복사 실패:', err);
        })
}

/* 로그인 페이지로 이동 */
const onClickToLogin = () => {

    userStoreObj.delAuthentication()    // 유저인증정보 초기화
    router.push('/')                    // 로그인페이지로 이동

}

</script>

<style scoped>
.card-header {
    padding: calc(var(--el-card-padding) - 2px) var(--el-card-padding);
    border-bottom: 1px solid var(--el-card-border-color);
    box-sizing: border-box;
    background-color: lavender !important;
}
.container {
    display: flex;
    justify-content: center;
    align-items: center;
}

.container_header {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
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
    --el-button-disabled-bg-color: rgb(189 189 189);
    --el-button-disabled-border-color: rgb(167 167 167);
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