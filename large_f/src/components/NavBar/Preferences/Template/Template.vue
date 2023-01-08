<template>
    <div>
        <div id="TemplateBody" v-if="IfTokenIsYours == true">
            <br>
            Templates

            <div id="ModifyBody" v-for="(template, index) in templates" :key="template.id">                
                
                <input :id="'templateNumber' + template.id" class="templateNumber" size="1" >
                <input :id="'templateName' + template.id" class="templateName" size="10">
                <input :id="'template' + template.id" class="template" size="30" @click="templateInput(template.id)">
                <input :id="'hotKey' + template.id" class="hotKey" size="15">

                <img @click="removeTemplate(index)" id="removeTemplateImg" src="../../../../../public/assets/image/removeTemplate.png" alt="removeTemplate">
                <!-- <div @click="removeTemplate()"><img @click="removeTemplate()" id="addTemplateImg" src="" alt="addTemplateImg"></div> -->
                 <img v-if="index == templates.length-1" @click="addTemplate(template.templateNumber)" id="addTemplateImg" src="../../../../../public/assets/image/addTemplate.png" alt="addTemplate">
                <br>
            </div>
            <div v-if="templates.length == 0">
                <span>Create Template</span> <img @click="addTemplate(0)" id="addTemplateImg" src="../../../../../public/assets/image/addTemplate.png" alt="addTemplate">
            </div>
            <br>

            <button @click="sendWholeModify()">button</button>
            <!-- <textarea id="textarea" cols="30" rows="10"></textarea> -->
        </div>    

        <div v-else-if="IfTokenIsYours == false">
            <AccessDenied />
        </div>
        <div v-else>
            <Pending />
        </div>
    </div>
</template>

<script>
import { ref } from "@vue/reactivity"
const axios = require('axios').default;

export default ({
    props: ["IfTokenIsYours"],
    setup() {
        let gitID = localStorage.gitID

        const templates = ref([])

        const toEditTemplateNumber = ref(0)
        const toEditTemplateName = ref("")
        const toEditTemplate = ref("")
        const toEditHotKey = ref("")

        const templateAddCount = ref(0)
        const templateDeleteCount = ref(0)

        return { 
            gitID, 
            templates,
            toEditTemplateNumber, toEditTemplateName, toEditTemplate, toEditHotKey,
            templateAddCount, templateDeleteCount,

         }
    },
    mounted() {
        this.getAllTemplates()
        // var inSlash = false 
        // var textarea = document.getElementById("textarea")
        // // var textarea = document.textarea
        // textarea.addEventListener('keydown', (e) => {
        //     if (e.key == "/") {
        //         inSlash = true
        //     }
        //     if (inSlash == true) {
        //         setTimeout(function () {
        //             console.log(textarea.value.indexOf("/http"))
        //         }, 100)
        //     }
        //     if (e.key == " ") {
        //         inSlash = false
        //     }
        // })
    },
    updated() {
        this.setDefaultInput();
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
            }).then(()=> {
                this.setDefaultInput()
            })
        },
        setDefaultInput() {
            this.templates.forEach((template) => {
                document.getElementById("templateNumber" + template.id).value = template.templateNumber
                document.getElementById("templateName" + template.id).value = template.templateName
                document.getElementById("template" + template.id).value = template.template
                document.getElementById("hotKey" + template.id).value = template.hotKey
            })
        },
        removeTemplate(index) {            
            this.templates.splice(index, 1)
        },
        addTemplate(lastNumber) { 
            this.templateAddCount += 1 
            const templateToAdd = {
                id: 'Add' + this.templateAddCount, 
                gitID: this.gitID, 
                templateName: '', 
                templateNumber: lastNumber + 1,
                template: '',
                hotKey: ''
                } 
        
            this.templates.push(templateToAdd)
        },
        templateInput(templateId) {
            document.getElementById("template" + templateId)
        },
        biggerTemplateInput() {
            
        },
        // setTemplateNumber() {
        //     this.templates.forEach((template, index) => {
        //         template.templateNumber = index + 1
        //     })
        //     this.setDefaultInput()
        // },
        sendWholeModify() {
            // this.setTemplateNumber()
            const wholeTemplate = []
            this.templates.forEach((template) => {
                template.templateName =  document.getElementById("templateName" + template.id).value 
                template.templateNumber = document.getElementById("templateNumber" + template.id).value
                template.template = document.getElementById("template" + template.id).value
                template.hotKey = document.getElementById("hotKey" + template.id).value 
                
                wholeTemplate.push(template)
            })
            axios({
                url: 'http://localhost:8080/template/wholeModify/' + localStorage.getItem("gitID"),
                method: 'post',
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem("AccessToken")
                },
                data: wholeTemplate
            })

        },
    }
})
</script>

<style scoped>
    #TemplateBody {
        text-align: center;
    }

    input {
        border-left: none;
        border-top: none;
        border-bottom: none;
        /* border-right; */
        font-family: 'Courier New', Courier, monospace;
    }
    input:focus {
        outline: none;
    }
    .hotKey {
        border-right: none;
    }
    #addTemplateImg {
        cursor: pointer;
        height: 1em;
        vertical-align: middle;
        margin-right: -1em;
    }
    #removeTemplateImg {
        cursor: pointer;
        height: 1em;
        vertical-align: middle;
        margin-left: 0.27em;
        margin-right: 0.27em;
    }

</style>
