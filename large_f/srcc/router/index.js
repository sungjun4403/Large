import { createWebHistory, createRouter } from "vue-router";
import DeleteAccount from "@/components/NavBar/Preferences/DeleteAccount.vue"

import Home from "@/components/Home.vue";

/*  NavBar components */
import EditProfile from "@/components/NavBar/EditProfile.vue"

import Preferences from "@/components/NavBar/Preferences.vue"
//Preferences components
import EditTemplate from "@/components/NavBar/Preferences/EditTemplate.vue"
import Template from "@/components/NavBar/Preferences/Template/Template.vue"

import IssueReport from "@/components/NavBar/IssueReport.vue"

//Post
import PostCreate from "@/components/Post/PostCreate.vue"
import PostViewAll from "@/components/Post/PostViewAll.vue"

//Exceptions
// import NotFound from "@/components/NotFound.vue"

//Login
import AfterLogin from "@/components/Login/AfterLogin.vue"
import LoginFailed from "@/components/Login/Exceptions/LoginFailed.vue"

//User
import UserHome from "@/components/User/UserHome.vue"
import Post from "@/components/Post/Post.vue"

const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/issue_report", name: "IssueReport", component: IssueReport },

  { path: "/:gitID/post/create", name: "PostCreate", component: PostCreate },
  { path: "/post/viewall", name: "PostViewAll", component: PostViewAll },

  { path: "/afterlogin", name: "AfterLogin", component: AfterLogin },
  { path: "/login_failed", name: "LoginFailed", component: LoginFailed },

  { path: "/:gitID", name: "UserHome", component: UserHome },
  { path: "/:gitID/postId=:postId", name: "Post", component: Post },

  { path: "/:gitID/preferences", name: "Preferences", component: Preferences },
  { path: "/:gitID/preferences/edit_template", name: "EditTemplate", component: EditTemplate },
  { path: "/:gitID/preferences/template", name: "Template", component: Template },
  { path: "/:gitID/preferences/delete_account", name: "DeleteAccount", component: DeleteAccount },

  { path: "/:gitID/edit_profile", name: "EditProfile", component: EditProfile },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;