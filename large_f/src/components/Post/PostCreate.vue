<template>
    <div>
        <UserSideBar />
        <div id="content">
            
            <h2>CREATE POST</h2>

            <br>

            <input type="text" id="title" name="title" v-model="title" placeholder="title">

            <br><br>

            <input type="text" name="body" v-model="body" placeholder="body">

            <br><br>

            ifAds<input type="checkbox" id="ifAds" name="ifAds" v-model="ifAds">

            <br><br>
            
            <button @click="PostCreate()">click</button> 
        </div>
    </div>
</template>

<script>
import { ref } from "@vue/reactivity";

const axios = require('axios').default;
localStorage.setItem("mounted", 2)

export default ({
    setup() {
        const title = ref("")
        const body = ref("")
        const ifAds = ref(false)

        return {title, body, ifAds}
    },
    created() {
        console.log("CREATED")
    },
    beforeMount() {
        console.log("BEFORE MOUNT")
    },
    mounted() {
        console.log(localStorage.getItem("mounted"))
        localStorage.setItem("mounted", localStorage.getItem("mounted")-1)
        console.log(localStorage.getItem("mounted"))
        console.log("MOUNTED")
    },

    methods: {
        PostCreate() {
            axios.post("http://localhost:8080/post", {
                title : this.title,
                body : this.body,
                ifAds : this.getCheckValueIfAds(),
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
    #content {
        float: right;
        width: 90vw;
    }
</style>
