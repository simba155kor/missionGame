import { createStore } from "vuex";

export default createStore({
  state: {
    userId: "자네",
    userImage : "null",
    loginState: false,
    myMemberNo: "",
    timeOutState : false,
  },
  mutations: {
    SET_LOGIN_STATE(state, data) {
      state.loginState = true;
      state.userId = data.userId;
      state.userImage = data.userImage;
      state.myMemberNo = data.memberNo;
    },
    SET_LOGOUT_STATE(state) {
      state.loginState = false;
      state.userId = "자네";
      state.userImage = "null";
      state.myMemberNo = "";
    },
    SET_TIMEOUT_STATE_TRUE(state) {
      state.timeOutState = true;
    },
    SET_TIMEOUT_STATE_FALSE(state) {
      state.timeOutState = true;
    },
  },
  getters: {
    getTimeOut(state) {
      return state.timeOutState;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserImage(state) {
      return state.userImage;
    },
    getLoginState(state) {
      return state.loginState;
    },
    getMyMemberNo(state) {
      return state.myMemberNo;
    }
  },
  actions: {},
  modules: {},
});
