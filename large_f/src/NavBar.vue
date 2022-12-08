<template>
    <div class="unselectable">
        <div id="RealNav">
            <div class="Nav" id="Nav">

                <router-link to="/edit_profile">
                    <div class="itm">
                        <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                        <span class="span5">Edit Profile</span>
                    </div>
                </router-link>

                <router-link to="/preferences">
                    <div class="itm1">
                        <img src="../public/assets/image/Preferences.png" alt="Preferences" width="50" class="img"><br>
                        <span class="span5">Preferences</span>
                    </div>
                </router-link>

                <router-link to="/about">
                <!-- <router-link to="/issue_report"> -->
                    <div class="itm">
                        <img src="../public/assets/image/IssueReport.png" alt="Issue report" width="40" class="img"><br>
                        <span class="span5">Issue Report</span>
                    </div>
                </router-link>

            </div>

            <div id="close" class="close" @click="close()">
                X close
            </div>
            <br><br><br>
            <router-link to="/post/create">POST CREATE</router-link>
            <br>
            <router-link to="/post/viewAll">POST VIEW ALL</router-link>
        </div>

        

        <router-view />

    </div>
</template>


<script>
export default {
    mounted() {
        this.close()
    },

    methods: {
        close() {
            document.getElementById('RealNav').style.display = 'none'
            // document.getElementById('Nav').style.visibility = 'hidden'
            // document.getElementById('close').style.display = 'hidden'
            // document.getElementById('Nav').style.display = 'none'
        },
    }
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