define(function (require, exports, module) {

    var userName = localStorage.getItem("userName");

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .menu-ul>li": "handlerShowLi",
            "click .logout-btn": "handlerLogout",
            "click .close-btn" : "handlerClose",
            "click .pwd-edit" : "handlerEdit",
            "click .pwdchange-btn" : "handlerShowPwd",
            "click .refresh-btn" : "handlerRefresh"
        },

        menuTemplate: _.template($('#menuTemplate').html()),

        initialize: function () {
            this.model = new Backbone.Model();
            this.getMenu();

            $(".user-name").text(userName);

            $("#content").attr("src", "../home/home.html");

        },

        menuRender: function () {
            $(".menu-ul").empty().append(this.menuTemplate(this.model.toJSON()));
        },

        getMenu: function () {
            utils.getPOST("/resource/menus", {}, function (res) {
                this.model.set("list", res);
                this.menuRender();

            }.bind(this));

        },

        handlerShowLi: function (event) {
            var target = $(event.currentTarget);
            var status = target.hasClass("active");
            target.find(".item-ul").toggle();
            target.addClass("active");
            if (status) {
                target.removeClass("active");
            } else {
                target.addClass("active");
            }

        },

        handlerLogout: function () {
            localStorage.removeItem("token");
            window.location.href = "../login/login.html";
        },

        handlerClose:function() {
            $(".pwd-view").hide();
        },

        handlerShowPwd:function() {
            $(".pwd-view").show();
        },

        handlerRefresh:function () {
            refreshIframe();
        },

        handlerEdit:function() {
            var oldPassword = $(".old-pwd").val();
            var password = $(".new-pwd").val();
            var repwd = $(".re-pwd").val();
            var _this = this;
            var postData = {
                "oldPassword" : oldPassword,
                "password" : password
            }
            if(oldPassword == "") {
                utils.showTip("旧密码不能为空");
                return;
            }
            if(password == "") {
                utils.showTip("新密码不能为空");
                return;
            }
            if(password.length < 6) {
                utils.showTip("密码不能少于6位");
                return;
            }
            if(password != repwd) {
                utils.showTip("两次输入的密码不一致");
                return;
            }
            utils.getPOST("/user/editCurrentUserPwd", postData, function (res) {
                 utils.showTip("修改成功");
                 setTimeout(function() {
                    _this.handlerLogout();
                 },1500); 
            });

        }


    });

    var home = new Home();

});

seajs.use('./index.js');
