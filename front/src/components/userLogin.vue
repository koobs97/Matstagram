<template>
    <el-form label-width="120px">
        <el-form-item>
            <br><br><br><br>
        </el-form-item>
        <el-row :gutter="0">
            <el-col :span="24">
                <div class="container">
                    <el-card shadow="never" style="height: 80%; width: 20%;">
                        <el-form label-width="120px">
                            <el-row :gutter="0">

                                <el-col :span="24">
                                    <p class="title">Matstagram</p>
                                </el-col>

                                <el-form-item/>

                                <el-col :span="24">
                                    <el-form-item/>
                                    <el-input
                                        v-model="state.ivo.userId"
                                        ref="id"
                                        @keyup.enter="onClickLogin"
                                        style="height: 40px; font-size: 15px;"
                                        placeholder="사용자ID 또는 이메일"
                                        />
                                </el-col>

                                <el-col :span="24">
                                    <el-form-item/>
                                    <el-input
                                        v-model="state.ivo.userPasswd"
                                        ref="passwd"
                                        @keyup.enter="onClickLogin"
                                        style="height: 40px; font-size: 15px;"
                                        type="password"
                                        placeholder="비밀번호"
                                        />
                                </el-col>

                                <el-col :span="24">
                                    <el-form-item/>
                                    <el-button color="#311B92" class="login" @click="onClickLogin">로그인</el-button>
                                </el-col>

                                <el-form-item/>

                                <el-col :span="24">
                                    <el-button type="info" link @click="onClickSearchId">아이디 찾기</el-button>
                                    <el-button type="info" link @click="onClickSearchPw">비밀번호 찾기</el-button>
                                </el-col>

                            </el-row> 
                        </el-form>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="24">
                <el-form-item/>
                <div class="container">
                    <el-card shadow="never" style="width: 20%; height: 60px;">
                        <el-button type="primary" style="font-weight: bold; color: #9575CD;" link @click="onClickGoJoinPage">회원가입</el-button>
                    </el-card>
                </div>
            </el-col>
        </el-row>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>

<script lang="ts" setup>

import { ElMessage } from 'element-plus'
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { Api, Common } from '../common/common'
import { userStore } from '../store/userStore'
import { UserLoginIvo } from '../vo/ivo/UserLoginIvo'

const router        = useRouter()
const userStoreObj  = userStore()

const id        = ref()
const passwd    = ref()

const state = reactive({
    ivo: new UserLoginIvo(),
    ivoParam: new UserLoginIvo()
})

// 화면진입 시
onMounted(() => {
    id.value.focus()
})

// 로그인
const onClickLogin = async () => {
    
    if(state.ivo.userId == '') {
        ElMessage({
            type: 'error',
            message: '사용자ID 또는 이메일을 입력하세요.',
        })
        id.value.focus()
    }
    else if(state.ivo.userPasswd == '') {
        ElMessage({
            type: 'error',
            message: '비밀번호를 입력하세요.',
        })
        passwd.value.focus()
    }
    else{

        /* 넘겨줄 user 정보 세팅 */
        state.ivoParam.userId = state.ivo.userId
        state.ivoParam.userPasswd = Common.encypt(state.ivo.userPasswd) // 패스워드 암호화

        let retData = await Api.post("/api/login", state.ivoParam)

        if(retData.data.longinRslt == '1') {
            userStoreObj.setUsername('구본상')
            router.push('/')
        }
        else if(retData.data.longinRslt == '2') {
            ElMessage({
                type: 'error',
                message: '사용자ID 또는 비밀번호를 확인하세요.',
            })
        }
    }
}

// 회원가입 페이지 이동
const onClickGoJoinPage = () => {
    router.push('/login/join')
}

// 아이디 찾기 페이지 이동
const onClickSearchId = () => {
    router.push('/login/idSearch')
}

// 아이디 찾기 페이지 이동
const onClickSearchPw = () => {
    router.push('/login/pwSearch')
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

@font-face {
    font-family: 'dancing';
    src:url('../../public/fonts/DancingScript-VariableFont_wght.ttf')
}

.title {
    font-size: 40px;
    font-weight: bold;
    font-family: 'dancing';
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.login {
    width: 100%;
    height: 45px; 
    font-weight: bold; 
    font-size: 16px;
}

.text-container {
  position: fixed;
  bottom: 2%;
  left: 0;
  right: 0;
  text-align: center;
}


</style>
