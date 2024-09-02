<template>
    <el-row>
        <el-col :span="24" style="margin-bottom: 0px;">
            <el-form-item style="margin-bottom: 4px;">
                <el-input v-model="state.ivo.input" style="font-size: 12px; height: 32px;" @keyup.enter="getApiBlogData()">
                    <template #append>
                        <el-button :icon="Search" @click="getApiBlogData()" />
                    </template>
                </el-input>
            </el-form-item>
        </el-col>
        <el-col :span="8" style="margin-bottom: 4px; text-align: left;">
            <el-button style="font-size: 11px; width: 80px; height: 20px; margin-left: 2px;" :icon="RefreshRight">옵션초기화</el-button>
        </el-col>
        <el-col :span="16" style="margin-bottom: 4px; text-align: right;">
            <el-button style="font-size: 11px; width: 70px; height: 20px; margin-left: 2px;" :icon="Tools">표시개수</el-button>
            <el-button style="font-size: 11px; width: 70px; height: 20px; margin-left: 2px;" :icon="Tools">시작위치</el-button>
            <el-button style="font-size: 11px; width: 70px; height: 20px; margin-left: 2px;" :icon="Tools">정렬방법</el-button>
        </el-col>

        <el-scrollbar always height="390px" style="width: 360px;" v-loading="loading" element-loading-background="rgba(255, 255, 255, 0.8)">
            <div v-for="(item, index) in state.apiItems" :key="index" style="width: 330px;">
                <el-divider class="divider" />
                <el-row>
                    <el-col :span="23" style="margin-bottom: 4px; margin-top: 0px; text-align: left;">
                        <el-text style="font-size: 11px;">
                        <el-icon style="font-size: 11px;">
                            <User />
                        </el-icon>
                        {{ item.bloggername }} : {{ item.postdate }}
                        </el-text>
                    </el-col>
                    <el-col :span="1">
                        <el-button link :icon="MoreFilled" style="font-size: 10px;"></el-button>
                    </el-col>
                </el-row>
                <el-col :span="24" style="margin-bottom: 4px; margin-top: 0px; text-align: left;">
                    <el-link style="font-size: 12px; font-weight: bold; color: #7C4DFF;" :href="item.link">{{ item.title }}</el-link>
                </el-col>
                <el-col :span="24" style="margin-bottom: 4px; margin-top: 0px; text-align: left;">
                    <el-link style="font-size: 12px;" :href="item.link">{{ item.description }}</el-link>
                </el-col>
            </div>
        </el-scrollbar>

        <el-col :span="24" style="text-align: right; margin-top: 12px;">
            <el-button style="background-color: #03c85f; width: 20px; height: 20px; padding: 0px; font-size: 12px; color: #ffffff; border-color: #03c85f;">N</el-button>
            <el-link :underline="false" href="https://www.naver.com/" target="_blank" style="font-size: 12px; margin-left: 3px; font-weight: bold;">
                Naver
            </el-link>
            <el-link :underline="false" href="https://developers.naver.com/main/" target="_blank" style="font-size: 12px; margin-left: 3px;">
                Developers
            </el-link>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { MoreFilled, RefreshRight, Search, Tools, User } from '@element-plus/icons-vue';
import { onMounted, reactive, ref } from 'vue';
import { Api } from '../common/common';
import { NaverOpenApiIvo } from '../vo/ivo/NaverOpenApiIvo';

const loading = ref(true)

const state = reactive({
    ivo : new NaverOpenApiIvo(),
    apiItems: {} as Record<string, any>,
    test: [] as any,
})

// 화면진입 시
onMounted( async () => {

    state.ivo.input = '안양시 맛집추천'

    getApiBlogData()
})

const getApiBlogData = async () => {

    const entities = {
        '&lt;': '<',
        '&gt;': '>',
        '&amp;': '&',
        '&quot;': '"',
        '&#39;': "'"
    } as any

    loading.value = true
    const retData = await Api.post("/api/naver/blog", state.ivo, false)

    console.log(retData)

    state.apiItems = {} as Record<string, any>
    for(let i in retData.data.items) {
        const itemIndex = Object.keys(state.apiItems).length;
        const newItemKey = `item${itemIndex}`;  // 새로운 아이템의 키 생성

        state.apiItems[newItemKey] = {
            bloggerlink : retData.data.items[i].bloggerlink,
            bloggername : retData.data.items[i].bloggername,
            description : retData.data.items[i].description.replaceAll('<b>', '').replaceAll('</b>', '').replace(/&lt;|&gt;|&amp;|&quot;|&#39;/g, (match: any) => entities[match]),
            link        : retData.data.items[i].link,
            postdate    : retData.data.items[i].postdate.replace(/\D/g, '').replace(/^(\d{4})(\d{2})(\d{2})$/, '$1.$2.$3'),
            title       : retData.data.items[i].title.replaceAll('<b>', '').replaceAll('</b>', '').replace(/&lt;|&gt;|&amp;|&quot;|&#39;/g, (match: any) => entities[match]),
        };
    }

    loading.value = false

}
</script>
<style>
.divider {
    margin-top: 8px;
    margin-bottom: 8px;
    width: 100%;
}
.el-naver-button {
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
    --el-button-hover-border-color: #03c85f;                            /* 마우스 올렸을 때 테두리 색 */
    --el-button-active-text-color: #9575CD;
    --el-button-active-border-color: #9575CD;
    --el-button-active-bg-color: var(--el-button-hover-bg-color);
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-hover-link-text-color: #9575CD;
    --el-button-active-color: #9575CD;
}
.el-link {
    --el-link-font-size: var(--el-font-size-base);
    --el-link-font-weight: var(--el-font-weight-primary);
    --el-link-text-color: var(--el-text-color-regular);
    --el-link-hover-text-color: #606266;
    --el-link-disabled-text-color: var(--el-text-color-placeholder);
}
.el-form-item {
    display: flex;
    --font-size: 12px;
    margin-bottom: 18px;
}
</style>