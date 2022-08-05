import { createStore } from "vuex";

export default createStore({
  state: {
    userId: "자네",
    loginState : false,
  },
  mutations: {
    SET_LOGIN_STATE(state, data) {
      state.loginState = true;
      state.userId = data.userId;
    },
    SET_LOGOUT_STATE(state) {
      state.loginState = false;
      state.userId = "자네";
    },
  },
  getters: {
    getUserId(state) {
      return state.userId;
    },
    getLoginState(state) {
      return state.loginState;
    }
  },
  actions: {},
  modules: {},
});
