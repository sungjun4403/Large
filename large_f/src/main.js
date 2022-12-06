import { createApp } from 'vue'
import router from './router'

import NavBar from './NavBar.vue'
import toRender from './toRender.vue'
import Rendered from './Rendered.vue'
import Home from './components/Home.vue'
import EditProfile from './components/NavBar/EditProfile.vue'
import IssueReport from './components/NavBar/IssueReport.vue'
import Preferences from './components/NavBar/Preferences.vue'
import NotFound from './components/NotFound.vue'


createApp(NavBar).use(router).mount('#NavBar')

createApp(Home).use(router).mount('#Home');

//NavBar
createApp(EditProfile).use(router).mount('#EditProfile');
createApp(IssueReport).use(router).mount('#IssueReport');
createApp(Preferences).use(router).mount('#Preferences');

createApp(NotFound).use(router).mount('#NotFound');

createApp(toRender).use(router).mount('#toRender');
createApp(Rendered).use(router).mount('#Rendered');

