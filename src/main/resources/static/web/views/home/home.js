define(function (require, exports, module) {

    var Home = Backbone.View.extend({

        el: document.getElementsByTagName('body')[0],

        events: {},

        initialize: function () {
            this.model = new Backbone.Model();

        },


    });

    var home = new Home();

});

seajs.use('./home.js');
