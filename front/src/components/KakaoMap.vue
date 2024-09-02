<template>
    
    <el-row>
        <el-col :span="24" style="text-align: left;">
            <el-text style="font-weight: bold; font-size: 14px;">
                <el-icon><Location /></el-icon>
                맛집 찾아보기
            </el-text>
        </el-col>
    </el-row>
    
    <el-divider class="divider" />

    <div id="map" style="width: 100%; height: 500px;" />

    <el-row>
        <el-col :span="24" style="text-align: right; margin-top: 18px;">
            <el-button 
                style="
                    background-color: #fee500; 
                    width: 20px; height: 20px; 
                    padding: 0px; 
                    font-size: 12px; 
                    color: #1a1a1c; 
                    border-color: #fee500; 
                    font-weight: bold;">K</el-button>
            <el-link 
                :underline="false" 
                href="https://www.kakaocorp.com/page/" 
                target="_blank" 
                style="
                    font-size: 12px; 
                    margin-left: 3px; 
                    font-weight: bold;">
                Kakao
            </el-link>
            <el-link :underline="false" href="https://developers.kakao.com/" target="_blank" style="font-size: 12px; margin-left: 3px;">
                Developers
            </el-link>
        </el-col>
    </el-row>
</template>

<script lang="ts" setup>
import { Location } from '@element-plus/icons-vue';
import { onMounted } from 'vue';

// Vue의 onMounted 라이프사이클 훅에서 카카오맵 로딩 함수 호출
onMounted(() => {
    loadKakaoMap();
})

// 카카오맵 API 로드 및 지도 초기화 함수
const loadKakaoMap = () => {
    // 카카오맵 스크립트가 이미 로드되었는지 확인

    // @ts-ignore
    if (window.kakao && window.kakao.maps) {
      initMap(); // 이미 로드된 경우 바로 지도 초기화
    } else {
      // 카카오맵 스크립트를 동적으로 로드
        const script = document.createElement('script');
        script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=20f40f771036e372ac7f8c6e60fea984&autoload=false`;
        script.onload = () => { // 스크립트 로드 후 kakao.maps.load를 호출하여 지도 로딩
            // @ts-ignore
            window.kakao.maps.load(() => {
            initMap(); // 로드가 완료된 후 지도 초기화
            })
        }
      document.head.appendChild(script); // 스크립트를 head에 추가하여 로드 시작
    }
}

// 지도를 초기화하는 함수
const initMap = () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const lat = position.coords.latitude; // 위도
                const lng = position.coords.longitude; // 경도

                const container = document.getElementById('map'); // 지도를 표시할 div 엘리먼트
                const options = {
                    // @ts-ignore
                    center: new window.kakao.maps.LatLng(lat, lng), // 지도의 중심 좌표
                    level: 3, // 지도의 확대 레벨
                };

                // @ts-ignore
                const map = new window.kakao.maps.Map(container, options); // 지도 객체 생성
                console.log(map)

                zoomControl(map)        // 줌 컨트롤 이벤트 등록
                mapTypeControl(map)     // 지도에 컨트롤 올리기

            }
        )
    }    
}



// 지도 확대-축소 줌 컨트롤
const zoomControl = (map: any) => {
    // @ts-ignore
    const zoomControl = new window.kakao.maps.ZoomControl()
    // @ts-ignore
    map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT)

    // let roadmapControl = document.getElementById('btnRoadmap')
    // let skyviewControl = document.getElementById('btnSkyview')
    // if (maptype === 'roadmap') {
    //     map.setMapTypeId(window.kakao.maps.MapTypeId.ROADMAP);    
    //     roadmapControl.className = 'selected_btn';
    //     skyviewControl.className = 'btn';
    // } else {
    //     map.setMapTypeId(window.kakao.maps.MapTypeId.HYBRID);    
    //     skyviewControl.className = 'selected_btn';
    //     roadmapControl.className = 'btn';
    // }
}

// 지도에 컨트롤 올리기
const mapTypeControl = (map: any) => {
    // @ts-ignore
    const mapTypeControl = new window.kakao.maps.MapTypeControl()
    // @ts-ignore
    map.addControl(mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT)
}


</script>

<style scoped>
/* 지도 크기 조정 */
#map {
    width: 100%;
    height: 500px;
}
.divider {
    margin-top: 8px;
    margin-bottom: 8px;
    width: 100%;
}
</style>