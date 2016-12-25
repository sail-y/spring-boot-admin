define(function(require, exports, module){
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
		},

		initialize:function(){
			this.model = new Backbone.Model();
			this.initData();
			
		},

		initData:function() {
			// $('#table').dataTable( {
			//   "ajaxSource": "sources/objects.txt",
			//   "columns": [
			//     { "data": "engine" },
			//     { "data": "browser" },
			//     { "data": "platform" },
			//     { "data": "version" },
			//     { "data": "grade" }
			//   ]
			// });
		}



	});

	var home = new Home();

});

seajs.use('./userList.js');
