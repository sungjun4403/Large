<template>
    <div id="body" class="unselectable">
      <div style="margin-top:10%; ">
        <span id="span1" class="large">LARGE</span><br>
        <div style="padding-left: 17%;">
          <span id="span3">bigger than </span> 
          <span id="span4">Medium</span>
        </div>
      </div>
      <button id="GetStartedBTN" type="button" onclick="location.href='https://github.com/login/oauth/authorize?client_id=010fc7e8d7cd79b2d8cb&redirect_uri=http://localhost:3000/afterlogin'">
        Get Started!
      </button>
      <div>
        <span id="span2">with git account</span>
        <img src="../../public/assets/image/GitIcon.png" title="go to github.com" width="20" alt="Gihubt Icon" class="imgg" onclick="location.href='https://github.com'">
      </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
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
    background-color: #BEBEBE;
    padding-top: 10%;
    padding-bottom: 50%;
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