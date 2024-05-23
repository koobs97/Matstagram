<template>
    <el-row>
        <el-col :span="3" style="text-align: left;">
            <el-text style="font-weight: bold; font-size: 14px;">날씨</el-text>
        </el-col>
        <el-col :span="1" style="text-align: left;">
            <el-button :icon="RefreshRight" style="font-size: 14px;" link @click="onClickGetWeather"></el-button>
        </el-col>
        <el-col :span="13" />
        <el-col :span="6" style="text-align: right;">
            <el-text>{{ state.city }}</el-text>
        </el-col>
    </el-row>
    <el-divider class="divider"></el-divider>
    <el-row>
        <el-col :span="7">
            <el-icon v-show="state.weather.info=='Clouds'"          style="font-size: 50px;"><Cloudy        /></el-icon>
            <el-icon v-show="state.weather.info=='PartlyCloudy'"    style="font-size: 50px;"><PartlyCloudy  /></el-icon>
            <el-icon v-show="state.weather.info=='Clear'"           style="font-size: 50px;"><Sunny         /></el-icon>
            <el-icon v-show="state.weather.info=='Rain'"            style="font-size: 50px;"><Pouring       /></el-icon>
            <el-icon v-show="state.weather.info=='Snow'"            style="font-size: 50px;"><Drizzling     /></el-icon>
        </el-col>
        <el-col :span="7" style="display: flex; align-items: center; margin-bottom: 10px; margin-right: 24px;">
            <div style="display: flex; flex-direction: column; align-items: center;">
                <el-text style="font-size: 40px; margin-right: 8px;">{{ state.weather.main.temp }}°</el-text>
                <el-text>{{ state.weather.info_kor }}</el-text>
            </div>
        </el-col>
        <el-col :span="4" style="display: flex; flex-direction: column; align-items: center; margin-top: 5px;">
            <el-tag type="primary" effect="plain" round style="margin-bottom: 4px;">{{ state.weather.main.temp_max }}°</el-tag>
            <el-tag type="danger" effect="plain" round style="margin-bottom: 4px;">{{ state.weather.main.temp_min }}°</el-tag>
        </el-col>
        <el-col :span="4" style="display: flex; flex-direction: column; align-items: left; margin-top: 5px;">
            <el-tag type="success" round style="margin-bottom: 4px;">매우좋음</el-tag>
            <el-tag type="primary" round style="margin-bottom: 4px;">좋음</el-tag>
        </el-col>
        <el-col :span="24" style="margin-top: 4px;">
            <el-row style="justify-content: center; align-items: center;">
                <el-col :span="12" style="padding-right: 4px;">
                    <el-card shadow="never" class="custom-card">
                        <el-row>
                            <el-col :span="8" style="display: flex; align-items: center;">
                                <el-text>일출</el-text>
                            </el-col>
                            <el-col :span="8" style="display: flex; align-items: center;">
                                <el-icon><Sunrise /></el-icon>
                            </el-col>
                            <el-col :span="8" style="display: flex; align-items: right;">
                                <el-text>{{ state.weather.sys.sunrise }}</el-text>
                            </el-col>
                        </el-row>
                    </el-card>
                </el-col>
                <el-col :span="12" style="padding-left: 4px;">
                    <el-card shadow="never" class="custom-card">
                        <el-row>
                            <el-col :span="8" style="display: flex; align-items: center;">
                                <el-text>일몰</el-text>
                            </el-col>
                            <el-col :span="8" style="display: flex; align-items: center;">
                                <el-icon><Sunset /></el-icon>
                            </el-col>
                            <el-col :span="8" style="display: flex; align-items: center;">
                                <el-text>{{ state.weather.sys.sunset }}</el-text>
                            </el-col>
                        </el-row>
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { Cloudy, Drizzling, PartlyCloudy, Pouring, RefreshRight, Sunny, Sunrise, Sunset } from '@element-plus/icons-vue';
import axios from "axios";
import { onMounted, reactive } from 'vue';
import city from '../../json/city.json';

interface weatherInfo {
    main : {
        temp: number,
        temp_min: number, 
        temp_max: number,
    },
    sys: {
        sunrise: string,
        sunset: string,
    },
    info: string,
    info_kor: string,
    pm: string,     // 미세먼지
}

const state = reactive({
    city: 'Anyang-si' as string,
    cityList: [] as any,
    weather: {
        main : {
            temp: 0,
            temp_min: 0,
            temp_max: 0,
        },
        sys: {
            sunrise: '00:00',
            sunset: '00:00',
        },
        info: '',
        info_kor: '',
    } as weatherInfo
})

// 화면진입 시
onMounted( async () => {

    /* 날씨 API 호출 */
    onClickGetWeather()

    for(let i in city) {
        if(city[i].country == 'KR') {
            state.cityList.push(city[i])
        }
    }
    console.log(state.cityList)

})

/* 날씨 API 호출 */
const onClickGetWeather = async () => {

    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=' + state.city + ', kr&appid=d9b852258ea16c6066275068c02d72aa');
    console.log(response)

    /* 켈빈 온도를 섭씨 온도로 변환 */
    state.weather.main.temp     = Math.round(((response.data.main.temp     * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10
    state.weather.main.temp_min = Math.round(((response.data.main.temp_min * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10
    state.weather.main.temp_max = Math.round(((response.data.main.temp_max * (9 / 5) - 459.67) - 32) * (5 / 9) * 10) / 10

    /* 일출, 일몰 시간 세팅 */
    const time1 = new Date(response.data.sys.sunrise * 1000)
    const time2 = new Date(response.data.sys.sunset * 1000)

    state.weather.sys.sunrise = '0' + time1.getHours() + ':' + time1.getMinutes()
    state.weather.sys.sunset  = time2.getHours() + ':' + time2.getMinutes()

    /* 날씨 세팅 */
    state.weather.info = response.data.weather['0'].main

    if(state.weather.info == 'Clouds') {
        if(response.data.clouds.all > 30 && response.data.clouds.all <= 60) {
            state.weather.info = 'PartlyCloudy'
        }
    }

    if(state.weather.info == 'Clouds'       ) { state.weather.info_kor = '흐림' }
    if(state.weather.info == 'PartlyCloudy' ) { state.weather.info_kor = '구름많음' }
    if(state.weather.info == 'Rain'         ) { state.weather.info_kor = '비' }
    if(state.weather.info == 'Clear'        ) { state.weather.info_kor = '맑음' }
    if(state.weather.info == 'Snow'         ) { state.weather.info_kor = '눈' }

    /* 대기질 데이터 가져오기 */
    const payload = await axios.get('https://api.openweathermap.org/data/2.5/air_pollution?lat=' + response.data.coord.lat + '&lon=' + response.data.coord.lon + '&appid=d9b852258ea16c6066275068c02d72aa')
    console.log(payload)

}
</script>
<style>
.divider {
    margin-top: 8px;
    margin-bottom: 8px;
    width: 100%;
}
.custom-card {
    --el-card-border-color: var(--el-border-color-light);
    --el-card-border-radius: 4px;
    --el-card-padding: 12px;
    --el-card-bg-color: var(--el-fill-color-blank);
}
</style>
<!-- 
https://home.openweathermap.org
koobs97
koobs97@naver.com
kbs@970729
key : d9b852258ea16c6066275068c02d72aa
-->
