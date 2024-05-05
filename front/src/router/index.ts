// import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

// // 동적 import를 사용하여 모든 페이지 컴포넌트를 가져옵니다.
// const pages = (import.meta as any).glob('../views/pages/*.vue');

// const routes: RouteRecordRaw[] = Object.keys(pages).map((path) => {
//     const name = path.match(/\.\/(.+)\.vue$/)![1];
//     return {
//         path: `/${name}`,
//         name,
//         component: pages[path],
//     };
// });

// const router = createRouter({
//     history: createWebHistory(),
//     routes,
// });

// export default router;