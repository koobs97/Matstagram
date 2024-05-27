<template>
    <div style="text-align: center; height: 60px;">
        <el-button style="font-size: 16px; font-weight: bold; color: #ffffff; background-color: #263c96; border-color: #263c96;">1호선</el-button>
        <el-row style="height: 760px;">
            <el-col :span="10" />
            <el-col :span="14" style="text-align: center;" :hidden="state.hiddenPage1">
                <el-timeline style="max-width: 100px; margin-top: 20px; margin-left: 12px;">
                    <el-timeline-item
                        v-for="(activity, index) in subway[0]"
                        v-model="currentPage"
                        v-model:page-size="pageSize"
                        :key="index"
                        :icon="activity.icon"
                        :type="activity.type"
                        :color="activity.color"
                        :size="activity.size"
                        style="text-align: left;"
                    >
                    <el-button link style="font-size: 14px;">{{ activity.station }}</el-button><br>
                    <el-text :style="activity.style1">{{ activity.transfer1 }}</el-text><br>
                    <el-text :style="activity.style2">{{ activity.transfer2 }}</el-text>
                    <el-text :style="activity.style3">{{ activity.transfer3 }}</el-text>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
            <el-col :span="14" style="text-align: center;" :hidden="state.hiddenPage2">
                <el-timeline style="max-width: 100px; margin-top: 20px; margin-left: 12px;">
                    <el-timeline-item
                        v-for="(activity, index) in subway[1]"
                        v-model="currentPage"
                        v-model:page-size="pageSize"
                        :key="index"
                        :icon="activity.icon"
                        :type="activity.type"
                        :color="activity.color"
                        :size="activity.size"
                        style="text-align: left;"
                    >
                    <el-button link style="font-size: 14px;">{{ activity.station }}</el-button><br>
                    <el-text :style="activity.style1">{{ activity.transfer1 }}</el-text><br>
                    <el-text :style="activity.style2">{{ activity.transfer2 }}</el-text><br>
                    <el-text :style="activity.style3">{{ activity.transfer3 }}</el-text>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
            <el-col :span="14" style="text-align: center;" :hidden="state.hiddenPage3">
                <el-timeline style="max-width: 100px; margin-top: 20px; margin-left: 12px;">
                    <el-timeline-item
                        v-for="(activity, index) in subway[2]"
                        v-model="currentPage"
                        v-model:page-size="pageSize"
                        :key="index"
                        :icon="activity.icon"
                        :type="activity.type"
                        :color="activity.color"
                        :size="activity.size"
                        style="text-align: left;"
                    >
                    <el-button link style="font-size: 14px;">{{ activity.station }}</el-button><br>
                    <el-text :style="activity.style1">{{ activity.transfer1 }}</el-text><br>
                    <el-text :style="activity.style2">{{ activity.transfer2 }}</el-text><br>
                    <el-text :style="activity.style3">{{ activity.transfer3 }}</el-text>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
            <el-col :span="14" style="text-align: center;" :hidden="state.hiddenPage4">
                <el-timeline style="max-width: 100px; margin-top: 20px; margin-left: 12px;">
                    <el-timeline-item
                        v-for="(activity, index) in subway[3]"
                        v-model="currentPage"
                        v-model:page-size="pageSize"
                        :key="index"
                        :icon="activity.icon"
                        :type="activity.type"
                        :color="activity.color"
                        :size="activity.size"
                        style="text-align: left;"
                    >
                    <el-button link style="font-size: 14px;">{{ activity.station }}</el-button><br>
                    <el-text :style="activity.style1">{{ activity.transfer1 }}</el-text><br>
                    <el-text :style="activity.style2">{{ activity.transfer2 }}</el-text><br>
                    <el-text :style="activity.style3">{{ activity.transfer3 }}</el-text>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
            <el-col :span="14" style="text-align: center;" :hidden="state.hiddenPage5">
                <el-timeline style="max-width: 100px; margin-top: 20px; margin-left: 12px;">
                    <el-timeline-item
                        v-for="(activity, index) in subway[4]"
                        v-model="currentPage"
                        v-model:page-size="pageSize"
                        :key="index"
                        :icon="activity.icon"
                        :type="activity.type"
                        :color="activity.color"
                        :size="activity.size"
                        style="text-align: left;"
                    >
                    <el-button link style="font-size: 14px;">{{ activity.station }}</el-button><br>
                    <el-text :style="activity.style1">{{ activity.transfer1 }}</el-text><br>
                    <el-text :style="activity.style2">{{ activity.transfer2 }}</el-text><br>
                    <el-text :style="activity.style3">{{ activity.transfer3 }}</el-text>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
        </el-row>
        <div>
            <el-row>
                <el-col :span="10" />
                <el-col :span="14">
                    <el-pagination
                        small
                        background
                        layout="prev, pager, next"
                        :total="50"
                        class="mt-4"
                        @current-change="handleCurrentChange"
                    />
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { MoreFilled } from '@element-plus/icons-vue';
import { reactive, ref } from 'vue';
const currentPage = ref(4)
const pageSize = ref(100)
const handleCurrentChange = (val: number) => {
    if(val == 1) {
        state.hiddenPage1 = false
        state.hiddenPage2 = true
        state.hiddenPage3 = true
        state.hiddenPage4 = true
        state.hiddenPage5 = true
    }
    else if(val == 2) {
        state.hiddenPage1 = true
        state.hiddenPage2 = false
        state.hiddenPage3 = true
        state.hiddenPage4 = true
        state.hiddenPage5 = true
    }
    else if(val == 3) {
        state.hiddenPage1 = true
        state.hiddenPage2 = true
        state.hiddenPage3 = false
        state.hiddenPage4 = true
        state.hiddenPage5 = true
    }
    else if(val == 4) {
        state.hiddenPage1 = true
        state.hiddenPage2 = true
        state.hiddenPage3 = true
        state.hiddenPage4 = false
        state.hiddenPage5 = true
    }
    else if(val == 5) {
        state.hiddenPage1 = true
        state.hiddenPage2 = true
        state.hiddenPage3 = true
        state.hiddenPage4 = true
        state.hiddenPage5 = false
    }
}
const state = reactive({
    hiddenPage1: false,
    hiddenPage2: true,
    hiddenPage3: true,
    hiddenPage4: true,
    hiddenPage5: true,
})
const subway = [
    [
        { station: '연천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '전곡', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '청산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '소요산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '동두천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '보산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '동두천중앙', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '지행', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '덕정', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '덕계', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '양주', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '녹양', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '가능', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '의정부', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '회룡', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '의정부선', style1: 'font-size: 12px; color: #ff9d27; font-weight: bold; margin-left: 3px;' },
        { station: '망월사', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        
    ],
    [
        { station: '도봉산', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '7호선', style1: 'font-size: 12px; color: #697215; font-weight: bold; margin-left: 4px;' },
        { station: '도봉', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '방학', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '창동', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '4호선', style1: 'font-size: 12px; color: #2c9ede; font-weight: bold; margin-left: 4px;' },
        { station: '녹천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '월계', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '광운대', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '경춘선', style1: 'font-size: 12px; color: #08af7b; font-weight: bold; margin-left: 4px;' },
        { station: '석계', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '6호선', style1: 'font-size: 12px; color: #b5500b; font-weight: bold; margin-left: 4px;' },
        { station: '신이문', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '외대앞', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '회기', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '경춘선', style1: 'font-size: 12px; color: #08af7b; font-weight: bold; margin-left: 4px;', transfer2: '경의중앙', style2: 'font-size: 12px; color: #7cc4a5; font-weight: bold; margin-left: 4px;' },
        { station: '청량리', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '경춘선', style1: 'font-size: 12px; color: #08af7b; font-weight: bold; margin-left: 4px;', transfer2: '경의중앙', style2: 'font-size: 12px; color: #7cc4a5; font-weight: bold; margin-left: 4px;', transfer3: '수인분당', style3: 'font-size: 12px; color: #ffce33; font-weight: bold; margin-left: 4px;' },
    ],
    [
        { station: '제기동', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '신설동', size: 'large', type: 'primary', icon: MoreFilled, color: '#263c96', transfer1: '2호선', style1: 'font-size: 12px; color: #3cb44a; font-weight: bold; margin-left: 4px;', transfer2: '우이신설', style2: 'font-size: 12px; color: #c6c100; font-weight: bold; margin-left: 4px;' },
        { station: '동묘앞', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '동대문', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '종로5가', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '종로3가', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '광명', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '석수', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '관악', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '종각', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '시청', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '서율역', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '남영', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '용산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '노량진', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '대방', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
    [
        { station: '신길', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '영등포', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '신도림', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '구로', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '가산디지털단지', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '독산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '금천구청', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '오산대', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '오산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '진위', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '송탄', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '안양', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '명학', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '금정', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '군포', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '당정', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
    [
        { station: '의왕', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '성균관대', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '화서', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '수원', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '세류', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '병점', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '서동탄', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '세마', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '서정리', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '평택지제', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '평택', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '성환', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '직산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '두정', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '천안', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '봉명', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
    [
        { station: '쌍용', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '아산', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '탕정', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '배방', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '온양온천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '신창', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
    [
        { station: '구일', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '개봉', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '오류동', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '온수', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '역곡', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '소사', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '부천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '중동', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '송내', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
    [
        { station: '부개', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '부평', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '백운', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '동암', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '간석', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '주안', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '도화', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '제물포', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '도원', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '동인천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
        { station: '인천', size: 'large', type: 'primary', icon: '', color: '#263c96' },
    ],
]


</script>
<style>
.el-button1 {
    --el-button-font-weight: var(--el-font-weight-primary);
    --el-button-border-color: var(--el-border-color);
    --el-button-bg-color: var(--el-fill-color-blank);
    --el-button-text-color: var(--el-text-color-regular);
    --el-button-disabled-text-color: var(--el-disabled-text-color);
    --el-button-disabled-bg-color: rgb(189 189 189);
    --el-button-disabled-border-color: rgb(167 167 167);
    --el-button-divide-border-color: rgba(255, 255, 255, 0.5);
    --el-button-hover-text-color: #4527A0;                              /* 마우스 올렸을 때 글씨 색 */
    --el-button-hover-bg-color: #EDE7F6;                                /* 마우스 올렸을 때 배경 색 */
    --el-button-hover-border-color: #7C4DFF;                            /* 마우스 올렸을 때 테두리 색 */
    --el-button-active-text-color: var(--el-button-hover-text-color);
    --el-button-active-border-color: #9575CD;
    --el-button-active-bg-color: var(--el-button-hover-bg-color);
    --el-button-outline-color: var(--el-color-primary-light-5);
    --el-button-hover-link-text-color: var(--el-color-info);
    --el-button-active-color: var(--el-text-color-primary);

    --el-icon-size: 14px;
    font-size: 12px; 
    height: 22px; 
    color: #ffffff; 
    background-color: #263c96;
    border-color: #263c96; 
    font-weight: bold; 
}
.el-button.is-round {
    padding: 8px 15px;
}
.el-timeline-item {
    position: relative;
    padding-bottom: 1px;
}
.el-timeline {
    --el-timeline-node-size-normal: 12px;
    --el-timeline-node-size-large: 14px;
    --el-timeline-node-color: var(--el-border-color-light);
}
.custom_el-card__body {
    padding: 0px;
}
</style>