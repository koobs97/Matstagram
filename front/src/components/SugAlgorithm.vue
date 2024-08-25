<template>
    <el-row>
        <el-col :span="24" style="margin-bottom: 0px;">
            <el-form-item label="검색어" style="font-size: 12px; margin-bottom: 0px;">
                <el-input v-model="state.searchTitle" style="font-size: 12px; height: 24px; width: calc(100% - 36px);" disabled></el-input>
                <el-button :icon="RefreshRight" style="width: 24px; height: 24px; margin-left: 4px;" @click="getApiBlogData()" />
            </el-form-item>
        </el-col>
        <el-divider class="divider"></el-divider>

        <el-col :span="24" style="margin-bottom: 0px; margin-top: 0px; text-align: left;">
            <el-text style="font-size: 11px;">{{ state.apiItems.item0.bloggername }} : {{ state.apiItems.item0.postdate }}</el-text>
        </el-col>
        <el-col :span="24" style="margin-bottom: 0px; margin-top: 0px; text-align: left;">
            <el-link style="font-size: 12px; font-weight: bold;" :href="state.apiItems.item0.link">{{ state.apiItems.item0.title }}</el-link>
        </el-col>

    </el-row>
</template>
<script lang="ts" setup>
import { RefreshRight } from '@element-plus/icons-vue';
import { onMounted, reactive } from 'vue';
import { Api } from '../common/common';

const state = reactive({
    searchTitle: '비오는날 음식추천' as string,
    apiItems: {
        item0 : {
            bloggerlink : '' as string,
            bloggername : '' as string,
            description : '' as string,
            link        : '' as string,
            postdate    : '' as string,
            title       : '' as string,
        },
        item1 : {
            bloggerlink : '' as string,
            bloggername : '' as string,
            description : '' as string,
            link        : '' as string,
            postdate    : '' as string,
            title       : '' as string,
        },
        item2 : {
            bloggerlink : '' as string,
            bloggername : '' as string,
            description : '' as string,
            link        : '' as string,
            postdate    : '' as string,
            title       : '' as string,
        },
    },
    test: [] as any,
})

// 화면진입 시
onMounted( async () => {
    getApiBlogData()
})

const getApiBlogData = async () => {

    const retData = await Api.post("/api/naver/blog", '흐림', false)

    console.log(retData)

    state.apiItems.item0 = retData.data.items['0']
    state.apiItems.item1 = retData.data.items['1']
    state.apiItems.item2 = retData.data.items['2']

    state.apiItems.item0.postdate = state.apiItems.item0.postdate.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')
    state.apiItems.item1.postdate = state.apiItems.item1.postdate.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')
    state.apiItems.item2.postdate = state.apiItems.item2.postdate.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3')

    state.apiItems.item0.title = state.apiItems.item0.title.replaceAll('<b>', '').replaceAll('</b>', '')
    state.apiItems.item1.title = state.apiItems.item1.title.replaceAll('<b>', '').replaceAll('</b>', '')
    state.apiItems.item2.title = state.apiItems.item2.title.replaceAll('<b>', '').replaceAll('</b>', '')

}
</script>
<style>
.divider {
    margin-top: 8px;
    margin-bottom: 8px;
    width: 100%;
}
</style>