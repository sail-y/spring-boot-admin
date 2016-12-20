define(function(require, exports, module){
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
		},

		template:_.template($('#treeTemplate').html()),

		initialize:function(){
			this.model = new Backbone.Model();
			 this.getTree();
			 this.model.set("resourceData",resourceData);
		},

		render:function() {
			$(".tree-body").empty().append(this.template(this.model.toJSON()));
		},

		getTree:function() {
			
			utils.getJSON("/resource/treeList",{},function(res) {
               this.model.set("list",res);
               this.render();
               $("#tree-basic").treetable({ expandable: true });
			}.bind(this))
		},





	});

	var home = new Home();

});

seajs.use('./menu.js');
