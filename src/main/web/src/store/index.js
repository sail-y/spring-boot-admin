import Vue from "vue";
import Vuex from "vuex";
import * as actions from "./actions";
import * as getters from "./getters";
import user from './modules/user'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'
// 如果在模块化构建系统中，请确保在开头调用了 Vue.use(Vuex)
export default new Vuex.Store({

  actions,
  getters,
  modules: {
    user
  },
  strict: debug
})


