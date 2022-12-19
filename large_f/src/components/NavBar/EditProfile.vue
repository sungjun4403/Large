<template>
    <div id="body">
        <div id="info">
            gitID : {{gitID}} <br>
            profileImg : <img :src="profileImg" width="40" height="40">  <br>
            name : {{name}} <br>
            company : {{company}} <br>
            gitBlog : {{gitBlog}} <br>
            location : {{location}} <br>
            gitEmail : {{gitEmail}} <br>
            bio : {{bio}} <br>
            updated_at : {{updated_at}} <br>
        </div>

        <h2>EDIT PROFILE</h2>

        <br>

        <input type="text" id="profileImg" name="profileImg" v-model="toEditProfileImg" placeholder="profileImg">

        <br><br>

        <input type="text" id="name" name="name" v-model="toEditName" placeholder="name">

        <br><br>

        <input type="text" id="company" name="company" v-model="toEditCompany" placeholder="company">

        <br><br>

        <input type="text" id="location" name="location" v-model="toEditLocation" placeholder="location">

        <br><br>

        <input type="text" id="bio" name="bio" v-model="toEditBio" placeholder="bio">

        <br><br>
        
        <button @click="memberEdit()">click</button> 

        <br><br>
    </div>
</template>

<script>
import { ref } from 'vue';
const axios = require('axios').default;

export default {
    setup() {
        const gitID = ref("")
        const profileImg = ref("")
        const name = ref("")
        const company = ref("")
        const gitBlog = ref("")
        const location = ref("")
        const gitEmail = ref("")
        const bio = ref("")
        const updated_at = ref("")

        return {gitID, profileImg, name, company, gitBlog, location, gitEmail, bio, updated_at}
    }, 
    mounted() {
        this.getMemberInfo()
    },

    methods: {
        getMemberInfo () {
            axios.post("http://localhost:8080/" + localStorage.getItem("gitID") + "/getUserInfo", {
                gitID: localStorage.getItem("gitID"),
                AccessToken: localStorage.getItem("AccessToken")
            }).then((response) => {
                this.gitID = response.data.gitID
                this.profileImg = response.data.profileImg
                this.name = response.data.name
                this.company = response.data.company
                this.gitBlog = response.data.gitBlog
                this.location = response.data.location
                this.gitEmail = response.data.gitEmail
                this.bio = response.data.bio
                this.updated_at = response.data.updated_at
            })
        },
        memberEdit() {
            axios.patch("http://localhost:8080/" + localStorage.getItem("gitID") + "/memberEdit", {
                // profileImg : this.toEditProfileImg.value,
                // name : this.toEditName.value,
                // company : this.toEditCompany.value,
                // location : this.toEditLocation.value,
                // bio : this.toEditBio.value,
                profileImg : this.toEditProfileImg,
                name : this.toEditName,
                company : this.toEditCompany,
                location : this.toEditLocation,
                bio : this.toEditBio,
            }).then((response) => {
                if (response.status == 200) {
                    this.getMemberInfo()
                }
            })
        },
    }
}
</script>

<style>
#info {
    text-align: left;
    margin-left: 20%;
}
</style>