<template>
    <div id="body">

        <div id="txt"><h2>REDIRECTING</h2> to <a href="/">Home</a></div>

    </div>
</template>

<script>
const axios = require('axios').default;

export default {
    mounted() {
        this.passCode()
    },

    methods: {
        passCode() {
            const url = window.location.href
            const code = url.split("=")[1]
            axios.get("https://api.large-devlog.com/auth/github/callback?code=" + code).then((response) => {
                if (response.status == 200) { //로그인 성공시 홈으로 리다이렉트                    
                    localStorage.setItem("AccessToken", response.headers["accesstoken"])
                    localStorage.setItem("memberToken", response.headers["membertoken"])
                    localStorage.setItem("gitID", response.headers["gitid"])
                    
                    window.location.href = "https://large-devlog.com/" + response.headers["gitid"]
                }
                
                else { //로그인 실패시 LoginFailed로 리다이렉트 
                    window.location.href = "https://large-devlog.com/login_failed"
                }
            }) 
        },
    }
}
</script>

<style>
#txt {
    
}
</style>