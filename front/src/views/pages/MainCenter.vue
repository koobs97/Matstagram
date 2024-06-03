<template>
    <el-row>
        <!-- 좌측 -->
        <el-col :span="14">
            <el-row>
                <el-col :span="24" style="height: 790px;">
                    <el-row>
                        <el-col :span="9" style="text-align: left; height: 790px">
                            <el-divider style="margin-top: 0px; margin-bottom: 0px; width: 70px; border-top: 4px solid; border-bottom: 4px solid; color: #263c96; border-radius: 2px;">
                                <el-tag 
                                    style="
                                    font-size: 14px; 
                                    font-weight: bold; 
                                    color: #263c96; 
                                    background-color: rgba(38, 60, 150, 0.2); 
                                    border-color: #263c96;">1호선</el-tag>
                            </el-divider>
                        </el-col>
                        <el-col :span="12" style="height: 790px;">
                            <el-timeline style="max-width: 100px; margin-top: 20px; height: 500px;">
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
                        <el-col :span="3" style="text-align: left; height: 790px" />
                        <el-col :span="5" />
                        <el-col :span="11">
                            <el-pagination
                                small
                                background
                                layout="prev, pager, next"
                                :total="90"
                                class="mt-4"
                            />
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </el-col>
        <!-- 우측 -->
        <el-col :span="10">
            <el-row>
                <el-col :span="24" style="height: 545px;">
                    <el-card shadow="never" style="height: 520px;">
                        <div style="text-align: left;">
                            <el-row>
                                <el-col :span="12">
                                    <el-text style="font-weight: bold;"><el-icon><Place /></el-icon> 연천</el-text>
                                </el-col>
                                <el-col :span="12" style="text-align: right;">
                                    <el-button :icon="Star"     link style="font-size: 16px;" />
                                    <el-button :icon="Position" link style="font-size: 16px;" />
                                    <el-button :icon="More"     link style="font-size: 16px;" />
                                </el-col>
                            </el-row>
                            <el-divider style="margin-top: 6px; margin-bottom: 18px;" />

                            <el-card shadow="never" style="height: 140px;">
                                <el-row>
                                    <el-col :span="17">
                                        <el-row>
                                            <el-col :span="24">
                                                <el-text style="font-size: 16px; font-weight: bold;">망향비빔국수본점</el-text>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 4px;">
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; ">가벼운식사</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">국수</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">가족외식</el-tag>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 30px;">
                                                <el-text style="font-size: 13px;">"저렴한 가격의 비빔국수 맛집"</el-text>
                                            </el-col>
                                        </el-row>
                                    </el-col>
                                    <el-col :span="6" style="text-align: right; margin-left: 4px;">
                                        <el-image v-for="url in urls" :key="url" :src="url" lazy style="width: 100px; height: 100px; border-radius: 6px;" />
                                    </el-col>
                                </el-row>
                            </el-card>
                            <el-card shadow="never" style="height: 140px; margin-top: 6px;">
                                <el-row>
                                    <el-col :span="17">
                                        <el-row>
                                            <el-col :span="24">
                                                <el-text style="font-size: 16px; font-weight: bold;">동막골 숯불닭갈비</el-text>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 4px;">
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px;">저녁식사</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">단체모임</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">서민적인</el-tag>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 30px;">
                                                <el-text style="font-size: 13px;">"모임장소로 적합한 닭갈비 맛집"</el-text>
                                            </el-col>
                                        </el-row>
                                    </el-col>
                                    <el-col :span="6" style="text-align: right; margin-left: 4px;">
                                        <el-image v-for="url in urls2" :key="url" :src="url" lazy style="width: 100px; height: 100px; border-radius: 6px;" />
                                    </el-col>
                                </el-row>
                            </el-card>
                            <el-card shadow="never" style="height: 140px; margin-top: 6px;">
                                <el-row>
                                    <el-col :span="17">
                                        <el-row>
                                            <el-col :span="24">
                                                <el-text style="font-size: 16px; font-weight: bold;">연천회관</el-text>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 4px;">
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px;">데이트</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">좌식테이블</el-tag>
                                                <el-tag round effect="plain" style="color: #B388FF; border-color: #B388FF; font-size: 11px; margin-left: 3px;">예쁜</el-tag>
                                            </el-col>
                                            <el-col :span="24" style="margin-top: 30px;">
                                                <el-text style="font-size: 13px;">"고즈넉한 분위기의 카페"</el-text>
                                            </el-col>
                                        </el-row>
                                    </el-col>
                                    <el-col :span="6" style="text-align: right; margin-left: 4px;">
                                        <el-image v-for="url in urls3" :key="url" :src="url" lazy style="width: 100px; height: 100px; border-radius: 6px;" />
                                    </el-col>
                                </el-row>
                            </el-card>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="24" style="text-align: left;">
                    <el-tag 
                        style="
                            margin-bottom: 4px; 
                            background-color: #f0f2f5; 
                            color: #303133; 
                            border-color: #e4e7ed;">지하철 노선</el-tag>
                    <el-card shadow="never" style="background-color: #f0f2f5;">
                        <el-row>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #263c96; 
                                        background-color: rgba(38, 60, 150, 0.2);
                                        border-color: #263c96; 
                                        margin-bottom: 4px;">1</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">1호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #3cb44a; 
                                        background-color: rgba(60, 180, 74, 0.2); 
                                        border-color: #3cb44a; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">2</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">2호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #ff7300; 
                                        background-color: rgba(255, 115, 0, 0.2); 
                                        border-color: #ff7300; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">3</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">3호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #2c9ede; 
                                        background-color: rgba(44, 158, 222, 0.2); 
                                        border-color: #2c9ede; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">4</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">4호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #8936e0; 
                                        background-color: rgba(137, 54, 224, 0.2); 
                                        border-color: #8936e0; 
                                        margin-bottom: 4px;">5</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">5호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #b5500b; 
                                        background-color: rgba(181, 80, 11, 0.2); 
                                        border-color: #b5500b; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">6</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">6호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #697215; 
                                        background-color: rgba(105, 114, 21, 0.2); 
                                        border-color: #697215; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">7</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">7호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #e51e6e; 
                                        background-color: rgba(229, 30, 110, 0.2); 
                                        border-color: #e51e6e; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">8</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">8호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #d1a62c; 
                                        background-color: rgba(209, 166, 44, 0.2); 
                                        border-color: #d1a62c; 
                                        margin-bottom: 4px;">9</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">9호선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #73b6e4; 
                                        background-color: rgba(115, 182, 228, 0.2); 
                                        border-color: #73b6e4; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">A</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">공항선</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #7cc4a5; 
                                        background-color: rgba(124, 196, 165, 0.2); 
                                        border-color: #7cc4a5; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">G</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">경의중앙</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #08af7b; 
                                        background-color: rgba(8, 175, 123, 0.2); 
                                        border-color: #08af7b; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">G</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">경춘</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #eba900; 
                                        background-color: rgba(235, 169, 0, 0.2); 
                                        border-color: #eba900; 
                                        margin-bottom: 4px;">S</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">수인분당</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #a71e31; 
                                        background-color: rgba(167, 30, 49, 0.2); 
                                        border-color: #a71e31; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">S</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">신분당</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #2673f2; 
                                        background-color: rgba(38, 115, 242, 0.2); 
                                        border-color: #2673f2; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">G</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">경강</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #8bc53f; 
                                        background-color: rgba(139, 197, 63, 0.2); 
                                        border-color: #8bc53f; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">S</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">서해</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #6f99d0; 
                                        background-color: rgba(111, 153, 208, 0.2); 
                                        border-color: #6f99d0; 
                                        margin-bottom: 4px;">I</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">인천1</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #f4ab3e; 
                                        background-color: rgba(244, 171, 62, 0.2); 
                                        border-color: #f4ab3e; 
                                        margin-bottom: 4px; margin-left: 4px;">I</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">인천2</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #77c371; 
                                        background-color: rgba(119, 195, 113, 0.2); 
                                        border-color: #77c371; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">E</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">에버라인</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #ff9d27; 
                                        background-color: rgba(255, 157, 39, 0.2); 
                                        border-color: #ff9d27; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">U</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">의정부</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #c6c100; 
                                        background-color: rgba(198, 193, 0, 0.2); 
                                        border-color: #c6c100; 
                                        margin-bottom: 4px;">U</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">우이신설</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #96710a; 
                                        background-color: rgba(150, 113, 10, 0.2); 
                                        border-color: #96710a; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">K</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">김포골드</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #4e67a5; 
                                        background-color: rgba(78, 103, 165, 0.2); 
                                        border-color: #4e67a5; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">S</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">신림</el-text>
                            </el-col>
                            <el-col :span="6">
                                <el-button 
                                    style="
                                        height: 29px; 
                                        width: 29px; 
                                        font-weight: bold; 
                                        color: #905a89; 
                                        background-color: rgba(144, 90, 137, 0.2); 
                                        border-color: #905a89; 
                                        margin-bottom: 4px; 
                                        margin-left: 4px;">G</el-button>
                                        <el-text style="margin-left: 4px; font-weight: bold;">GTX-A</el-text>
                            </el-col>
                        </el-row>
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { More, MoreFilled, Place, Position, Star } from '@element-plus/icons-vue';
import { ref } from 'vue';
const currentPage = ref(4)
const pageSize = ref(100)
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
]

const urls = [
    '/images/main/line1/1_Yean.jpg'
]
const urls2 = [
    '/images/main/line1/2_Yean.jpg'
]
const urls3 = [
    '/images/main/line1/3_Yean.jpg'
]

</script>
<style>
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
    --el-pagination-hover-color: #B388FF;
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
.el-pagination.is-background .btn-next.is-active, .el-pagination.is-background .btn-prev.is-active, .el-pagination.is-background .el-pager li.is-active {
    background-color: #673AB7;
    color: var(--el-color-white);
}
.el-divider__text {
    position: absolute;
    background-color: var(--el-bg-color);
    padding: 0 0px;
    font-weight: 500;
    color: var(--el-text-color-primary);
    font-size: 14px;
}

</style>