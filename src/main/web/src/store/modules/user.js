import Vue from "vue";

const state = {
  token: '',
  username: '',
  password: '',
  msg: ''
}

// getters
const getters = {}

// actions
const actions = {
  login ({commit}, user) {

    return new Promise((resolve, reject) => {
      Vue.http.post("http://localhost:9003/user/login", user)
        .then((response) => {
          const data = response.data;

          if (data.code && data.code != 200) {
            commit('loginFail', data.msg)
          } else {
            commit('setUser', data)
          }

          resolve(true)
        })


    })
  },
  getUser ({commit}) {
    commit('setUser', JSON.parse(localStorage.getItem('user')))
  }

}

// mutations
const mutations = {
  setUser (state, session) {

    if (session) {

      state.token = session.token
      state.userId = session.id
      state.resourceList = session.resourceList

      localStorage.setItem('user', JSON.stringify(session))
    }
  },
  loginFail (state, msg) {
    state.msg = msg;
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
