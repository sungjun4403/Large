<template>
    <div>
        <div id="EditTemplateBody" v-if="IfTokenIsYours == true">
            Edit Template

            <br><br>

            <input type="text" id="toEditTemplateName" name="toEditTemplateName" v-model="toEditTemplateName" placeholder="TemplateName">

            <br><br>

            <input type="text" id="toEditTemplateNumber" name="toEditTemplateNumber" v-model="TemplateNumber" placeholder="TemplateNumber">

            <br><br>

            <input type="text" id="toEditHotKey" name="toEditHotKey" v-model="toEditHotKey" placeholder="HotKey">

            <br><br>

            <input type="text" id="toEditTemplate" name="toEditTemplate" v-model="toEditTemplate" placeholder="Template">

            <br><br>

            <button @click="templateEdit()">ONCLICK</button>

        </div>
        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
    </div>
</template>


<script>
import { ref } from "@vue/reactivity"
import axios from "axios"
// const axios = require("axios")


export default ({
    props: ["IfTokenIsYours"],
    setup() {
        const TemplateName = ref("")
        const TemplateNumber = ref(-1)
        const HotKey = ref("")
        const Template = ref("")

        const toEditTemplateName = ref("")
        const toEditTemplateNumber = ref(-1)
        const toEditHotKey = ref("")
        const toEditTemplate = ref("")

        return {TemplateName, TemplateNumber, HotKey, Template,
        toEditTemplateName, toEditTemplateNumber, toEditHotKey, toEditTemplate}
    },
    mounted() {
        
    },
    methods: {
        templateEdit() {
            // axios.patch("", headers: {
                

            // })
            axios({
                url: 'http://localhost:8080/template/' + localStorage.getItem("gitID") + '/1',
                method: 'patch',
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem("AccessToken")
                }
            })
        }
    }
})
</script>


<style>
#EditTemplateBody {
    text-align: center;
}
</style>