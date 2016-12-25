define(function(require, exports, module){

	var list = [];

	var zTree;

	var pid;

	var id = utils.getQuery("id");
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
			"click .sure-btn" : "handlerSure",
			"click .inp-tree" : "handlerShow"
		},

		oneTemplate:_.template($('#oneTemplate').html()),

		twoTemplate:_.template($('#twoTemplate').html()),

		initialize:function(){
			this.model = new Backbone.Model();
			this.getMenu();
			
		},

		menuRender:function() {
			$(".one-sel").empty().append(this.oneTemplate(this.model.toJSON()));
		},

		twoRender:function() {
			$(".two-sel").empty().append(this.twoTemplate(this.model.toJSON()));
		},

		getMenu:function() {
			utils.getPOST("/resource/menus",{},function(res) {
				list = res;
				this.model.set("onelist",res);
				this.menuRender();
				this.initData();
				this.initTree(res);

			}.bind(this));
			
		},

		handlerSure:function() {
			var type = $(".type-sel").val();
			var name = $(".name").val();
			var remark = $(".remark").val();
			var seq = $(".seq").val();
			var postData = {
				"type" : type,
				"name" : name,
				"seq" : seq
			}
			if (remark != "") {
				postData["remark"] = remark;
			}
			if (name == "") {
				utils.showTip("请输入资源名");
				return;
			}
			if (seq == "") {
				utils.showTip("请输入排序");
				return;
			}
			if (pid) {
				postData["pid"] = pid;
			}

			
			if (id) {
				this.handlerEdit(postData);
			}else{
				this.handlerAdd(postData);
			}
		},

		handlerAdd:function(postData) {
			utils.getPOST("/resource",postData,function(res) {
				utils.showTip("添加成功");
				setTimeout(function() {
					window.location.href = "../roleList/roleList.html";
				},1000);

			})
		},

		handlerEdit:function(postData) {
			postData["id"] = id;
			utils.getPUT("/resource",postData,function(res) {
				utils.showTip("修改成功");
				
				setTimeout(function() {
					window.location.href = "../roleList/roleList.html";
				},1000);

			})
		},

		initData:function() {
			var _this = this;
		    if (id) {
		    	$(".sure-btn").text("修改");
		    	utils.getJSON("/resource/" + id,{},function(res){
		    		_this.dealData(res);
		    	})
		    }
		},

		dealData:function(res) {
			var type = res.type;
			$(".type-sel").val(type);
			$(".name").val(res.name);
			$(".seq").val(res.seq);
			
			if (res.pid) {
				pid = res.pid;
				$(".inp-tree").val(res.pname);
			}
			if (res.url) {
				$(".url").val(res.url);
			}
			if (res.remark) {
				$(".remark").val(res.remark);
			}
			if (res.method) {
				$(".way-sel").val(res.method);
			}

		},

		initTree:function(res) {
			   var setting = {
				   	view: {
						dblClickExpand: false
					},
					data: {
						key: {
							name: "text",
							children: "children",
							url:"url1"
						}
					},
					callback: {
						onClick: this.onClick
					}
				};
				zTree = $.fn.zTree.init($("#tree"), setting, res);
		},

		onClick:function() {
			var nodes = zTree.getSelectedNodes();
			var text = nodes[0].text;
				pid = nodes[0].id;
                $(".inp-tree").val(text);
				$("#tree").hide();
		},

		handlerShow:function() {
			$("#tree").toggle();
		}



	});

	var home = new Home();

});

seajs.use('./addRole.js');
