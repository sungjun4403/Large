<template>
    <div id="UserHomeBody">
        <div v-if="UserExists == true">
            {{gitID}}'s dev-log ! 
            <br>
            WELCOME!
            <br><br>

            <!-- if post exists -->
            <div v-for="(post) in posts" :key="post.id">
                <router-link :to="{ name: 'Post', params: { gitID: post.gitID, postId: post.id }}">
                    <div>
                        {{post.title}}
                        {{post.body}}
                        {{post.gitID}}
                        {{post.LastModifiedFromNow}}
                    </div>
                </router-link>
                <br>
            </div>

            <!-- if post dosen't exists -->
            <div v-if="posts.length == 0">
                no posts yet
            </div>

            <!-- create post button. appears when blog is user's regardless to post exists or not   -->
            <div v-if="IfBlogIsMine == true">
                <router-link :to="{ name: 'PostCreate', params: { gitID: gitID}}">
                    <button>Create Post</button>
                </router-link>
            </div>
        </div>
        <div v-if="UserExists == false">
            Page does not exists 
        </div>
    </div>
</template>

<script>
import { ref } from '@vue/reactivity'
const axios = require("axios").default
const moment = require('moment')

export default {
    setup() {
        const gitID = ref("")
        const posts = ref([])
        const UserExists = ref("pending")
        const IfBlogIsMine = ref("pending")
        
        return {
            gitID,
            posts,
            UserExists,
            IfBlogIsMine
        }
    },
    beforeMount() {
        this.gitID = this.$route.params.gitID

        //If User Exists
        axios({
            url: 'https://api.large-devlog.com/post/brief/' + this.gitID,
            method: 'get',
            
        }).then((response) => {
            this.UserExists = true;
            response.data.forEach(element => {
                const LastModifiedFromNow = moment(element.lastModifiedDate).fromNow()
                element.LastModifiedFromNow = LastModifiedFromNow
                this.posts.push(element)    
            })
            
        }).catch((ErrorResponse) => {
            if (ErrorResponse.response.status == 500) {
                this.UserExists = false;
            }   
        })

        //If Blog is Mine
        axios({
            url: 'https://api.large-devlog.com/ifTokenIsAuthentic/' + this.gitID,
            headers: {
                'Authorization' : 'Bearer ' + localStorage.AccessToken
            },
        }).then((response) => {
            if (response.data == true) {
                this.IfBlogIsMine = true
                console.log('NO')
            }
            else {
                this.IfBlogIsMine = false
            }
        }) 
    },
    mounted() {
        setInterval(()=> {
            this.posts.forEach(post => {
                post.LastModifiedFromNow = moment(post.lastModifiedDate).fromNow()            
            }, 30000)
        })
    },
    methods: {

    },
}
</script>

<style >
    #UserHomeBody {
        text-align: center;
    }
</style>