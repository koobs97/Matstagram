<!--
 * [확인창 다이얼로그]
 * 
 * @Class   ConfirmDialog.vue
 * @Author  Koo Bon Sang
 * @Date    2024.10.29
 * @Version 1.1.0
 * 
-->
<template>
    <div style="text-align: center; margin-top: 0px; margin-bottom: 5px;" >
        <el-text>{{ state.bodyMessage }}</el-text>
    </div>
    <div style="text-align: center; margin-top: 30px">
        <el-button 
            style="margin-left: 16px; width: 80px;" 
            type="primary"
            color="#7E57C2" 
            @click="onClickConfirm"
        >
            {{ state.confirmText }}
        </el-button>
        <el-button 
            style="margin-left: 4px; width: 80px;" 
            @click="onClickCancel"
        >
            {{ state.cancelText }}
        </el-button>
    </div>
</template>
<script lang="ts" setup>
import { defineEmits, defineProps, onMounted, reactive } from 'vue';

const props = defineProps({
    headerMessage: {
        type: String,
        required: false,
        default : '알림창',
    },
    bodyMessage: {
        type: String,
        required: true,
        default : '확인창메시지입니다.',
    },
    confirmText: {
        type: String,
        required: true,
        default : '확인',
    },
    cancelText: {
        type: String,
        required: true,
        default : '확인',
    },
})
const emit = defineEmits(['close-dialog'])

const state = reactive({
    headerMessage   : '' as string,
    bodyMessage     : '' as string,
    confirmText     : '' as string,
    cancelText      : '' as string,
})

// 화면진입 시
onMounted( () => {
    Object.assign(state, {
        headerMessage   : props.headerMessage,
        bodyMessage     : props.bodyMessage,
        confirmText     : props.confirmText,
        cancelText      : props.cancelText,
    });
})

// 확인버튼
const onClickConfirm = () => {
    emit('close-dialog', true);
}

// 취소 버튼
const onClickCancel = () => {
    emit('close-dialog', false);
}

</script>
<style>
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