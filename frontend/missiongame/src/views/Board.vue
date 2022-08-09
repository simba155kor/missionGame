<template>
  <div class="board">
    <h1>This is an board page</h1>
    <BoardComponent></BoardComponent>
  </div>
</template>


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

.boardTable {
}
</style>

<script>
import BoardComponent from "../components/BoardComponent.vue";

export default {
  name: "Board",
  components: { BoardComponent },
};
</script>