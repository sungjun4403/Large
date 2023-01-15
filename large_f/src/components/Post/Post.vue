<template>
    <div id="PostBody">
        POST
        {{$route.params.gitID}}
        {{$route.params.postId}}
        <br>
        <br>
        {{post}}
        <br><br>
        <StatusIcon v-bind:value="[IconHeight, LastModifiedDate]"/>

        <br><br>

        <NameNImg />

        <br>

        <Comments />

    </div>
</template>

<script>
import { ref } from '@vue/reactivity'
import StatusIcon from '../User/components/StatusIcon.vue'
const axios = require('axios').default

export default {
  components: { StatusIcon },
    props: ["IfTokenIsYours"],
    setup() {
        const post = ref([])
        const NameNImg = ref("")
        const comments = ref("")
        const IconHeight = '20px'
        const LastModifiedDate = ref("")

        return {
            NameNImg, comments, 
            IconHeight, LastModifiedDate,
            post

        }
    },
    beforeMount() {
        const postId = this.$route.params.postId
        
        axios({
            url: 'http://localhost:8080/post/' + postId,
            method: 'get',

        }).then((response) => {
            console.log(response)
            this.post.push(response.data)
            
        })
    },
    // mounted() {
    //     console.log(this.$route.params.postId)
        
    // }
    
}
</script>

<style scoped>

</style>