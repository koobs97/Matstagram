import axios from "axios";
import CryptoJS from 'crypto-js';
import { ElLoading, ElMessageBox } from 'element-plus';

export class Common {

    /**
     * Alert Message Box
     * @param message 
     * @param options 
     */
    public static alert = (message: string, options?: any) => {
        ElMessageBox.alert(message, '메시지', {
            confirmButtonText: 'OK',
            type: 'info',
            callback: options.callback || (() => {}),
        })
    }

    /**
     * Confirm Message Box
     * @param message 
     * @param options 
     */
    public static confirm = (message: string, options?: any) => {
        ElMessageBox.confirm(message, '확인', {
            cancelButtonText: '취소',
            confirmButtonText: '확인',
            cancelButtonClass: 'el-button--info',
            confirmButtonClass: 'el-button--primary',
            type: 'info',
            callback: options.callback || (() => {}),
        })
    }

    /**
     * Warning Message Box
     * @param message 
     * @param options 
     */
    public static warning = (message: string, options?: any) => {
        ElMessageBox.alert(message, '경고', {
            confirmButtonText: 'OK',
            type: 'warning',
            callback: options.callback || (() => {}),
        })
    }

    /**
     * Error Message Box
     * @param message 
     * @param options 
     */
    public static error = (message: string, options?: any) => {
        ElMessageBox.alert(message, '에러', {
            confirmButtonText: 'OK',
            type: 'error',
        })
    }

    public static encypt = (params: string): string => {

        const secretKey = 'aGcdefgihjkl6afl70stKvwx2zabcKKf'    // 32자리
        const iv = '0293557701946234'                           // 16자리
    
        const data = params.padEnd(32, " ")
        
        const cipher = CryptoJS.AES.encrypt(data, CryptoJS.enc.Utf8.parse(secretKey), {
            iv: CryptoJS.enc.Utf8.parse(iv),
            mode: CryptoJS.mode.CBC
        })

        return cipher.toString()
    }

}


export class Api {

    /**
     * Axios Transaction post
     * @param url           // backend url
     * @param params        // 보낼 파라미터
     * @param loadingOption // loading 옵션 사용 여부
     * @returns 
     */
    public static post = async (url: string, params: Object, loadingOption?: Boolean): Promise<any> => {
        
        if(loadingOption) {
            const loading = ElLoading.service({
                lock: true,
                text: 'Loading',
                background: 'rgba(0, 0, 0, 0.7)',
            })
    
            try {
    
                const retData = await axios.post(url, params)
    
                loading.close()
    
                return retData
            } catch (error) {
                loading.close()
                Common.error('요청 중 에러가 발생했습니다.')
                return Promise.reject(error)
            }
        }
        else {
    
            try {
    
                const retData = await axios.post(url, params)
    
                return retData
            } catch (error) {
                Common.error('요청 중 에러가 발생했습니다.')
                return Promise.reject(error)
            }
        }
    }

}

/**
 * axios Interceptors
 */
axios.interceptors.request.use((config) => {

    return config

}, (error) => {
    return Promise.reject(error)
})

/**
 * EventBus 구현
 * @summary 같은 레벨의 컴포넌트 사이에 이벤트를 주고받을 수 있다.
 */
type EventCallback = (data?: any) => void;
class EventBus {
    private events: Map<string, EventCallback[]> = new Map();

    on(event: string, callback: EventCallback) {
        if (!this.events.has(event)) {
            this.events.set(event, []);
        }
        this.events.get(event)!.push(callback)
    }
    off(event: string, callback: EventCallback) {
        const callbacks = this.events.get(event)
        if(callbacks) {
            this.events.set(event, callbacks.filter(cb => cb !== callback))
        }
    }
    emit(event: string, data?: any) {
        const callbacks = this.events.get(event)
        if(callbacks) {
            callbacks.forEach(callback => callback(data))
        }
    }
}
export const eventBus = new EventBus()