<template>
    <router-view></router-view>
</template>

<script lang="ts" setup>

import { onActivated } from 'vue';
import { useRouter } from 'vue-router';
import { userStore } from './store/userStore';

const userStoreObj = userStore()
const router = useRouter()

onActivated(() => {
    console.log(userStoreObj.isLoggedIn)
    if(userStoreObj.isLoggedIn == false) {
        router.push('/login')
    }
    else{
        router.push('/')
    }
})

</script>



<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 12px;
}
</style>
