<template>
    <el-row>
        <div style="width: 100%;">
            <FullCalendar
                :options="state.calendarOptions"
            />
        </div>
    </el-row>
    <el-row>
        <el-col :span="24" style="text-align: right; margin-top: 12px;">
            <el-button 
                style="
                    background-color: #1976d2; 
                    width: 20px; height: 20px; 
                    padding: 0px; 
                    font-size: 12px; 
                    color: #ffffff; 
                    border-color: #1976d2; 
                    font-weight: bold;">F</el-button>
            <el-link 
                :underline="false" 
                href="https://fullcalendar.io/" 
                target="_blank" 
                style="
                    font-size: 12px; 
                    margin-left: 3px; 
                    font-weight: bold;">
                FullCalendar
            </el-link>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import dayGridPlugin from '@fullcalendar/daygrid'; // DayGrid 플러그인 (월별, 주별 보기)
import interactionPlugin from '@fullcalendar/interaction'; // 상호작용 플러그인 (클릭, 드래그)
import timeGridPlugin from '@fullcalendar/timegrid'; // TimeGrid 플러그인 (시간별 보기)
import FullCalendar from '@fullcalendar/vue3'; // FullCalendar의 Vue3 버전 가져오기
import { reactive } from 'vue';

const state = reactive({
    calendarOptions: {
        plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        locale: 'ko',
        headerToolbar: {
            left: 'prev,next today',   // 왼쪽에 '이전', '다음', '오늘' 버튼 표시
            center: 'title',           // 가운데에 달력의 타이틀 (월/연도) 표시
            right: 'dayGridMonth,timeGridWeek,timeGridDay' // 오른쪽에 월/주/일 보기 전환 버튼 표시
        },
        buttonText: {
            today: '오늘',
            month: '월',
            week: '주',
            day: '일'
        },
        selectable: true,   // 클릭 및 드래그 선택 기능
        selectMirror: true, // 힌트표시
    },
})


</script>

<style scoped>

/* 일요일 날짜: 빨간색 */
.fc-day-sun a {
    color: red;
}

/* 토요일 날짜: 파란색 */
.fc-day-sat a {
    color: blue;
}

/* FullCalendar의 기본 폰트를 Quicksand로 설정하고, 글씨 크기를 줄이기 */
.fc {
    font-family: 'Quicksand', sans-serif; /* 폰트 변경 */
    font-size: 0.85rem; /* 기본 글씨 크기 줄이기 */
}

/* 달력 헤더 부분 (월, 연도, 네비게이션 버튼) 스타일 */
.fc-toolbar-title {
    font-size: 1rem; /* 달력의 제목 크기를 살짝 키우기 */
    font-weight: 600;  /* 제목 글씨 두께 */
}

/* 요일 이름 (월, 화, 수 등) 스타일 */
.fc-col-header-cell {
    font-size: 0.9rem; /* 요일 이름 크기 */
    font-weight: 500;
}

/* 날짜 숫자 스타일 */
.fc-daygrid-day-number {
    font-size: 0.8rem; /* 날짜 크기 줄이기 */
}
/* 이벤트 텍스트 스타일 */
.fc-event-title {
    font-size: 0.75rem; /* 이벤트 텍스트 크기 */
    font-weight: 500; /* 이벤트 텍스트 두께 */
}

</style>