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

    <div id="map" style="width: 100%; height: 500px;" ><div id="centerAddr"></div></div>
    

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
import { nextTick, onMounted } from 'vue';

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
        script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=20f40f771036e372ac7f8c6e60fea984&autoload=false&libraries=services,clusterer,drawing`;
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
                addMarker(map)          // 지도에 마커 표시

                nextTick(()=>{
                    // @ts-ignore
                const geocoder = new window.kakao.maps.services.Geocoder()
                console.log(geocoder)

                // @ts-ignore
                const marker = new window.kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
                    // @ts-ignore
                    infowindow = new window.kakao.maps.InfoWindow({zindex:1})

                // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
                searchAddrFromCoords(map.getCenter(), displayCenterInfo);

                // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
                // @ts-ignore
                window.kakao.maps.event.addListener(map, 'click', function(mouseEvent: any) {
                    searchDetailAddrFromCoords(mouseEvent.latLng, function(result: any, status: any) {
                        // @ts-ignore
                        if (status === window.kakao.maps.services.Status.OK) {
                            var detailAddr = result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                            
                            var content = '<div class="bAddr">' +
                                            '<span class="title">법정동 주소정보</span>' + 
                                            detailAddr + 
                                        '</div>';

                            // 마커를 클릭한 위치에 표시합니다 
                            marker.setPosition(mouseEvent.latLng);
                            marker.setMap(map);

                            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                        }   
                    });
                });

                // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
                // @ts-ignore
                window.kakao.maps.event.addListener(map, 'idle', function() {
                    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
                });
                function searchAddrFromCoords(coords: any, callback: any) {
                    // 좌표로 행정동 주소 정보를 요청합니다
                    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
                }
                function searchDetailAddrFromCoords(coords: any, callback: any) {
                    // 좌표로 법정동 상세 주소 정보를 요청합니다
                    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
                }
                // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
                function displayCenterInfo(result: any, status: any) {
                    // @ts-ignore
                    if (status === window.kakao.maps.services.Status.OK) {
                        var infoDiv = document.getElementById('centerAddr');

                        for(var i = 0; i < result.length; i++) {
                            // 행정동의 region_type 값은 'H' 이므로
                            if (result[i].region_type === 'H') {
                                // @ts-ignore
                                infoDiv.innerHTML = result[i].address_name;
                                break;
                            }
                        }
                    }    
                }
                })

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
}

// 지도에 컨트롤 올리기
const mapTypeControl = (map: any) => {
    // @ts-ignore
    const mapTypeControl = new window.kakao.maps.MapTypeControl()
    // @ts-ignore
    map.addControl(mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT)
}

// 지도에 마커 표시
const addMarker = (map: any) => {
    // @ts-ignore
    const marker = new window.kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter() 
    })

    // 마커 생성
    marker.setMap(map)

    // 마커가 드래그 설정
    marker.setDraggable(true);

    // 지도에 클릭 이벤트 등록
    // @ts-ignore
    window.kakao.maps.event.addListener(map, 'click', function (mouseEvent: window.kakao.maps.event.MouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        const latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);
    });
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