import { createApp } from 'vue'
import router from './router'

import NavBar from './NavBar.vue'

import UserNavBar from "@/components/User/UserNavBar.vue"

createApp(NavBar).use(router).mount('#NavBar')
createApp(UserNavBar).use(router).mount('#UserNavBar')