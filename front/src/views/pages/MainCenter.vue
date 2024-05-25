<template>
    <div>
        <el-timeline style="max-width: 100px">
            <el-timeline-item
            v-for="(activity, index) in subway"
            :key="index"
            :icon="activity.icon"
            :type="activity.type"
            :color="activity.color"
            :size="activity.size"
            :hollow="activity.hollow"
            :timestamp="activity.timestamp"
            >
            {{ activity.station }}
            </el-timeline-item>
        </el-timeline>
    </div>
</template>
<script lang="ts" setup>
import { MoreFilled } from '@element-plus/icons-vue';

const subway = [
    {
        station: '연천',
        timestamp: '2018-04-12 20:46',
        size: 'large',
        type: 'primary',
        icon: MoreFilled,
    },
    {
        content: 'Custom color',
        timestamp: '2018-04-03 20:46',
        color: '#0bbd87',
    },
    {
        content: 'Custom hollow',
        timestamp: '2018-04-03 20:46',
        type: 'primary',
        hollow: true,
    },
    {
        content: 'Default node',
        timestamp: '2018-04-03 20:46',
    },
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
</style>