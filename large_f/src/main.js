import { createApp } from 'vue'
import router from './router'

import NavBar from './NavBar.vue'

import toRender from './toRender.vue'
import Rendered from './Rendered.vue'

//NavBar components
import EditProfile from './components/NavBar/EditProfile.vue'
import IssueReport from './components/NavBar/IssueReport.vue'
import Preferences from './components/NavBar/Preferences.vue'

import Home from './components/Home.vue'

//Exceptions
import NotFound from './components/NotFound.vue'

//Post
import PostCreate from './components/Post/PostCreate.vue'
import PostViewAll from '@/components/Post/PostViewAll.vue'

//Login 
import AfterLogin from "@/components/Login/AfterLogin.vue"


createApp(NavBar).use(router).mount('#NavBar')

createApp(Home).use(router).mount('#Home');

//NavBar components
createApp(EditProfile).use(router).mount('#EditProfile');
createApp(IssueReport).use(router).mount('#IssueReport');
createApp(Preferences).use(router).mount('#Preferences');

//Exceptions
createApp(NotFound).use(router).mount('#NotFound');

//Post
createApp(PostCreate).use(router).mount('#PostCreate');
createApp(PostViewAll).use(router).mount('#PostViewAll');

//Login
createApp(AfterLogin).use(router).mount('#AfterLogin');

createApp(toRender).use(router).mount('#toRender');
createApp(Rendered).use(router).mount('#Rendered');


