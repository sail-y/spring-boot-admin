
import Vue from 'vue'
import VueResource from 'vue-resource'
import store from './store/index'
import router from './routes/index'
import App from './App.vue'

Vue.use(VueResource);



// 现在我们可以启动应用了！
// 路由器会创建一个 App 实例，并且挂载到选择符 #app 匹配的元素上。
const app = new Vue({
  // （缩写）相当于 router: router
  router,
  // 把 store 对象提供给 “store” 选项，这可以把 store 的实例注入所有的子组件
  store,
  render: h => h(App)
}).$mount('#app')

