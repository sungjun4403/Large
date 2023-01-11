<template>
    <div id="StatusIconBody" >
        <div id="StatusIcon"> 
            
        </div>
        <span>{{now}}</span>

    </div>
    
</template>

<script>
import { ref } from '@vue/reactivity'
const moment = require('moment')

//now - time = icon bcg color
export default {
    props: ['IconHeight', 'time'],
    setup() {
        const now = ref("")
        
        return {
            now
        }
    },
    mounted() {        
        this.now = moment().locale('ko').format('LTS')        
        
        setInterval(()=> {
            this.now = moment().locale('ko').format('LTS')
        }, 500)

        const StatusIcon = document.getElementById("StatusIcon")    
        StatusIcon.style.width = this.IconHeight
        StatusIcon.style.height = this.IconHeight
    },
    methods: {

    }
}
</script>

<style scoped>
    #StatusIcon {
        width: 100px;
        height: 100px;
        background-color: rgba(127, 255, 0, 1);
        border-radius: 50%;
    }
</style>