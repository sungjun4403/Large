<template>
    <div id="PostBody">
        
        <div id="PostStatusIcon">
            <StatusIcon :IconHeight='IconHeight' :time='LastModifiedDate' />
        </div>
        
        <br><br>
         
        <div id="PostNameNImg">
            <NameNImg :profileImg='profileImg' :bio='bio' :authorGitID='authorGitID' />
        </div>

        <br>

        <div id="PostPost">
            {{post.title}} by {{post.gitID}} <br>
            Created at {{post.createDate}} 
            Last Modidied at {{post.lastModifiedDate}} <br>
            {{post.body}}
            {{post}}            
        </div>
        
        <br><br><br><br><br>

        <div id="PostComments" v-if="post.ifComments == true">
            <Comments />
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

        const authorGitID = ref('')
        
        const comments = ref("")
    
        const IconHeight = '20px'
        const LastModifiedDate = ref("")
        const CreateDate = ref("")

        //NameNImg
        const profileImg = ref('')
        const bio = ref('')
        const name = ref('')
        const nname = ref('')

        return {
            post, authorGitID,

            //NameNImg
            profileImg, bio, nname, name,
            
            comments, 
            IconHeight, LastModifiedDate, CreateDate,
        }
    },
    beforeMount() {
        const postId = this.$route.params.postId
        
        axios({
            url: 'http://localhost:8080/post/' + postId,
            method: 'get',

        }).then((response) => {
            //glob
            this.post = response.data
            this.authorGitID = response.data.gitID

            this.LastModifiedDate = response.data.lastModifiedDate
            this.CreateDate = response.data.createDate

            //NameNImg
            this.profileImg = response.data.profileImg
            this.bio = response.data.bio
            
            
            
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

    #PostCommentsNotAllowed {

    }
</style>