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
                        <el-col :span="5" style="text-align: center;">
                            <el-card shadow="never" style="height: 100%; width: 100%;">
                                <el-icon style="font-size: 75px;"><UserFilled /></el-icon>
                            </el-card>
                        </el-col>

                        <el-col :span="19" style="text-align: left;">
                            <el-text style="margin-left: 15px; font-size: 15px; font-weight: bold;">이름</el-text>
                            <el-input style="width: 270px; height: 25%; padding-left: 50px; font-size: 15px;" v-model="state.userName" disabled></el-input>

                            <div style="margin-bottom: 10px;" />

                            <el-text style="margin-left: 15px; font-size: 15px; font-weight: bold;">휴대전화</el-text>
                            <el-input style="width: 240px; height: 25%; padding-left: 20px; font-size: 13px;" v-model="state.phoneNumber" disabled></el-input>

                            <div style="margin-bottom: 10px;" />

                            <el-text style="margin-left: 15px; font-size: 15px; font-weight: bold;">아이디</el-text>
                            <el-input style="width: 255px; height: 25%; padding-left: 35px; font-size: 13px;" v-model="state.userId" disabled></el-input>
                                
                        </el-col>

                    </el-row>
                    
                </el-form>
            </el-card>
        </div>

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button @click="onClickToLogin">로그인페이지로<el-icon class="el-icon--right"><DArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>

<script lang="ts" setup>
import { DArrowRight, InfoFilled, UserFilled } from '@element-plus/icons-vue';
import { onMounted, reactive, watch } from 'vue';
import { useRouter } from 'vue-router';
import { Api } from '../common/common';
import { userStore } from '../store/userStore';
import { SearchUserId } from '../vo/ivo/SearchUserId';

const router        = useRouter()
const userStoreObj  = userStore()

// state
const state = reactive({
    ivo: new SearchUserId(),

    userName: '',
    phoneNumber: '',
    userId: '',

})

// 화면진입 시
onMounted( async () => {

    state.ivo.userName = userStoreObj.getAuthenticated['username']
    state.ivo.email = userStoreObj.getAuthenticated['email']

    let retData = await Api.post("/api/search/showId", state.ivo)

    state.userName = retData.data.result.userName
    state.phoneNumber = retData.data.result.phoneNumber
    state.userId = retData.data.result.userId
})

// 전화번호 포맷
watch(
    () => state.phoneNumber,
    (newValue) => {
        const formattedPhnum = newValue.replace(/\D/g, '').replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3')
        // 포맷된 전화번호를 상태에 설정
        state.phoneNumber = formattedPhnum
    }
)

// 로그인 페이지로 이동
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

</style>