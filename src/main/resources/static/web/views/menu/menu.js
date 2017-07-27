define(function (require, exports, module) {

    var resourceId = "";

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {
            "click .add-btn": "handlerAdd",
            "click .del-btn": "handlerDelete",
            "click .edit-btn": "handlerEdit"
        },

        template: _.template($('#treeTemplate').html()),
        bTemplate: _.template($('#buttonTemplate').html()),

        initialize: function () {
            this.model = new Backbone.Model();
            this.getTree();
            this.model.set("resourceData", resourceData);
            this.hideView();

        },

        render: function () {
            $("#tree-basic").empty().append(this.template(this.model.toJSON()));
            $("#toolBox").empty().append(this.bTemplate(this.model.toJSON()));
        },

        getTree: function () {

            utils.getJSON("/resource/treeList", {}, function (res) {
                this.model.set("list", res);
                this.render();
                $("#tree-basic").treetable({expandable: true});
                $("#tree-basic").treetable("expandAll");
            }.bind(this))
        },

        handlerAdd: function () {
            window.location.href = "../addmenu/addmenu.html";
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
                $(this).parent().parent().parent().hide();
                _this.handlerSureDel();
            })
        },

        handlerSureDel: function () {
            var _this = this;
            utils.getDelete("/resource/" + resourceId, {}, function (res) {
                utils.showTip("删除成功");
                $(window).trigger("changeMenu");
                setTimeout(function () {
                    window.location.href = "../menu/menu.html";
                }, 1000);
            })
        },

        handlerEdit: function (event) {
            var target = $(event.currentTarget);
            var id = target.data("id");
            window.location.href = "../addmenu/addmenu.html?id=" + id;

        }


    });

    var home = new Home();

});

seajs.use('./menu.js');
