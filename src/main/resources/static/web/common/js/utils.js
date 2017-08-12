window.baseUrl = '';
var basesUrl = "";
var host = location.host;
var urlLink = location.href;
var token = localStorage.getItem("token");
baseUrl = 'http://' + location.host;
basesUrl = 'https://' + location.host;


window.utils = {

    showPage: function (page) {
        $('.page-view').hide();
        $('.' + page).show();
    },

    showTip: function (message) {
        var $tips = $('#tips');
        $tips.show(200).text(message);
        setTimeout(function () {
            $tips.hide(200);
        }, 3000);
    },

    loadingShow: function (show) {
        var loading = $('#loading');
        if (show && !loading.hasClass('loading-show')) {
            loading.addClass('loading-show');
        } else {
            loading.removeClass('loading-show');
        }
    },

    getQuery: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]);
        return null;
    },

    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
            qq: u.match(/\sQQ/i) == " qq", //是否QQ
            ipod: u.indexOf('iPod') > -1 //是否ipod
        };
    }(),

    getJSON: function (url, param, callback) {
        var _this = this;
        $.ajax({
            url: baseUrl + url,
            type: 'GET',
            dataType: 'json',
            data: param ? param : {},
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (res) {
                _this.getData(res, callback);
            },
            fail: function () {
            }
        });
    },

    getPUT: function (url, param, callback) {
        var _this = this;
        $.ajax({
            url: baseUrl + url,
            type: 'PUT',
            dataType: 'json',
            data: JSON.stringify(param ? param : {}),
            contentType: "application/json; charset=utf-8",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (res) {
                _this.getData(res, callback);
            },
            fail: function () {
            }
        });
    },

    getPOST: function (url, param, callback) {
        var _this = this;
        $.ajax({
            url: baseUrl + url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            data: JSON.stringify(param ? param : {}),
            success: function (res) {
                _this.getData(res, callback);;
            },
            fail: function () {
            }
        });
    },

    getLogin: function (url, param, callback) {
        var _this = this;
        $.ajax({
            url: baseUrl + url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(param ? param : {}),
            success: function (res) {

                _this.getData(res, callback);
            },
            fail: function () {
            }
        });
    },

    getDelete: function (url, param, callback) {
        var _this = this;
        $.ajax({
            url: baseUrl + url,
            type: 'DELETE',
            dataType: 'json',
            data: param ? param : {},
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", token);
            },
            success: function (res) {
                _this.getData(res, callback);
            },
            fail: function () {
            }
        });
    },


    getData: function (res, callback) {
        var _this = this;
        if (res.code && res.code !== 200) {
            _this.showTip(res.msg);

            if (res.code === 10002) {
                setTimeout(function () {
                    window.location.href = "../login/login.html";
                }, 1500);
            }
            return;
        } else {
            if (callback) callback(res);
        }
    },

    showConfirm: function (text, callback) {
        $(".alert-view .alert-txt", parent.document).text(text);
        $(".alert-view", parent.document).show();

        $(".alert-view .s-btn", parent.document).unbind("click");
        var _parent = parent.document;
        $(".alert-view .s-btn", parent.document).click(function () {
            var $alert = $(".alert-view", _parent);
            if ($alert)
                $alert.hide();
            callback();
        })
    }


};
$("body").scrollTop(0);
