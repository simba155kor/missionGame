<template>
  <div class="choiceComponent">
    <p></p>
    <button class="btn btn-primary" @click="getMission()">미션 뽑기</button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { mapMutations } from "vuex";
import http from "../util/http-connect";

export default {
  name: "ChoiceComponent",
  props: {
    msg: String,
  },
  computed: {
    ...mapGetters([
      "getLoginState",
      "getUserId",
      "getUserImage",
      "getMyMemberNo",
    ]),
  },
  created() {
    this.getYourMission();
  },
  data() {
    return {
      canChocie: false,
    };
  },
  methods: {
    ...mapMutations(["SET_LOGIN_STATE", "SET_LOGOUT_STATE"]),
    getYourMission() {
      let query = { myId: this.getMyMemberNo };
      http
        .get(`/membermission/${query.myId}`, { query: query })
        .then(({ data }) => {
          if (data === "") {
            this.canChocie = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMission() {
      if (this.canChocie === false) {
        alert("이미 뽑았잖아.");
      } else {
        let params = {};
        http
          .get(`/mission/choiceone`, { params: params })
          .then(({ data }) => {
            this.createMissionMember(data.missionNo);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    createMissionMember(missonNo) {
      let params = { kakaomemberNo: this.getMyMemberNo, missionNo: missonNo };
      http
        .post(`/membermission/createmission`, params)
        .then(({ data }) => {
          let missionName = data.mission.missionName;
          let missionContent = data.mission.missionContent;
          alert("미션을 뽑았다! 너의 미션은 " + missionName);
          alert(missionContent);
        })
        .catch((err) => {
          console.log(err);
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

.choiceComponent {
  width: 700px;
  height: 800px;
  position: absolute;
  left: 50%;
  margin-left: -350px;
  text-align: center;
  background-color: rgba(255, 255, 255, 1);
}
</style>
