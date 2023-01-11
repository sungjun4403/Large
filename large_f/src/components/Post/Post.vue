<template>
    <div id="PostBody">
        POST
        {{$route.params.gitID}}
        {{$route.params.postId}}
        
        <br><br>

        <NameNImg />

        <br>

        <Comments />

    </div>
</template>

<script>
import { ref } from '@vue/reactivity'
const axios = require('axios').default

export default {
    props: ["IfTokenIsYours"],
    setup() {
        const NameNImg = ref("")
        const comments = ref("")

        return {
            NameNImg, comments,

        }
    },
    beforeMount() {
        const postId = this.$route.params.postId
        
        axios({
            url: 'http://localhost:8080/' + postId,
            method: 'get',

        }).then((response) => {
            console.log(response)
        })
    },
    // mounted() {
    //     console.log(this.$route.params.postId)
        
    // }
    
}
</script>

<style scoped>

</style>