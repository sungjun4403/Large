<template>
    <div id="NameNImgBody">
        <img :src="profileImg" alt="profileImg" id="profileImg"> 
        {{nname}} 
        {{authorGitID}}
        {{name}} <br>        
        {{bio}}
        
    </div>
</template>

<script>
export default {
    props: ['profileImg', 'bio', 'nname', 'name', 'authorGitID'],
    setup() {
        
    },
}
</script>

<style scoped>

</style>