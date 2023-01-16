<template>
    <div id="StatusIconBody" >
        <div id="StatusIcon" :title="IconTitle"></div>
        <span>{{now}}</span>
    </div>
    
</template>

<script>
import { ref } from '@vue/reactivity'
const moment = require('moment')

let colors = ["86FFA2","6BCC82","509961","366641","1B3320","000000"]
// second, minute, hour, day, month, year

//now - time = icon bcg color
export default {
    props: ['IconHeight', 'time'],
    setup() {
        const now = ref("")
        const IconTitle = ref("none")
        
        const killInterval = ref(false)
        return {
            now, IconTitle, killInterval
        }
    },
    mounted() {
        this.setIconHeight()
        this.now = moment().locale('ko').format('LTS')        
        
        const timeInterval = setInterval(()=> {
            this.now = moment().locale('ko').format('LTS')
            this.setIconColorNTitle()

            if (this.killInterval == true) {
                console.log("KILL INTERVAL")
                clearInterval(timeInterval)
            }
        }, 500)
    },
    methods: {
        setIconHeight() {
            const StatusIcon = document.getElementById("StatusIcon")    
            StatusIcon.style.width = this.IconHeight
            StatusIcon.style.height = this.IconHeight
        },
        setIconColorNTitle() {
            const fromNow = moment(this.time).fromNow()
            var i = -1
            this.IconTitle = fromNow
            if (fromNow.split(" ")[2] == "seconds" || fromNow.split(" ")[2] == "second") {
                i = 0
            }
            if (fromNow.split(" ")[1] == "minutes" || fromNow.split(" ")[1] == "minute") {
                i = 1
            }
            if (fromNow.split(" ")[1] == "hours" || fromNow.split(" ")[1] == "hour") {
                i = 2
            }
            if (fromNow.split(" ")[1] == "days" || fromNow.split(" ")[1] == "day") {
                i = 3
            }
            if (fromNow.split(" ")[1] == "months" || fromNow.split(" ")[1] == "month") {
                i = 4
            }
            if (fromNow.split(" ")[1] == "years" || fromNow.split(" ")[1] == "year") {
                i = 5
            }
            
            const StatusIcon = document.getElementById("StatusIcon")        
            StatusIcon.style.backgroundColor = '#' + colors[i]
        },
    },
    updated() {
        
    },
    unmounted() {
        this.killInterval = true
    }
}
</script>

<style scoped>
    #StatusIcon {
        width: 100px;
        height: 100px;
        background-color: #BEBEBE;
        border-radius: 50%;
    }
</style>