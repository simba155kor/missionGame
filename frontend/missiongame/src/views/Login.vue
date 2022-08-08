<template>
  <div class="login">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
    <LoginComponent msg="로그인" />
  </div>
</template>

<script>
import LoginComponent from "../components/LoginComponent.vue";

export default {
  components: { LoginComponent },
};
</script>

<style scoped>
.login {
  height: 1200px;
}
</style>
