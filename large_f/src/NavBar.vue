<template>
    <div class="unselectable">
        <div class="Nav">
            <div class="itm">
                <img src="../public/assets/image/ProfileIcon.png" alt="Profile Icon" width="40" class="img"><br>
                <span>Edit Profile</span>
            </div>
            <div class="itm">
                <img src="../public/assets/image/ProfileIcon.png" alt="Preferences" width="40" class="img"><br>
                <span>Preferences</span>
            </div>
            <div class="itm">
                <img src="../public/assets/image/ProfileIcon.png" alt="Issue report" width="40" class="img"><br>
                <span>Issue Report</span>
            </div>
        </div>
        <div id="close" class="close" @click="close()">
            X close
        </div>
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

    .itm {
        width: fit-content;
        cursor: pointer;
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
    }

    .Nav {
        background-color: #D9D9D9;
    }

    div {
        font-size: large;
    }
</style>