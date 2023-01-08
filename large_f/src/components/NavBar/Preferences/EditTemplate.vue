<template>
    <div id="EditTemplateBody">
        Edit Template

        <input type="text" id="toEditTemplateName" name="toEditTemplateName" v-model="toEditTemplateName" placeholder="TemplateName">

        <br><br>

        <input type="text" id="toEditTemplateNumber" name="toEditTemplateNumber" v-model="TemplateNumber" placeholder="TemplateNumber">

        <br><br>

        <input type="text" id="toEditHotKey" name="toEditHotKey" v-model="toEditHotKey" placeholder="HotKey">

        <br><br>

        <input type="text" id="toEditTemplate" name="toEditTemplate" v-model="toEditTemplate" placeholder="Template">

        <br><br>

        <buyo

    </div>
</template>


<script>
import { ref } from "@vue/reactivity"
// const axios = require("axios")


export default ({
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
    method: {
    }
})
</script>


<style>
#EditTemplateBody {
    text-align: center;
}
</style>