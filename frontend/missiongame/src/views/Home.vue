<template>
  <div class="home">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
    <MainComponent msg=" 지금 뭐하는 건가???" />
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from "@/components/HelloWorld.vue";
import MainComponent from "../components/MainComponent.vue";

export default {
  name: "Home",
  components: {
    //    HelloWorld,
    MainComponent,
  },
};
</script>

<style scoped>
.home {
  height: 1200px;
}
</style>