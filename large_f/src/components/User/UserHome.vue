<template>
    <div id="UserHomeBody">
        
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
        // const gitID = "suingujn4403"
        return {
            gitID,
            posts,
        }
    },
    beforeMount() {
        this.gitID = this.$route.params.gitID

        axios({
            url: 'http://localhost:8080/post/brief/' + this.gitID,
            method: 'get',
            
        }).then((response) => {
            response.data.forEach(element => {
                const LastModifiedFromNow = moment(element.lastModifiedDate).fromNow()
                element.LastModifiedFromNow = LastModifiedFromNow
                this.posts.push(element)    

            })
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