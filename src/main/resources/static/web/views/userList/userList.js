define(function(require, exports, module){

	var table;

	var code;

	var userId;
		
	var Home = Backbone.View.extend({

		el:document.getElementsByTagName('body')[0],

		events:{
			"click .edit-btn" : "handlerEdit",
			"click .pwd-btn" : "handlerPwd",
			"click .del-btn" : "handlerDelete",
			"click .add-btn" : "handlreAdd",
			"click .close-btn" : "handlerClose",
			"click .auth-sure" : "handlerAuth",
			"click .auth-btn" : "handlerShow"
		},

		initialize:function(){
			this.model = new Backbone.Model();
			this.initData();
			this.hideView();
			this.getData();
			
		},

		initData:function() {
			table = $('#table').DataTable({
			    "processing": false,
			    "serverSide": true,
			    "paging" : true,
			    "ajax": {
			        url:"/user/tables",
			        type:"post",
			        beforeSend: function(request) {
			            request.setRequestHeader("Authorization", token);
			        },
			        contentType: "application/json; charset=utf-8",
			        "data": function ( d ) {
			            return JSON.stringify(d);
			        }
			    },
			    "columns": [
			        {"data": "username"},
			        {"data": "name"},
			        {"data": "createTime"},
			        {render: function (data, type, row, meta) {
			                return "<button data-id='"+row.id+"' class='btn btn-danger del-btn btn-xs margin-right-5'>删除</button>"
			                       + "<button data-id='"+row.id+"' class='btn btn-primary edit-btn btn-xs margin-right-5'>编辑</button>"
			                       + "<button data-id='"+row.id+"' class='btn btn-default pwd-btn btn-xs margin-right-5'>修改密码</button>"
			                       + "<button data-id='"+row.id+"' class='btn btn-primary auth-btn btn-xs'>授权</button>"
					            }
					}
			    ],
			    "pagingType":   "full_numbers",
				"sZeroRecords": "暂无数据",
				stateSave: true,
				"searching": false,
				"dom": 'rt<"bottom"iflp<"clear">>',
				"language": {
	                 "processing": "玩命加载中...",
					"lengthMenu": "显示 _MENU_ 项结果",
					"zeroRecords": "没有匹配结果",
					"info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
					"infoEmpty": "显示第 0 至 0 项结果，共 0 项",
					"infoFiltered": "(由 _MAX_ 项结果过滤)",
					"infoPostFix": "",
					"url": "",
					"paginate": {
						"first":    "首页",
						"previous": "上一页",
						"next":     "下一页",
						"last":     "末页"
					}
				}
			});
		},

		handlerEdit:function(event) {
			var target = $(event.currentTarget);
			var id = target.data("id");
			window.location.href = "../addUser/addUser.html?id=" + id;
		},

		handlerPwd:function(event) {
			var target = $(event.currentTarget);
			var id = target.data("id");
			window.location.href = "../editPassword/editPassword.html?id=" + id;
		},

		handlerDelete:function(event) {
			var target = $(event.currentTarget);
			    resourceId = target.data("id");
			$(".alert-view .alert-txt",parent.document).text("确定要删除吗？");
			$(".alert-view",parent.document).show();

		},

		hideView:function() {
			var _this = this;

			$(".alert-view .s-btn",parent.document).click(function() {
				$(".alert-view",parent.document).hide();
				_this.handlerSureDel();
			})
		},

		handlerSureDel:function() {
			var _this = this;
			utils.getDelect("/user/" + resourceId,{},function(res) {
				utils.showTip("删除成功");
				setTimeout(function() {
					table.ajax.reload();
				},1000);
			})
		},

		handlreAdd:function() {
			window.location.href = "../addUser/addUser.html";
		},

		getData:function() {
			utils.getJSON("/role/tree",{},function(res) {
				this.initTree(res);

			}.bind(this));
			
		},

		initTree:function(res) {
			   var setting = {
				   	view: {
						dblClickExpand: false
					},
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pid"
						}
					}
				};
				zTree = $.fn.zTree.init($("#role"), setting, res);
				this.setCheck();
				$("#py").bind("change", this.setCheck);
				$("#sy").bind("change", this.setCheck);
				$("#pn").bind("change", this.setCheck);
				$("#sn").bind("change", this.setCheck);
		},

	   setCheck:function() {
			var zTree = $.fn.zTree.getZTreeObj("role"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			this.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		},
		showCode:function(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		},

		handlerClose:function(event) {
			var target = $(event.currentTarget);
				target.parent().parent().hide();
		},

		handlerAuth:function() {
			var treeObj = $.fn.zTree.getZTreeObj("role");
			var nodes = treeObj.getCheckedNodes(true);
			var arr = [];
				for(var i = 0; i < nodes.length; i ++) {
					arr.push(nodes[i].id);
				}
				if (arr.length == 0) {
					utils.showTip("请选择权限");
					return;
				}
				utils.getPOST("/user/grant",{
					"id" : userId,
					"roleIds" : arr
				},function(res) {
					utils.showTip("配置成功");
					setTimeout(function() {
						$(".role-view").hide();
						table.ajax.reload();
					},1000);
					
				})

		},

		handlerShow:function(event) {
			var target = $(event.currentTarget);
			var _this = this;
				userId = target.data("id");
				utils.getJSON("/user/" + userId,{},function(res) {
					_this.initEdit(res);
				})
				

		},

		initEdit:function(res) {
			var treeObj = $.fn.zTree.getZTreeObj("role");
			var nodes = treeObj.getNodes();
			var data = res.roleIds;
				for(var i = 0; i < data.length; i ++) {
					for(var j = 0; j < nodes.length; j ++) {
						if (data[i] == nodes[j].id) {
							nodes[j].checked = true;
							treeObj.updateNode(nodes[i]);
						}
					}
				}
				$(".role-view").show();
		}



	});

	var home = new Home();

});

seajs.use('./userList.js');
