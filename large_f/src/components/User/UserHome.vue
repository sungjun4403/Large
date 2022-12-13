<template>
    <div id="body">
        
        {{gitID}}'s dev-log ! 
        <br>
        WELCOME!
        <div>
            
            <br><br>
        </div>
    </div>
</template>

<script>
import { ref } from '@vue/reactivity'

export default {
    setup() {
        // const gitID = this.$route.params.get("gitID")
        const gitID = ref("")
        // const gitID = "suingujn4403"
        return {gitID}
    },
    mounted() {
        // this.gitID = this.$route.params.gitID
        this.gitID = localStorage.getItem("gitID")
    },
    methods: {

    },
}
</script>

<style>

</style>