import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import { createPinia } from 'pinia'; // store에서 사용하는 pinia
import piniaPersist from 'pinia-plugin-persist'; // store 새로고침 시 유지되로록
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

const app = createApp(App)
const pinia = createPinia()

app.config.errorHandler = (err, vm, info) => {
    console.error('ERROR', err);
}

app.use(ElementPlus).use(router).use(pinia.use(piniaPersist))
app.mount('#app')
