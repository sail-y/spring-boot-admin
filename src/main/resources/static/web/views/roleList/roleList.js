define(function (require, exports, module) {

    var table;

    var code;

    var userId;

    var resourceId;

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .edit-btn": "handlerEdit",
            "click .del-btn": "handlerDelete",
            "click .add-btn": "handlreAdd",
            "click .close-btn": "handlerClose",
            "click .auth-sure": "handlerAuth",
            "click .auth-btn": "handlerShow",
            "click .refresh-btn" : "handlerRefresh"
        },
        template: _.template($('#buttonTemplate').html()),
        initialize: function () {
            this.model = new Backbone.Model();
            this.model.set("resourceData", resourceData);
            this.initData();
            this.render();
            this.hideView();
            this.getData();

        },

        render: function () {
            $("#toolBox").empty().append(this.template(this.model.toJSON()));
        },

        initData: function () {
            console.log(baseUrl)
            table = $('#table').DataTable({
                "ajax": {
                    url: baseUrl + "/role/tables"
                },
                "columns": [
                    {"data": "name"},
                    {"data": "remark"},
                    {"data": "seq"},
                    {
                        render: function (data, type, row, meta) {
                            var str = "";

                            if ($.inArray("/role-put", resourceData) > -1) {
                                str += "<button data-text='编辑角色'  data-id='addRole' data-link='../addRole/addRole.html?id=" + row.id + "' class='btn btn-primary edit-btn btn-xs margin-right-5'><i class='fa fa-pencil' aria-hidden='true'></i> 编辑</button>"
                            }

                            if ($.inArray("/role/grant-post", resourceData) > -1) {
                                str += "<button data-id='" + row.id + "' class='btn btn-primary auth-btn btn-xs margin-right-5'><i class='fa fa-unlock-alt' aria-hidden='true'></i> 授权</button>"
                            }
                            if ($.inArray("/role/*-delete", resourceData) > -1) {
                                str += "<button data-id='" + row.id + "' class='btn btn-danger del-btn btn-xs '><i class='fa fa-trash-o' aria-hidden='true'></i> 删除</button>";
                            }


                            return str;
                        }
                    }
                ]
            });
        },

        handlerEdit: function (event) {

            addTab(event, true);
        },

        handlerDelete: function (event) {
            var target = $(event.currentTarget);
            resourceId = target.data("id");
            $(".alert-view .alert-txt", parent.document).text("确定要删除吗？");
            $(".alert-view", parent.document).show();

        },

        hideView: function () {
            var _this = this;

            $(".alert-view .s-btn", parent.document).click(function () {
                $(".alert-view", parent.document).hide();
                _this.handlerSureDel();
            })
        },

        handlerSureDel: function () {
            var _this = this;
            utils.getDelete("/role/" + resourceId, {}, function (res) {
                utils.showTip("删除成功");
                setTimeout(function () {
                    table.ajax.reload(null, false);
                }, 1000);
            })
        },

        handlreAdd: function (event) {
            addTab(event, true);
        },

        getData: function () {
            utils.getJSON("/resource/treeList", {}, function (res) {
                this.initTree(res);

            }.bind(this));

        },

        initTree: function (res) {
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

        setCheck: function () {
            var zTree = $.fn.zTree.getZTreeObj("role"),
                py = $("#py").attr("checked") ? "p" : "",
                sy = $("#sy").attr("checked") ? "s" : "",
                pn = $("#pn").attr("checked") ? "p" : "",
                sn = $("#sn").attr("checked") ? "s" : "",
                type = {"Y": py + sy, "N": pn + sn};
            zTree.setting.check.chkboxType = type;
            this.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
        },
        showCode: function (str) {
            if (!code) code = $("#code");
            code.empty();
            code.append("<li>" + str + "</li>");
        },

        handlerClose: function (event) {
            var target = $(event.currentTarget);
            target.parent().parent().hide();
        },

        handlerAuth: function () {
            var treeObj = $.fn.zTree.getZTreeObj("role");
            var nodes = treeObj.getCheckedNodes(true);
            var arr = [];
            for (var i = 0; i < nodes.length; i++) {
                arr.push(nodes[i].id);
            }
            if (arr.length == 0) {
                utils.showTip("请选择权限");
                return;
            }
            utils.getPOST("/role/grant", {
                "id": userId,
                "resourceIds": arr
            }, function (res) {
                utils.showTip("配置成功");
                setTimeout(function () {
                    $(".role-view").hide();
                    table.ajax.reload(null, false);
                }, 1000);

            })

        },

        handlerShow: function (event) {
            var target = $(event.currentTarget);
            var _this = this;
            userId = target.data("id");
            utils.getJSON("/role/" + userId, {}, function (res) {
                _this.initEdit(res);
            })


        },

        initEdit: function (res) {
            var treeObj = $.fn.zTree.getZTreeObj("role");
            var data = res.resourceIds;
            var nodes = treeObj.transformToArray(treeObj.getNodes());
            treeObj.checkAllNodes(false);
            console.log(nodes);
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i] == nodes[j].id) {
                        treeObj.checkNode(nodes[j], true, true);
                    }
                }
            }
            $(".role-view").show();
        },

        handlerRefresh: function () {
            table.ajax.reload(null, false);
        }


    });

    var home = new Home();

});

seajs.use('./roleList.js');
