<template>
  <div class="oauthloginpage">
    <h1>카카오 로그인 중~~~~~~~~~~~~~~~~</h1>
    <h1>카카오 로그인 중~~~~~~~~~~~~~~~~</h1>
    <h1>카카오 로그인 중~~~~~~~~~~~~~~~~</h1>
  </div>
</template>


<style scoped>
.oauthloginpage {
  height: 1200px;
}
</style>

<script>
import http from "../util/http-connect";
import { mapMutations } from "vuex";

export default {
  name: "Oauthloginpage",
  components: {},
  created() {
    let queryString = window.location.search;
    let code = queryString.replace("?code=", "");
    this.kakaoOauthLogin(code);
  },
  methods: {
    ...mapMutations(["SET_LOGIN_STATE", "SET_LOGOUT_STATE"]),
    kakaoOauthLogin(code) {
      let params = { auth_code: code };
      http
        .get(`/auth/code`, { params: params })
        .then(({ data }) => {
          console.log(data);
          console.log("!!");
          localStorage.setItem("jwtFake", data);

          alert("카카오 로그인 성공!");
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
          alert("error");
        });
    },
  },
};
</script>