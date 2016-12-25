define(function(require, exports, module){

	var id = utils.getQuery("id");
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
			"click .sure-btn" : "handlerSure"
		},

		initialize:function(){
			this.model = new Backbone.Model();
			this.initData();
			
		},

		handlerSure:function() {
			var password = $(".new-pwd").val();
			var postData = {
				"password" : password
			}
				if (password == "") {
					utils.showTip("请输入新密码");
					return;
				}

			this.handlerEdit(postData);
		},

		handlerEdit:function(postData) {
			postData["id"] = id;
			utils.getPUT("/user/editPwd",postData,function(res) {
				utils.showTip("修改成功");
				
				setTimeout(function() {
					window.location.href = "../userList/userList.html";
				},1000);

			})
		},

		initData:function() {
			var _this = this;
		    if (id) {
		    	utils.getJSON("/user/" + id,{},function(res){
		    		_this.dealData(res);
		    	})
		    }
		},

		dealData:function(res) {
			$(".name").val(res.name);
			$(".user-name").val(res.username);

		},



	});

	var home = new Home();

});

seajs.use('./editPassword.js');
