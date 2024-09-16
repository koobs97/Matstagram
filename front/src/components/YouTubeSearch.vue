<template>
    <el-row>
        <el-col :span="24" style="text-align: left;">
            <el-text style="font-weight: bold; font-size: 14px;">
                <el-icon><VideoPlay /></el-icon>
                영상추천
            </el-text>
        </el-col>
        <el-divider class="divider" />
        <el-col :span="11" style="margin-top: 4px;">
            <el-input style="font-size: 12px; height: 32px;" :suffix-icon="Mouse">
                <template #append>
                    <el-button :icon="Search" />
                </template>
            </el-input>
        </el-col>
        <el-col :span="1" style="margin-top: 4px; margin-left: 2px;">
            <el-button style="width: 16px;">
                <el-icon><RefreshRight /></el-icon>
            </el-button>
        </el-col>
        <el-col :span="11"></el-col>
        <el-col :span="9" style="margin-top: 12px; text-align: left;">
            <iframe
            id='player'
            type='text/html'
            width='180'
            height='130'
            :src="state.src"
            ></iframe>
        </el-col>
        <el-col :span="15" style="margin-top: 12px; text-align: left;">
            <el-text style="font-size: 13px; font-weight: bold;">
                {{ state.title }}
            </el-text>
            <br>
            <el-text style="font-size: 11px;">
                {{ state.channelTitle }}
            </el-text>
            <br>
            <br>
            <el-text style="font-size: 11px;">
                {{ state.description }}
            </el-text>
        </el-col>
        <el-col :span="5" />
        <el-col :span="12" style="text-align: center; margin-left: 40px;">
            <el-pagination
                size="small"
                layout="prev, pager, next"
                :total="50"
                class="el-pagination"
                @current-change="handleCurrentChange"
            />
        </el-col>
        <el-col :span="5" style="text-align: right; margin-top: 4px;">
            <el-button 
                style="
                    background-color: #ff0000; 
                    width: 20px; height: 16px; 
                    padding: 0px; 
                    font-size: 12px; 
                    color: #ffffff; 
                    border-color: #ff0000; 
                    font-weight: bold;">
                <el-icon><CaretRight /></el-icon>
                </el-button>
            <el-link 
                :underline="false" 
                href="https://www.youtube.com/" 
                target="_blank" 
                style="
                    font-size: 12px; 
                    margin-left: 3px; 
                    font-weight: bold;">
                YouTube
            </el-link>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { CaretRight, Mouse, RefreshRight, Search, VideoPlay } from '@element-plus/icons-vue';
import axios from "axios";
import { onMounted, reactive } from 'vue';

const state = reactive({
    vid: '' as any,
    src: '' as any,
    title: '' as any,
    channelTitle: '' as any,
    description: '' as any,
    vidlist: [] as any,
})

// youTube 동영상 호출
onMounted( async () => {

    const params = {
        key: 'AIzaSyC-pY7809fvpNIpXyh5saS3OGz35drTsXE',
        part: 'snippet',
        q: '안양맛집',
        maxResults: 15,
    }

    const response = await axios.get('https://www.googleapis.com/youtube/v3/search', {params});
    console.log(response)

    for(let i in response.data.items) {
        state.vidlist.push(response.data.items[i])
    }

    state.src = 'http://www.youtube.com/embed/' + state.vidlist[0]['id']['videoId'] + '?enablejsapi=1&origin=http://example.com'
    state.title = state.vidlist[0]['snippet']['title']
    state.channelTitle = state.vidlist[0]['snippet']['channelTitle']
    state.description = state.vidlist[0]['snippet']['description']
})

const handleCurrentChange = (val: number) => {
    console.log(`${val} items per page`)
}

</script>
<style scoped>
.el-pagination {
    --el-pagination-font-size: 14px;
    --el-pagination-bg-color: var(--el-fill-color-blank);
    --el-pagination-text-color: var(--el-text-color-primary);
    --el-pagination-border-radius: 2px;
    --el-pagination-button-color: var(--el-text-color-primary);
    --el-pagination-button-width: 32px;
    --el-pagination-button-height: 32px;
    --el-pagination-button-disabled-color: var(--el-text-color-placeholder);
    --el-pagination-button-disabled-bg-color: var(--el-fill-color-blank);
    --el-pagination-button-bg-color: var(--el-fill-color);
    --el-pagination-hover-color: #673AB7;
    --el-pagination-font-size-small: 12px;
    --el-pagination-button-width-small: 24px;
    --el-pagination-button-height-small: 24px;
    --el-pagination-item-gap: 16px;
    white-space: nowrap;
    color: var(--el-pagination-text-color);
    font-size: var(--el-pagination-font-size);
    font-weight: 400;
    display: flex;
    align-items: center;
}
</style>