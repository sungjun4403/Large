<template>
    <div id="UserHomeBody">
        
        {{gitID}}'s dev-log ! 
        <br>
        WELCOME!
        <!-- <script type="application/javascript" defer src="https://giscus.app/client.js"
            data-repo="sungjun4403/Giscus"
            data-repo-id="R_kgDOIm2KXA"
            data-category="General"
            data-category-id="DIC_kwDOIm2KXM4CTDA4"
            data-mapping="pathname"
            data-strict="0"
            data-reactions-enabled="1"
            data-emit-metadata="0"
            data-input-position="bottom"
            data-theme="preferred_color_scheme"
            data-lang="ko"
            crossorigin="anonymous"
            async>
        </script>
        <div id="giscus"></div> -->

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
    #UserHomeBody {
        text-align: center;
    }
</style>