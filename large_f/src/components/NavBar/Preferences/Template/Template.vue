<template>
    <div>
        <div id="TemplateBody" v-if="IfTokenIsYours == true">
            <br>
            Template

            <li v-for="template in templates" :key="template.id">
                {{template.templateNumber}}
                {{template.templateName}}
                {{template.template}}
                {{template.hotKey}}
                <br>
            </li>

            <br>
            <textarea id="textarea" cols="30" rows="10"></textarea>
        </div>    

        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
    </div>
</template>

<script>
import { ref } from "@vue/reactivity"
const axios = require('axios').default;

export default ({
    props: ["IfTokenIsYours"],
    setup() {
        const templates = ref([])

        return { templates }
    },
    mounted() {
        var inSlash = false
        this.getAllTemplates()
        var textarea = document.getElementById("textarea")
        // var textarea = document.textarea
        textarea.addEventListener('keydown', (e) => {
            if (e.key == "/") {
                inSlash = true
            }
            if (inSlash == true) {
                setTimeout(function () {
                    console.log(textarea.value.indexOf("/http"))
                }, 100)
            }
            if (e.key == " ") {
                inSlash = false
            }
        })
    },
    methods: {
        getAllTemplates() {
            axios({
                url: 'http://localhost:8080/template/' + localStorage.getItem("gitID"),
                method: 'get',
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem("AccessToken")
                }
            }).then((response) => {
                response.data.forEach(element => {
                    this.templates.push(element)
                });
            })
        }
            
    }
})
</script>

<style>
    #TemplateBody {
        text-align: center;
    }
</style>
