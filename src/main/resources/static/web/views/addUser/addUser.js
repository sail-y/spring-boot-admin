define(function (require, exports, module) {


    var id = utils.getQuery("id");

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .sure-btn": "handlerSure"
        },

        initialize: function () {
            this.model = new Backbone.Model();
            this.initData();

        },

        handlerSure: function () {
            var userName = $(".user-name").val();
            var name = $(".name").val();
            var password = $(".pwd").val();
            var postData = {
                "username": userName,
                "name": name,

            }
            if (userName == "") {
                utils.showTip("请输入用户名");
                return;
            }
            if (name == "") {
                utils.showTip("请输入昵称");
                return;
            }

            if (id) {
                this.handlerEdit(postData);
            } else {
                if (password == "") {
                    utils.showTip("请输入密码");
                    return;
                } else {
                    postData["password"] = password;
                }
                this.handlerAdd(postData);
            }
        },

        handlerAdd: function (postData) {
            var _this = this;
            utils.getPOST("/user", postData, function (res) {
                utils.showTip("添加成功");
                setTimeout(function () {
                    _this.refreshData();
                }, 1000);

            })
        },

        handlerEdit: function (postData) {
            var _this = this;
            postData["id"] = id;
            utils.getPUT("/user", postData, function (res) {
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
                $(".p-line").hide();
                utils.getJSON("/user/" + id, {}, function (res) {
                    _this.dealData(res);
                })
            }
        },

        dealData: function (res) {
            $(".name").val(res.name);
            $(".user-name").val(res.username);

        },

        refreshData: function () {
            closeTab(frameElement);
        }


    });

    var home = new Home();

});

seajs.use('./addUser.js');
