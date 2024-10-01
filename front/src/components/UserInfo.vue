<!--
 * [사용자정보 컴포넌트]
 * 
 * @Class   UserInfo.vue
 * @Author  Koo Bon Sang
 * @Date    2024.08.18
 * @Version 1.1.0
 * 
-->

<template>
    <el-popover
        placement="bottom"
        :width="250"
        trigger="click"
    >
        <el-menu>
            <el-row>
                <el-col :span="6">
                    <el-button 
                        type="info" 
                        plain 
                        style="width: 40px; height: 40px;"
                        disabled
                    >
                        <el-icon style="font-size: 20px;">
                            <User />
                        </el-icon>
                    </el-button>
                </el-col>
                <el-col :span="12">
                    <el-text style="font-weight: bold; font-size: 12px;">
                        {{ props.userInfo.user.userName }}
                    </el-text>
                    <el-text style="font-size: 11px;">
                        {{ props.userInfo.user.email }}
                    </el-text>
                </el-col>
            </el-row>
        <el-divider style="margin-top: 12px; margin-bottom: 12px;" />
        <el-menu-item index="1">
            <el-icon><Edit /></el-icon>
            <span>개인정보수정</span>
        </el-menu-item>
        <el-menu-item index="2" @click="openChngPasswd">
            <el-icon><Lock /></el-icon>
            <span>비밀번호변경</span>
        </el-menu-item>
        <el-divider style="margin-top: 12px; margin-bottom: 12px;" />
        <el-menu-item index="3" @click="onClickLogOut">
            <el-icon><SwitchButton /></el-icon>
            <span>로그아웃</span>
        </el-menu-item>
        </el-menu>
        <template #reference>
            <el-button :icon="Avatar" class="UserFilled1" circle />
        </template>
    </el-popover>
</template>
<script lang="ts" setup>
import { Avatar, Edit, Lock, SwitchButton, User } from '@element-plus/icons-vue';
import { defineProps } from 'vue';
import { eventBus } from '../common/common';
import { userStore } from '../store/userStore';

const userStoreObj  = userStore()

const props = defineProps({
    userInfo: {
        type: Object,
        required: true,
        default : null,
    }
})

const onClickLogOut = () => {
    userStoreObj.delUserInfo()
    location.reload();
}

const openChngPasswd = () => {
    eventBus.emit('openChngPasswd', { isOpen: true })
}
</script>
<style>
:root {
    --el-menu-border-color: #ffffff;
    --el-menu-item-height: 40px;
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
    --el-button-active-text-color: #9575CD;
    --el-button-active-border-color: #9575CD;
    --el-button-active-bg-color: var(--el-button-hover-bg-color);
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-hover-link-text-color: #9575CD;
    --el-button-active-color: #9575CD;
}
</style>