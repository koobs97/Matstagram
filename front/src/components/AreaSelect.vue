<!--
 * [사는지역 선택 다이얼로그]
 * 
 * @Class   AreaSelect.vue
 * @Author  Koo Bon Sang
 * @Date    2024.10.29
 * @Version 1.1.0
 * 
-->
<template>
    <el-row>
        <el-col :span="11" 
            style="
                text-align: right;
                margin-left: 8px;
                ">
            <el-select 
                v-model="selectedArea"
                @change="onChangeSubArea"
                placeholder="선택"
                filterable
                ref="area1"
                style="
                    width: 240px;
                    height: 32px;
                    color: #303133;
                    background-color: #f6f1ff;
                    border: 2px solid #f6f1ff;
                    border-radius: 6px;
                    font-family: Arial;
                    cursor: pointer;
                    transition: all 0.3s ease;
                    "
                >
                <el-option
                    style="color: #303133;"
                    v-for="item in area"
                    :key="item.name"
                    :label="item.label"
                    :value="item.label"
                >
                </el-option>
            </el-select>
        </el-col>
        <el-col :span="12"
            style="
                text-align: right;
                margin-left: 4px;
                ">
            <el-select 
                v-model="selectedSubArea"
                placeholder="선택"
                filterable
                ref="area2"
                style="
                    width: 240px;
                    height: 32px;
                    color: #303133;
                    background-color: #f6f1ff;
                    border: 2px solid #f6f1ff;
                    border-radius: 6px;
                    font-family: Arial;
                    cursor: pointer;
                    transition: all 0.3s ease;
                    "
                >
                <el-option
                    style="color: #303133;"
                    v-for="item in state.subArea"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name"
                >
                </el-option>
            </el-select>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { nextTick, onMounted, reactive, ref } from 'vue';

const selectedArea = ref('서울특별시')
const selectedSubArea = ref('강남구')

const area1 = ref(null) as any
const area2 = ref(null) as any

const state = reactive({
    subArea: [{}] as any,
})

onMounted(() => {
    onChangeSubArea()
    nextTick(()=>{
        area1.value.toggleMenu()
    })
})

const onChangeSubArea = () => {
    const subAreaList = subAreaAll.find(area => area.name === selectedArea.value);
    state.subArea = subAreaList?.subArea
    selectedSubArea.value = state.subArea[0].name
    area2.value.toggleMenu()
}

const area = [
    { name: "서울특별시", label: "서울특별시",},
    { name: "경기도", label: "경기도" },
    { name: "인천광역시", label: "인천광역시" },
    { name: "대전광역시", label: "대전광역시" },
    { name: "대구광역시", label: "대구광역시" },
    { name: "부산광역시", label: "부산광역시" },
    { name: "울산광역시", label: "울산광역시" },
    { name: "광주광역시", label: "광주광역시" },
    { name: "강원도", label: "강원도" },
    { name: "충청북도", label: "충청북도" },
    { name: "충청남도", label: "충청남도" },
    { name: "경상북도", label: "경상북도" },
    { name: "경상남도", label: "경상남도" },
    { name: "전라북도", label: "전라북도" },
    { name: "전라남도", label: "전라남도" },
    { name: "제주도", label: "제주도" },
    { name: "경상남도", label: "경상남도" },
]

const subAreaAll = [
    {
        name: "서울특별시", label: "서울특별시",
        subArea: [
            { name : "강남구" },
            { name : "강동구" },
            { name : "강북구" },
            { name : "강서구" },
            { name : "관악구" },
            { name : "광진구" },
            { name : "구로구" },
            { name : "금천구" },
            { name : "노원구" },
            { name : "도봉구" },
            { name : "동대문구"},
            { name : "동작구" },
            { name : "마포구" },
            { name : "서대문구" },
            { name : "서초구" },
            { name : "성동구" },
            { name : "성북구" },
            { name : "송파구" },
            { name : "양천구" },
            { name : "영등포구 "},
            { name : "용산구" },
            { name : "은평구" },
            { name : "종로구" },
            { name : "중구" },
            { name : "중랑구" },
        ]
    },
    {
        name: "경기도", label: "경기도",
        subArea: [
            { name : "고양시" },
            { name : "과천시" },
            { name : "광명시" },
            { name : "광주시" },
            { name : "구리시" },
            { name : "군포시" },
            { name : "김포시" },
            { name : "남양주시" },
            { name : "동두천시" },
            { name : "부천시" },
            { name : "성남시" },
            { name : "수원시" },
            { name : "시흥시" },
            { name : "안산시" },
            { name : "안성시" },
            { name : "안양시" },
            { name : "양주시" },
            { name : "오산시" },
            { name : "용인시" },
            { name : "의왕시" },
            { name : "의정부시" },
            { name : "이천시" },
            { name : "파주시" },
            { name : "평택시" },
            { name : "포천시" },
            { name : "하남시" },
            { name : "화성시" },
            { name : "가평군" },
            { name : "양평군" },
            { name : "여주군" },
            { name : "연천군" },
        ]
    }, 
    { 
        name: "인천광역시", label : "인천광역시",
        subArea: [
            { name : "계양구" },
            { name : "미추홀구" },
            { name : "남동구" },
            { name : "동구" },
            { name : "부평구" },
            { name : "서구" },
            { name : "연수구" },
            { name : "중구" },
            { name : "강화군" },
            { name : "옹진군" },
        ]
    },
    {
		name: "대전광역시", label: "대전광역시",
		subArea: [
            { name : "대덕구" }, 
            { name : "동구" }, 
            { name : "서구" }, 
            { name : "유성구" }, 
            { name : "중구" }
        ],
	},
    {
		name: "대구광역시", label: "대구광역시",
		subArea: [
            { name : "남구" },
            { name : "달서구" },
            { name : "동구" },
            { name : "북구" },
            { name : "서구" },
            { name : "수성구" },
            { name : "중구" },
            { name : "달성군" }
        ],
	},
    {
        name: "부산광역시", label: "부산광역시",
        subArea: [
            { name : "강서구" },
            { name : "금정구" },
            { name : "남구" },
            { name : "동구" },
            { name : "동래구" },
            { name : "부산진구" },
            { name : "북구" },
            { name : "사상구" },
            { name : "사하구" },
            { name : "서구" },
            { name : "수영구" },
            { name : "연제구" },
            { name : "영도구" },
            { name : "중구" },
            { name : "해운대구" },
            { name : "기장군" },
        ]
    },
    {
		name: "울산광역시", label: "울산광역시",
		subArea: [
            { name : "남구" }, 
            { name : "동구" }, 
            { name : "북구" }, 
            { name : "중구" }, 
            { name : "울주군" }
        ],
	},
    {
		name: "광주광역시", label: "광주광역시",
		subArea: [
            { name : "광산구" }, 
            { name : "남구" }, 
            { name : "동구" }, 
            { name : "북구" }, 
            { name : "서구" }
        ],
	},
    {
        name: "강원도", label: "강원도",
        subArea: [
            { name : "강릉시" },
            { name : "동해시" },
            { name : "삼척시" },
            { name : "속초시" },
            { name : "원주시" },
            { name : "춘천시" },
            { name : "태백시" },
            { name : "고성군" },
            { name : "양구군" },
            { name : "양양군" },
            { name : "영월군" },
            { name : "인제군" },
            { name : "정선군" },
            { name : "철원군" },
            { name : "평창군" },
            { name : "홍천군" },
            { name : "화천군" },
            { name : "횡성군" },
        ]
    },
    {
        name: "충청북도", label: "충청북도",
        subArea: [
            { name : "제천시" },
            { name : "청주시" },
            { name : "충주시" },
            { name : "괴산군" },
            { name : "단양군" },
            { name : "보은군" },
            { name : "영동군" },
            { name : "옥천군" },
            { name : "음성군" },
            { name : "증평군" },
            { name : "진천군" },
            { name : "청원군" },
        ]
    },
    {
        name: "충청남도", label: "충청남도",
        subArea: [
            { name : "계룡시" },
            { name : "공주시" },
            { name : "논산시" },
            { name : "보령시" },
            { name : "서산시" },
            { name : "아산시" },
            { name : "천안시" },
            { name : "금산군" },
            { name : "당진군" },
            { name : "부여군" },
            { name : "서천군" },
            { name : "연기군" },
            { name : "예산군" },
            { name : "청양군" },
            { name : "태안군" },
            { name : "홍성군" },
        ]
    },
    {
        name: "경상북도", label: "경상북도",
        subArea: [
            { name : "경산시" },
            { name : "경주시" },
            { name : "구미시" },
            { name : "김천시" },
            { name : "문경시" },
            { name : "상주시" },
            { name : "안동시" },
            { name : "영주시" },
            { name : "영천시" },
            { name : "포항시" },
            { name : "고령군" },
            { name : "군위군" },
            { name : "봉화군" },
            { name : "성주군" },
            { name : "영덕군" },
            { name : "영양군" },
            { name : "예천군" },
            { name : "울릉군" },
            { name : "울진군" },
            { name : "의성군" },
            { name : "청도군" },
            { name : "청송군" },
            { name : "칠곡군" },
        ]
    },
    {
        name: "경상남도", label: "경상남도",
        subArea: [
            { name : "거제시" },
            { name : "김해시" },
            { name : "마산시" },
            { name : "밀양시" },
            { name : "사천시" },
            { name : "양산시" },
            { name : "진주시" },
            { name : "진해시" },
            { name : "창원시" },
            { name : "통영시" },
            { name : "거창군" },
            { name : "고성군" },
            { name : "남해군" },
            { name : "산청군" },
            { name : "의령군" },
            { name : "창녕군" },
            { name : "하동군" },
            { name : "함안군" },
            { name : "함양군" },
            { name : "합천군" },
        ]
    },
    {
        name: "전라북도", label: "전라북도",
        subArea: [
            { name : "군산시" },
            { name : "김제시" },
            { name : "남원시" },
            { name : "익산시" },
            { name : "전주시" },
            { name : "정읍시" },
            { name : "고창군" },
            { name : "무주군" },
            { name : "부안군" },
            { name : "순창군" },
            { name : "완주군" },
            { name : "임실군" },
            { name : "장수군" },
            { name : "진안군" },
        ]
    },
    {
        name: "전라남도", label: "전라남도",
        subArea: [
            { name : "광양시" },
            { name : "나주시" },
            { name : "목포시" },
            { name : "순천시" },
            { name : "여수시" },
            { name : "강진군" },
            { name : "고흥군" },
            { name : "곡성군" },
            { name : "구례군" },
            { name : "담양군" },
            { name : "무안군" },
            { name : "보성군" },
            { name : "신안군" },
            { name : "영광군" },
            { name : "영암군" },
            { name : "완도군" },
            { name : "장성군" },
            { name : "장흥군" },
            { name : "진도군" },
            { name : "함평군" },
            { name : "해남군" },
            { name : "화순군" },
        ]
    },
    {
        name: "제주도", label: "제주도",
        subArea: [
            { name : "서귀포시" },
            { name : "제주시" }
            ]
    },
    {
        name: "경상남도", label: "경상남도",
        subArea: [
            { name : "거제시" },
            { name : "김해시" },
            { name : "마산시" },
            { name : "밀양시" },
            { name : "사천시" },
            { name : "양산시" },
            { name : "진주시" },
            { name : "진해시" },
            { name : "창원시" },
            { name : "통영시" },
            { name : "거창군" },
            { name : "고성군" },
            { name : "남해군" },
            { name : "산청군" },
            { name : "의령군" },
            { name : "창녕군" },
            { name : "하동군" },
            { name : "함안군" },
            { name : "함양군" },
            { name : "합천군" },
        ]
    },
]

</script>
<style scoped>
</style>