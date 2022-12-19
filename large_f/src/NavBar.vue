<template>
    <div class="unselectable">
        <div id="RealNav">
            <div class="Nav" id="Nav">
                <div v-if="IfTokenIsYours == true">
                    <router-link :to="{ name: 'EditProfile', params: { gitID: gitID }}">
                        <div class="itm">
                            <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                            <span class="span5">Edit Profile</span>
                        </div>
                    </router-link>

                    <router-link :to="{ name: 'Preferences', params: { gitID : gitID }}">
                        <div class="itm1">
                            <img src="../public/assets/image/Preferences.png" alt="Preferences" width="50" class="img"><br>
                            <span class="span5">Preferences</span>
                        </div>
                    </router-link>

                    <router-link :to="{ name: 'IssueReport' }">
                        <div class="itm">
                            <img src="../public/assets/image/IssueReport.png" alt="Issue report" width="40" class="img"><br>
                            <span class="span5">Issue Report</span>
                        </div>
                    </router-link>
                </div>

                <div v-else>
                    <div class="itm" @click="LoginRequired('Edit Profile')">
                        <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                        <span class="span5">Edit Profile</span>
                    </div>

                    <div class="itm1" @click="LoginRequired('Preferences')">
                        <img src="../public/assets/image/Preferences.png" alt="Preferences" width="50" class="img"><br>
                        <span class="span5">Preferences</span>
                    </div>

                    <div class="itm" @click="LoginRequired('Issue Report')">
                        <img src="../public/assets/image/IssueReport.png" alt="Issue report" width="40" class="img"><br>
                        <span class="span5">Issue Report</span>
                    </div>
                </div>
            </div>

            <div id="close" class="close" @click="close()">
                X close
            </div>
            
            <router-link to="/">
                <button type="button">go to login page</button>
            </router-link>

            <button type="button" @click="logout();">logout</button>

            <br><br><br>            
        </div>
        
        <div id="FloatAlert"></div>

        <router-view />

    </div>
</template>


<script>
import axios from 'axios';
import { ref } from '@vue/reactivity';

// const axios = require('axios').default

export default {
    setup() {
        const ScrollUp = 0;
        const IfTokenIsYours = ref(false)
        const gitID = ref("")
        return {ScrollUp, IfTokenIsYours, gitID}
    },
    beforeMount() {
        this.gitID = localStorage.getItem("gitID")
        this.getIfTokenIsAuthentic()
    },
    mounted() {
        addEventListener("mousewheel", e => {
            const direction = e.deltaY > 0 ? "Scroll Down" : "Scroll Up";
            if (direction == "Scroll Up" && window.scrollY == 0) {
                if (document.getElementById('RealNav').style.display == 'none' && this.ScrollUp > 3){
                    document.getElementById('RealNav').style.display = 'inline'
                    this.ScrollUp = 0;
                }
                this.ScrollUp = this.ScrollUp + 1;
            }

            if (direction == "Scroll Down") {
                this.ScrollUp = 0;
            }    
            
        });
        this.close()
    },
    methods: {
        close() {
            document.getElementById('RealNav').style.display = 'none'
        }, 
        logout() {
            localStorage.clear()
            window.location.href = "http://localhost:3000/"
        },
        getIfTokenIsAuthentic() {
            const AccessToken = localStorage.getItem("AccessToken")
            const gitID = localStorage.getItem("gitID")
            axios.post("http://localhost:8080/ifTokenIsAuthentic", {
                AccessToken: AccessToken,
                gitID: gitID
            }).then((response) => {
                this.IfTokenIsYours = response.data
            })
        },
        LoginRequired(PageName) {
            this.showFloatAlert(PageName)
        },
        showFloatAlert(Message) {
            const FloatAlert = document.getElementById("FloatAlert")
            FloatAlert.innerHTML = "Login required to access <h4>" + Message + "</h4>!"
            FloatAlert.style.opacity = 1
            setTimeout(function() {
                FloatAlert.style.opacity = 0
                FloatAlert.innerHTML = ""
            }, 5000)
            
        }
    },
}
</script>


<style>
    @import "../public/assets/css/unselectable.css";

    #NavBar {
        font-family: 'Courier New', Courier, monospace;
    }

    .span5 {
        cursor: pointer;
    }

    .itm {
        width: fit-content;
    }    
    
    .itm1 {
        width: fit-content;
        margin-top: 1.6%;
        margin-bottom: 1.4%;
        margin-left: 7%;
        margin-right: 7%;
    }

    .close {
        width: fit-content;       
        background-color: #7B7B7B; 
        padding-top: 0.75%;
        padding-bottom: 0.75%;
        padding-right: 1%;
        padding-left: 1.2%;
        float: right;
        cursor: pointer;
        
    }

    .img {
        margin-bottom: 2.2%;
        cursor: pointer;
        border-radius: 3px;
        
    }

    .Nav {
        background-color: #D9D9D9;
        text-align: center;
    }

    .Nav div {
        display: inline-block;
        width: fit-content;
    }

    div {
        font-size: large;
    }

    a:link {
    color : black;
    }
    a:visited {
    color : black;
    }
    a:hover {
    color : black;
    }
    a:active {
    color : black;
    }
    h4 {
        display: inline; 
    }
    #FloatAlert {
        transition: 1000ms;
        opacity: 0;
        transition: all 0.3s;
    }


</style>