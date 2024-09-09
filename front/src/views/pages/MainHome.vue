<template>
    <el-row>
        <el-col :span="10">

        </el-col>
        <el-col :span="14">
            <el-card shadow="never" style="height: 580px; margin-left: 20px;">
                <KakaoMap />
            </el-card>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import KakaoMap from '../../components/KakaoMap.vue';
</script>

<style scoped>
</style>