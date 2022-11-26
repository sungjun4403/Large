import { createApp } from 'vue'
import NavBar from './NavBar.vue'
import toRender from './toRender.vue'
import Rendered from './Rendered.vue'


createApp(NavBar).mount('#NavBar');
createApp(toRender).mount('#toRender');
createApp(Rendered).mount('#Rendered');


