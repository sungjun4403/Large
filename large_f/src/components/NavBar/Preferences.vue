<template>
    <div>
        <div id="TemplateBody" v-if="IfTokenIsYours == true">
            Preferences <br>
            
            <router-link :to="{ name: 'Template', params: { gitID: gitID }}">
                Template
            </router-link>

            <br>
            <router-link :to="{ name: 'Template', params: { gitID: gitID }}">
                
            </router-link>


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

}
</script>