import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
const pinia = createPinia()

app.config.errorHandler = (err, vm, info) => {
    console.error('ERROR', err);
}

app.use(ElementPlus).use(router).use(pinia)
app.mount('#app')
