<template>
    <div v-loading="loading" element-loading-background="rgba(255, 255, 255, 0.8)">
        <el-row>
            <el-col :span="3" style="text-align: left;">
                <el-text style="font-weight: bold; font-size: 14px;">날씨</el-text>
            </el-col>
            <el-col :span="1" style="text-align: left;">
                <el-button :icon="RefreshRight" style="font-size: 14px;" link @click="onClickGetWeather"></el-button>
            </el-col>
            <el-col :span="8" />
            <el-col :span="12" style="text-align: right;">
                <el-select
                    v-model="state.city"
                    size="small"
                    :style="state.style"
                    @change="onChangeWeather"
                >
                <el-option
                    v-for="item in state.cityList"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name"
                />
                </el-select>
            </el-col>
        </el-row>
        <el-divider class="divider"></el-divider>
        <el-row>
            <el-col :span="6">
                <el-icon v-show="state.weather.info=='Clouds'"          style="font-size: 50px;"><Cloudy        /></el-icon>
                <el-icon v-show="state.weather.info=='PartlyCloudy'"    style="font-size: 50px;"><PartlyCloudy  /></el-icon>
                <el-icon v-show="state.weather.info=='Clear'"           style="font-size: 50px;"><Sunny         /></el-icon>
                <el-icon v-show="state.weather.info=='ClearNight'"      style="font-size: 50px;"><Moon          /></el-icon>
                <el-icon v-show="state.weather.info=='Rain'"            style="font-size: 50px;"><Pouring       /></el-icon>
                <el-icon v-show="state.weather.info=='Snow'"            style="font-size: 50px;"><Drizzling     /></el-icon>
            </el-col>
            <el-col :span="6" style="display: flex; align-items: center; margin-bottom: 10px; margin-right: 24px;">
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <el-text style="font-size: 40px; margin-right: 8px;">{{ state.weather.main.temp }}°</el-text>
                    <el-text>{{ state.weather.info_kor }}</el-text>
                </div>
            </el-col>
            <el-col :span="8" style="margin-top: 5px; margin-left: 8px; text-align: left;">
                <el-row>
                    <el-col :span="12">
                        <el-tag type="primary" effect="plain" round style="margin-bottom: 4px;">{{ state.weather.main.temp_max }}°</el-tag>
                        <el-tag type="danger" effect="plain" round style="margin-bottom: 4px;">{{ state.weather.main.temp_min }}°</el-tag>
                    </el-col>
                    <el-col :span="12">
                        <el-tag :type="state.weather.pm10_type" round style="margin-bottom: 4px;">{{ state.weather.pm10_text }}</el-tag>
                        <el-tag :type="state.weather.pm2_5_type" round style="margin-bottom: 4px;">{{ state.weather.pm2_5_text }}</el-tag>
                    </el-col>
                </el-row>
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
            <el-col :span="24" style="text-align: right; margin-top: 12px;">
                <el-button style="background-color: #eb6e4b; width: 20px; height: 20px; padding: 0px; font-size: 12px; color: #f1f3f2; border-color: #eb6e4b;">W</el-button>
                <el-link :underline="false" href="https://www.naver.com/" target="_blank" style="font-size: 12px; margin-left: 3px; margin-top: 1px; font-weight: bold;">
                    OpenWeather
                </el-link>
            </el-col>
        </el-row>
    </div>
</template>
<script lang="ts" setup>
import { Cloudy, Drizzling, Moon, PartlyCloudy, Pouring, RefreshRight, Sunny, Sunrise, Sunset } from '@element-plus/icons-vue';
import axios from "axios";
import translate from 'translate';
import { onMounted, reactive, ref } from 'vue';
import city from '../../json/city.json';

const loading = ref(true)

interface weatherInfo {
    main : {
        temp    : number,   // 현재기온(켈빈타입)
        temp_min: number,   // 현재기온_min(켈빈타입)
        temp_max: number,   // 현재기온_max(켈빈타입)
    },
    sys: {
        sunrise: string,    // 일출시간(유닉스시간)
        sunset : string,    // 일몰시간(유닉스시간)
    },
    info        : string,   // 날씨타입
    info_kor    : string,   // 날씨표시
    pm10_type   : string,   // 미세먼지타입
    pm10_text   : string,   // 미세먼지표시
    pm2_5_type  : string,   // 초미세먼지타입
    pm2_5_text  : string,   // 초미세먼지표시
}

const state = reactive({
    city: 'Anyang-si' as string,
    cityList: [] as any,
    style: '',
    weather: {
        main : {
            temp    : 0,
            temp_min: 0,
            temp_max: 0,
        },
        sys: {
            sunrise : '00:00',
            sunset  : '00:00',
        },
        info        : '',
        info_kor    : '',
        pm10_type   : '',
        pm10_text   : '',
        pm2_5_type  : '',
        pm2_5_text  : '',
    } as weatherInfo
})

// 화면진입 시
onMounted( async () => {

    /* 기본값 */
    state.city = 'Anyang-si'

    /* 도시 select box 세팅 */
    for(let i in city) {
        if(city[i].country == 'KR') {
            state.cityList.push(city[i])
        }
    }

    /* 날씨 API 호출 */
    onClickGetWeather()

})

/* 날씨 API 호출 */
const onClickGetWeather = async () => {

    loading.value = true
    const response = await axios.get('https://api.openweathermap.org/data/2.5/weather?q=' + state.city + ', kr&appid=d9b852258ea16c6066275068c02d72aa&');

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
    if(state.weather.info == 'Clear') {
        const date = new Date()
        if(date.getHours() > 20) {
            state.weather.info = 'ClearNight'
        }
    }

    /* 대기질 데이터 가져오기 */
    const payload = await axios.get('https://api.openweathermap.org/data/2.5/air_pollution?lat=' + response.data.coord.lat + '&lon=' + response.data.coord.lon + '&appid=d9b852258ea16c6066275068c02d72aa')

    /* 미세먼지 */
    if(payload.data.list['0'].components.pm10 >= 0 && payload.data.list['0'].components.pm10 < 20) {
        state.weather.pm10_text = '매우좋음'
        state.weather.pm10_type = 'success'
    }
    else if(payload.data.list['0'].components.pm10 >= 20 && payload.data.list['0'].components.pm10 < 50) {
        state.weather.pm10_text = '좋음'
        state.weather.pm10_type = 'primary'
    }
    else if(payload.data.list['0'].components.pm10 >= 50 && payload.data.list['0'].components.pm10 < 100) {
        state.weather.pm10_text = '보통'
        state.weather.pm10_type = 'info'
    }
    else if(payload.data.list['0'].components.pm10 >= 100 && payload.data.list['0'].components.pm10 < 200) {
        state.weather.pm10_text = '나쁨'
        state.weather.pm10_type = 'warning'
    }
    else if(payload.data.list['0'].components.pm10 >= 200) {
        state.weather.pm10_text = '매우나쁨'
        state.weather.pm10_type = 'danger'
    }

    /* 초미세먼지 */
    if(payload.data.list['0'].components.pm2_5 >= 0 && payload.data.list['0'].components.pm2_5 < 10) {
        state.weather.pm2_5_text = '매우좋음'
        state.weather.pm2_5_type = 'success'
    }
    else if(payload.data.list['0'].components.pm2_5 >= 10 && payload.data.list['0'].components.pm2_5 < 25) {
        state.weather.pm2_5_text = '좋음'
        state.weather.pm2_5_type = 'primary'
    }
    else if(payload.data.list['0'].components.pm2_5 >= 25 && payload.data.list['0'].components.pm2_5 < 50) {
        state.weather.pm2_5_text = '보통'
        state.weather.pm2_5_type = 'info'
    }
    else if(payload.data.list['0'].components.pm2_5 >= 50 && payload.data.list['0'].components.pm2_5 < 75) {
        state.weather.pm2_5_text = '나쁨'
        state.weather.pm2_5_type = 'warning'
    }
    else if(payload.data.list['0'].components.pm2_5 >= 75) {
        state.weather.pm2_5_text = '매우나쁨'
        state.weather.pm2_5_type = 'danger'
    }

    /* 도시 이름 한글로 번역 */
    state.city = await translate(state.city, { to: 'ko' })

    /* select box 넓이 조정 */
    state.style = 'width: ' + String((state.city.length) * 30) + 'px; text-align: right;'

    loading.value = false

}

/* 도시 변경 시 날씨 api 호출 */
const onChangeWeather = () => {
    onClickGetWeather()
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
.el-select__wrapper {
    display: flex;
    align-items: center;
    position: relative;
    box-sizing: border-box;
    cursor: pointer;
    text-align: left;
    font-size: 14px;
    padding: 4px 12px;
    gap: 6px;
    min-height: 32px;
    line-height: 24px;
    border-radius: var(--el-border-radius-base);
    background-color: var(--el-fill-color-blank);
    transition: var(--el-transition-duration);
    box-shadow: 0 0 0 0px var(--el-border-color) inset;
}
</style>
<!-- 
https://home.openweathermap.org
koobs97
koobs97@naver.com
kbs@970729
key : d9b852258ea16c6066275068c02d72aa
-->
