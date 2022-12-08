<template>
    <div id="body">
        <h2>EDIT PROFILE</h2>

        <br>

        <input type="text" id="profileImg" name="profileImg" v-model="profileImg" placeholder="profileImg">

        <br><br>

        <input type="text" id="name" name="name" v-model="name" placeholder="name">

        <br><br>

        <input type="text" id="company" name="company" v-model="company" placeholder="company">

        <br><br>

        <input type="text" id="location" name="location" v-model="location" placeholder="location">

        <br><br>

        <input type="text" id="bio" name="bio" v-model="bio" placeholder="bio">

        <br><br>
        
        <button @click="write()">click</button> 

        <br><br>
    </div>
</template>

<script>
import { ref } from 'vue';
const axios = require('axios').default;

export default {
    setup() {
        const profileImg = ref("")
        const name = ref("")
        const company = ref("")
        const location = ref("")
        const bio = ref("")
        
        return {profileImg, name, company, location, bio}
    }, 

    methods: {
        memberEdit() {
            axios.patch("http://localhost:8080/post", {
                profileImg : this.profileImg.value,
                name : this.name.value,
                company : this.company.value,
                location : this.location.value,
                bio : this.bio.value,
            })
        },
    }
}
</script>

<style>
#body {

}
</style>