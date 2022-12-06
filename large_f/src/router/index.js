import { createWebHistory, createRouter } from "vue-router";

import Home from "@/components/Home.vue";
import EditProfile from "@/components/NavBar/EditProfile.vue"
import Preferences from "@/components/NavBar/Preferences.vue"
import IssueReport from "@/components/NavBar/IssueReport.vue"
// import NotFound from "@/components/NotFound.vue"

const routes = [
  {
    path: "/about",
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
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;