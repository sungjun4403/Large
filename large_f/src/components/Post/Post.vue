<template>
    <div id="PostBody">
        <br>
        <div id="PostPost">
            {{post.title}} by {{post.gitID}} <br>
            Created at {{post.createDate}} 
            Last Modidied at {{post.lastModifiedDate}} <br>
            {{post.body}}
            
        </div>
        
        <br><br>

        <div id="PostStatusIcon">
            <StatusIcon :IconHeight='IconHeight' :time='LastModifiedDate' />
        </div>

        <br><br>
        
        <NameNImg />

        <br>

        <div id="PostComments" v-if="post.ifComments == true">
            <Comments />
            CSPODSPOKLS:K
        </div>

        <div id="PostCommentsNotAllowed" v-if="post.ifComments == false">
            comments are not allowed in this post
        </div>

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
            this.post = response.data
            this.LastModifiedDate = response.data.lastModifiedDate
            this.CreateDate = response.data.createDate
            
            
        })
    },    
    updated() {
    },
}
</script>

<style scoped>
    #PostPost {
        text-align: center;
    }
</style>