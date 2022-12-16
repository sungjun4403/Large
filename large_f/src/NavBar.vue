<template>
    <div class="unselectable">
        <div id="RealNav">
            <div class="Nav" id="Nav">
                <!-- <router-link :to="{ name: 'EditProfile', params: { gitID: localStorage.getItem('gitID') }}"> -->
                <router-link to="/">
                    <div class="itm">
                        <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                        <span class="span5">Edit Profile</span>
                    </div>
                </router-link>

                <router-link to="/preferences">
                <!-- <router-link to="this.$route.params.gitID + '/preferences'"> -->
                    <div class="itm1">
                        <img src="../public/assets/image/Preferences.png" alt="Preferences" width="50" class="img"><br>
                        <span class="span5">Preferences</span>
                    </div>
                </router-link>

                <router-link to="/issue_report">
                    <div class="itm">
                        <img src="../public/assets/image/IssueReport.png" alt="Issue report" width="40" class="img"><br>
                        <span class="span5">Issue Report</span>
                    </div>
                </router-link>
                

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

        <router-view />

    </div>
</template>


<script>
import axios from 'axios';

// const axios = require('axios').default

export default {
    setup() {
        const ScrollUp = 0;
        return {ScrollUp}
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

        this.ifTokenIsAuthentic()

    },
    // beforeMount() {
    beforeMount() {
        
        this.getMemberInfo()
    },

    methods: {
        close() {
            document.getElementById('RealNav').style.display = 'none'
        },
        getMemberInfo() {
            // axios.post("http://localhost:8080/getUserInfo", {
            //     AccessToken : localStorage.getItem("AccessToken")
            // }).then((response) => {
            //     localStorage.setItem("memberToken", response.data.memberToken) 
            // })
        },

        logout() {
            localStorage.clear()
        },

        ifTokenIsAuthentic() {
            const AccessToken = localStorage.getItem("AccessToken")
            const gitID = localStorage.getItem("gitID")
            axios.post("http://localhost:8080/ifTokenIsAuthentic", {
                AccessToken: AccessToken,
                gitID: gitID
            }).then((response) => {
                console.log(response.data)
            })
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

</style>