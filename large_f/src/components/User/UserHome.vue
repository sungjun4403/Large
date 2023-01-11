<template>
    <div id="UserHomeBody">
        
        {{gitID}}'s dev-log ! 
        <br>
        WELCOME!
        <br><br>
        <div v-for="(post, index) in posts" :key="post.id">
            <router-link :to="{ name: 'Post', params: { gitID: post.gitID, postId: post.id }}">
                <div>
                    {{post.title}}
                    {{post}}
                    {{index}}
                </div>
            </router-link>
            <br>
        </div>
 
    </div>
</template>

<script>
import { ref } from '@vue/reactivity'
const axios = require("axios").default

export default {
    setup() {
        // const gitID = this.$route.params.get("gitID")
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
        this.$forceUpdate()

        axios({
            url: 'http://localhost:8080/post/brief/' + this.gitID,
            method: 'get',
            
        }).then((response) => {
            response.data.forEach(element => {
                this.posts.push(element)    
            })
        })
    },
    mounted() {
        
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