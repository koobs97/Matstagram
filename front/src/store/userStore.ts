import { defineStore } from 'pinia';

interface userState {
    userId      : string,
    userName    : string,
    email       : String,
    phoneNumber : string,
    birthDate   : string,
    genderCode  : string,
}

export const userStore = defineStore("userStore", {
    state: () => ({
        userInfo: {
            userId      : '' as string,
            userName    : '' as string,
            email       : '' as string,
            phoneNumber : '' as string,
            birthDate   : '' as string,
            genderCode  : '' as string,
        } as userState,
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
        getUserInfo: (state) => {
            return state.userInfo
        },
    },
    actions: {
        /* 유저 정보 세팅 */
        setUserInfo(userInfo: userState): void {
            this.userInfo = userInfo
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
        delUserInfo() {
            this.userInfo = {
                userId      : '' as string,
                userName    : '' as string,
                email       : '' as string,
                phoneNumber : '' as string,
                birthDate   : '' as string,
                genderCode  : '' as string,
            }
        }
    },
    persist: {
        enabled: true,                                              // 전체 state 대상, SessionStorage가 default 저장
        strategies: [
          { storage: sessionStorage, paths: ["userInfo"] },         // SessionStorage 저장
        ],
    },
})
