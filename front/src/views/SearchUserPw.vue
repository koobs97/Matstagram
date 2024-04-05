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
                            <el-input />
                        </el-col>
                        <el-col :span="7"/>

                        <el-col :span="4"/>
                        <el-col :span="4" style="text-align: left; height: 40px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이메일주소">
                            </el-form-item>
                        </el-col>
                        <el-col :span="9" style="text-align: left; height: 40px;">
                            <el-input />
                        </el-col>
                        <el-col :span="7"/>

                        <el-col :span="4"/>
                        <el-col :span="4" style="text-align: left; height: 40px; margin-bottom: 15px;">
                            <el-form-item style="font-weight: bold;" label="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디">
                            </el-form-item>
                        </el-col>
                        <el-col :span="9" style="text-align: left; height: 40px;">
                            <el-input />
                        </el-col>
                        <el-col :span="7"/>
                        
                        <el-col :span="24">
                            <el-button type="info" plain :icon="ChatLineSquare" style="width: 158px;" @click="state.isOpen.Question = true">질문 답변하기</el-button>
                        </el-col>

                        <el-dialog v-model="state.isOpen.Question" align-center style="width: 500px; height: 280px; border-radius: 8px;">
                            <template #header>
                                <h4 style="margin-top: 5px; margin-bottom: 5px;">질문에 답변하기</h4>
                            </template>
                            <el-form :model="state">
                            <el-form-item label="질문">
                                <el-input autocomplete="off" disabled />
                            </el-form-item>
                            <el-form-item label="정답">
                                <el-input autocomplete="off" />
                            </el-form-item>
                            </el-form>
                            <template #footer>
                            <div class="dialog-footer">
                                <el-button type="primary" @click="state.isOpen.Question = false" color="#7E57C2">
                                인증
                                </el-button>
                                <el-button @click="state.isOpen.Question = false">취소</el-button>
                            </div>
                            </template>
                        </el-dialog>

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
import { ArrowLeft, ArrowRight, ChatLineSquare, Checked } from '@element-plus/icons-vue';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

const router        = useRouter()   // router

const plainId       = ref(true)     // 찾기 구분 선택
const plainPasswd   = ref(false)    // 찾기 구분 선택

// state reactive
const state = reactive({
    isOpen: {
        Question: false,
    },
})

// 아이디 찾기 페이지로 이동
const onClickToSearchId = () => {
    router.push('/login/idSearch')
}

// 로그인 페이지로 이동
const onClickToLogin = () => {
    router.push('/')
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

</style>