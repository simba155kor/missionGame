<template>
  <div class="mainComponent">
    <p></p>
    <h1>
      <b>{{ userId }} {{ msg }}</b>
    </h1>
    <img alt="자네 지금 뭐하나?" src="../assets/main1.jpg" />
    <p></p>
    <p><b>각자 임무를 뽑으세요.</b></p>
    <p><b>각자의 임무를 맞추세요.</b></p>
    <p><b>자기의 임무를 들키지 마세요.</b></p>
    <h3></h3>
    <button class="btn btn-primary" @click="goPick()">시작하기</button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "MainComponent",
  props: {
    msg: String,
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId"]),
  },
  created() {
    console.log(this.getUserId);
    this.userId = this.getUserId;
  },
  data() {
    return {
      userId: "hhh",
    };
  },
  methods: {
    goPick() {
      this.$router.push("/choice");
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

.mainComponent {
  width: 700px;
  height: 800px;
  position: absolute;
  left: 50%;
  margin-left: -350px;
  text-align: center;
  background-color: rgba(255, 255, 255, 1);
}
</style>
