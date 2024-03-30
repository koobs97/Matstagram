<template>
    <el-form label-width="120px">
        <el-form-item>
            <br><br>
        </el-form-item>

        <div class="container_header">
            <el-button color="#626aef" class="top_button" :plain="plainId" @click="onClickToSearchId">아이디 찾기</el-button>
            <el-button color="#626aef" class="top_button" :plain="plainPasswd">비밀번호 찾기</el-button>
        </div>

        <div class="container">
            <el-card shadow="never" style="height: 80%; width: 40%;">

                <el-form label-width="120px" label-position="left">
                    <el-row :gutter="0">

                        <!-- todo -->
                    </el-row>
                </el-form>

            </el-card>
        </div>

        <br>

        <div class="container">
            <el-card shadow="never" style="height: 80%; width: 40%;">

                <el-form label-width="120px" label-position="left">
                    <el-row :gutter="0">

                        
                    </el-row>
                </el-form>

            </el-card>
        </div>

        <div style="padding-top: 10px; font-size: 15px;">
            <el-button :icon="ArrowLeft" >이전</el-button>
            <el-button >다음<el-icon class="el-icon--right"><ArrowRight /></el-icon></el-button>
        </div>

        <div class="text-container">
            <el-text class="mx-1">CopyRight © BONSANG. All Rights Reserved.</el-text>
        </div>

    </el-form>
</template>
<script lang="ts" setup>
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router        = useRouter()   // router

const plainId       = ref(true)     // 찾기 구분 선택
const plainPasswd   = ref(false)    // 찾기 구분 선택

// 아이디 찾기 페이지로 이동
const onClickToSearchId = () => {
    router.push('/login/idSearch')
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