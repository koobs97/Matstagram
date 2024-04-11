<!--
 * <h3>
 *  비밀번호 찾기 화면
 * </h3>
 * 
 * 질문/답변으로 비밀번호 찾기
 * 
 * @Class   SearchUserPw.vue
 * @Author  Koo Bon Sang
 * @Date    2024.03.29
 * @Version 1.1.0
 * 
 -->

<template>
    <el-form label-width="120px">
        <el-form-item>
            <br><br>
        </el-form-item>

        <div class="container_header">
            <el-button color="#4527A0" class="top_button" :plain="plainId" @click="onClickToSearchId">아이디 찾기</el-button>
            <el-button color="#4527A0" class="top_button" :plain="plainPasswd">비밀번호 찾기</el-button>
        </div>

        <div class="container">
            <el-card shadow="never" style="height: 80%; width: 40%;">

                <el-form label-width="120px" label-position="left">
                    <el-row :gutter="0">

                        <el-col :span="24" style="text-align: left;">
                            <el-icon style="font-size: 13px;"><Checked /></el-icon>
                            <el-text style="font-size: 16px; font-weight: bold;">&nbsp;질문/답변으로 비밀번호 찾기</el-text>
                            <el-divider style="margin-top: 7px;"></el-divider>
                        </el-col>

                        <el-col :span="4"/>
                        <el-col :span="4" style="text-align: left; height: 40px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사용자명">
                            </el-form-item>
                        </el-col>
                        <el-col :span="9" style="text-align: left; height: 40px;">
                            <el-input ref="userName" v-model="state.ivo.userName" />
                        </el-col>
                        <el-col :span="7"/>

                        <el-col :span="4"/>
                        <el-col :span="4" style="text-align: left; height: 40px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이메일주소">
                            </el-form-item>
                        </el-col>
                        <el-col :span="9" style="text-align: left; height: 40px;">
                            <el-input ref="email" v-model="state.ivo.email" />
                        </el-col>
                        <el-col :span="7"/>

                        <el-col :span="4"/>
                        <el-col :span="4" style="text-align: left; height: 40px; margin-bottom: 15px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디">
                            </el-form-item>
                        </el-col>
                        <el-col :span="9" style="text-align: left; height: 40px;">
                            <el-input ref="userId" v-model="state.ivo.userId" />
                        </el-col>
                        <el-col :span="7"/>
                        
                        <el-col :span="24">
                            <el-button type="info" plain :icon="ChatLineSquare" style="width: 158px;" @click="onClickOpenQuestion">질문 답변하기</el-button>
                        </el-col>

                        <!-- 질문/답변 Dialog -->
                        <el-dialog v-model="state.isOpen.Question" align-center style="width: 500px; height: 280px; border-radius: 8px;">
                            <template #header>
                                <h4 style="margin-top: 5px; margin-bottom: 5px;">질문에 답변하기</h4>
                            </template>
                            <el-form :model="state">
                                <el-form-item label="질문">
                                    <el-input autocomplete="off" v-model="state.ivo.passwdHint" disabled />
                                </el-form-item>
                                <el-form-item label="정답">
                                    <el-input autocomplete="off" />
                                </el-form-item>
                            </el-form>
                            <template #footer>
                                <div style="text-align: center;">
                                    <el-button type="primary" @click="onClickAuthPw" color="#7E57C2">인증</el-button>
                                    <el-button @click="state.isOpen.Question = false">취소</el-button>
                                </div>
                            </template>
                        </el-dialog>
                        <!-- 질문/답변 Dialog -->

                        <!-- 질문/답변 Dialog -->
                        <el-dialog v-model="state.isOpen.chgPasswd" align-center style="width: 500px; height: 500px; border-radius: 8px;" :title="'비밀번호 변경'">
                            <div>
                                    <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 8px">
                                        <el-row :gutter="0">
                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px;">저희 사이트를 사용해주셔서 감사합니다.</el-text>
                                            </el-col>
                                            <el-col :span="4"/>

                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px;">비밀번호 생성규칙은 다음과 같습니다.</el-text>
                                            </el-col>
                                            <el-col :span="4"/>
                                        </el-row>
                                    </el-card>
                                </div>
                                <div>
                                    <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0;">
                                        <el-row :gutter="0">
                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">1. 8자리 이상</el-text>
                                            </el-col>
                                            <el-col :span="4"/>

                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">2. 영문/특수기호/숫자 전부 포함</el-text>
                                            </el-col>
                                            <el-col :span="4"/>

                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">3. ID와 동일하게 사용불가</el-text>
                                            </el-col>
                                            <el-col :span="4"/>

                                            <el-col :span="4"/>
                                            <el-col :span="16" style="text-align: left; margin-bottom: 1px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">4. 생년월일/전화번호 포함 불가</el-text>
                                            </el-col>
                                            <el-col :span="4"/>
                                        </el-row>
                                    </el-card>
                                </div>
                                <el-divider><el-button link :icon="Unlock">재설정하기</el-button></el-divider>

                                <el-form :model="state">
                                    <el-form-item label="비밀변호 변경">
                                        <el-input autocomplete="off" v-model="state.ivo.passwdHint" />
                                    </el-form-item>
                                    <el-form-item label="비밀변호 확인">
                                        <el-input autocomplete="off" />
                                    </el-form-item>
                                </el-form>
                            <template #footer>
                                <div style="text-align: center;">
                                    <el-button type="primary" @click="onClickAuthPw" color="#7E57C2">비밀번호변경</el-button>
                                    <el-button @click="state.isOpen.Question = false">다음에하기</el-button>
                                </div>
                            </template>
                        </el-dialog>
                        <!-- 질문/답변 Dialog -->

                    </el-row>
                </el-form>

            </el-card>
        </div>

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button :icon="ArrowLeft" @click="onClickToLogin">이전</el-button>
            <el-button >다음<el-icon class="el-icon--right"><ArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

        
    </el-form>

</template>
<script lang="ts" setup>
import { ArrowLeft, ArrowRight, ChatLineSquare, Checked, Unlock } from '@element-plus/icons-vue';
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { SearchUserPwIvo } from '../vo/ivo/SearchUserPwIvo';

const router        = useRouter()   // router

const plainId       = ref(true)     // 찾기 구분 선택
const plainPasswd   = ref(false)    // 찾기 구분 선택

const userName  = ref()             // focus
const email     = ref()             // focus
const userId    = ref()             // focus

// state reactive
const state = reactive({
    ivo: new SearchUserPwIvo(),
    isOpen: {
        Question: false,    // 질문 팝업창
        chgPasswd: false,   // 비밀번호 변경
    },
})

// 화면진입 시
onMounted( () => {
    userName.value.focus()
})

// 질문 답변하기 팝업 open
const onClickOpenQuestion = async () => {

    // 필수입력 체크
    // if(state.ivo.userName == '') {
    //     ElMessage({
    //         type: 'error',
    //         message: '사용자명을 입력하세요.',
    //     })
    //     userName.value.focus()
    //     return
    // }
    // if(state.ivo.email == '') {
    //     ElMessage({
    //         type: 'error',
    //         message: '이메일을 입력하세요.',
    //     })
    //     email.value.focus()
    //     return
    // }
    // if(state.ivo.userId == '') {
    //     ElMessage({
    //         type: 'error',
    //         message: '아이디를 입력하세요.',
    //     })
    //     userId.value.focus()
    //     return
    // }

    // let retData = await Api.post("/api/search/searchUserPw", state.ivo)
    // console.log(retData)

    // if(retData.data == '') {
    //     ElMessage({
    //         type: 'error',
    //         message: '사용자 정보를 확인해주세요.',
    //     })
    //     return
    // }
    // else {
    //     state.isOpen.Question = true
    //     state.ivo.passwdHint = retData.data.passwdHint
    // }

    state.isOpen.Question = true

}

// 아이디 찾기 페이지로 이동
const onClickToSearchId = () => {
    router.push('/login/idSearch')
}

// 로그인 페이지로 이동
const onClickToLogin = () => {
    router.push('/')
}




/********************************
 * 질문 답변 팝업
 ********************************/
const onClickAuthPw = () => {
    state.isOpen.Question = false

    state.isOpen.chgPasswd = true
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
    width: 380px;
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

.custom-card {
    --el-card-border-color: var(--el-border-color-light);
    --el-card-border-radius: 4px;
    --el-card-padding: 12px;
    --el-card-bg-color: var(--el-fill-color-blank);
}

.custom-title .el-dialog__header {
    padding: 10px 20px; /* 패딩값을 조정합니다. */
}

</style>