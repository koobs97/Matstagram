import { createRouter, createWebHistory } from 'vue-router';

import userLogin from './components/userLogin.vue';
import indexHome from './indexHome.vue';
import { userStore } from './store/userStore';

import SearchUserId from './views/SearchUserId.vue';
import SearchUserPw from './views/SearchUserPw.vue';
import ShowUserId from './views/ShowUserId.vue';
import joinMember from './views/joinMember.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: indexHome,
            beforeEnter: async (to, from, next) => {

                const isLoggedIn = await userStore().isLoggedIn

                if (isLoggedIn) {
                  next();           // 로그인 상태면 계속 진행
                } else {
                  next('/login');   // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
                }
              },
        },
        {
            path: '/login',
            component: userLogin,
        },
        {
            path: '/login/join',
            component: joinMember,
        },
        {
          path: '/login/idSearch',
            component: SearchUserId,
        },
        {
          path: '/login/pwSearch',
          component: SearchUserPw,
        },
        {
          path: '/login/showId/',
            component: ShowUserId,
            beforeEnter: async (to, from, next) => {

              const isAuthenticated = await userStore().isAuthenticated

              if (isAuthenticated != undefined) {
                next();                   // 인증 완료 상태면 계속 진행
              } else {
                next('/login/idSearch');  // 인증 완료 되어 있지 않으면 아이디찾기 페이지로 리다이렉트
              }
            },
        },
    ]
})
  
export default router