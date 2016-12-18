define(function(require, exports, module){
	console.log(utils);
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
			"focus .pwd" : "hanlderPwd",
			"blur .pwd" : "hanlderMove",
			"click .login-btn" : "hanlderSubmit"
		},

		initialize:function(){
			this.model = new Backbone.Model();
		},

		hanlderPwd:function() {
			$(".hand").addClass("active");
		},

		hanlderMove:function() {
			$(".hand").removeClass("active");
		},

		hanlderSubmit:function() {
			var name = $(".user-name").val();
			var password = $(".pwd").val();

			utils.getLogin("/user/login",{
				"username" : name,
				"password" : password
			},function(res) {
				var token = res.token;
				localStorage.setItem("token",token);
				window.location.href = "../index/index.html";
			})
		}



	});

	var home = new Home();

});

seajs.use('./login.js');
