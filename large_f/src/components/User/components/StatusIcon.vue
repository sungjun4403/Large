<template>
    <div id="StatusIconBody" >
        <div id="StatusIcon"> 
            
        </div>
        <span>
            {{now}}
        </span>
    </div>
    
</template>

<script>
const moment = require('moment')
export default {
    props: ['IconHeight'],
    setup() {
        return {
        
        }
    },
    mounted() {
        console.log(this.IconHeight)
        const today = moment()
        console.log(today.format())
        const StatusIcon = document.getElementById("StatusIcon")
        StatusIcon.style.width = this.IconHeight
        StatusIcon.style.height = this.IconHeight
    }
}
</script>

<style scoped>
    #StatusIcon {
        width: 100px;
        height: 100px;
        background-color: chartreuse;
        border-radius: 50%;
    }
</style>