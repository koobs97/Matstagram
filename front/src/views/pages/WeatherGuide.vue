<template>
    <el-row>
        <el-col :span="4" style="text-align: left;">
            <el-text style="font-weight: bold; font-size: 16px;">날씨</el-text>
        </el-col>
        <el-col :span="14" />
        <el-col :span="6" style="text-align: right;">
            <el-text>{{ state.city }}</el-text>
        </el-col>
    </el-row>
    <el-divider class="divider"></el-divider>
    <el-row>
        <el-col :span="5">
            <el-icon style="font-size: 70px;"><Cloudy /></el-icon>
        </el-col>
        <el-col :span="11">
            <el-text style="font-size: 40px;">{{ state.weather.main.temp }}°</el-text><br>
            <el-text>{{ state.weather.main.temp_min }}°</el-text> / 
            <el-text>{{ state.weather.main.temp_max }}°</el-text>
        </el-col>
    </el-row>
    <el-button @click="onClickGetWeather">날씨불러오기</el-button>
</template>
<script lang="ts" setup>
import { Cloudy } from '@element-plus/icons-vue';
import axios from "axios";
import { onMounted, reactive } from 'vue';
import city from '../../json/city.json';

interface weatherInfo {
    main : {
        temp: number,
        temp_min: number, 
        temp_max: number,
    }
}

const state = reactive({
    city: 'Anyang' as string,
    cityList: [] as any,
    weather: {
        main : {
            temp: 0,
            temp_min: 0,
            temp_max: 0,
        }
    } as weatherInfo
})

// 화면진입 시
onMounted( async () => {

    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=' + state.city + '&appid=d9b852258ea16c6066275068c02d72aa');
    console.log(response)

    /* 켈빈 온도를 섭씨 온도로 변환 */
    state.weather.main.temp     = Math.round(((response.data.main.temp     * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10
    state.weather.main.temp_min = Math.round(((response.data.main.temp_min * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10
    state.weather.main.temp_max = Math.round(((response.data.main.temp_max * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10

    for(let i in city) {
        if(city[i].country == 'KR') {
            state.cityList.push(city[i])
        }
    }

    console.log(state.cityList)

})

/* 날씨 API 호출 */
const onClickGetWeather = async () => {
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=' + state.city + '&appid=d9b852258ea16c6066275068c02d72aa');
    console.log(response)

    state.weather.main.temp = (response.data.main.temp * (5 / 9) - 459.67) * (5 / 9) - 32
}
</script>
<style>
.divider {
    margin-top: 12px;
    margin-bottom: 12px;
    width: 100%;
}
</style>
<!-- 
https://home.openweathermap.org
koobs97
koobs97@naver.com
kbs@970729
key : d9b852258ea16c6066275068c02d72aa
-->
