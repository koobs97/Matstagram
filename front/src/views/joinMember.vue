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

                                <el-col :span="17" style="margin-bottom: 8px">
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
                                        placeholder="사용자ID"
                                        />
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle0"></el-button>
                                </el-col>
                                <el-col :span="5" style="margin-bottom: 8px; text-align: left">
                                    <el-button type="info" plain style="font-weight: bold; margin-left: 4px; width: 100%;" @click="onClickChkId">중복확인</el-button>
                                </el-col>

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-tooltip
                                        class="box-item"
                                        effect="dark"
                                        placement="right-start"
                                        content="<strong>비밀번호 생성규칙</strong>
                                        <br>1. 8자리 이상
                                        <br>2. 영문/특수기호/숫자 전부 포함
                                        <br>3. ID와 동일하게 사용불가
                                        <br>4. 생년월일/전화번호 포함 불가"
                                        raw-content
                                    >
                                    <el-tooltip
                                        effect="dark"
                                        placement="left-start"
                                        raw-content
                                        :content="state.ruleChk.msg"
                                        :visible="state.isVisibleRule"
                                    >
                                    <el-input
                                        ref="userPasswd"
                                        v-model="state.ivo.userPasswd"
                                        type="password"
                                        placeholder="비밀번호"
                                        />
                                    </el-tooltip>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle1"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
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
                                        type="password"
                                        placeholder="비밀번호 확인"
                                        />
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle2"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-input
                                        ref="passwdHint"
                                        v-model="state.ivo.passwdHint"
                                        placeholder="비밀번호 찾기 질문"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle3"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-input
                                        ref="passwdHintAnswer"
                                        v-model="state.ivo.passwdHintAnswer"
                                        placeholder="정답 입력"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle4"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-input
                                        ref="email1"
                                        v-model="state.ivo.email"
                                        placeholder="이메일"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle5"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-input
                                        ref="userName"
                                        v-model="state.ivo.userName"
                                        placeholder="사용자명"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle6"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-input
                                        ref="birthDate"
                                        maxlength="10"
                                        v-model="state.ivo.birthDate"
                                        placeholder="생년월일 ex) 2024.02.01"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle7"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="17" style="margin-bottom: 8px">
                                    <el-select
                                        ref="genderCode"
                                        v-model="gender"
                                        class="m-2"
                                        placeholder="성별"
                                    >
                                        <el-option
                                        v-for="item in genOptions"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                        />
                                    </el-select>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle8"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="4" style="margin-bottom: 8px">
                                    <el-input
                                        ref="ph1"
                                        v-model="state.ph1"
                                        placeholder="010"
                                        />
                                </el-col>
                                <el-col :span="13" style="margin-bottom: 8px; text-align: right">
                                    <el-input
                                        ref="ph2"
                                        v-model="state.ph2"
                                        style="width: 98%;"
                                        placeholder="휴대폰 뒷자리"
                                        />
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <el-button :icon="Select" :class="state.style.btnStyle9"></el-button>
                                </el-col>
                                <el-col :span="5" />

                                <el-col :span="8" style="text-align: left; padding-bottom: 10px;">
                                    <el-checkbox label="[필수] 인증약관동의" 
                                                ref="agreed"
                                                v-model="isAgreed"
                                                size="large" 
                                                style="font-weight: bold; 
                                                height: 50px; 
                                                width: 100%;" 
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
import { ArrowLeft, Select } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import { Api, Common } from '../common/common'
import { joinMemberIvo } from '../vo/ivo/joinMemberIvo'

const router = useRouter()

const isAgreed = ref(false)
const activeNames = ref('') as any

// 필수입력 focus
const userId = ref()
const userPasswd = ref()
const passwdChk = ref()
const passwdHint = ref()
const passwdHintAnswer = ref()
const email1 = ref()
const userName = ref()
const birthDate = ref()
const genderCode = ref()
const ph1 = ref()
const ph2 = ref()
const agreed = ref()

const state = reactive({
    ivo: new joinMemberIvo(),
    ivoParam: new joinMemberIvo(),
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
        btnStyle9: 'btn_default',
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

    let retData = await Api.post("/api/join/chkUserId", state.ivo, false)

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



// 아이디
watch(
    () => state.ivo.userId,
    () => {
        state.isVisibleIdChk = true
        state.idChk.msg = state.idChk.default.msg
        state.idChk.type = state.idChk.default.type
        state.canSignDvcd.isUserIdDup = true
    }
)

// 비밀번호
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
                && state.ivo.birthDate.length != 0) {
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
            else if(state.ivo.userPasswd.length == 0) {
                state.isVisibleRule = true
            }
            else {
                state.style.btnStyle1 = 'btn_success'
                state.isVisibleRule = true
                state.canSignDvcd.isPasswdRule = true

                state.ruleChk.msg = state.ruleChk.success.msg
                state.ruleChk.type = state.ruleChk.success.type
            }
        }
    }
)


// 비밀번호 확인
watch(
    () => [state.ivo.userPasswd, state.passwdChk],
    () => {
        // 비밀번호 확인이 다를 때
        if(state.passwdChk.length != 0 && state.passwdChk != state.ivo.userPasswd) {
            state.isHiddenChk = true
            state.canSignDvcd.ispasswdChk = false

            state.style.btnStyle2 = 'btn_error'
            state.chkType.msg = state.chkType.error.msg
            state.chkType.type = state.chkType.error.type
        }
        // 비밀번호 확인이 같을 때
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

// 생년월일 포맷
watch(
    () => state.ivo.birthDate,
    (newValue) => {
        const formattedDate = newValue.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')
        // 포맷된 날짜를 상태에 설정
        state.ivo.birthDate = formattedDate
    }
)

// 전화번호 포맷
watch(
    () => state.ph2,
    (newValue) => {
        const formattedPhnum = newValue.replace(/\D/g, '').replace(/^(\d{4})(\d{4})$/, '$1-$2')
        // 포맷된 전화번호를 상태에 설정
        state.ph2 = formattedPhnum
    }
)

// 기타 입력정보 입력에 따른 오류색상 컨트롤
watch(
    () => state.ivo,
    () => {

        // 비밀번호 찾기 질문
        if(state.ivo.passwdHint.trim().length == 0) {
            state.style.btnStyle3 = 'btn_default'
        }
        else {
            state.style.btnStyle3 = 'btn_success'
        }

        // 정답
        if(state.ivo.passwdHintAnswer.trim().length == 0) {
            state.style.btnStyle4 = 'btn_default'
        }
        else {
            state.style.btnStyle4 = 'btn_success'
        }

        // 이메일
        if(state.ivo.email.trim().length == 0) {
            state.style.btnStyle5 = 'btn_default'
        }
        else {
            state.style.btnStyle5 = 'btn_success'
        }

        // 사용자명
        if(state.ivo.userName.trim().length == 0) {
            state.style.btnStyle6 = 'btn_default'
        }
        else {
            state.style.btnStyle6 = 'btn_success'
        }

        // 생년월일
        if(state.ivo.birthDate.trim().length == 0) {
            state.style.btnStyle7 = 'btn_default'
        }
        else if(state.ivo.birthDate.trim().length == 0 || state.ivo.birthDate.trim().length != 10) {
            state.style.btnStyle7 = 'btn_error'
        }
        else {
            state.style.btnStyle7 = 'btn_success'
        }

        // 성별
        if(gender.value.trim().length == 0) {
            state.style.btnStyle8 = 'btn_default'
        }
        else {
            state.style.btnStyle8 = 'btn_success'
        }

        // 전화번호
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

// 약관보기 컨트롤
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

// 약관상세에 따른 전체동의 컨트롤
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

const mail = ref('')

const gender = ref('')
const genOptions = [
    {
        value: 'M',
        label: '남자',
    },
    {
        value: 'W',
        label: '여자',
    },
    {
        value: 'N',
        label: '선택안함',
    },
]

// 회원가입버튼
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
    else{
        state.style.btnStyle7 = 'btn_success'
    }

    if(gender.value == '') {
        ElMessage({
            type: 'error',
            message: '성별을 선택해주세요.',
        })
        state.style.btnStyle8 = 'btn_error'
        genderCode.value.focus()
        return
    }
    else{
        state.style.btnStyle8 = 'btn_success'
    }

    if(state.ph1.trim().length == 0 || state.ph1.trim().length != 3) {
        ElMessage({
            type: 'error',
            message: '휴대폰 번호를 확인해주세요.',
        })
        state.style.btnStyle9 = 'btn_error'
        ph1.value.focus()
        return
    }
    else if(state.ph2.trim().length == 0 || state.ph2.trim().length != 9) {
        ElMessage({
            type: 'error',
            message: '휴대폰 번호를 확인해주세요.',
        })
        state.style.btnStyle9 = 'btn_error'
        ph2.value.focus()
        return
    }
    else{
        state.style.btnStyle9 = 'btn_success'
    }

    if(isAgreed.value == false) {
        ElMessage({
            type: 'error',
            message: '인증약관동의에 체크해주세요.',
        })
        activeNames.value = ['', '1']
        return
    }

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
</style>