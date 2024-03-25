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
     * @param url 
     * @param params 
     * @returns 
     */
    public static post = async (url: string, params: Object): Promise<any> => {
        
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

}

/**
 * axios Interceptors
 */
axios.interceptors.request.use((config) => {

    return config

}, (error) => {
    return Promise.reject(error)
})