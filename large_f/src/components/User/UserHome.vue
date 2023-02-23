<template>
    <div id="UserHomeBody">
        <div v-if="UserExists == true">
            {{gitID}}'s dev-log ! 
            <br>
            WELCOME!
            <br><br>
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

            <div v-if="posts.length == 0">
                no posts yet
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
        
        return {
            gitID,
            posts,
            UserExists,
        }
    },
    beforeMount() {
        this.gitID = this.$route.params.gitID

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