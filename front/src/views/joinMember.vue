<!--
 * [회원가입 화면]
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
        
        <el-row :gutter="0">
            <el-col :span="24">
                
                <div class="container">
                    <el-card shadow="never" style="height: 80%; width: 30%;">
                        <div style="font-size: 10px; text-align: left; height: 10px;">
                            <el-button :icon="ArrowLeft" @click="onClickToLogin">로그인페이지</el-button>
                        </div>
                        <el-form label-width="120px" style="height: 50px;">
                            <p class="title">Matstagram</p>
                        </el-form>
                        <el-form label-width="120px">
                            <el-row :gutter="0">
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-tooltip
                                        class="box-item"
                                        effect="dark"
                                        placement="left-start"
                                        raw-content
                                        :content="state.idChk.msg"
                                        :visible="state.isVisibleIdChk"
                                    >
                                        <el-input
                                            ref="userId"
                                            v-model="state.ivo.userId"
                                            maxlength="20"
                                            show-word-limit
                                            placeholder="사용자ID"
                                        />
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle0"
                                    />
                                </el-col>
                                <el-col :span="5" style="margin-bottom: 4px; text-align: left">
                                    <el-button 
                                        type="info" 
                                        plain 
                                        style="font-weight: bold; margin-left: 4px; width: 100%;" 
                                        @click="onClickChkId">
                                        중복확인
                                    </el-button>
                                </el-col>
                                <el-col :span="17" style="margin-bottom: 4px">
                                        <el-tooltip
                                            effect="dark"
                                            placement="left-start"
                                            raw-content
                                            :content="state.ruleChk.msg"
                                            :visible="state.isVisibleRule"
                                        >
                                        <div class="mt-4">
                                            <el-popover placement="right" :width="300" trigger="click">
                                                <template #reference>
                                                    <el-input
                                                        ref="userPasswd"
                                                        v-model="state.ivo.userPasswd"
                                                        maxlength="50"
                                                        type="password"
                                                        placeholder="비밀번호"
                                                    >
                                                        <template #append>
                                                            <el-button :icon="InfoFilled" />
                                                        </template>
                                                    </el-input>
                                                </template>
                                                <div>
                                                    <el-text style="font-weight: bold;">
                                                        <el-icon style="margin-right: 1px;">
                                                            <InfoFilled />
                                                        </el-icon>
                                                        비밀번호 생성규칙
                                                    </el-text>
                                                    <el-divider style="margin-bottom: 0px; margin-top: 0px;"></el-divider>
                                                        <el-tag style="margin-bottom: 4px; margin-top: 4px; width: 275px; justify-content: left; color: #4527A0;">1. 8자리 이상</el-tag>
                                                        <el-tag style="margin-bottom: 4px; width: 275px; justify-content: left; color: #4527A0;">2. 영문/특수기호/숫자 전부 포함</el-tag>
                                                        <el-tag style="margin-bottom: 4px; width: 275px; justify-content: left; color: #4527A0;">3. ID와 동일하게 사용불가</el-tag>
                                                        <el-tag style="margin-bottom: 4px; width: 275px; justify-content: left; color: #4527A0;">4. 생년월일/전화번호 포함 불가</el-tag>
                                                    <el-divider style="margin-bottom: 0px; margin-top: 0px;"></el-divider>
                                                    <el-tag style="margin-bottom: 4px; margin-top: 4px; width: 275px; justify-content: left; background-color: #F5F5F5; color: #212121;">생성규칙에 맞춰 비밀번호를 생성해주세요</el-tag>
                                                </div>
                                            </el-popover>
                                        </div>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle1"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-tooltip
                                        effect="dark"
                                        placement="left-start"
                                        raw-content
                                        
                                        :content="state.chkType.msg"
                                        :visible="state.isHiddenChk"
                                    >
                                        <el-input
                                            ref="passwdChk"
                                            v-model="state.passwdChk"
                                            maxlength="50"
                                            type="password"
                                            placeholder="비밀번호 확인"
                                        />
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle2"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-input
                                        ref="passwdHint"
                                        maxlength="50"
                                        v-model="state.ivo.passwdHint"
                                        placeholder="비밀번호 찾기 질문"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle3"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-input
                                        ref="passwdHintAnswer"
                                        maxlength="10"
                                        show-word-limit
                                        v-model="state.ivo.passwdHintAnswer"
                                        placeholder="정답 입력"
                                    />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle4"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-input
                                        ref="email1"
                                        v-model="state.ivo.email"
                                        maxlength="50"
                                        placeholder="이메일"
                                    />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle5"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <el-input
                                        ref="userName"
                                        v-model="state.ivo.userName"
                                        maxlength="8"
                                        show-word-limit
                                        placeholder="사용자명"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle6"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="17" style="margin-bottom: 4px">
                                    <div style="display: flex;">
                                        <el-input
                                            ref="birthDate"
                                            maxlength="10"
                                            style="width: 74%;"
                                            v-model="state.ivo.birthDate"
                                            placeholder="생년월일 ex) 2024.02.01"
                                        />
                                    <el-radio-group v-model="gender" ref="genderCode" style="margin-left: 6px;">
                                        <el-radio-button label="남" value="M" />
                                        <el-radio-button label="여" value="W" />
                                    </el-radio-group>
                                    </div>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle7"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="4" style="margin-bottom: 4px">
                                    <el-input
                                        ref="ph1"
                                        v-model="state.ph1"
                                        placeholder="010"
                                    />
                                </el-col>
                                <el-col :span="13" style="margin-bottom: 4px; text-align: right">
                                    <el-input
                                        ref="ph2"
                                        v-model="state.ph2"
                                        style="width: 98%;"
                                        placeholder="휴대폰 뒷자리"
                                    />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button 
                                        :icon="Select" 
                                        :class="state.style.btnStyle8"
                                    />
                                </el-col>
                                <el-col :span="5" />
                                <el-col :span="8" style="text-align: left; padding-bottom: 10px;">
                                    <el-checkbox 
                                        label="[필수] 인증약관동의" 
                                        ref="agreed"
                                        v-model="isAgreed"
                                        size="large" 
                                        style="font-weight: bold; height: 50px; width: 100%;" 
                                        border 
                                    />
                                </el-col>
                                <el-col :span="16" style="padding-left: 10px;">
                                    <el-collapse v-model="activeNames" style="width: 97%;">
                                        <el-collapse-item title="약관보기" name="1">
                                            <div style="display: flex;">
                                                <div style="width: 50%; text-align: left;">
                                                    <el-checkbox v-model="state.chk.val1" label="개인정보 이용"/>
                                                    <el-checkbox v-model="state.chk.val2" label="개인정보수집 동의"/>
                                                    <el-checkbox v-model="state.chk.val3" label="고유식별정보 처리"/>
                                                </div>
                                                <div style="width: 50%; text-align: left;">
                                                    <el-checkbox v-model="state.chk.val4" label="휴대폰번호 이용약관"/>
                                                    <el-checkbox v-model="state.chk.val5" label="기타 사항"/>
                                                </div>
                                            </div>
                                        </el-collapse-item>
                                    </el-collapse>
                                </el-col>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                            </el-row> 
                        </el-form>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="24">
                <el-form-item/>
                <div class="container">
                    <el-form label-width="120px" style="height: 80%; width: 30%;">
                        <el-form>
                            <el-button color="#311B92" class="login" @click="onClickJoin" >회원가입</el-button>

                            <!-- 회원가입진행여부 확인창 Dialog -->
                            <el-dialog v-model="state.isOpen.confirm" align-center style="width: 280px; height: 140px; border-radius: 8px;">
                                <template #header>
                                    <div style="text-align: left;">
                                        <h4 style="margin-top: 0px; margin-bottom: 18px; font-size: 16px">
                                            <el-icon style="margin-right: 2px; font-size: 12px"><InfoFilled /></el-icon>
                                            알림창
                                        </h4>
                                    <el-text>회원가입을 진행하시겠습니까?</el-text>
                                    </div>
                                    <div style="text-align: left; margin-top: 20px">
                                        <el-button type="primary" color="#7E57C2" @click="onClickjoinComfirm">확인</el-button>
                                        <el-button style="margin-left: 4px;" @click="state.isOpen.confirm = false">취소</el-button>
                                    </div>
                                </template>
                            </el-dialog>
                            <!-- 회원가입진행여부 확인창 Dialog -->

                        </el-form>
                    </el-form>
                </div>
            </el-col>
        </el-row>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>

<script lang="ts" setup>
import { ArrowLeft, InfoFilled, Select } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import { Api, Common } from '../common/common'
import { joinMemberIvo } from '../vo/ivo/joinMemberIvo'

const router = useRouter()

const isAgreed      = ref(false)
const activeNames   = ref('') as any

/* 필수입력 focus */
const userId            = ref()
const userPasswd        = ref()
const passwdChk         = ref()
const passwdHint        = ref()
const passwdHintAnswer  = ref()
const email1            = ref()
const userName          = ref()
const birthDate         = ref()
const genderCode        = ref()
const ph1               = ref()
const ph2               = ref()
const agreed            = ref()

const mail       = ref('')
const gender     = ref('')

/* state defined */
const state = reactive({
    ivo: new joinMemberIvo(),       // 입력 ivo
    ivoParam: new joinMemberIvo(),  // 저장 ivo
    isOpen: {
        confirm: false
    },
    joinYn: false,
    ph1: '',
    ph2: '',
    canSignDvcd: {
        isUserIdDup: true,
        isPasswdRule: false,
        ispasswdChk: false,
    },
    isVisibleIdChk: false,
    idChk: {
        msg: '',
        type: '',
        default: {
            msg: '중복확인 후 진행해주세요.',
            type: 'danger',
        },
        valid: {
            msg: '아이디는 5자리 이상이어야 합니다.',
            type: 'danger',
        },
        error: {
            msg: '사용할 수 없는 아이디입니다.',
            type: 'danger',
        },
        success: {
            msg: '사용 가능한 아이디입니다.',
            type: 'success',
        },
    },
    ruleChk: {
        msg: '',
        type: 'danger',
        error1: {
            msg: '비밀번호는 8자리 이상이어야 합니다.',
            type: 'danger',
        },
        error2: {
            msg: '비밀번호에는 [영문/특수기호/숫자]가 포함되어야 합니다.',
            type: 'danger',
        },
        error3: {
            msg: '비밀번호에는 사용자ID를 포함할 수 없습니다.',
            type: 'danger',
        },
        error4: {
            msg: '비밀번호에는 생년월일을 포함할 수 없습니다.',
            type: 'danger',
        },
        error5: {
            msg: '비밀번호에는 휴대폰번호를 포함할 수 없습니다.',
            type: 'danger',
        },
        success: {
            msg: '비밀번호 생성규칙에 적합합니다.',
            type: 'success',
        },
    },
    isVisibleRule: false,
    passwdChk: '' as string,
    chkType: {
        msg: '',
        type: '',
        error: {
            msg: '비밀번호가 일치하지 않습니다.',
            type: 'danger',
        },
        success: {
            msg: '비밀번호가 일치합니다.',
            type: 'success',
        },
    },
    isHiddenChk: false,
    chk: {
        val1: false,
        val2: false,
        val3: false,
        val4: false,
        val5: false,
    } as any,
    click: false,
    style: {
        btnStyle0: 'btn_default',
        btnStyle1: 'btn_default',
        btnStyle2: 'btn_default',
        btnStyle3: 'btn_default',
        btnStyle4: 'btn_default',
        btnStyle5: 'btn_default',
        btnStyle6: 'btn_default',
        btnStyle7: 'btn_default',
        btnStyle8: 'btn_default',
    }
})

// 아이디 중복확인
const onClickChkId = async () => {

    state.isVisibleIdChk = true

    if(state.ivo.userId.trim().length < 5) {
        state.idChk.msg = state.idChk.valid.msg
        state.canSignDvcd.isUserIdDup = true

        return
    }

    const retData = await Api.post("/api/join/chkUserId", state.ivo, false)

    if(retData.data.idDupYn == 'Y') {
        state.style.btnStyle0 = 'btn_error'
        state.idChk.msg = state.idChk.error.msg
        state.canSignDvcd.isUserIdDup = true
    }
    else if(retData.data.idDupYn == 'N') {
        state.style.btnStyle0 = 'btn_success'
        state.idChk.msg = state.idChk.success.msg
        state.canSignDvcd.isUserIdDup = false
    }
}

/* 아이디 */
watch(
    () => state.ivo.userId,
    () => {
        state.isVisibleIdChk = true
        state.idChk.msg = state.idChk.default.msg
        state.idChk.type = state.idChk.default.type
        state.canSignDvcd.isUserIdDup = true
    }
)

/* 비밀번호 */
watch(
    () => [state.ivo.userPasswd, state.ivo.userId, state.ivo.birthDate, state.ivo.phoneNumber],
    () => {

        const hasLettersRegExp: RegExp = /[a-zA-Z]/         // 영문자 포함 정규식
        const hasSpecialCharsRegExp: RegExp = /[^\w\s]/     // 특수문자 포함 정규식
        const hasNumbersRegExp: RegExp = /\d/               // 숫자 포함 정규식

        if(state.ivo.userPasswd.length > 0) {
            // 비밀번호 8자리 체크
            if(state.ivo.userPasswd.length < 8) {
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = false

                state.style.btnStyle1 = 'btn_error'
                state.ruleChk.msg = state.ruleChk.error1.msg
                state.ruleChk.type = state.ruleChk.error1.type
            }
            // 영문 포함여부 확인
            else if(!hasLettersRegExp.test(state.ivo.userPasswd) || !hasSpecialCharsRegExp.test(state.ivo.userPasswd) || !hasNumbersRegExp.test(state.ivo.userPasswd)) {
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = false

                state.style.btnStyle1 = 'btn_error'
                state.ruleChk.msg = state.ruleChk.error2.msg
                state.ruleChk.type = state.ruleChk.error2.type
            }
            // 유저ID 포함여부 확인
            else if (state.ivo.userPasswd.includes(state.ivo.userId) && state.ivo.userId.length != 0) {
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = false

                state.style.btnStyle1 = 'btn_error'
                state.ruleChk.msg = state.ruleChk.error3.msg
                state.ruleChk.type = state.ruleChk.error3.type
            }
            // 생년월일 8자리 포함여부 확인
            else if( (state.ivo.userPasswd.includes(state.ivo.birthDate.replaceAll('.', '').substring(0,4)) 
                || state.ivo.userPasswd.includes(state.ivo.birthDate.replaceAll('.', '').substring(4,8)) )
                && state.ivo.birthDate.length != 0 && state.ivo.birthDate.length >= 8) {
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = false

                state.style.btnStyle1 = 'btn_error'
                state.ruleChk.msg = state.ruleChk.error4.msg
                state.ruleChk.type = state.ruleChk.error4.type
            }
            // 휴대폰번호 포함여부 확인
            else if( (state.ivo.userPasswd.includes(state.ivo.phoneNumber.replaceAll('-', '').substring(0,4)) 
                || state.ivo.userPasswd.includes(state.ivo.phoneNumber.replaceAll('-', '').substring(4,8)) )
                && state.ivo.phoneNumber.length != 0) {
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = false

                state.style.btnStyle1 = 'btn_error'
                state.ruleChk.msg = state.ruleChk.error5.msg
                state.ruleChk.type = state.ruleChk.error5.type
            }
            else {
                state.style.btnStyle1 = 'btn_success'
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = true

                state.ruleChk.msg = state.ruleChk.success.msg
                state.ruleChk.type = state.ruleChk.success.type
            }
        }
        else if(state.ivo.userPasswd.length == 0) {
            state.isVisibleRule = false
            state.style.btnStyle1 = 'btn_default'
        }
    }
)


/* 비밀번호 확인 */
watch(
    () => [state.ivo.userPasswd, state.passwdChk],
    () => {
        /* 비밀번호 확인이 다를 때 */
        if(state.passwdChk.length != 0 && state.passwdChk != state.ivo.userPasswd) {
            state.isHiddenChk = true
            state.canSignDvcd.ispasswdChk = false

            state.style.btnStyle2 = 'btn_error'
            state.chkType.msg = state.chkType.error.msg
            state.chkType.type = state.chkType.error.type
        }
        /* 비밀번호 확인이 같을 때 */
        if(state.passwdChk.length != 0 && state.passwdChk == state.ivo.userPasswd) {
            state.isHiddenChk = true
            state.canSignDvcd.ispasswdChk = true

            state.style.btnStyle2 = 'btn_success'
            state.chkType.msg = state.chkType.success.msg
            state.chkType.type = state.chkType.success.type
        }

        if(state.passwdChk.length == 0) {
            state.style.btnStyle2 = 'btn_default'
            state.isHiddenChk = false
            state.canSignDvcd.ispasswdChk = false
        }
    }
)

/* 생년월일 포맷 */
watch(
    () => state.ivo.birthDate,
    (newValue) => {
        const formattedDate = newValue.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')
        state.ivo.birthDate = formattedDate // 포맷된 날짜를 상태에 설정
    }
)

/* 전화번호 포맷 */
watch(
    () => state.ph2,
    (newValue) => {
        const formattedPhnum = newValue.replace(/\D/g, '').replace(/^(\d{4})(\d{4})$/, '$1-$2')
        state.ph2 = formattedPhnum  // 포맷된 전화번호를 상태에 설정
    }
)

/* 기타 입력정보 입력에 따른 오류색상 컨트롤 */
watch(
    () => [state.ivo, gender.value, state.ph1, state.ph2],
    () => {

        /* 비밀번호 찾기 질문 */
        if(state.ivo.passwdHint.trim().length == 0) {
            state.style.btnStyle3 = 'btn_default'
        }
        else {
            state.style.btnStyle3 = 'btn_success'
        }

        /* 정답 */
        if(state.ivo.passwdHintAnswer.trim().length == 0) {
            state.style.btnStyle4 = 'btn_default'
        }
        else {
            state.style.btnStyle4 = 'btn_success'
        }

        /* 이메일 */
        if(state.ivo.email.trim().length == 0) {
            state.style.btnStyle5 = 'btn_default'
        }
        else {
            state.style.btnStyle5 = 'btn_success'
        }

        /* 사용자명 */
        if(state.ivo.userName.trim().length == 0) {
            state.style.btnStyle6 = 'btn_default'
        }
        else {
            state.style.btnStyle6 = 'btn_success'
        }

        /* 생년월일 */
        if(state.ivo.birthDate.trim().length == 0) {
            state.style.btnStyle7 = 'btn_default'
        }
        else if(state.ivo.birthDate.trim().length == 0 || state.ivo.birthDate.trim().length != 10) {
            state.style.btnStyle7 = 'btn_error'
        }
        else {
            state.style.btnStyle7 = 'btn_success'
        }

        /* 성별 */
        if(gender.value.trim().length == 0) {
            state.style.btnStyle7 = 'btn_default'
        }
        else {
            state.style.btnStyle7 = 'btn_success'
        }

        /* 전화번호 */
        if(state.ph1.trim().length == 0 && state.ph2.trim().length == 0) {
            state.style.btnStyle8 = 'btn_default'
        }
        else if(state.ph1.trim().length == 0 || state.ph1.trim().length != 3) {
            state.style.btnStyle8 = 'btn_error'
        }
        else if(state.ph2.trim().length == 0 || state.ph2.trim().length != 9) {
            state.style.btnStyle8 = 'btn_error'
        }
        else {
            state.style.btnStyle8 = 'btn_success'
        }
    },
    {deep: true}
)

/* 약관보기 컨트롤 */
watch(
    () => isAgreed.value,
    () => {
        if(isAgreed.value == true) {
            if(activeNames.value == '') {
                activeNames.value = ['', '1']
            }

            state.chk.val1 = true
            state.chk.val2 = true
            state.chk.val3 = true
            state.chk.val4 = true
            state.chk.val5 = true
        }
        if(isAgreed.value == false && state.click == false) {
            state.chk.val1 = false
            state.chk.val2 = false
            state.chk.val3 = false
            state.chk.val4 = false
            state.chk.val5 = false
        }

        state.click = false
    }
)

/* 약관상세에 따른 전체동의 컨트롤 */
watch(
    () => [state.chk.val1, state.chk.val2, state.chk.val3, state.chk.val4, state.chk.val5],
    () => {
        for (const key in state.chk) {
            if(state.chk[key] == false) {
                isAgreed.value = false
                state.click = true

                return
            }
        }
        isAgreed.value = true
    }
)

/* 회원가입버튼 */
const onClickJoin = async () => {

    if(state.canSignDvcd.isUserIdDup == true) {
        ElMessage({
            type: 'error',
            message: '사용자ID를 확인해주세요.',
        })
        state.style.btnStyle0 = 'btn_error'
        userId.value.focus()
        return
    }
    else{
        state.style.btnStyle0 = 'btn_success'
    }

    if(state.canSignDvcd.isPasswdRule == false) {
        ElMessage({
            type: 'error',
            message: '비밀번호를 다시 설정해주세요.',
        })
        state.style.btnStyle1 = 'btn_error'
        userPasswd.value.focus()
        return
    }
    else{
        state.style.btnStyle1 = 'btn_success'
    }

    if(state.canSignDvcd.ispasswdChk == false) {
        ElMessage({
            type: 'error',
            message: '비밀번호 확인이 올바르지 않습니다.',
        })
        state.style.btnStyle2 = 'btn_error'
        passwdChk.value.focus()
        return
    }
    else{
        state.style.btnStyle2 = 'btn_success'
    }

    if(state.ivo.passwdHint.trim().length == 0) {
        ElMessage({
            type: 'error',
            message: '비밀번호 찾기 질문을 설정해주세요.',
        })
        state.style.btnStyle3 = 'btn_error'
        passwdHint.value.focus()
        return
    }
    else{
        state.style.btnStyle3 = 'btn_success'
    }

    if(state.ivo.passwdHintAnswer.trim().length == 0) {
        ElMessage({
            type: 'error',
            message: '비밀번호 찾기 질문에 대한 정답을 설정해주세요.',
        })
        state.style.btnStyle4 = 'btn_error'
        passwdHintAnswer.value.focus()
        return
    }
    else{
        state.style.btnStyle4 = 'btn_success'
    }

    if(state.ivo.email.trim().length == 0) {
        ElMessage({
            type: 'error',
            message: '이메일을 확인해주세요.',
        })
        state.style.btnStyle5 = 'btn_error'
        email1.value.focus()
        return
    }
    else{
        state.style.btnStyle5 = 'btn_success'
    }
    
    if(state.ivo.userName.trim().length == 0) {
        ElMessage({
            type: 'error',
            message: '사용자명을 입력해주세요.',
        })
        state.style.btnStyle6 = 'btn_error'
        userName.value.focus()
        return
    }
    else{
        state.style.btnStyle6 = 'btn_success'
    }

    if(state.ivo.birthDate.trim().length == 0 || state.ivo.birthDate.trim().length != 10) {
        ElMessage({
            type: 'error',
            message: '생년월일을 확인해주세요.',
        })
        state.style.btnStyle7 = 'btn_error'
        birthDate.value.focus()
        return
    }
    else if(gender.value == '') {
        ElMessage({
            type: 'error',
            message: '성별을 선택해주세요.',
        })
        state.style.btnStyle7 = 'btn_error'
        genderCode.value.focus()
        return
    }
    else{
        state.style.btnStyle7 = 'btn_success'
    }

    if(state.ph1.trim().length == 0 || state.ph1.trim().length != 3) {
        ElMessage({
            type: 'error',
            message: '휴대폰 번호를 확인해주세요.',
        })
        state.style.btnStyle8 = 'btn_error'
        ph1.value.focus()
        return
    }
    else if(state.ph2.trim().length == 0 || state.ph2.trim().length != 9) {
        ElMessage({
            type: 'error',
            message: '휴대폰 번호를 확인해주세요.',
        })
        state.style.btnStyle8 = 'btn_error'
        ph2.value.focus()
        return
    }
    else{
        state.style.btnStyle8 = 'btn_success'
    }

    if(isAgreed.value == false) {
        ElMessage({
            type: 'error',
            message: '인증약관동의에 체크해주세요.',
        })
        activeNames.value = ['', '1']
        return
    }

    /* 확인창 Open */
    state.isOpen.confirm = true

}

// 확인 버튼 누를 시 회원가입 진행
const onClickjoinComfirm = async () => {

    /* 로그인 파라미터 세팅 */
    state.ivoParam.userId           = state.ivo.userId                          // 사용자ID
    state.ivoParam.userName         = state.ivo.userName                        // 사용자명
    state.ivoParam.email            = state.ivo.email + mail.value              // 이메일
    state.ivoParam.birthDate        = state.ivo.birthDate.replaceAll('.', '')   // 생년월일
    state.ivoParam.genderCode       = gender.value                              // 성별코드
    state.ivoParam.phoneNumber      = state.ph1 + state.ph2.replaceAll('-', '') // 핸드폰번호
    state.ivoParam.userPasswd       = Common.encypt(state.ivo.userPasswd)       // 패스워드(암호화)
    state.ivoParam.termsAgreYn      = 'Y'                                       // 약관동의여부
    state.ivoParam.passwdHint       = state.ivo.passwdHint                      // 비밀번호 찾기 질문
    state.ivoParam.passwdHintAnswer = state.ivo.passwdHintAnswer                // 정답

    // 공통코드 테이블 만들기
    const retData = await Api.post("/api/join/joinUser", state.ivoParam)
    if(retData.data.userDupYn == 'Y') {
        Common.confirm('가입한 이력이 있습니다. 비밀번호를 찾으시겠습니까?', 
            {
                callback: (params: any) => {
                    if(params == 'confirm') {
                        alert('추후')
                    }
                }
            }
        )
    }
    if(retData.data.joinResult == 1) {
        ElMessage({
            type: 'success',
            message: '회원가입에 성공했습니다. 로그인창으로 이동합니다.',
        })
        router.push('/')
    }

}

// 로그인 페이지로 이동
const onClickToLogin = () => {
    router.push('/')
}

</script>


<style scoped>
.title {
    font-size: 25px;
    font-weight: bold;
    font-family: 'dancing';
}
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: -50px;
}
.login {
    width: 100%;
    height: 45px; 
    font-weight: bold; 
    font-size: 16px;
    margin-top: 50px;
}

.text-container {
    position: fixed;
    bottom: 2%;
    left: 0;
    right: 0;
    text-align: center;
}

.btn_default {
    background-color: #F5F5F5;
    width: 90%;
}

.btn_error {
    background-color: #FFCDD2; 
    width: 90%;
}

.btn_success {
    background-color: #C8E6C9; 
    width: 90%;
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
    --el-button-active-text-color: var(--el-button-hover-text-color);
    --el-button-active-border-color: #9575CD;
    --el-button-active-bg-color: var(--el-button-hover-bg-color);
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-hover-link-text-color: var(--el-color-info);
    --el-button-active-color: var(--el-text-color-primary);
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

/* 태그 색상 */
.el-tag {
    --el-tag-bg-color: var(--el-color-primary-light-9);
    --el-tag-border-color: #dfdfdf;
    --el-tag-hover-color: var(--el-color-primary);
    --el-tag-text-color: var(--el-color-primary);
    background-color: var(--el-tag-bg-color);
    border-color: var(--el-tag-border-color);
    color: var(--el-tag-text-color);
    display: inline-flex;
    justify-content: center;
    align-items: center;
    vertical-align: middle;
    height: 24px;
    padding: 0 9px;
    font-size: var(--el-tag-font-size);
    line-height: 1;
    border-width: 1px;
    border-style: solid;
    border-radius: var(--el-tag-border-radius);
    box-sizing: border-box;
    white-space: nowrap;
    --el-icon-size: 14px;
    border: none;
}

.el-radio-button {
    --el-radio-button-checked-bg-color: #4527A0;
    --el-radio-button-checked-text-color: var(--el-color-white);
    --el-radio-button-checked-border-color: #4527A0;
    --el-radio-button-disabled-checked-fill: var(--el-border-color-extra-light);
}

</style>