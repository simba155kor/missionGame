<template>
  <div class="boardComponent">
    <table class="boardTable">
      <tr>
        <th>닉네임</th>
        <th>프로필 이미지</th>
        <th>미션</th>
        <th>예측하기</th>
      </tr>
      <tr v-for="(board, index) in boardList" :key="index">
        <td>
          <span>{{ board.nickname }}</span>
        </td>
        <td><img :src="board.profileImage" height="150" width="150" /></td>
        <td>
          <span
            ><b> {{ board.missionName }} </b></span
          >
          <br />
          <span v-if="checkMe(board.memberNo)">{{
            yourMission.missionContent
          }}</span>
          <span v-else-if="this.getTimeOut">
            {{ allUserMissionList[board.memberNo].missionContent }}</span
          >
          <span v-else> *****************</span>
        </td>
        <!-- <td><span v-html="item.subtitle"></span></td> -->
        <td>
          <button
            class="btn btn-secondary"
            @click="openModal(board.nickname, board.memberNo)"
          >
            입력
          </button>
        </td>

        <!-- <td><span v-html="item.director"></span></td>
        <td><span v-html="item.actor"></span></td>
        <td><span v-html="item.userRating"></span></td> -->
      </tr>
      <BoardModal
        v-if="showModal"
        @close="showModal = false"
        :nickname="propsNickname"
        :yourId="propsMemberNo"
      >
      </BoardModal>
    </table>
    <div class="blank" style="margin-top: 200px"></div>
    <table v-if="this.getTimeOut" class="boardResult">
      <tr>
        <th>닉네임</th>
        <th>프로필 이미지</th>
        <th>예측</th>
      </tr>
      <tr v-for="(board, index) in boardList" :key="index">
        <td>
          <span>{{ board.nickname }}</span>
        </td>
        <td><img :src="board.profileImage" height="150" width="150" /></td>
        <td>
          <!-- <span v-if="checkMe(board.memberNo)">{{ yourMission }}</span>
          <span v-else> *****************</span> -->
          <tr>
            <th>닉네임</th>
            <th>예측내용</th>
          </tr>
          <tr
            v-for="(predictlist, index) in predictLists[board.memberNo]"
            :key="index"
          >
            <td>{{ predictlist.nickname }}</td>
            <td>{{ predictlist.predictContent }}</td>
          </tr>
        </td>
        <!-- <td><span v-html="item.subtitle"></span></td> -->

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
        // console.log(data);
        this.boardList = data;
        for (let a = 0; a < this.boardList.length; a++) {
          this.getManMissionTitle(this.boardList[a].memberNo, a);
        }
        this.getAllUserMissionPredict();
        if (this.getTimeOut) this.getAllUserMission();
        console.log(this.boardList);
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
      predictLists: {},
      tempData: null,
      propsNickname: null,
      propsMemberNo: null,
      allUserMissionList: {},
    };
  },
  computed: {
    ...mapGetters([
      "getLoginState",
      "getUserId",
      "getMyMemberNo",
      "getTimeOut",
    ]),
  },
  methods: {
    checkMe(num) {
      if (this.getMyMemberNo == num) return true;
      else return false;
    },
    getAllUserMission() {
      this.boardList.forEach((element) => {
        this.getYourMissionAll(element.memberNo);
      });
    },
    getYourMissionAll(userId) {
      let query = { myId: userId };
      http
        .get(`/membermission/${query.myId}`, { query: query })
        .then(({ data }) => {
          this.allUserMissionList[userId] = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getYourMission() {
      let query = { myId: this.getMyMemberNo };
      http
        .get(`/membermission/${query.myId}`, { query: query })
        .then(({ data }) => {
          this.yourMission = data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getManMissionTitle(myId, num) {
      let query = { myId: myId };
      http
        .get(`/membermission/${query.myId}`, { query: query })
        .then(({ data }) => {
          this.boardList[num].missionName = data.missionName;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getAllUserMissionPredict() {
      this.boardList.forEach((board) => {
        this.getHisMissionPredict(board.memberNo, board.nickname);
      });
      console.log(this.predictLists);
    },
    getHisMissionPredict(myId, nickname) {
      let query = { myId: myId };
      http
        .get(`/predict/${query.myId}`, { query: query })
        .then(({ data }) => {
          data.forEach((element) => {
            let data = element;
            data["nickname"] = nickname;
            if (!Array.isArray(this.predictLists[data.hisMemberNo]))
              this.predictLists[data.hisMemberNo] = [];
            this.predictLists[data.hisMemberNo].push(data);
          });
          // let predictUserInfo = {
          //   nickname: nickname,
          //   predictContent: data.predictContent,
          // };
        })
        .catch((err) => {
          console.log(err);
        });
    },
    openModal(nickname, memberNo) {
      this.showModal = true;
      this.propsNickname = nickname;
      this.propsMemberNo = memberNo;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.board {
  height: 2000px;
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
