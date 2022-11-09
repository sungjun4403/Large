<template>
    <div class="unselectable">
        <div class="Nav">
            <div class="itm">
                <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                <span class="span5">Edit Profile</span>
            </div>
            <div class="itm1">
                <img src="../public/assets/image/Preferences.png" alt="Preferences" width="50" class="img"><br>
                <span class="span5">Preferences</span>
            </div>
            <div class="itm">
                <img src="../public/assets/image/IssueReport.png" alt="Issue report" width="40" class="img"><br>
                <span class="span5">Issue Report</span>
            </div>
        </div>
        <div id="close" class="close" @click="close()">
            X close
        </div>
        <br><br><br>
    </div>
</template>


<script>
export default {
    beforeMount() {
        this.close()
    },

    methods: {
        close() {
            document.getElementById('NavBar').style.display = 'none'
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
</style>