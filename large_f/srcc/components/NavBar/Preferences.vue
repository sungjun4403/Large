<template>
    <div>
        <div v-if="IfTokenIsYours == true">
            <div id="PreferencesBody"> 
                <span id="span1">Preferences</span> 
                
                <br><br>
                
                <router-link :to="{ name: 'Template', params: { gitID: gitID }}">
                    Template
                </router-link>

                <br><br>
                
                <router-link :to="{ name: 'DeleteAccount', params: { gitID: gitID }}">
                    Delete Account
                </router-link>

                <br><br>
            </div>
        </div>
        
        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
    </div>
    
</template>

<script>
export default {
    props: ["IfTokenIsYours"],
    setup() {
        const gitID = localStorage.gitID
        return { gitID }    
    },
    // beforeMount() {
    mounted() {
        
        console.log(document.getElementById('PreferencesBody'))
        // document.getElementById('PreferencesBody').style.width = '10'
        // console.log(document.getElementById('PreferencesBody').style.width)
    },
    methods: {
    }

}
</script>

<style scoped>
    #PreferencesBody {
        text-align: left;
        width: fit-content;

    }
    #span1 {
        font-weight: bold;
        font-size: xx-large;
    }
</style>