<template>
    
    <el-row>
        <el-col :span="12" style="text-align: left;">
            <el-text style="font-weight: bold; font-size: 14px;">
                <el-icon><Location /></el-icon>
                맛집 찾아보기
            </el-text>
        </el-col>
        <el-col :span="12" style="text-align: right;">
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
    
    <el-divider class="divider" />

    <div class="map_wrap">
        <el-row>
            <el-col :span="24">
                <div id="map" :style="state.mapStyle"></div>
                <div class="hAddr">
                    <span class="title">지도중심기준 행정동 주소정보</span>
                    <span id="centerAddr"></span>
                </div>
                <div id="roadviewControl" @click="setRoadviewRoad()"></div>
            </el-col>
        </el-row>
        <el-popover
            ref="popover"
            placement="right"
            :width="600"
            :visible="state.viewLoadMap"
        >   
            <div id="rvWrapper" style="width:100%; height: 500px; position:relative; overflow:hidden;" :hidden="state.loadViewHidden">
                <div id="roadview" style="width:100%;height:100%;"></div> <!-- 로드뷰를 표시할 div 입니다 -->
                <div id="close" title="로드뷰닫기" @click="closeRoadview()"><span class="img"></span></div>
            </div>
        </el-popover>
    </div>
    <div>
        <el-row>
            <el-col :span="24" style="margin-top: 115px;">
                <el-row>
                    <el-col :span="22">
                        <el-input style="font-size: 12px; height: 32px;" v-model="state.searchText" @keyup.enter="onClickSearchArea">
                            <template #append>
                                <el-button :icon="Search" @click="onClickSearchArea" />
                            </template>
                        </el-input>
                    </el-col>
                    <el-col :span="1" style="margin-left: 2px;" @click="onClickClearSearchArea">
                        <el-button>
                            <el-icon><RefreshRight /></el-icon>
                        </el-button>
                    </el-col>
                </el-row>
            </el-col>
            <el-col :span="24">
                <el-table :data="state.searchData" style="width: 100%; margin-top: 8px;" height="250" empty-text="검색결과 없음">
                    <el-table-column prop="place_name" label="장소" width="345" height="20">
                        <template #default="scope">
                            <el-text style="font-size: 14px; font-weight: bold;">
                                <el-icon><LocationInformation /></el-icon>
                                {{ scope.row.place_name }}
                            </el-text>
                            <br>
                            <el-text style="font-size: 12px;">
                                {{ scope.row.address_name }}
                            </el-text>
                        </template>
                    </el-table-column>
                    <el-table-column prop="category_group_name" label="카테고리" width="100" height="20">
                        <template #default="scope">
                            <el-tag style="color: #4527A0;">
                                {{ scope.row.category_group_name }}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="place_url" label="정보" width="55" height="20">
                        <template #default="scope">
                            <el-link style="font-size: 24px;" :icon="Position" @click="openDialog(scope.row.place_url)">
                            </el-link>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-dialog
            v-model="state.dialogVisible"
            width="1100"
            style="height: 720px;"
        >
            <iframe
                id="iframeExample"
                width="100%"
                style="height: 600px;"
                :src="state.placeUrl"
            />
            <div style="text-align: center; margin-top: 10px">
                <el-button @click="state.dialogVisible=false">닫기</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script lang="ts" setup>
import { Location, LocationInformation, Position, RefreshRight, Search } from '@element-plus/icons-vue';
import { onMounted, reactive } from 'vue';

// Vue의 onMounted 라이프사이클 훅에서 카카오맵 로딩 함수 호출
onMounted(() => {
    loadKakaoMap();
})

const state = reactive({
    map: '' as any,
    mapStyle: 'width:100%; height:450px; position:relative;overflow:hidden;',
    loadViewHidden: true,
    viewLoadMap: false,
    mapCenter: '' as any,
    overlayOn: false,                                       // 지도 위에 로드뷰 오버레이가 추가된 상태를 가지고 있을 변수
    marker: '' as any,
    infowindow: '' as any,
    ps: '' as any,
    div: {
        container: document.getElementById('container') as any,    // 지도와 로드뷰를 감싸고 있는 div 입니다
        mapWrapper: document.getElementById('mapWrapper') as any,  // 지도를 감싸고 있는 div 입니다
        mapContainer: document.getElementById('map') as any,       // 지도를 표시할 div 입니다
        rvContainer: document.getElementById('roadview') as any,    //로드뷰를 표시할 div 입니다
    },
    loadView: {
        control: {} as any,
        rv: '' as any,
        rvClient: '' as any,
        marker: '' as any,
    } as any,
    searchText: '',
    searchData: '' as any,
    arrMarker: [] as any,
    dialogVisible: false,
    placeUrl: '' as any,
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

const openDialog = (url: any) => {
    state.placeUrl = url
    state.dialogVisible = true
}

// 지도를 초기화하는 함수
const initMap = () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const lat = position.coords.latitude; // 위도
                const lng = position.coords.longitude; // 경도

                /* div 영역 초기화 */
                state.div.mapContainer = document.getElementById('map')
                state.div.mapWrapper = document.getElementById('mapWrapper')
                state.div.container = document.getElementById('container')
                state.div.rvContainer = document.getElementById('roadview')

                const options = {
                    // @ts-ignore
                    center: new window.kakao.maps.LatLng(lat, lng), // 지도의 중심 좌표
                    level: 3, // 지도의 확대 레벨
                };

                // @ts-ignore
                state.map = new window.kakao.maps.Map(state.div.mapContainer, options); // 지도 객체 생성

                // @ts-ignore
                state.mapCenter = new window.kakao.maps.LatLng(lat, lng)

                // @ts-ignore
                state.loadView.rv = new window.kakao.maps.Roadview(state.div.rvContainer)

                // @ts-ignore
                state.loadView.rvClient = new window.kakao.maps.RoadviewClient()


                setCssStyle()       // css 세팅하기
                zoomControl()       // 줌 컨트롤 이벤트 등록
                viewMarkerAddr()    // 마커에 행정동 주소정보 표시
                addMarker()         // 지도에 마커 표시
                loadMarker()

            }
        )
    }    
}

const clearLoadView = () => {

    const lat = state.marker.getPosition().getLat();
    const lng = state.marker.getPosition().getLng(); // 경도

    /* div 영역 초기화 */
    state.div.mapContainer = document.getElementById('map')
    state.div.mapWrapper = document.getElementById('mapWrapper')
    state.div.container = document.getElementById('container')
    state.div.rvContainer = document.getElementById('roadview')

    const options = {
        // @ts-ignore
        center: new window.kakao.maps.LatLng(lat, lng), // 지도의 중심 좌표
        level: 3, // 지도의 확대 레벨
    };

    // @ts-ignore
    state.map = new window.kakao.maps.Map(state.div.mapContainer, options); // 지도 객체 생성

    // @ts-ignore
    state.mapCenter = new window.kakao.maps.LatLng(lat, lng)

    // @ts-ignore
    state.loadView.rv = new window.kakao.maps.Roadview(state.div.rvContainer)

    // @ts-ignore
    state.loadView.rvClient = new window.kakao.maps.RoadviewClient()


    setCssStyle()       // css 세팅하기
    zoomControl()       // 줌 컨트롤 이벤트 등록
    viewMarkerAddr()    // 마커에 행정동 주소정보 표시
    addMarker()         // 지도에 마커 표시
    loadMarker()

}

/* css 세팅하기 */
const setCssStyle = () => {

    // CSS를 동적으로 추가하는 코드
    const style = document.createElement('style');
    style.textContent = `
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block;font-size 12px;text-align:left;padding-bottom:4px;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;font-size:12px;text-align:left;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;font-size:11px;text-align:left;}

    #container {overflow:hidden;height:300px;position:relative;}
    #mapWrapper {width:100%;height:300px;z-index:1;}
    #rvWrapper {width:50%;height:300px;top:0;right:0;position:relative;z-index:0;}
    #container.view_roadview #mapWrapper {width: 50%;}
    #roadviewControl {position:absolute;bottom:5px;right:5px;width:42px;height:42px;z-index: 1;cursor: pointer; background: url(https://t1.daumcdn.net/localimg/localimages/07/2018/pc/common/img_search.png) 0 -450px no-repeat;}
    #roadviewControl.active {background-position:0 -350px;}
    #close {position: absolute;padding: 2px;top: 5px;left: 5px;cursor: pointer;background: #fff;border-radius: 4px;border: 1px solid #c8c8c8;box-shadow: 0px 1px #888;}
    #close .img {display: block;background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/rv_close.png) no-repeat;width: 12px;height: 12px;}
    `;
    document.head.appendChild(style)

}

/* 지도 확대-축소 줌 컨트롤 */
const zoomControl = () => {
    // @ts-ignore
    const zoomControl = new window.kakao.maps.ZoomControl()
    // @ts-ignore
    state.map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT)
}

/* 지도에 마커 표시 */
const addMarker = () => {
    // @ts-ignore
    state.marker = new window.kakao.maps.Marker({ 
        // 지도 중심좌표에 마커를 생성합니다 
        position: state.map.getCenter(),
        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    })

    // 마커 생성
    state.marker.setMap(state.map)

    // 마커가 드래그 설정
    state.marker.setDraggable(true);

    // 지도에 클릭 이벤트 등록
    // @ts-ignore
    window.kakao.maps.event.addListener(state.map, 'click', function (mouseEvent: window.kakao.maps.event.MouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        const latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        state.marker.setPosition(latlng);
    });

    var iwContent = '<div style="font-weight:bold;display:block;font-size:12px;text-align:left;padding-top:4px;padding-bottom:4px;padding-left:4px;padding-right: 24px;">카카오맵을 통해 맛집을 검색해보세요!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
    // @ts-ignore
    state.infowindow = new window.kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 마커 위에 인포윈도우를 표시합니다
    state.infowindow.open(state.map, state.marker);
}

/* 마커에 행정동 주소정보 표시 */
const viewMarkerAddr = () => {

    // @ts-ignore
    const geocoder = new window.kakao.maps.services.Geocoder()

    // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    searchAddrFromCoords(state.map.getCenter(), displayCenterInfo);

    // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
    // @ts-ignore
    window.kakao.maps.event.addListener(state.map, 'click', function(mouseEvent: any) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function(result: any, status: any) {
            // @ts-ignore
            if (status === window.kakao.maps.services.Status.OK) {
                var detailAddr = result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                
                var content = '<div class="bAddr">' +
                                '<span class="title">주소정보</span>' + 
                                detailAddr + 
                            '</div>';

                state.infowindow.close()
                state.infowindow = ''

                // @ts-ignore
                state.infowindow = new window.kakao.maps.InfoWindow({zindex:1})

                // 마커를 클릭한 위치에 표시합니다 
                state.marker.setPosition(mouseEvent.latLng);
                state.marker.setMap(state.map);

                // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                state.infowindow.setContent(content);
                state.infowindow.open(state.map, state.marker);
            }   
        });
    });

    // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
    // @ts-ignore
    window.kakao.maps.event.addListener(state.map, 'idle', function() {
        searchAddrFromCoords(state.map.getCenter(), displayCenterInfo);
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

}

const setRoadviewRoad = () => {
    state.loadView.control = document.getElementById('roadviewControl')
    // 버튼이 눌린 상태가 아니면
    if (state.loadView.control.className.indexOf('active') === -1) {
        state.loadView.control.className = 'active';

        state.loadViewHidden = false
        state.viewLoadMap = true

        state.infowindow.close()

        // 로드뷰 도로 오버레이가 보이게 합니다
        toggleOverlay(true);

        var iwContent = '<div style="font-weight:bold;display:block;font-size:12px;text-align:left;padding-top:4px;padding-bottom:4px;padding-left:4px;padding-right: 24px;">마커를 옮겨 로드뷰를 변경하세요!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        // 인포윈도우를 생성합니다
        // @ts-ignore
        state.infowindow = new window.kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });

        // 마커 위에 인포윈도우를 표시합니다
        state.infowindow.open(state.map, state.marker);
    } else {
        state.loadView.control.className = '';

        state.loadViewHidden = true
        state.viewLoadMap = false

        // 로드뷰 도로 오버레이를 제거합니다
        toggleOverlay(false);

        // 로드뷰 초기화
        clearLoadView()
    }

    // 로드뷰에 좌표가 바뀌었을 때 발생하는 이벤트를 등록합니다 
    // @ts-ignore
    window.kakao.maps.event.addListener(state.map, 'click', function(mouseEvent: any) {
     // 지도 위에 로드뷰 도로 오버레이가 추가된 상태가 아니면 클릭이벤트를 무시합니다 
        if(!state.overlayOn) {
            return;
        }

        // 클릭한 위치의 좌표입니다 
        var position = mouseEvent.latLng;

        // 마커를 클릭한 위치로 옮깁니다
        state.marker.setPosition(position);

        // 클락한 위치를 기준으로 로드뷰를 설정합니다
        toggleRoadview(position);
    });
}

/* 지도 위의 로드뷰 도로 오버레이를 추가,제거하는 함수입니다 */
const toggleOverlay = (active: any) => {
    if (active) {
        state.overlayOn = true;

        // @ts-ignore
        state.map.addOverlayMapTypeId(window.kakao.maps.MapTypeId.ROADVIEW);    // 지도 위에 로드뷰 도로 오버레이를 추가합니다

        // 지도 위에 마커를 표시합니다
        state.marker.setMap(state.map);

        // 마커의 위치를 지도 중심으로 설정합니다 
        state.marker.setPosition(state.map.getCenter());

        // 로드뷰의 위치를 지도 중심으로 설정합니다
        toggleRoadview(state.map.getCenter());
    } else {
        state.overlayOn = false;

        // @ts-ignore
        state.map.removeOverlayMapTypeId(window.kakao.maps.MapTypeId.ROADVIEW); // 지도 위의 로드뷰 도로 오버레이를 제거합니다

        // 지도 위의 마커를 제거합니다
        state.marker.setMap(null);
    }
}

/* 로드뷰를 설정하는 함수입니다 */
const toggleRoadview = (position: any) => {
    state.loadView.rvClient.getNearestPanoId(position, 50, function(panoId: any) {
        // 파노라마 ID가 null 이면 로드뷰를 숨깁니다
        if (panoId === null) {
            toggleMapWrapper(true, position);
        } else {
            toggleMapWrapper(false, position);

            // panoId로 로드뷰를 설정합니다
            state.loadView.rv.setPanoId(panoId, position);
        }
    });
}

/* 지도를 감싸고 있는 div의 크기를 조정하는 함수입니다 */
const toggleMapWrapper = (active: any, position: any) => {
    if (active) {
        // 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
        state.map.relayout();

        // 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
        state.map.setCenter(position);
    } else {

        // 지도만 보여지고 있는 상태이면 지도의 너비가 50%가 되도록 class를 변경하여
        // 로드뷰가 함께 표시되게 합니다
        if (state.div.container != null && state.div.container.className.indexOf('view_roadview') === -1) {
            state.div.container.className = 'view_roadview';

            // 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
            state.map.relayout();

            // 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
            state.map.setCenter(position);
        }
    }
}

const loadMarker = () => {
    // @ts-ignore
    const markImage = new window.kakao.maps.MarkerImage(
        'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
        // @ts-ignore
        new window.kakao.maps.Size(26, 46),
        {
            // 스프라이트 이미지를 사용합니다.
            // 스프라이트 이미지 전체의 크기를 지정하고
            // @ts-ignore
            spriteSize: new window.kakao.maps.Size(1666, 168),
            // 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
            // background-position으로 지정하는 값이며 부호는 반대입니다.
            // @ts-ignore
            spriteOrigin: new window.kakao.maps.Point(705, 114),
            // @ts-ignore
            offset: new window.kakao.maps.Point(13, 46)
        }
    );

    // @ts-ignore
    state.loadView.marker = new window.kakao.maps.Marker({
        image : markImage,
        position: state.mapCenter,
        draggable: true
    });

    // 마커에 dragend 이벤트를 등록합니다
    // @ts-ignore
    window.kakao.maps.event.addListener(state.loadView.marker, 'dragend', function() {

        // 현재 마커가 놓인 자리의 좌표입니다 
        var position = state.loadView.marker.getPosition();

        // 마커가 놓인 위치를 기준으로 로드뷰를 설정합니다
        toggleRoadview(position);
    });
}

/* 로드뷰 닫기 */
const closeRoadview = () => {
    state.mapStyle = 'width:100%; height:450px; position:relative;overflow:hidden;'
    state.loadViewHidden = true
    state.viewLoadMap = false
}

/* 현재 지도에서 검색한 장소 찾기 */
const onClickSearchArea = () => {

    /* 검색 input 이 없으면 return */
    if(state.searchText.trim() == '') {
        return
    }

    /* 검색결과로 표시된 마커 전체 제거 */
    for (var i = 0; i < state.arrMarker.length; i++) {
        state.arrMarker[i].setMap(null);  // 마커를 지도에서 제거
    }
    state.arrMarker = []

    /* info 제거 */
    state.infowindow.close()
    state.marker.setMap(null);

    // @ts-ignore
    state.ps = new window.kakao.maps.services.Places(state.map)

    state.ps.keywordSearch(state.searchText, placesSearchCB, {useMapBounds:true})

    function placesSearchCB (data: any, status: any) {
        // @ts-ignore
        if (status === window.kakao.maps.services.Status.OK) {
            for (var i=0; i<data.length; i++) {
                displayMarker(data[i]);
            }

            console.log(data)
            state.searchData = data
        }
        // @ts-ignore
        if(status === window.kakao.maps.services.Status.ZERO_RESULT) {
            state.searchData = []
            for (var idx = 0; idx < state.arrMarker.length; idx++) {
                state.arrMarker[idx].setMap(null);  // 마커를 지도에서 제거
            }
            state.arrMarker = []
        }
        console.log(status)
    }

    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place: any) {

        state.arrMarker.push(
            // @ts-ignore
            new window.kakao.maps.Marker({
                map: state.map,
                // @ts-ignore
                position: new window.kakao.maps.LatLng(place.y, place.x) 
            })
        )

        const idx = state.arrMarker.length - 1 as number

        // 마커에 클릭이벤트를 등록합니다
        // @ts-ignore
        window.kakao.maps.event.addListener(state.arrMarker[idx], 'click', function() {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            state.infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
            state.infowindow.open(state.map, state.arrMarker[idx]);
        });
    }
}

/* 검색과 관련된 것들 초기화 */
const onClickClearSearchArea = () => {

    /* 검색결과로 표시된 마커 전체 제거 */
    for (var i = 0; i < state.arrMarker.length; i++) {
        state.arrMarker[i].setMap(null);  // 마커를 지도에서 제거
    }
    state.arrMarker = []

    /* info 제거 */
    state.infowindow.close()
    state.marker.setMap(null);

    state.searchText = ''
    state.searchData = []

}

</script>

<style scoped>
/* 지도 크기 조정 */
#map {
    width: 100%;
    height: 300px;
}
.divider {
    margin-top: 8px;
    margin-bottom: 8px;
    width: 100%;
}
</style>