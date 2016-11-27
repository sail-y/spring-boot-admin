

<template>
  <div id="main">
     <div class="line1"></div>
     <div class="box">
         <div class="mao-box">
              <img src="../assets/images/tou.png" class="tou-img">
              <span id="left" class="left-img" v-bind:class="{ active: isActive}"></span>
              <span class="right-img"  v-bind:class="{ active: isActive}"></span>
         </div>
         <div class="title">欢迎使用贝斯系统</div>
         <div class="line">
             <input type="text" v-model="name" name="name" class="user-name" placeholder="用户名">
         </div>
         <div class="line">
             <input type="password" v-model="password" v-on:focus="hanlderPwd" v-on:blur="hanlderMove" class="pwd" name="pwd" placeholder="密码">
         </div>
         <div class="text-center">
             <button  v-on:click="hanlderSubmit" class="login-btn">登录</button>
         </div>
     </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      "isActive" : false,
      "name" : "",
      "password" : ""
    }
  },
  created:function() {
    console.log(5555);

  },
  methods: {
    hanlderPwd: function () {
      this.isActive = true;
    },
    hanlderMove: function () {
      this.isActive = false;  
    },
    hanlderSubmit: function () {
      console.log(this.name);
      console.log(this.password);
    }
  }
}

</script>


<style scoped>
html,body{
  height: 100%;
}
.line1{
  height: 1px;
}
 #main{
  width: 100%;
  background-color: #f3f3f4;
  height: 100%;
 }
 .box{
  width: 500px;
  margin:0 auto;
  color: #333;
  margin-top: 15%;
  text-align: center;
  background-color: #fff;
  border:solid 1px #ccc;
  padding:30px 0;
  position: relative;
 }
 .line{
  margin-bottom: 20px;
 }
 input{
    width: 300px;
    border:solid 1px #ccc;
    height: 32px;
    padding-left:5px;
    border-radius: 3px;
 }
 .login-btn{
    background-color: #449d44;
    width: 305px;
    height: 32px;
    line-height: 32px;
    border:none;
    border-radius: 3px;
    color: #fff;
    text-align: center;
    font-size: 15px;
    letter-spacing: 3px;
 }
 .title{
  margin-bottom:20px;
  color: #666;
  font-size: 20px;
 }
 .mao-box{
  position: absolute;
  top: -87px;
  left: 50%;
  width: 100%;
  margin-left: -50%;
  text-align: center;
 }
 .tou-img{
    width: 100px;
 }
 .left-img{
    position: absolute;
    left: 25%;
    bottom: 0;
    width: 28px;
    display: inline-block;
    background-image: url(../assets/images/hand.png);
    background-repeat: no-repeat;
    background-size: 100% 100%;
    height: 18px;
 }
.right-img{
    position: absolute;
    right: 25%;
    bottom: 0;
    width: 28px;
    display: inline-block;
    background-image: url(../assets/images/hand.png);
    background-repeat: no-repeat;
    background-size: 100% 100%;
    height: 18px;
 }
  .left-img.active{
    animation: left 0.5s linear;
    -webkit-animation: left 0.5s linear;
    -webkit-animation-fill-mode:forwards;
  }
  .right-img.active{
    animation: right 0.5s linear;
    -webkit-animation: right 0.5s linear;
    -webkit-animation-fill-mode:forwards;
  }
 @keyframes left {
  0%{
    left: 25%;  
    background-image: url(../assets/images/hand.png);
  }
  60%,100%{
    left: 42.5%;
    height: 40px;
    bottom: 10px;
    background-image: url(../assets/images/left-hand.png);
  }
 }
  @-webkit-keyframes left {
  0%{
    left: 25%;  
    background-image: url(../assets/images/hand.png);
  }
  60%,100%{
    left: 42.5%;
    height: 40px;
    bottom: 10px;
    background-image: url(../assets/images/left-hand.png);
  }
 }

  @keyframes right {
  0%{
    right: 25%;
    bottom: 0;  
    background-image: url(../assets/images/hand.png);
  }
  60%,100%{
    right: 42.5%;
    height: 40px;
    bottom: 10px;
    background-image: url(../assets/images/right-hand.png);
  }
 }
  @-webkit-keyframes right {
  0%{
    right: 25%;
    bottom: 0;  
    background-image: url(../assets/images/hand.png);
  }
  60%,100%{
    right: 42.5%;
    height: 40px;
    bottom: 10px;
    background-image: url(../assets/images/right-hand.png);
  }
 }
</style>
