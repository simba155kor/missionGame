<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header"> {{ nickname }} 의 미션은? </slot>
          </div>

          <div class="modal-body">
            <slot name="body"><input v-model="hisInput" /></slot><br />
            <slot name="body">{{ myPredict }}</slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="modal-default-button" @click="setHisMission()">
                확인
              </button>
              <button class="modal-default-button" @click="$emit('close')">
                close
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { mapGetters } from "vuex";
import http from "../util/http-connect";

export default {
  name: "BoardModal",
  components: {},
  created() {
    this.getHisMission();
  },
  data() {
    return {
      hisInput: "",
      myPredict: "아직없음.",
    };
  },
  props: {
    nickname: String,
    yourId: Number,
  },
  computed: {
    ...mapGetters(["getLoginState", "getUserId", "getMyMemberNo"]),
  },
  methods: {
    setHisMission() {
      let params = {
        myMemberNo: this.getMyMemberNo,
        hisMemberNo: this.$props.yourId,
        predictContent: this.hisInput,
      };
      http
        .post(`/predict/setpredict`, params)
        .then(({ data }) => {
          console.log(data);
          alert("저장되었습니다.");
          this.myPredict = this.hisInput;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getHisMission() {
      if (this.getMyMemberNo === this.$props.yourId) {
        alert("니껄 왜 예측해?");
      }
      let query = { myId: this.getMyMemberNo, yourId: this.$props.yourId };
      http
        .get(`/predict/${query.myId}/${query.yourId}`, { query: query })
        .then(({ data }) => {
          // console.log(data);
          this.myPredict = data.predictContent;
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
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}
.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
.modal-body,
.modal {
  color: #666 !important;
}
</style>
