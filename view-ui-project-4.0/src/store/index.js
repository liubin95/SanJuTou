import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

//储存
const state = {
  // 存储token
  token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
  userName: localStorage.getItem('userName') ? localStorage.getItem('userName') : ''
};
//处理state
const mutations = {
  // 修改token，并将token存入localStorage
  changeLogin: (state, token) => {
    state.token = token;
    localStorage.setItem('token', token);
  },
  changeLoginName: (state, name) => {
    state.userName = name;
    localStorage.setItem('userName', name);
  }
};
//提交mutations
const actions = {
};
//获得state
const getters = {
  getdata: state => state.notes.i
};

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  strict: process.env.NODE_ENV !== 'production' //在非生产环境下,使用严格模式
});
