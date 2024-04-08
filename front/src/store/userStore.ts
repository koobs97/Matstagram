import { defineStore } from 'pinia';

export const userStore = defineStore("userStore", {
    state: () => ({
        userInfo: {
            userId      : '' as string,
            userName    : '' as string,
            email       : '' as String,
            phoneNumber : '' as String,
            birthDate   : '' as String,
            genderCode  : '' as String,
        },
        authInfo: {
            username: '' as string,           // 이메일인증 성공 시
            email   : '' as string,           // 이메일인증 성공 시
            authenticated: false as boolean,  // 아이디 찾기 인증 성공 시
        }
    }),
    getters: {
        isLoggedIn: (state) => {
            return state.userInfo.userId != ''
        },
        isAuthenticated: (state) => {
            if(state.authInfo.authenticated != false) {
                return state.authInfo
            }
            else return undefined
        },
        getAuthenticated: (state) => {
            return state.authInfo
        },
    },
    actions: {
        setUsername(newUsername: string) {
            this.userInfo.userName = newUsername;
        },
        authentication(username: string, email: string, authenticated: boolean) {
            this.authInfo.username = username;
            this.authInfo.email = email;
            this.authInfo.authenticated = authenticated;
        },
        delAuthentication() {
            this.authInfo.username = '';
            this.authInfo.email = '';
            this.authInfo.authenticated = false;
        },
    },
})
