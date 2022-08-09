<template>
  <div class="boardComponent">
    <table class="boardTable">
      <tr>
        <th>닉네임</th>
        <th>프로필 이미지</th>
        <th>미션</th>
        <th>버튼</th>
        <th>제작연도</th>
      </tr>
      <tr v-for="(board, index) in boardList" :key="index">
        <td>
          <span>{{ board.nickname }}</span>
        </td>
        <td><img :src="board.profileImage" /></td>
        <td>
          <span v-if="checkMe(board.memberNo)">{{ yourMission }}</span>
          <span v-else> *****************</span>
        </td>
        <!-- <td><span v-html="item.subtitle"></span></td> -->
        <td>
          <button class="btn btn-secondary" @click="showModal = true">
            입력
          </button>
        </td>
        <BoardModal
          v-if="showModal"
          @close="showModal = false"
          :nickname="board.nickname"
          :yourId="board.memberNo"
        >
        </BoardModal>

        <!-- <td><span v-html="item.director"></span></td>
        <td><span v-html="item.actor"></span></td>
        <td><span v-html="item.userRating"></span></td> -->
      </tr>
    </table>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "../util/http-connect";
import BoardModal from "./BoardModal.vue";

export default {
  name: "BoardComponent",
  components: { BoardModal },
  created() {
    if (this.getLoginState === false) {
      alert("로그인해.");
      this.$router.push("/login");
    } else {
      this.getYourMission();
    }
    http
      .get(`/board/allboard`)
      .then(({ data }) => {
        console.log(data);
        this.boardList = data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  data() {
    return {
      boardList: null,
      showModal: false,
      yourMission: "",
    };
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId", "getMyMemberNo"]),
  },
  methods: {
    checkMe(num) {
      if (this.getMyMemberNo == num) return true;
      else return false;
    },
    getYourMission() {
      let query = { myId: this.getMyMemberNo };
      http
        .get(`/membermission/${query.myId}`, { query: query })
        .then(({ data }) => {
          console.log(data);
          this.yourMission = data;
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
.board {
  height: 1200px;
}

td,
th {
  padding: 10px;
  border: 1px solid #ccc;
}
body {
  padding: 1rem;
}

.boardComponent {
  width: 700px;
  height: 800px;
  position: absolute;
  left: 50%;
  margin-left: -350px;
  text-align: center;
  background-color: rgba(255, 255, 255, 1);
}
</style>
