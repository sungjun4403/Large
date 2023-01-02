<template>
<!--     
    Access : User
    Component Relations : 
 -->
    <div>
        <div id="EditProfileBody" v-if="IfTokenIsYours == true">
            <div id="info">
                <h4> Github ID </h4> &nbsp; <img :src="profileImg" id="imgProfileImg" width="50" height="50">  <br>
                {{gitID}} <br><br>

                <h4> Profile Image </h4> <br>
                <!-- <input type="text" v-model="toEditProfileImg" placeholder="profileImg" :size="{ profileImgLength }"> -->
                <input type="text" id="profileImg" v-model="toEditProfileImg" placeholder="profileImg" @input="modifyInputSize('profileImg')">
                 <br><br>

                <h4> Name </h4> <br> 
                <input type="text" id="name" v-model="toEditName" placeholder="name" @input="modifyInputSize('name')"> <br><br>

                <h4> Company </h4> <br>
                <input type="text" id="company" v-model="toEditCompany" placeholder="company" @input="modifyInputSize('company')"> <br><br>

                <h4> Blog </h4> <br>
                {{gitBlog}} <br><br>

                <h4> Location </h4> <br>
                <input type="text" id="location" v-model="toEditLocation" placeholder="location" @input="modifyInputSize('location')"> <br><br>

                <h4> Email </h4> <br>
                {{gitEmail}} <br><br>

                <h4> Bio </h4> <br>
                <input type="text" id="bio" v-model="toEditBio" placeholder="bio" @input="modifyInputSize('bio')"> <br><br>
            </div>
            <br><br>

            <button @click="memberEdit()">click</button> 

            <br><br>
        </div>

        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
const axios = require('axios').default;

export default {
    props: ["IfTokenIsYours"],
    setup() {
        const toEditProfileImg = ref("")
        const toEditName = ref("")
        const toEditCompany = ref("")
        const toEditLocation = ref("")
        const toEditBio = ref("")
        
        const gitID = ref("")
        const profileImg = ref("")
        const name = ref("")
        const company = ref("")
        const gitBlog = ref("")
        const location = ref("")
        const gitEmail = ref("")
        const bio = ref("")
        const updated_at = ref("")

        const AccessToken = localStorage.AccessToken

        const nameLength = ref(1)
        const profileImgLength = ref(1)
        const companyLength = ref(1)
        const locationLength = ref(1)
        const bioLength = ref(1)

        const jLength = "40"

        return {
            gitID, profileImg, name, company, gitBlog, location, gitEmail, bio, updated_at,
            toEditProfileImg, toEditName, toEditCompany, toEditLocation, toEditBio,
            AccessToken,
            nameLength, profileImgLength, companyLength, locationLength, bioLength, jLength
            }
    }, 
    mounted() {
        this.getMemberInfo()
    },

    methods: {
        getMemberInfo () {
            axios({
                url: 'http://localhost:8080/getUserInfo/' + localStorage.gitID,
                method: 'get',
                headers: {
                    'Authorization' : 'Bearer ' + this.AccessToken
                }
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
                this.setDefaultInput()
                
                this.modifyInputSize('profileImg')
                this.modifyInputSize('name')
                this.modifyInputSize('company')
                this.modifyInputSize('location')
                this.modifyInputSize('bio')
            })
        },
        setDefaultInput() {
            this.toEditProfileImg = this.profileImg
            this.toEditName = this.name
            this.toEditCompany = this.company
            this.toEditLocation = this.location
            this.toEditBio = this.bio
        },
        memberEdit() {
            axios({
                url: "http://localhost:8080/memberEdit/" + localStorage.gitID, 
                method: 'patch',
                headers: {
                    'Authorization' : 'Bearer ' + this.AccessToken
                },
                data: {
                    profileImg : this.toEditProfileImg,
                    name : this.toEditName,
                    company : this.toEditCompany,
                    location : this.toEditLocation,
                    bio : this.toEditBio,
                }
            }).then((response) => {
                if (response.status == 200) {
                    this.getMemberInfo()
                }
            })
        },
        modifyInputSize(inputID) {
            const input = document.getElementById(inputID)
            const toEdit = this.modifyVaribleName(inputID)

            if (eval("this." + toEdit)) {
                input.size = eval("this." + toEdit + ".length + 3")
            }
            else {
                input.size = inputID.length + 3
            }
        },
        modifyVaribleName(toModify) {
            const toCap = toModify[0]
            toModify = toModify.slice(1)
            return "toEdit" + toCap.toUpperCase() + toModify
        },
    }
}
</script>

<style scoped>
    #info {
        text-align: left;
        margin-left: 20%;
    }
    input {
        border: none;
        background-color: #BEBEBE;
        font-family: 'Courier New', Courier, monospace;
    }
    input:focus {
        outline: none;
    }
    #imgProfileImg {
        vertical-align: text-top;
        margin-left: 2.75%;
        margin-bottom: -4.5%;
    }
    #EditProfileBody {
        
    }

</style>