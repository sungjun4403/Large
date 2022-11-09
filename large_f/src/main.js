import { createApp } from 'vue'
import NavBar from './NavBar.vue'
import Vue from '../node_modules/vue'
import router from './router'

Vue.use(router)

createApp(NavBar).use(router).mount('#NavBar')
