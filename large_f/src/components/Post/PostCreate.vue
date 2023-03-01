<template>
    <div id="PostCreateBody">
        <!-- <UserSideBar /> -->
        <div v-if="IfTokenIsYours == true">
            <div>
                
                <h2>CREATE POST</h2>

                <br>

                <input type="text" id="title" name="title" v-model="title" placeholder="title">

                <br><br>

                <input type="text" name="body" v-model="body" placeholder="body">

                <br><br>

                ifAds<input type="checkbox" id="ifAds" name="ifAds" v-model="ifAds">

                <router-link to="/post/create"><button @click="PostCreate()">click</button></router-link>
            </div>    
        </div>

        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
        <div v-else>
            <Pending />
        </div>
    </div>
</template>

<script>
import { ref } from "@vue/reactivity";

const axios = require('axios').default;

export default ({
    props: ["IfTokenIsYours"],
    setup() {
        const title = ref("")
        const body = ref("")
        const ifAds = ref(false)
        const AccessToken = ref("")

        return {title, body, ifAds, AccessToken}
    },
    beforeMount() {
        this.AccessToken = localStorage.AccessToken
    },
    methods: {
        PostCreate() {
            axios({
                url: 'https://api.large-devlog.com/post',
                method: 'post',
                headers: {
                    'Authorization' : 'Bearer ' + this.AccessToken
                },
                data: {
                    title : this.title,
                    body : this.body,
                    ifAds : this.getCheckValueIfAds(),   
                }
            })
        },
        getCheckValueIfAds() {
            const ifAds = document.getElementById('ifAds')
            if (ifAds.checked) {
                return true
            }
            else {
                return false
            }
        },
    },
});
</script>

<style>
</style>
