<!--
 * [비밀번호 찾기 화면]
 * 질문/답변으로 비밀번호 찾기
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
            <el-button color="#4527A0" class="top_button" :plain="plainId" @click="onClickToSearchId">아이디 찾기</el-button>
            <el-button color="#4527A0" class="top_button" :plain="plainPasswd">비밀번호 찾기</el-button>
        </div>

        <!-- container 영역 -->
        <div class="container">
            <el-card shadow="never" style="height: 80%; width: 40%;">

                <el-form label-width="120px" label-position="left">
                    <el-row :gutter="0">

                        <el-col :span="24" style="text-align: left;">
                            <el-text style="font-size: 16px; font-weight: bold;">
                                <el-icon style="font-size: 13px;"><Checked /></el-icon>
                                질문/답변으로 비밀번호 찾기
                            </el-text>
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
                            :title="'질문에 답변하기'"
                            style="width: 520px; height: 480px; border-radius: 8px; transition: width 0.1s ease;"
                        >
                            <div style="text-align: left; margin-top: 0px; margin-bottom: 5px;" >
                                <el-text style="font-weight: bold;"><el-icon><CaretRight /></el-icon>사용자정보</el-text>
                            </div>

                            <!-- 사용자정보 -->
                            <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">
                                <el-row :gutter="0">

                                    <el-col :span="3">
                                        <el-avatar style="height: 41px ; width: 41px;" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
                                    </el-col>
                                    <el-col :span="21">
                                        <el-descriptions :column="2" border>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon><UserFilled /></el-icon>
                                                        아이디
                                                    </div>
                                                </template>
                                                {{ state.pop1.ivo.userId }}
                                            </el-descriptions-item>
                                            <el-descriptions-item label-align="left" align="left">
                                                <template #label>
                                                    <div class="cell-item">
                                                        <el-icon><User /></el-icon>
                                                        사용자명
                                                    </div>
                                                </template>
                                                {{ state.pop1.ivo.userName }}
                                            </el-descriptions-item>
                                        </el-descriptions>
                                    </el-col>

                                </el-row>
                            </el-card>
                            <!-- 사용자정보 -->

                            <!-- 안내문구 -->
                            <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 30px">
                                <el-row :gutter="0">
                                    <el-col :span="24" style="text-align: left; margin-bottom: 2px;">
                                        <el-text style="margin-left: 15px;">회원가입 시 입력한 "비밀번호 찾기 질문"에 대한 답변을 입력해주세요.</el-text>
                                    </el-col>
                                </el-row>
                            </el-card>
                            <!-- 안내문구 -->

                            <el-divider><el-icon><InfoFilled /></el-icon></el-divider>

                            <el-form :model="state">
                                <div style="text-align: left;">
                                    <el-input v-model="state.pop1.ivo.passwdHint" disabled></el-input>
                                    <el-input 
                                        :type="passwdHintAnswerType"
                                        style="margin-top: 5px;" v-model="state.pop1.ivo.passwdHintAnswer" 
                                        placeholder="본인확인 질문 답변" 
                                        ref="passwdHintAnswer"
                                    />
                                    <el-checkbox v-model="isAnswerHide">본인 확인 답변 숨기기</el-checkbox>
                                </div>
                            </el-form>
                            
                            <div style="text-align: center; margin-top: 50px">
                                <el-button type="primary" @click="onClickAuthPw" color="#7E57C2">인증</el-button>
                                <el-button @click="state.isOpen.Question = false">취소</el-button>
                            </div>
                        </el-dialog>
                        <!-- 질문/답변 Dialog -->

                        <!-- 비밀번호 변경 Dialog -->
                        <el-dialog 
                            v-model="state.isOpen.chgPasswd" 
                            :show-close="false" 
                            :before-close="handleClose"
                            :draggable="false"
                            destroy-on-close
                            align-center
                            :title="'비밀번호 변경'"
                            style="width: 400px; height: 780px; border-radius: 8px;">

                            <div style="text-align: left; margin-top: 0px; margin-bottom: 5px;" >
                                <el-text style="font-weight: bold;"><el-icon><CaretRight /></el-icon>사용자정보</el-text>
                            </div>

                            <!-- 사용자정보 -->
                            <div style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">
                                <el-descriptions :column="1" border>
                                    <el-descriptions-item label-align="left" align="left">
                                        <template #label>
                                            <div class="cell-item">
                                                <el-icon><User /></el-icon>
                                                사용자명
                                            </div>
                                        </template>
                                        {{ state.pop2.pwChgIvo1.userName }}
                                    </el-descriptions-item>
                                    <el-descriptions-item label-align="left" align="left">
                                        <template #label>
                                            <div class="cell-item">
                                                <el-icon><UserFilled /></el-icon>
                                                아이디
                                            </div>
                                        </template>
                                        {{ state.pop2.pwChgIvo1.userId }}
                                    </el-descriptions-item>
                                    <el-descriptions-item label-align="left" align="left">
                                        <template #label>
                                            <div class="cell-item">
                                                <el-icon><Calendar /></el-icon>
                                                생년월일
                                            </div>
                                        </template>
                                        {{ state.pop2.pwChgIvo1.birthDate }}
                                    </el-descriptions-item>
                                    <el-descriptions-item label-align="left" align="left">
                                        <template #label>
                                            <div class="cell-item">
                                                <el-icon><Phone /></el-icon>
                                                전화번호
                                            </div>
                                        </template>
                                        {{ state.pop2.pwChgIvo1.phoneNumber }}
                                    </el-descriptions-item>
                                </el-descriptions>
                            </div>
                            <!-- 사용자정보 -->

                            <!-- 안내문구 -->
                            <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 5px">
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
                            <!-- 안내문구 -->

                            <!-- 비밀번호 생성규칙 -->
                            <el-descriptions
                                :column="1"
                                size="small"
                                border
                            >
                                <el-descriptions-item label-class-name="my-label" label="필수">8자리 이상</el-descriptions-item>
                                <el-descriptions-item label-class-name="my-label" label="필수">영문/특수기호/숫자 전부 포함</el-descriptions-item>
                                <el-descriptions-item label-class-name="my-label" label="필수">ID와 동일하게 사용불가</el-descriptions-item>
                                <el-descriptions-item label-class-name="my-label" label="필수">생년월일/전화번호 포함 불가</el-descriptions-item>
                                <el-descriptions-item label="권장">3자리의 순차적인 숫자 사용 금지</el-descriptions-item>
                            </el-descriptions>
                            <!-- 비밀번호 생성규칙 -->

                            <el-divider style="margin-bottom: 20px;"><el-text disabled><el-icon style="margin-right: 5px;"><Unlock /></el-icon>재설정하기</el-text></el-divider>

                            <!-- 비밀번호 변경 -->
                            <el-form :model="state">
                                <div style="margin-bottom: 5px; margin-top: 5px;">
                                    <el-tooltip
                                        effect="dark"
                                        placement="right-start"
                                        raw-content
                                        :content="state.pop2.ruleChk.msg"
                                        :visible="state.pop2.isVisibleRule"
                                    >
                                        <el-input 
                                            v-model="state.pop2.pwChgIvo1.userPasswd" 
                                            type="password" 
                                            placeholder="비밀번호 변경" 
                                            :suffix-icon="Hide" 
                                            ref="chgPasswd" 
                                        />
                                    </el-tooltip>
                                </div>
                                <div style="margin-bottom: 12px;">
                                    <el-tooltip
                                        effect="dark"
                                        placement="right-start"
                                        raw-content
                                        :content="state.pop2.chkType.msg"
                                        :visible="state.pop2.isHiddenChk"
                                    >
                                        <el-input 
                                            v-model="state.pop2.pwChgIvo1.userPasswdChk" 
                                            type="password" 
                                            placeholder="비밀번호 확인" 
                                            :suffix-icon="Hide" 
                                            ref="chkPasswd" 
                                        />
                                    </el-tooltip>
                                </div>
                            </el-form>
                            <!-- 비밀번호 변경 -->

                            <!-- 패스워드 복잡도 -->
                            <el-col :span="24" style="margin-top: 20px;">
                                <el-tag plain style="color: #4527A0;">
                                    <el-icon><Lock /></el-icon>
                                    패스워드 복잡도
                                </el-tag>
                            </el-col>
                            <div style="text-align: center; display: flex;">
                                <el-col :span="6" />
                                <el-col :span="16">
                                    <el-progress
                                        :stroke-width="8"
                                        :percentage="state.pop2.complexity.percentage" :status="state.pop2.complexity.status" />
                                </el-col>
                                <el-col :span="2"/>
                            </div>
                            <!-- 패스워드 복잡도 -->

                            <div style="text-align: center; margin-top: 50px">
                                <el-button type="primary" @click="onClickOpenCinfirm1" color="#7E57C2" :disabled="state.pop2.disableButton">비밀번호변경</el-button>
                                <el-button @click="onClickOpenCinfirm2" :disabled="state.pop2.disableButton">다음에하기</el-button>
                            </div>

                        </el-dialog>
                        <!-- 비밀번호 변경 Dialog -->

                        <!-- 비밀번호 변경하기 확인창  -->
                        <el-dialog 
                            v-model="state.isOpen.confirm1" 
                            :before-close="handleClose" 
                            :show-close="false" 
                            align-center 
                            style="width: 280px; height: 140px; border-radius: 8px;"
                        >
                            <template #header>
                                <div style="text-align: left;">
                                    <h4 style="margin-top: 0px; margin-bottom: 18px; font-size: 16px">
                                        <el-icon style="margin-right: 2px; font-size: 12px"><InfoFilled /></el-icon>
                                        알림창
                                    </h4>
                                <el-text>비밀번호를 변경하시겠습니까?</el-text>
                                </div>
                                <div style="text-align: center; margin-top: 20px">
                                    <el-button style="margin-left: 16px;" type="primary" color="#7E57C2" @click="onClickChangePw">확인</el-button>
                                    <el-button style="margin-left: 4px;" @click="state.isOpen.confirm1 = false">취소</el-button>
                                </div>
                            </template>
                        </el-dialog>
                        <!-- 비밀번호 변경하기 확인창  -->

                        <!-- 다음에하기 확인창  -->
                        <el-dialog 
                            v-model="state.isOpen.confirm2" 
                            :before-close="handleClose" 
                            :show-close="false" 
                            align-center 
                            style="width: 280px; height: 140px; border-radius: 8px;"
                        >
                            <template #header>
                                <div style="text-align: left;">
                                    <h4 style="margin-top: 0px; margin-bottom: 18px; font-size: 16px">
                                        <el-icon style="margin-right: 2px; font-size: 12px"><InfoFilled /></el-icon>
                                        알림창
                                    </h4>
                                <el-text>다음에 변경하시겠습니까?</el-text>
                                </div>
                                <div style="text-align: center; margin-top: 20px">
                                    <el-button style="margin-left: 16px;" type="primary" color="#7E57C2" @click="onClickConfirm">확인</el-button>
                                    <el-button style="margin-left: 4px;" @click="state.isOpen.confirm2 = false">취소</el-button>
                                </div>
                            </template>
                        </el-dialog>
                        <!-- 다음에하기 확인창  -->

                    </el-row>
                </el-form>

            </el-card>
        </div>
        <!-- container 영역 -->

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button @click="onClickToLogin">로그인페이지<el-icon class="el-icon--right"><ArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
    <!-- 전체 form -->

</template>
<script lang="ts" setup>
import { ArrowRight, Calendar, CaretRight, ChatLineSquare, Checked, Hide, InfoFilled, Phone, Unlock, User, UserFilled, Lock } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { onMounted, reactive, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { Api, Common } from '../common/common'
import { ChgUserPwIvo } from '../vo/ivo/ChgUserPwIvo';
import { SearchUserPwIvo } from '../vo/ivo/SearchUserPwIvo';

const router        = useRouter()   // router

const plainId       = ref(true)     // 찾기 구분 선택
const plainPasswd   = ref(false)    // 찾기 구분 선택

const isAnswerHide          = ref(false)    // 본인확인 질문 숨기기
const passwdHintAnswerType  = ref('text')   // focus

const userName          = ref()             // focus
const email             = ref()             // focus
const userId            = ref()             // focus
const passwdHintAnswer  = ref()             // focus
const chgPasswd         = ref()             // focus
const chkPasswd         = ref()             // focus

/* state reactive */
const state = reactive({
    ivo: new SearchUserPwIvo(),     // 메인화면 ivo
    
    /* 팝업 오픈 변수 */
    isOpen: {
        Question: false,            // 질문 팝업창
        chgPasswd: false,           // 비밀번호 변경
        confirm1: false,            // 비밀번호변경 확인창
        confirm2: false,            // 다음에 하기 확인창
    },

    /* 팝업1 변수 */
    pop1: {
        ivo: new SearchUserPwIvo(),
    },

    /* 팝업2 변수 */
    pop2: {

        pwChgIvo1: new ChgUserPwIvo(),      // 비밀번호변경 팝업 ivo
        pwChgIvo2: new ChgUserPwIvo(),      // 비밀번호변경 팝업 ivo - 저장할 리스트

        /* 비밀번호 변경 변수 */
        isVisibleRule: false,               // 비밀번호 tool-tip 보이기 여부
        ruleChk: {
            msg: '',
            type: '',
            error1: { msg: '비밀번호는 8자리 이상이어야 합니다.', type: 'danger' },
            error2: { msg: '비밀번호에는 [영문/특수기호/숫자]가 포함되어야 합니다.', type: 'danger' },
            error3: { msg: '비밀번호에는 사용자ID를 포함할 수 없습니다.', type: 'danger' },
            error4: { msg: '비밀번호에는 생년월일을 포함할 수 없습니다.', type: 'danger' },
            error5: { msg: '비밀번호에는 휴대폰번호를 포함할 수 없습니다.', type: 'danger' },
            success: { msg: '비밀번호 생성규칙에 적합합니다.', type: 'success', },
        },

        /* 비밀번호 확인 변수 */
        isHiddenChk: false,     // 비밀번호 확인 tool-tip 보이기 여부
        chkType: {
            msg: '',
            type: '',
            error: { msg: '비밀번호가 일치하지 않습니다.', type: 'danger' },
            success: { msg: '비밀번호가 일치합니다.', type: 'success' },
        },

        canChgDvcd: false,  // 비밀번호 변경가능여부

        /* 패스워드 복잡도 상태 */
        complexity: {
            percentage: '33',
            status: 'exception',
        },

        /* 패스워드 변경 완료 후 잠시 버튼 disabled */
        disableButton: false,
    },

})

/* 화면진입 시 */
onMounted( () => {
    userName.value.focus()
})

/* 질문 답변하기 팝업 open */
const onClickOpenQuestion = async () => {

    /* 필수입력 체크 */
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

    /* 질문 조회 */
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
        state.pop1.ivo.passwdHint = retData.data.passwdHint

        /* 질문답변하기 팝업 열렸 때 */
        onMoundPop1()

    }

}

/* 팝업창 바깥 클릭 시 */
const handleClose = () => {
}

/* 아이디 찾기 페이지로 이동 */
const onClickToSearchId = () => {
    router.push('/login/idSearch')
}

/* 로그인 페이지로 이동 */
const onClickToLogin = () => {
    router.push('/')
}


/***************************************************************************
 * 질문 답변 팝업
 ***************************************************************************/

/* 질문답변하기 팝업 열렸 때 */
const onMoundPop1 = () => {

    state.pop1.ivo.userId = state.ivo.userId
    state.pop1.ivo.userName = state.ivo.userName
    state.pop1.ivo.email = state.ivo.email

    setTimeout(()=>{ passwdHintAnswer.value.focus() }, 50)
}

/* 답변 숨기기 체크 시 */
watch(
    () => isAnswerHide.value,
    () => {

        if(isAnswerHide.value == true) {
            passwdHintAnswerType.value = 'password'
        }
        else {
            passwdHintAnswerType.value = 'text'
        }

    }
)

/* 인증하기 버튼 클릭 */
const onClickAuthPw = async () => {

    /* 필수입력 체크 */
    if(state.pop1.ivo.passwdHintAnswer == '') {
        ElMessage({
            type: 'error',
            message: '정답을 입력하세요.',
        })
        passwdHintAnswer.value.focus()
        return
    }

    /* 정답 조회 */
    const retData = await Api.post("/api/search/searchPwAnswer", state.pop1.ivo)

    /* 정답이 다른 경우 */
    if(retData.data.userId == null) {
        ElMessage({
            type: 'error',
            message: '정답이 일치하지 않습니다.',
        })
        return
    }
    else {

        /* 사용자 정보 세팅 */
        state.pop2.pwChgIvo1.userName       = retData.data.userName
        state.pop2.pwChgIvo1.userId         = retData.data.userId
        state.pop2.pwChgIvo1.birthDate      = retData.data.birthDate
        state.pop2.pwChgIvo1.phoneNumber    = retData.data.phoneNumber
        state.pop2.pwChgIvo1.email          = retData.data.email

        ElMessage({
            type: 'success',
            grouping: true,
            message: '인증이 완료되었습니다. 비밀번호를 변경해주세요.',
        })

        setTimeout(()=>{ state.isOpen.Question = false }, 40)
        setTimeout(()=>{ state.isOpen.chgPasswd = true }, 80)

        setTimeout(()=>{ chgPasswd.value.focus() }, 100)

    }
}


/***************************************************************************
 * 비밀번호 변경 팝업
 ***************************************************************************/

/* 생년월일 포맷 */
watch(
    () => state.pop2.pwChgIvo1.birthDate,
    (newValue) => {
        const formattedDate = newValue.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1-$2-$3')
        state.pop2.pwChgIvo1.birthDate = formattedDate      // 포맷된 날짜를 상태에 설정
    }
)

/* 전화번호 포맷 */
watch(
    () => state.pop2.pwChgIvo1.phoneNumber,
    (newValue) => {
        const formattedPhnum = newValue.replace(/\D/g, '').replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3')
        state.pop2.pwChgIvo1.phoneNumber = formattedPhnum   // 포맷된 전화번호를 상태에 설정
    }
)

/**
 * [비밀번호 복잡도 기준]
 * 
 * 취약 : 변경 조건을 만족하지 못함 
 * 보통 : 변경 조건을 전부 만족하지만 연속된 순차적인 숫자가 있는경우
 * 안전 : 변경 조건을 전부 만족하고 연속된 순차적인 숫자가 없을때
 */

/* 비밀번호 복잡도 세팅 */
watch(
    () => [state.pop2.pwChgIvo1.userPasswd, state.pop2.pwChgIvo1.userId, state.pop2.pwChgIvo1.birthDate, state.pop2.pwChgIvo1.phoneNumber, state.pop2.ruleChk.type],
    () => {

        /* 비밀번호 변경 창이 열렸을 때만 수행 */
        if(state.isOpen.chgPasswd == true) {

            /* 취약 : 변경 조건을 만족하지 못함 */
            if(state.pop2.ruleChk.type != 'success') {
                state.pop2.complexity.percentage = '33'
                state.pop2.complexity.status = 'exception'
            }
            else {

                /* 숫자를 연달아 세번 사용한 경우 */
                if(state.pop2.pwChgIvo1.userPasswd.match(/\d{3,}/g) != null) {

                    for(let i in state.pop2.pwChgIvo1.userPasswd.match(/\d{3,}/g)) {

                        /**
                         * ex: number = ['123', '456']
                         */
                        const number = state.pop2.pwChgIvo1.userPasswd.match(/\d{3,}/g)[i] as string
                        let numberList = []

                        for(let i = 0; i < number.length; i++) {
                            numberList.push(number.substring(i, i + 1))
                        }

                        /* 보통 : 변경 조건을 전부 만족하지만 순차적인 숫자를 사용한 경우 */
                        if(numberList.length >= 3) {

                            let index = 0
                            for(let i = 0; i < numberList.length; i++) {

                                if(Number(numberList[i]) + 1 == Number(numberList[i + 1])) {
                                    index++
                                }
                                else {
                                    index = 0
                                }

                                if(index == 2)  {
                                    break
                                }
                            }

                            if(index == 2) {
                                state.pop2.complexity.percentage = '66'
                                state.pop2.complexity.status = 'warning'
                            }
                            else {
                                state.pop2.complexity.percentage = '100'
                                state.pop2.complexity.status = 'success'
                            }

                        }
                        else {
                            state.pop2.complexity.percentage = '100'
                            state.pop2.complexity.status = 'success'
                        }

                    }

                }
                /* 숫자를 연달아 세번 사용한 경우 */
                else {
                    state.pop2.complexity.percentage = '100'
                    state.pop2.complexity.status = 'success'
                }

            }

        }
        
    }
)

/* 비밀번호 변경 시 이벤트 */
watch(
    () => [state.pop2.pwChgIvo1.userPasswd, state.pop2.pwChgIvo1.userId, state.pop2.pwChgIvo1.birthDate, state.pop2.pwChgIvo1.phoneNumber, state.pop2.ruleChk.type],
    () => {

        /* 비밀번호 변경 창이 열렸을 때만 수행 */
        if(state.isOpen.chgPasswd == true) {

            const hasLettersRegExp: RegExp = /[a-zA-Z]/         // 영문자 포함 정규식
            const hasSpecialCharsRegExp: RegExp = /[^\w\s]/     // 특수문자 포함 정규식
            const hasNumbersRegExp: RegExp = /\d/               // 숫자 포함 정규식

            if(state.pop2.pwChgIvo1.userPasswd.length > 0) {

                /* 비밀번호 8자리 체크 */
                if(state.pop2.pwChgIvo1.userPasswd.length < 8) {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.error1.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.error1.type
                }
                /* 영문 포함여부 확인 */
                else if(!hasLettersRegExp.test(state.pop2.pwChgIvo1.userPasswd) || !hasSpecialCharsRegExp.test(state.pop2.pwChgIvo1.userPasswd) || !hasNumbersRegExp.test(state.pop2.pwChgIvo1.userPasswd)) {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.error2.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.error2.type
                }
                /* 유저ID 포함여부 확인 */
                else if (state.pop2.pwChgIvo1.userPasswd.includes(state.pop2.pwChgIvo1.userId)) {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.error3.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.error3.type
                }
                /* 생년월일 8자리 포함여부 확인 */
                else if( (state.pop2.pwChgIvo1.userPasswd.includes(state.pop2.pwChgIvo1.birthDate.replaceAll('-', '').substring(0,4)) 
                    || state.pop2.pwChgIvo1.userPasswd.includes(state.pop2.pwChgIvo1.birthDate.replaceAll('-', '').substring(4,8)) )
                    && state.pop2.pwChgIvo1.birthDate.length != 0 && state.pop2.pwChgIvo1.birthDate.length >= 8) {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.error4.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.error4.type
                }
                /* 휴대폰번호 포함여부 확인 */
                else if( (state.pop2.pwChgIvo1.userPasswd.includes(state.pop2.pwChgIvo1.phoneNumber.replaceAll('-', '').substring(0,4)) 
                    || state.pop2.pwChgIvo1.userPasswd.includes(state.pop2.pwChgIvo1.phoneNumber.replaceAll('-', '').substring(4,8)) )
                    && state.pop2.pwChgIvo1.phoneNumber.length != 0) {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.error5.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.error5.type
                }
                /* 성공일때 */
                else {
                    state.pop2.isVisibleRule = true

                    state.pop2.ruleChk.msg = state.pop2.ruleChk.success.msg
                    state.pop2.ruleChk.type = state.pop2.ruleChk.success.type
                }
            }
            /* 입력을 전부 지우면 tool-tip 없애기 */
            else {
                state.pop2.isVisibleRule = false
            }

        }
        
    }
)

/* 비밀번호 확인 */
watch(
    () => [state.pop2.pwChgIvo1.userPasswd, state.pop2.pwChgIvo1.userPasswdChk],
    () => {

        /* 비밀번호 변경 창이 열렸을 때만 수행 */
        if(state.isOpen.chgPasswd == true) {

            /* 비밀번호 확인 입력 시에만 수행 */
            if(state.pop2.pwChgIvo1.userPasswdChk.length != 0) {

                /* 비밀번호 확인이 다를 때 */
                if(state.pop2.pwChgIvo1.userPasswdChk != state.pop2.pwChgIvo1.userPasswd) {
                    state.pop2.isHiddenChk = true
                    state.pop2.canChgDvcd = false

                    state.pop2.chkType.msg = state.pop2.chkType.error.msg
                    state.pop2.chkType.type = state.pop2.chkType.error.type
                }
                /* 비밀번호 확인이 같을 때 */
                if(state.pop2.pwChgIvo1.userPasswdChk == state.pop2.pwChgIvo1.userPasswd) {
                    state.pop2.isHiddenChk = true
                    state.pop2.canChgDvcd = true

                    state.pop2.chkType.msg = state.pop2.chkType.success.msg
                    state.pop2.chkType.type = state.pop2.chkType.success.type
                }

            }
            else {
                state.pop2.isHiddenChk = false
                state.pop2.canChgDvcd = false
            }
        }


    }
)

/* 다음에하기 버튼 클릭 시 */
const onClickOpenCinfirm2 = () => {
    state.isOpen.confirm2 = true    // 확인창 Open
}

/* 다음에하기 확인창에서 확인 버튼 누를 시 팝업창 닫기 */
const onClickConfirm = async () => {
    state.isOpen.confirm2 = false
    
    setTimeout(()=> {
        state.isOpen.chgPasswd = false
    }, 100)

    clearPopupItem2()   // 비밀번호 변경 팝업 변수 초기화
}

/* 비밀번호 변경 팝업 변수 초기화 */
const clearPopupItem2 = () => {

    state.pop2.pwChgIvo1 = new ChgUserPwIvo()       // ivo 초기화
    state.pop2.isVisibleRule = false                // tool-tip visible 초기화
    state.pop2.isHiddenChk = false                  // tool-tip visible 초기화
    state.pop2.ruleChk.msg = ''                     // tool-tip 메시지 초기화
    state.pop2.ruleChk.type = ''                    // tool-tip 타입 초기화
    state.pop2.chkType.msg = ''                     // tool-tip 메시지 초기화
    state.pop2.chkType.type = ''                    // tool-tip 타입 초기화
    state.pop2.complexity.percentage = '33'         // 패스워드 복잡도 초기화
    state.pop2.complexity.status = 'exception'      // 패스워드 복잡도 초기화
    state.pop2.canChgDvcd = false                   // 비밀번호 변경가능여부 초기화

}

/* 비밀번호 변경 - 확인창 열기 */
const onClickOpenCinfirm1 = () => {

    if(state.pop2.canChgDvcd == false) {
        ElMessage({
            type: 'error',
            grouping: true,
            message: '비밀번호 확인을 진행해주세요.',
        })
        return
    }

    state.isOpen.confirm1 = true    // 확인창 오픈
}

/* 비밀번호 변경 확인 */
const onClickChangePw = async () => {

    /* 변경(저장)할 파라미터 세팅 */
    state.pop2.pwChgIvo2.userName       = state.pop2.pwChgIvo1.userName
    state.pop2.pwChgIvo2.userId         = state.pop2.pwChgIvo1.userId
    state.pop2.pwChgIvo2.birthDate      = state.pop2.pwChgIvo1.birthDate
    state.pop2.pwChgIvo2.phoneNumber    = state.pop2.pwChgIvo1.phoneNumber
    state.pop2.pwChgIvo2.email          = state.pop2.pwChgIvo1.email
    state.pop2.pwChgIvo2.userPasswd     = Common.encypt(state.pop2.pwChgIvo1.userPasswd)       // 패스워드(암호화)

    /* 비밀번호 변경 */
    let retData = await Api.post("/api/search/updateUserPw", state.pop2.pwChgIvo2)
    
    if(retData.data == true) {

        state.isOpen.confirm1 = false
    
        ElMessage({
            type: 'success',
            grouping: true,
            message: '비밀번호가 변경되었습니다.',
        })
        setTimeout(()=>{ 
            ElMessage({
                type: 'success',
                grouping: true,
                message: '로그인창으로 이동합니다.',
            })
        }, 1500)
        
        state.pop2.disableButton = true

        setTimeout(()=>{ router.push('/') }, 4500)

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
.custom-card {
    --el-card-border-color: var(--el-border-color-light);
    --el-card-border-radius: 4px;
    --el-card-padding: 12px;
    --el-card-bg-color: var(--el-fill-color-blank);
}
/* 체크박스 색상 */
.el-checkbox {
    --el-checkbox-checked-text-color: #7C4DFF;
    --el-checkbox-checked-input-border-color: #70039b;
    --el-checkbox-checked-bg-color: #70039b;
}
.el-alert--info.is-light {
    background-color: #EDE7F6;
    color: #4527A0;
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
.el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
    color: #ffffff;
    cursor: not-allowed;
    background-image: none;
    background-color: rgb(201 200 200);
    border-color: rgb(181 181 181);
}
/* description 라벨 색상 */
:deep(.my-label) {
    background: var(--el-color-success-light-9) !important;
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