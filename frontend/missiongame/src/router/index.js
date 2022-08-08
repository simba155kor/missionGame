import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Choice from "../views/Choice.vue";
import Oauthloginpage from "../views/Oauthloginpage.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Login.vue"),
  },
  {
    path: "/choice",
    name: "Choice",
    component : Choice,
  },
  {
    path: "/oauthloginpage",
    name: "Oauthloginpage",
    component : Oauthloginpage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
