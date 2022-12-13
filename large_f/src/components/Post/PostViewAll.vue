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
        this.getAllPost();
    },

    methods: {
        getAllPost() {
            axios.get("http://localhost:8080/post").then((response) => {
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
