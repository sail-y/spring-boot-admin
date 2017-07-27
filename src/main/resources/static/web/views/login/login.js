define(function (require, exports, module) {
    console.log(utils);

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "focus .pwd": "hanlderPwd",
            "blur .pwd": "hanlderMove",
            "click .login-btn": "hanlderSubmit"
        },

        initialize: function () {
            this.model = new Backbone.Model();
            this.handlerKeyup();
        },

        hanlderPwd: function () {
            $(".hand").addClass("active");
        },

        hanlderMove: function () {
            $(".hand").removeClass("active");
        },

        hanlderSubmit: function () {
            var name = $(".user-name").val();
            var password = $(".pwd").val();

            if(name == "") {
                utils.showTip("用户名不能为空");
                return;
            }
            if(password == "") {
                utils.showTip("密码不能为空");
                return;
            }
 
            utils.getLogin("/user/login", {
                "username": name,
                "password": password
            }, function (res) {
                var token = res.token;
                var resourceList = JSON.stringify(res.resourceList);
                console.log(res);
                localStorage.setItem("token", token);
                localStorage.setItem("userName", res.name);
                localStorage.setItem("resourceList", resourceList);

                window.location.href = "../index/index.html";
            })
        },

        handlerKeyup:function(event) {
            $(document).keydown(function(event){
                if(event.keyCode == 13) {
                    this.hanlderSubmit();
                }
            }.bind(this));

        }


    });

    var home = new Home();

});

seajs.use('./login.js');
