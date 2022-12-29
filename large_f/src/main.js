import { createApp } from 'vue'
import router from './router'

import NavBar from './NavBar.vue'
import UserSideBar from "@/components/User/UserSideBar.vue"
import AccessDenied from "@/components/Login/Exceptions/AccessDenied.vue"
// createApp(NavBar).use(router).mount('#NavBar')
// createApp(UserNavBar).use(router).mount('#UserNavBar')

const app = createApp(NavBar)

app.use(router)
app.component("UserSideBar", UserSideBar)
app.component("AccessDenied", AccessDenied)
app.mount("#NavBar")