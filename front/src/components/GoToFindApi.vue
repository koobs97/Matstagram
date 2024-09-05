<template>
    <el-row>
        <el-col :span="15" style="text-align: right;">
            <el-text style="font-size: 24px; margin-top: 0px;">Open</el-text>
            <el-text style="font-size: 24px; font-weight: bold; margin-top: 0px;"> API</el-text>
        </el-col>
        <el-col :span="9" style="text-align: left; margin-top: 2px;">
            <el-link link :icon="Search" style="font-size: 24px; margin-left: 12px;" :underline="false" href="https://github.com/dl0312/open-apis-korea" target="_blank"></el-link>
        </el-col>

        
    </el-row>
</template>
<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue';
</script>