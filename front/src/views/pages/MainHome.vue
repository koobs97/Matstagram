<template>
    <el-row>
        <el-col :span="12">
            <el-card shadow="never" style="height: 510px;">
                <FullCalendar />
            </el-card>
            <el-card shadow="never" style="height: 280px; margin-top: 20px;">
            </el-card>
        </el-col>
        <el-col :span="12">
            <el-card shadow="never" style="height: 810px; margin-left: 20px;">
                <KakaoMap />
            </el-card>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import FullCalendar from '../../components/FullCalendar.vue';
import KakaoMap from '../../components/KakaoMap.vue';
</script>

<style scoped>

</style>