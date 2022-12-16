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
            NOOO
            <br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO<br><br>
            NOOO

            <button @click="PostCreate()">click</button> 
        </div>
    </div>
</template>

<script>
import { ref } from "@vue/reactivity";

const axios = require('axios').default;

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
