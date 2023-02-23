<template>
    <div id="DeleteAccountBody">
        <div v-if="IfTokenIsYours == true">
            <button type="button" @click="DeleteAccount();">Delete Account</button>
        </div>

        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
    </div>
</template>

<script>
const axios = require('axios').default
export default {
    props: ['IfTokenIsYours'],
    methods: {
        DeleteAccount() {
            axios({
                url: 'https://api.large-devlog.com/deleteNCascade/' + this.gitID,
                method: 'get',
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem("AccessTokenㅣ,")
                }
            }).then(()=> {
                window.location.href = "https://large-devlog.com/"
            })
        },
    }
        
}
</script>

<style scoped>
    #DeleteAccountBody {
        text-align: center;
    }
</style>