import { createWebHistory, createRouter } from "vue-router";

import Home from "@/components/Home.vue";

//NavBar components
import EditProfile from "@/components/NavBar/EditProfile.vue"
import Preferences from "@/components/NavBar/Preferences.vue"
import IssueReport from "@/components/NavBar/IssueReport.vue"

//Post
import PostCreate from "@/components/Post/PostCreate.vue"
import PostViewAll from "@/components/Post/PostViewAll.vue"

//Exceptions
// import NotFound from "@/components/NotFound.vue"

//Login
import AfterLogin from "@/components/Login/AfterLogin.vue"


const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/edit_profile",
    name: "EditProfile",
    component: EditProfile,
  },
  {
    path: "/preferences",
    name: "Preferences",
    component: Preferences,
  },
  {
    path: "/issue_report",
    name: "IssueReport",
    component: IssueReport,
  },
  {
    path: "/post/create",
    name: "PostCreate",
    component: PostCreate,
  },
  {
    path: "/post/viewAll",
    name: "PostViewAll",
    component: PostViewAll,
  },
  {
    path: "/afterlogin",
    name: "AfterLogin",
    component: AfterLogin,
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;