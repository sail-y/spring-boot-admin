import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login.vue";
import Home from "../components/Home.vue";

Vue.use(VueRouter);

// 创建一个路由器实例
// 并且配置路由规则

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/',
      component: Login
    },
    {
      path: '/home',
      component: Home
    }
  ]

})

router.beforeEach((to, from, next) => {
  let store = router.app.$store
  store.dispatch('getUser');
  // 判断是否登陆
  if (to.path == '/' || store.getters.isLogin) {
    next()
  } else {
    next('/')
  }
})

export default router;
