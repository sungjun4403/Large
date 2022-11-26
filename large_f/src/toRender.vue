<template>
    <textarea id="textareaBody" cols="50" rows="20" v-on:input="renderBody()"></textarea>
    <textarea id="textareaStyle" cols="50" rows="20"></textarea>
    <textarea id="textareaScript" cols="50" rows="20" v-on:input="renderScript()"></textarea>
    
    <!-- <a id="google" href="https://google.com">GOOGLE</a>
    <style> 
        #google {
            text-decoration: none;
        } 
    </style>  -->
</template>


<script>
export default {
    beforeMount() {
        var OriginalCSS = document.styleSheets[0].cssRules.length
        console.log(OriginalCSS)
        console.log(document.scripts)
        this.renderScript();
    },

    mounted() {
        const textareaStyle = document.getElementById('textareaStyle')
        textareaStyle.addEventListener('keydown', (e) => {
            if (e.key == ';') {
                this.renderStyle()
            }
            else {
                console.log('NO RENDER')
            }
        });
    },

    methods: {
        renderBody() {            
            var toRenderBody = document.getElementById("textareaBody").value
            var RenderedBody = document.getElementById("RenderedBody")
            RenderedBody.innerHTML = toRenderBody
            
        },

        renderStyle() {
            var toRenderStyle =  document.getElementById("textareaStyle").value
            var css = document.styleSheets[0]
            this.removeFormalCSS()
            css.insertRule(toRenderStyle)
            console.log('RENDER')
            console.log(css.cssRules.length)
            document.write('<style>' + toRenderStyle + '/<style>')
        }, 

        renderScript() {
            // var toRenderScript = document.getElementById("textareaScript").value
            document.write('<script>' + 'console.log("MOUNT SUCCESS")  ' + 'function clck() {   console.log("wow!!!!") }' + '</sc'+'ript>')
        },

        removeFormalCSS() {
            var a = parseInt(document.styleSheets[0].cssRules.length)+1
            console.log(this.OriginalCSS)
            var b = this.OriginalCSS+1  
            var css = document.styleSheets[0]

            for (var i=b; i<a; i++) {
              css.deleteRule(i)
            }
        },
    }
}
</script>


<style id="style">
    #toRender {
        /* float: left; */
        
        width: 40%;
        font-family: 'Courier New', Courier, monospace;
    }
</style>