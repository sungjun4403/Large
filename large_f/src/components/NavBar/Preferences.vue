<template>
    <div id="TemplateBody">
        Preferences
        <router-link :to="{ name: 'Template', params: { gitID: gitID }}">
            Template
        </router-link>
    </div>
</template>

<script>
export default {
    setup() {
        const gitID = localStorage.gitID
        return { gitID }    
    },

}
</script>