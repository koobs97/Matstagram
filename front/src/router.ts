import { createRouter, createWebHistory } from 'vue-router';

import indexHome from './indexHome.vue';
import { userStore } from './store/userStore';

// views/login 폴더 내의 모든 파일을 import
const requirePages = require.context(
  // 페이지 디렉토리의 상대 경로
  './views/login',
  // 하위 디렉토리 포함 여부
  true,
  // 파일 이름을 확인하는 정규식
  /\.vue$/
);

// 페이지 경로를 저장할 배열
const routes = [];

// requirePages로 가져온 페이지 파일들을 순회하여 라우터 경로 등록
requirePages.keys().forEach(fileName => {
  // 파일 경로를 기반으로 페이지 컴포넌트 가져오기
  const component = requirePages(fileName).default;
  // 파일 경로에서 경로명 추출
  const path = fileName.replace(/^\.\//, '').replace(/\.vue$/, '');

  // 라우터 경로 객체 생성 및 배열에 추가
  routes.push({
    path: '/' + path,
    component: component
  });
});

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: indexHome,
            beforeEnter: async (to, from, next) => {
              await console.log(requirePages)
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
            component: requirePages('./userLogin.vue').default,
        },
        {
            path: '/login/join',
            component: requirePages('./joinMember.vue').default
        },
        {
          path: '/login/idSearch',
            component: requirePages('./SearchUserId.vue').default
        },
        {
          path: '/login/pwSearch',
          component: requirePages('./SearchUserPw.vue').default
        },
        {
          path: '/login/showId',
            component: requirePages('./ShowUserId.vue').default,
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