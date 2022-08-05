<template>
  <div class="loginComponent">
    <h1>{{ msg }}</h1>
    <div>
      <p>
        <b>아이디</b
        ><input
          type="text"
          v-model="userLoginData.id"
          style="margin-left: 16px"
        />
      </p>
      <p>
        <b>패스워드</b
        ><input
          type="password"
          v-model="userLoginData.pwd"
          style="margin-left: 4px"
        />
      </p>
    </div>
    <br />
    <button class="btn btn-primary" @click="login()">로그인</button>
    <button class="btn btn-success" style="margin-left: 3px">회원가입</button>
  </div>
</template>

<script>
import http from "../util/http-connect";
import { mapMutations } from "vuex";
import { mapGetters } from "vuex";

export default {
  name: "LoginComponent",
  props: {
    msg: String,
  },
  data() {
    return {
      userLoginData: { id: null, pwd: null },
    };
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId"]),
  },
  methods: {
    ...mapMutations(["SET_LOGIN_STATE", "SET_LOGOUT_STATE"]),
    login() {
      http.post(`/member/login`, this.userLoginData).then(({ data }) => {
        if (data.id != null) {
          //로그인 vuex 변경할것.
          this.SET_LOGIN_STATE({
            userId: data.id,
          });
          alert("로그인 성공.");
          this.$router.push("/");
        } else {
          alert("로그인 실패.");
        }
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #cd7228;
}

.loginComponent {
  width: 700px;
  height: 800px;
  position: absolute;
  left: 50%;
  margin-left: -350px;
  text-align: center;
  background-color: rgba(255, 255, 255, 1);
}
</style>
