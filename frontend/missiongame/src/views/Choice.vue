<template>
  <div class="choice">
    <h1>This is an choice page</h1>
    <ChoiceComponent></ChoiceComponent>
  </div>
</template>


<style scoped>
.choice {
  height: 1200px;
}
</style>

<script>
import { mapGetters } from "vuex";
import ChoiceComponent from "../components/ChoiceComponent.vue";

export default {
  name: "Choice",
  components: { ChoiceComponent },
  created() {
    if (this.getLoginState === false) {
      alert("로그인해.");
      this.$router.push("/login");
    }
    // http.get(`/auth/code`).then(({ data }) => {
    //     console.log(data);
    //   });
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId"]),
  },
};
</script>