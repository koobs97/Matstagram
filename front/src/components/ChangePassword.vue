<template>
    <div style="text-align: left; margin-top: 0px; margin-bottom: 5px;" >
        <el-text style="font-weight: bold;"><el-icon><CaretRight /></el-icon>사용자정보</el-text>
    </div>
    <!-- 사용자정보 -->
    <div style="height: 100%; width: 100%; text-align: left; margin-bottom: 5px">
        <el-descriptions :column="2" border>
            <el-descriptions-item label-align="left" align="left">
                <template #label>
                    <div class="cell-item">
                        <el-icon style="margin-right: 4px;"><User /></el-icon>
                        사용자명
                    </div>
                </template>
                {{ state.user.userName }}
            </el-descriptions-item>
            <el-descriptions-item label-align="center" align="center">
                <template #label>
                    <div style="text-align: center;">
                        성별
                    </div>
                </template>
                <el-tag plain style="color: #4527A0;">
                    {{ state.user.genderCode }}
                </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label-align="left" align="left" :span="2">
                <template #label>
                    <div class="cell-item">
                        <el-icon style="margin-right: 4px;"><UserFilled /></el-icon>
                        아이디
                    </div>
                </template>
                {{ state.user.userId }}
            </el-descriptions-item>
        </el-descriptions>
    </div>
    <!-- 사용자정보 -->

    <!-- 안내문구 -->
    <el-card class="custom-card" shadow="never" style="height: 100%; width: 100%; background-color: #f0f0f0; text-align: left; margin-bottom: 5px">
        <el-row :gutter="0">
            <el-col :span="1"/>
            <el-col :span="21" style="text-align: left; margin-bottom: 2px;">
                <el-button style="height: 8px; width: 8px; padding: 0px;" :icon="InfoFilled"></el-button>
                <el-text style="margin-left: 15px;">저희 사이트를 사용해주셔서 감사합니다.</el-text>
            </el-col> 
            <el-col :span="2"/>
            <el-col :span="1"/>
            <el-col :span="21" style="text-align: left; margin-bottom: 2px;">
                <el-text style="margin-left: 22px;">비밀번호 생성규칙은 다음과 같습니다.</el-text>
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
        <el-descriptions-item label="권장">대문자(영문) 소문자(영문)이 한개 이상 포함</el-descriptions-item>
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
                :content="state.ruleChk.msg"
                :visible="state.isVisibleRule"
            >
                <el-input 
                    v-model="state.pwChgIvo.userPasswd" 
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
                :content="state.chkType.msg"
                :visible="state.isHiddenChk"
            >
                <el-input 
                    v-model="state.pwChgIvo.userPasswdChk" 
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
                :percentage="state.complexity.percentage" :status="state.complexity.status" />
        </el-col>
        <el-col :span="2"/>
    </div>
    <!-- 패스워드 복잡도 -->

    <div style="text-align: center; margin-top: 50px">
        <el-button type="primary" @click="onClickOpenCinfirm1" color="#7E57C2" :disabled="state.disableButton">비밀번호변경</el-button>
        <el-button style="margin-left: 4px;" @click="onClickOpenCinfirm2" :disabled="state.disableButton">다음에하기</el-button>
    </div>

    <!-- 비밀번호 변경하기 확인창  -->
    <el-dialog 
        v-model="state.isOpen.confirm1" 
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
        :show-close="false" 
        align-center
        style="width: 350px; height: 205px; border-radius: 4px;"
    >
        <template #header>
            <div style="margin-left: 20px;">
                <el-icon style="font-size: 34px"><WarningFilled /></el-icon>
                <h4 style="margin-top: 0px; margin-bottom: 0px; font-size: 14px">
                    확인창
                </h4>
            </div>
        </template>
        <ConfirmDialog 
            :bodyMessage="'다음에 변경하시겠습니까?'"
            @close-dialog="onClickConfirm" />
    </el-dialog>
    <!-- 다음에하기 확인창  -->

</template>
<script lang="ts" setup>
import { CaretRight, Hide, InfoFilled, Unlock, WarningFilled } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { defineEmits, onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { Api, Common } from '../common/common';
import { userStore } from '../store/userStore';
import { ChgUserPwIvo } from '../vo/ivo/ChgUserPwIvo';
import ConfirmDialog from './ConfirmDialog.vue';

const router        = useRouter()   // router
const userStoreObj  = userStore()

interface userState {
    userId      : string,
    userName    : string,
    email       : String,
    phoneNumber : string,
    birthDate   : string,
    genderCode  : string,
}

/* state reactive */
const state = reactive({
    pwChgIvo: new ChgUserPwIvo(),      // 비밀번호변경 팝업 ivo - 저장할 리스트
    user: {
            userId      : '' as string,
            userName    : '' as string,
            email       : '' as string,
            phoneNumber : '' as string,
            birthDate   : '' as string,
            genderCode  : '' as string,
    } as userState,
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
    /* 패스워드 복잡도 상태 */
    complexity: {
        percentage: '33',
        status: 'exception',
    },
    /* 팝업 오픈 변수 */
    isOpen: {
        confirm1: false,            // 비밀번호변경 확인창
        confirm2: false,            // 다음에 하기 확인창
    },
    canChgDvcd: false,              // 비밀번호 변경가능여부
    disableButton: false,           // 패스워드 변경 완료 후 잠시 버튼 disabled
})

/* 화면진입 시 */
onMounted( () => {
    state.user = userStoreObj.getUserInfo

    /* 생년월일 포맷 */
    state.user.birthDate = state.user.birthDate.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1-$2-$3')

    /* 전화번호 포맷 */
    state.user.phoneNumber = state.user.phoneNumber.replace(/\D/g, '').replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3')
})

/* 비밀번호 변경 - 확인창 열기 */
const onClickOpenCinfirm1 = () => {

    if(state.canChgDvcd == false) {
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
    state.pwChgIvo.userName       = state.user.userName
    state.pwChgIvo.userId         = state.user.userId
    state.pwChgIvo.birthDate      = state.user.birthDate
    state.pwChgIvo.phoneNumber    = state.user.phoneNumber
    state.pwChgIvo.email          = state.user.email as string
    state.pwChgIvo.userPasswd     = Common.encypt(state.pwChgIvo.userPasswd)       // 패스워드(암호화)

    /* 비밀번호 변경 */
    let retData = await Api.post("/api/search/updateUserPw", state.pwChgIvo)
    
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
        
        state.disableButton = true

        setTimeout(()=>{ router.push('/') }, 4500)

    }

}

/* 다음에하기 버튼 클릭 시 */
const onClickOpenCinfirm2 = () => {
    state.isOpen.confirm2 = true    // 확인창 Open
}

/* 다음에하기 확인창에서 확인 버튼 누를 시 팝업창 닫기 */
const onClickConfirm = async (ret : boolean) => {
    state.isOpen.confirm2 = false
    if(ret) {
        close()
    }
}

const emit = defineEmits(['close-dialog'])

const close = () => {
    emit('close-dialog');
};

</script>
<style scoped>
/* description 라벨 색상 */
:deep(.my-label) {
    background: var(--el-color-success-light-9) !important;
}
</style>