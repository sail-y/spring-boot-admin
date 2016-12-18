define(function(require, exports, module){
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
			"click .menu-ul li" : "handlerShowLi",
			"click .item-ul li" : "handlerPage"
		},

		menuTemplate:_.template($('#menuTemplate').html()),

		initialize:function(){
			this.model = new Backbone.Model();
			this.getMenu();
			$("#content").attr("src","./index.html");
			
		},

		menuRender:function() {
			$(".menu-ul").empty().append(this.menuTemplate(this.model.toJSON()));
		},

		getMenu:function() {
			utils.getPOST("/resource/menus",{},function(res) {
				console.log(res);
				this.model.set("list",res);
				this.menuRender();

			}.bind(this));
			
		},

		handlerShowLi:function(event) {
			var target = $(event.currentTarget);
			var status = target.hasClass("active");
			target.find(".item-ul").toggle();
			target.addClass("active");
			if (status) {
				target.removeClass("active");
			}else{
				target.addClass("active");
			}

		},

		handlerPage:function(event) {
			event.stopPropagation();
			var target = $(event.currentTarget);
			var url = target.data("link");
			console.log(url);
			$("#content").attr("src",url);
		}



	});

	var home = new Home();

});

seajs.use('./index.js');
