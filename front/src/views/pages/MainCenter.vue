<template>
    <el-row>
        <el-col :span="4">
            
        </el-col>
        <el-col :span="12" style="height: 790px;">
        </el-col>
        <el-col :span="8" style="height: 790px; text-align: left;">
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #263c96; border-color: #263c96; margin-bottom: 4px;">1</el-button><el-text style="margin-left: 4px; font-weight: bold;">1호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #3cb44a; border-color: #3cb44a; margin-bottom: 4px; margin-left: 4px;">2</el-button><el-text style="margin-left: 4px; font-weight: bold;">2호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #ff7300; border-color: #ff7300; margin-bottom: 4px; margin-left: 4px;">3</el-button><el-text style="margin-left: 4px; font-weight: bold;">3호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #2c9ede; border-color: #2c9ede; margin-bottom: 4px; margin-left: 4px;">4</el-button><el-text style="margin-left: 4px; font-weight: bold;">4호선</el-text><br>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #8936e0; border-color: #8936e0; margin-bottom: 4px;">5</el-button><el-text style="margin-left: 4px; font-weight: bold;">5호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #b5500b; border-color: #b5500b; margin-bottom: 4px; margin-left: 4px;">6</el-button><el-text style="margin-left: 4px; font-weight: bold;">6호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #697215; border-color: #697215; margin-bottom: 4px; margin-left: 4px;">7</el-button><el-text style="margin-left: 4px; font-weight: bold;">7호선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #e51e6e; border-color: #e51e6e; margin-bottom: 4px; margin-left: 4px;">8</el-button><el-text style="margin-left: 4px; font-weight: bold;">8호선</el-text><br>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #d1a62c; border-color: #d1a62c; margin-bottom: 4px;">9</el-button><el-text style="margin-left: 4px; font-weight: bold;">9호선</el-text>

            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #73b6e4; border-color: #73b6e4; margin-bottom: 4px; margin-left: 4px;">A</el-button><el-text style="margin-left: 4px; font-weight: bold;">공항선</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #7cc4a5; border-color: #7cc4a5; margin-bottom: 4px; margin-left: 4px;">G</el-button><el-text style="margin-left: 4px; font-weight: bold;">경의중앙</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #08af7b; border-color: #08af7b; margin-bottom: 4px; margin-left: 4px;">G</el-button><el-text style="margin-left: 4px; font-weight: bold;">경춘</el-text><br>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #eba900; border-color: #eba900; margin-bottom: 4px;">S</el-button><el-text style="margin-left: 4px; font-weight: bold;">수인분당</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #a71e31; border-color: #a71e31; margin-bottom: 4px; margin-left: 4px;">S</el-button><el-text style="margin-left: 4px; font-weight: bold;">신분당</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #2673f2; border-color: #2673f2; margin-bottom: 4px; margin-left: 4px;">G</el-button><el-text style="margin-left: 4px; font-weight: bold;">경강</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #8bc53f; border-color: #8bc53f; margin-bottom: 4px; margin-left: 4px;">S</el-button><el-text style="margin-left: 4px; font-weight: bold;">서해</el-text><br>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #6f99d0; border-color: #6f99d0; margin-bottom: 4px;">I</el-button><el-text style="margin-left: 4px; font-weight: bold;">인천1</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #f4ab3e; border-color: #f4ab3e; margin-bottom: 4px; margin-left: 4px;">I</el-button><el-text style="margin-left: 4px; font-weight: bold;">인천2</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #77c371; border-color: #77c371; margin-bottom: 4px; margin-left: 4px;">E</el-button><el-text style="margin-left: 4px; font-weight: bold;">에버라인</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #ff9d27; border-color: #ff9d27; margin-bottom: 4px; margin-left: 4px;">U</el-button><el-text style="margin-left: 4px; font-weight: bold;">의정부</el-text><br>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #c6c100; border-color: #c6c100; margin-bottom: 4px;">U</el-button><el-text style="margin-left: 4px; font-weight: bold;">우이신설</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #96710a; border-color: #96710a; margin-bottom: 4px; margin-left: 4px;">K</el-button><el-text style="margin-left: 4px; font-weight: bold;">김포골드</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #4e67a5; border-color: #4e67a5; margin-bottom: 4px; margin-left: 4px;">S</el-button><el-text style="margin-left: 4px; font-weight: bold;">신림</el-text>
            <el-button style="height: 29px; width: 29px; font-weight: bold; color: #ffffff; background-color: #905a89; border-color: #905a89; margin-bottom: 4px; margin-left: 4px;">G</el-button><el-text style="margin-left: 4px; font-weight: bold;">GTX-A</el-text><br>
        </el-col>

        <el-col :span="8">
        </el-col>
        <el-col :span="8">
                <el-pagination
                    small
                    background
                    layout="prev, pager, next"
                    :total="50"
                    class="mt-4"
                />
        </el-col>
        <el-col :span="8">
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>

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