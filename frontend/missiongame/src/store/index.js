import { createStore } from "vuex";

export default createStore({
  state: {
    userId: "자네",
    userImage : "null",
    loginState : false,
  },
  mutations: {
    SET_LOGIN_STATE(state, data) {
      state.loginState = true;
      state.userId = data.userId;
      state.userImage = data.userImage;
    },
    SET_LOGOUT_STATE(state) {
      state.loginState = false;
      state.userId = "자네";
      state.userImage = "null";
    },
  },
  getters: {
    getUserId(state) {
      return state.userId;
    },
    getUserImage(state) {
      return state.userImage;
    },
    getLoginState(state) {
      return state.loginState;
    }
  },
  actions: {},
  modules: {},
});
