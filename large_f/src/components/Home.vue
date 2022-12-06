<template>
    <div id="NavBar"></div>
    <div id="body" class="unselectable">
      <div style="margin-top:10%; ">
        <span id="span1" class="large">LARGE</span><br>
        <div style="padding-left: 17%;">
          <span id="span3">bigger than </span> 
          <span id="span4">Medium</span>
        </div>
      </div>
      <button id="GetStartedBTN" type="button" onclick="location.href='https://github.com/login/oauth/authorize?client_id=010fc7e8d7cd79b2d8cb&redirect_uri=http://localhost:3000'">
        Get Started!
      </button>
      <div>
        <span id="span2">with git account</span>
        <img src="../../public/assets/image/GitIcon.png" title="go to github.com" width="20" alt="Gihubt Icon" class="imgg" onclick="location.href='https://github.com'">
      </div>


<!-- 
      <br>
      <br>
      <br>  
      <router-link to="/about">to ABOUT</router-link> -->
    </div>
    <br>
    <!-- <script type="text/javascript" src="./assets/js/NavBar.js"></script>  -->
</template>

<script>
export default {


}
</script>

<style>
 #span1 {
    font-size: 700%;
  }

  #span3 {
    font-size: 100%;
  }

  #span4 {
    font-size: 100%;
  }

  #body {
    font-family: 'Courier New', Courier, monospace;
    text-align: center;
  }

  #GetStartedBTN {
    margin-top: 10%;
    background-color: black;
    color:white;
    padding: 0.75%;
    padding-left: 1.1%;
    border-radius: 5px;
    font-family: inherit;
    font-size: large;
    margin-bottom: 10px;
    cursor: pointer;
  }

  #home {
    background-color: #BEBEBE;
  }

  .imgg {
      vertical-align: middle;
      cursor: pointer;
  }
  
  #span2 {
      font-size: 15px;
      margin-bottom: 5px;
      margin-right: 0.27%;
      cursor: default;
  }
</style>