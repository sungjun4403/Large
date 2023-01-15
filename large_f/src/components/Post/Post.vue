<template>
    <div id="PostBody">
        POST
        {{$route.params.gitID}}
        {{$route.params.postId}}
        <br><br>
        {{post}}
        <br><br>
        <StatusIcon :IconHeight='IconHeight' :time='LastModifiedDate' />

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
        const CreateDate = ref("")

        return {
            NameNImg, comments, 
            IconHeight, LastModifiedDate, CreateDate,
            post

        }
    },
    beforeMount() {
        const postId = this.$route.params.postId
        
        axios({
            url: 'http://localhost:8080/post/' + postId,
            method: 'get',

        }).then((response) => {
            this.post.push(response.data)
            this.LastModifiedDate = response.data.lastModifiedDate
            this.CreateDate = response.data.createDate
            
            
        })
    },    
    updated() {
    },
}
</script>

<style scoped>

</style>