define(function (require, exports, module) {

    var list = [];

    var zTree;

    var pid;

    var id = utils.getQuery("id");

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .sure-btn": "handlerSure",
            "click .inp-tree": "handlerShow"
        },

        initialize: function () {
            this.model = new Backbone.Model();
            this.getMenu();

        },

        getMenu: function () {
            utils.getJSON("/role/tree", {}, function (res) {
                list = res;
                this.initData();
                this.initTree(res);

            }.bind(this));

        },

        handlerSure: function () {
            var name = $(".name").val();
            var remark = $(".remark").val();
            var seq = $(".seq").val();
            var postData = {
                "name": name,
                "seq": seq
            }
            if (remark != "") {
                postData["remark"] = remark;
            }
            if (name == "") {
                utils.showTip("请输入角色名");
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
            } else {
                this.handlerAdd(postData);
            }
        },

        handlerAdd: function (postData) {
            var _this = this;

            utils.getPOST("/role", postData, function (res) {
                utils.showTip("添加成功");
                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        handlerEdit: function (postData) {
            var _this = this;
            postData["id"] = id;
            utils.getPUT("/role", postData, function (res) {
                utils.showTip("修改成功");

                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        initData: function () {
            var _this = this;
            if (id) {
                $(".sure-btn").text("修改");
                utils.getJSON("/role/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".name").val(res.name);
            $(".seq").val(res.seq);

            if (res.pid) {
                pid = res.pid;
                $(".inp-tree").val(res.pname);
            }
            if (res.remark) {
                $(".remark").val(res.remark);
            }
            if (res.method) {
                $(".way-sel").val(res.method);
            }

        },

        initTree: function (res) {
            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pid"
                    }
                },
                callback: {
                    onClick: this.onClick
                }
            };
            zTree = $.fn.zTree.init($("#tree"), setting, res);
        },

        onClick: function () {
            var nodes = zTree.getSelectedNodes();
            var text = nodes[0].name;
            pid = nodes[0].id;
            console.log(nodes);
            $(".inp-tree").val(text);
            $("#tree").hide();
        },

        handlerShow: function () {
            $("#tree").toggle();
        },

        refreshData: function () {
            closeTab(frameElement);
        }



    });

    var home = new Home();

});

seajs.use('./addRole.js');
