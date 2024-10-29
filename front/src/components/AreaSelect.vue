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
        <el-col :span="12">
            <el-select 
                v-model="selectedArea"
                placeholder="Select" 
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
        <el-col :span="12">
            <el-select 
                v-model="selectedSubArea"
                placeholder="Select" 
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
                    v-for="item in subArea"
                    :key="item.name"
                    :label="item.label"
                    :value="item.label"
                >
                </el-option>
            </el-select>
        </el-col>
    </el-row>
</template>
<script lang="ts" setup>
import { ref } from 'vue';

const selectedArea = ref('서울특별시')
const selectedSubArea = ref('')

const area = [
    { name: "서울특별시", label: "서울특별시" },
    { name: "경기도", label: "경기도" },
    { name: "인천광역시", label: "인천광역시" },
    { name: "대전광역시", label: "대전광역시" },
    { name: "대구광역시", label: "대구광역시" },
]

const subArea = [
    {
        name: "서울특별시", label: "서울특별시",
        subArea: [
			{
                name: "강남구", label: "강남구",
            }
		],
    },
    {
        name: "경기도", label: "경기도",
        subArea: [
			{
                name: "안양시", label: "안양시",
            }
		],
    }
]

</script>
<style scoped>
</style>