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
                        <el-dialog 
                            v-model="state.isOpen.Question" 
                            :show-close="false" 
                            :before-close="handleClose"
                            draggable 
                            destroy-on-close 
                            align-center 
                            style="width: 520px; height: 480px; border-radius: 8px; transition: width 0.1s ease;"
                        >
                                <template #header>
                                    <div style="text-align: left; display: flex; margin-bottom: 0px;">
                                        <h4 style="margin-top: 0px; margin-bottom: 15px; font-size: 16px">
                                            질문에 답변하기
                                        </h4>
                                    </div>
                                    <el-divider style="margin-top: 0px; margin-bottom: 0px; width: 50%;"></el-divider>
                                </template>

                                <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">
                                    <el-row :gutter="0">

                                        <el-col :span="2" style="height: 40px;" />
                                        <el-col :span="4" style="height: 40px;">
                                            <el-form-item label="아이디">
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="15" style="height: 40px;">
                                            <el-input v-model="state.local.userId" disabled />
                                        </el-col>
                                        <el-col :span="3" style="height: 40px;" />

                                        <el-col :span="2" style="height: 30px;" />
                                        <el-col :span="4" style="height: 30px;">
                                            <el-form-item label="사용자명">
                                            </el-form-item>
                                        </el-col>
                                        <el-col :span="15" style="height: 30px;">
                                            <el-input v-model="state.local.userName" disabled />
                                        </el-col>
                                        <el-col :span="3" style="height: 30px;" />

                                    </el-row>
                                </el-card>

                                <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 30px">
                                    <el-row :gutter="0">
                                        <el-col :span="24" style="text-align: left; margin-bottom: 2px;">
                                            <el-text style="margin-left: 15px;">회원가입 시 입력한 "비밀번호 찾기 질문"에 대한 답변을 입력해주세요.</el-text>
                                        </el-col>
                                    </el-row>
                                </el-card>

                                <el-divider><el-icon><InfoFilled /></el-icon></el-divider>

                                <el-form :model="state">
                                    <el-form-item label="질문">
                                        <el-input v-model="state.ivo.passwdHint" disabled />
                                    </el-form-item>
                                    <el-form-item label="정답">
                                        <el-input v-model="state.ivo.passwdHintAnswer" ref="passwdHintAnswer" />
                                    </el-form-item>
                                </el-form>
                                
                                <div style="text-align: center; margin-top: 50px">
                                    <el-button type="primary" @click="onClickAuthPw" color="#7E57C2">인증</el-button>
                                    <el-button @click="state.isOpen.Question = false">취소</el-button>
                                </div>
                        </el-dialog>
                        <!-- 질문/답변 Dialog -->

                        <!-- 질문/답변 Dialog -->
                        <el-dialog 
                            v-model="state.isOpen.chgPasswd" 
                            :show-close="false" 
                            :before-close="handleClose"
                            draggable 
                            destroy-on-close 
                            align-center
                            style="width: 400px; height: 760px; border-radius: 8px;">
                            <template #header>
                                <div style="text-align: left; display: flex; margin-bottom: 0px;">
                                    <h4 style="margin-top: 0px; margin-bottom: 15px; font-size: 16px">
                                        비밀번호 변경
                                    </h4>
                                </div>
                                <el-divider style="margin-top: 0px; margin-bottom: 0px; width: 50%;"></el-divider>
                            </template>

                            <div style="text-align: left; margin-top: 0px; margin-bottom: 5px;" >
                                <el-icon style="font-size: 14px;"><CaretRight /></el-icon> <el-text style="font-weight: bold;">사용자정보</el-text>
                            </div>
                            <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">

                                
                                        <el-descriptions :column="1" border>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon>
                                                            <User />
                                                        </el-icon>
                                                        사용자명
                                                    </div>
                                                </template>
                                                구본상
                                            </el-descriptions-item>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon>
                                                            <UserFilled />
                                                        </el-icon>
                                                        아이디
                                                    </div>
                                                </template>
                                                koobs97
                                            </el-descriptions-item>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon>
                                                            <Calendar />
                                                        </el-icon>
                                                        생년월일
                                                    </div>
                                                </template>
                                                1997-07-29
                                            </el-descriptions-item>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon>
                                                            <Phone />
                                                        </el-icon>
                                                        전화번호
                                                    </div>
                                                </template>
                                                010-8702-3099
                                            </el-descriptions-item>
                                        </el-descriptions>

                                </el-card>

                                <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 8px">
                                        <el-row :gutter="0">
                                            <el-col :span="2"/>
                                            <el-col :span="20" style="text-align: left; margin-bottom: 2px;">
                                                <el-text>저희 사이트를 사용해주셔서 감사합니다.</el-text>
                                            </el-col> 
                                            <el-col :span="2"/>

                                            <el-col :span="2"/>
                                            <el-col :span="20" style="text-align: left; margin-bottom: 2px;">
                                                <el-text >비밀번호 생성규칙은 다음과 같습니다.</el-text>
                                            </el-col>
                                            <el-col :span="2"/>
                                        </el-row>
                                    </el-card>
                                    <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0;">
                                        <el-row :gutter="0">
                                            <el-col :span="3"/>
                                            <el-col :span="19" style="text-align: left; margin-bottom: 2px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">1. 8자리 이상</el-text>
                                            </el-col>
                                            <el-col :span="2"/>

                                            <el-col :span="3"/>
                                            <el-col :span="19" style="text-align: left; margin-bottom: 2px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">2. 영문/특수기호/숫자 전부 포함</el-text>
                                            </el-col>
                                            <el-col :span="2"/>

                                            <el-col :span="3"/>
                                            <el-col :span="19" style="text-align: left; margin-bottom: 2px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">3. ID와 동일하게 사용불가</el-text>
                                            </el-col>
                                            <el-col :span="2"/>

                                            <el-col :span="3"/>
                                            <el-col :span="19" style="text-align: left; margin-bottom: 2px;">
                                                <el-text style="margin-left: 15px; font-weight: bold">4. 생년월일/전화번호 포함 불가</el-text>
                                            </el-col>
                                            <el-col :span="2"/>
                                        </el-row>
                                    </el-card>
                                <el-divider><el-button link :icon="Unlock">재설정하기</el-button></el-divider>

                                <el-form :model="state">
                                    <el-form-item label="비밀변호 변경">
                                        <el-input autocomplete="off" v-model="state.ivo.passwdHint" :suffix-icon="Hide" />
                                    </el-form-item>
                                    <el-form-item label="비밀변호 확인">
                                        <el-input autocomplete="off" :suffix-icon="Hide" />
                                    </el-form-item>
                                </el-form>
                                
                                <div style="text-align: center; margin-top: 50px; display: flex;">
                                    <el-col :span="3"/>
                                    <el-col :span="2">
                                        {{ '안전' }}
                                    </el-col>
                                    <el-col :span="1"/>
                                    <el-col :span="16">
                                        <el-progress :percentage="100" status="success" />
                                    </el-col>
                                    <el-col :span="2"/>
                                </div>
                                <div style="text-align: center; margin-top: 20px">
                                    <el-button type="primary" @click="onClickAuthPw" color="#7E57C2">비밀번호변경</el-button>
                                    <el-button @click="state.isOpen.Question = false">다음에하기</el-button>
                                </div>

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
import { ArrowLeft, ArrowRight, Calendar, CaretRight, ChatLineSquare, Checked, Hide, InfoFilled, Phone, Unlock, User, UserFilled } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { Api } from '../common/common';
import { SearchUserPwIvo } from '../vo/ivo/SearchUserPwIvo';

const router        = useRouter()   // router

const plainId       = ref(true)     // 찾기 구분 선택
const plainPasswd   = ref(false)    // 찾기 구분 선택

const userName  = ref()             // focus
const email     = ref()             // focus
const userId    = ref()             // focus
const passwdHintAnswer = ref()      // focus

// state reactive
const state = reactive({
    ivo: new SearchUserPwIvo(),
    isOpen: {
        Question: false,    // 질문 팝업창
        chgPasswd: false,   // 비밀번호 변경
    },
    local: {
        userId: '',
        userName: '',
    }
})

// 화면진입 시
onMounted( () => {
    userName.value.focus()
})

// 질문 답변하기 팝업 open
const onClickOpenQuestion = async () => {

    state.isOpen.chgPasswd = true

    // 필수입력 체크
    if(state.ivo.userName == '') {
        ElMessage({
            type: 'error',
            message: '사용자명을 입력하세요.',
        })
        userName.value.focus()
        return
    }
    if(state.ivo.email == '') {
        ElMessage({
            type: 'error',
            message: '이메일을 입력하세요.',
        })
        email.value.focus()
        return
    }
    if(state.ivo.userId == '') {
        ElMessage({
            type: 'error',
            message: '아이디를 입력하세요.',
        })
        userId.value.focus()
        return
    }

    // 질문 조회
    let retData = await Api.post("/api/search/searchUserPw", state.ivo)

    if(retData.data == '') {
        ElMessage({
            type: 'error',
            message: '사용자 정보를 확인해주세요.',
        })
        return
    }
    else {
        state.isOpen.Question = true
        state.ivo.passwdHint = retData.data.passwdHint

        state.local.userId = state.ivo.userId
        state.local.userName = state.ivo.userName

        setTimeout(()=>{ passwdHintAnswer.value.focus() }, 50)
    }

}

// 팝업창 바깥 클릭 시
const handleClose = () => {
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
const onClickAuthPw = async () => {
    // state.isOpen.Question = false
    // state.isOpen.chgPasswd = true

    // 필수입력 체크
    if(state.ivo.passwdHintAnswer == '') {
        ElMessage({
            type: 'error',
            message: '정답을 입력하세요.',
        })
        passwdHintAnswer.value.focus()
        return
    }

    // 정답 조회
    let retData = await Api.post("/api/search/searchPwAnswer", state.ivo)

    if(retData.data.userId == null) {
        ElMessage({
            type: 'error',
            message: '정답이 일치하지 않습니다.',
        })
        return
    }
    else {
        state.isOpen.Question = false
        state.isOpen.chgPasswd = true
    }
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

</style>