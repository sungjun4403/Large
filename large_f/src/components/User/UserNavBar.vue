<template>
    <div id="UserNav">
        {{gitID}}
        &nbsp;&nbsp;
        <router-link :to="{ name: 'UserHome', params: { gitID: gitID }}">HOME</router-link>
        &nbsp;
        <router-link :to="'/post/create'">POST CREATE</router-link>
        &nbsp;
        <router-link to="/post/viewAll">POST VIEW ALL</router-link>
        
    </div>
</template>

<script>
// import { ref } from "@vue/reactivity"

export default ({
    setup() {
        // const gitID = ref("")
        const gitID = "sungjun4403"
        return {gitID}
    },
})
</script>

<style>
    #UserNav {
        width: 100vw;
        background-color: pink;
    }
</style>