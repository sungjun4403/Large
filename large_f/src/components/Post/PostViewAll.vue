<template>
    <div id="body">
        <li v-for="post in posts" :key="post.id">
            {{post.title}} /
            {{post.body}} /
            {{post.ifAds}} 
            <br>
        </li>
    </div>

    
</template>

<script>
// import { ref } from "@vue/reactivity";
import { ref } from '@vue/reactivity';
const axios = require('axios').default;

export default ({

    setup() {
        const posts = ref([])

        return {posts}
    },

    mounted() {
        this.getAllPosts();
    },

    methods: {
        getAllPosts() {
            axios.get("https://api.large-devlog.com/post").then((response) => {
                response.data.forEach(element => {
                    this.posts.push(element)
                });
            })
        },
    },
});
</script>

<style>

</style>
