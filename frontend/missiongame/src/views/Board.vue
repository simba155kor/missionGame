<template>
  <div class="board">
    <h1>This is an board page</h1>
  </div>
</template>


<style scoped>
.board {
  height: 1200px;
}
</style>

<script>
import { mapGetters } from "vuex";
import http from "../util/http-connect";

export default {
  name: "Board",
  components: {},
  created() {
    if (this.getLoginState === false) {
      alert("로그인해.");
      this.$router.push("/login");
    }
    http
      .get(`/board/allboard`)
      .then(({ data }) => {
        console.log(data);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId"]),
  },
};
</script>