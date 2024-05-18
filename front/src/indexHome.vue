<template #header>
    <div style="text-align: right; margin-right: 30px;">
        <el-row>
            <el-col :span="2">
                <el-link class="title">FoodingStore</el-link>
            </el-col>
            <el-col :span="16" />
            <el-col :span="3">
                <el-button :icon="Search" class="UserFilled2" link />
                <el-button :icon="Bell" class="UserFilled2" link />
                <el-button :icon="Avatar" class="UserFilled1" circle />
            </el-col>
            <el-col :span="2" />
            <el-col :span="21">
                <el-divider class="divider1" />
            </el-col>
            <el-col :span="2" />
        </el-row>
    </div>

    <el-row class="tac">

        <el-col :span="3">
            <el-menu
                default-active="1"
            >
                <el-menu-item index="1">
                    <el-icon><HomeFilled /></el-icon>
                    <span>Home</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <el-icon><Aim /></el-icon>
                    <span>맛집찾기</span>
                </el-menu-item>
                <el-menu-item index="3">
                    <el-icon><Opportunity /></el-icon>
                    <span>유용한 정보</span>
                </el-menu-item>
                <el-divider class="divider2" />
                <el-menu-item index="7">
                    <el-icon><UserFilled /></el-icon>
                    <span>내 저장소</span>
                </el-menu-item>
                <el-divider class="divider2" />
                <el-menu-item index="4">
                    <el-icon><Tools /></el-icon>
                    <span>설정</span>
                </el-menu-item>
                <el-menu-item index="5">
                    <el-icon><InfoFilled /></el-icon>
                    <span>도움말</span>
                </el-menu-item>
                <el-menu-item index="6">
                    <el-icon><Share /></el-icon>
                    <span>공유하기</span>
                </el-menu-item>
                <el-divider class="divider2" />
                
                <div style="text-align: left; margin-left: 20px;">
                    <el-link>이용약관</el-link> &nbsp;
                    <el-link>프로젝트</el-link> &nbsp; <br><br>
                    <el-text>
                        CopyRight © BONSANG. <br>koobs970729@gmail.com <br>All Rights Reserved
                    </el-text>
                </div>
            </el-menu>
        </el-col>
        <el-col :span="13">
            <el-card shadow="never" style="height: 850px; margin-left: 20px;">
            </el-card>
        </el-col>
        <el-col :span="5">
            <el-card shadow="never" style="height: 300px; width: calc(100% - 47px); margin-left: 20px;">
                <WeatherGuide />
            </el-card>
        </el-col>
        <el-col :span="2" />
    </el-row>
</template>

<script lang="ts" setup>
import { Aim, Avatar, Bell, HomeFilled, InfoFilled, Opportunity, Search, Share, Tools, UserFilled } from '@element-plus/icons-vue';
import { onMounted, reactive } from 'vue';
import WeatherGuide from '../src/views/pages/WeatherGuide.vue';
import { userStore } from './store/userStore';

const userStoreObj  = userStore()

interface userState {
    userId      : string,
    userName    : string,
    email       : String,
    phoneNumber : string,
    birthDate   : string,
    genderCode  : string,
}

const userInfo = reactive({
    user: {
            userId      : '' as string,
            userName    : '' as string,
            email       : '' as string,
            phoneNumber : '' as string,
            birthDate   : '' as string,
            genderCode  : '' as string,
    } as userState,
})

// 화면진입 시
onMounted(() => {
    userInfo.user = userStoreObj.getUserInfo
})

</script>

<style scoped>
/* home 아이콘 */
.title {
    font-size: 25px;
    font-weight: bold;
    font-family: 'dancing';
    margin-top: 0px;
    margin-bottom: 0px;
}
/* 유저 */
.UserFilled1 {
    width: 35px; 
    height: 35px;
    font-size: 15px;
}
/* 탐색, 벨 */
.UserFilled2 {
    width: 35px; 
    height: 35px;
    font-size: 23px;
}
/* 구분선 css1 */
.divider1 {
    margin-top: 12px;
    margin-bottom: 20px;
    width: 100%;
}
.divider2 {
    margin-top: 12px;
    margin-bottom: 12px;
    width: 100%;
}
/* 좌측메뉴 */
.el-menu {
    border-right: solid 0px var(--el-menu-border-color);
    list-style: none;
    position: relative;
    margin: 0;
    padding-left: 0;
    background-color: var(--el-menu-bg-color);
    box-sizing: border-box;
}
</style>