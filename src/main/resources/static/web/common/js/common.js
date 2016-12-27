$(function () {
    var token = localStorage.getItem("token");
    var link = location.href;


    window.resourceData = localStorage.getItem("resourceList");

    if (resourceData) {
        resourceData = $.parseJSON(resourceData);
    }

    if (!token && link.indexOf("login") < 0) {
        window.location.href = "../login/login.html";
    }

    function getSize() {

        var bodyWidth = $(window).width() - 220;
        var bodyHeight = $(window).height() - 102;

        $(".main-content iframe").css({
            "width": bodyWidth + "px",
            "height": bodyHeight + "px"
        })
    }

    function getList(list) {
        var html = "";
        for (var i = 0; i < list.length; i++) {
            var name = list[i].text;
            html += '<li><a><img src="../../images/cai.png" class="cai-img" />'
                + name
                + '<img src="../../images/arrow.png" class="arrow" /></a>'
                + '<ul class="item-ul">';
            for (var j = 0; j < list[i].children.length; j++) {
                var url = list[i].children[j].url;
                var text = list[i].children[j].text;
                var id = list[i].children[j].id;
                html += '<li class="iframe' + id + '" data-text="' + text + '" data-link="' + url + '" data-id="' + id + '"><span class="li-icon">-</span>' + text + '</li>';
            }
            html += '</ul></li>';
        }
        $(".menu-ul", parent.document).empty().html(html);
    }

    function getMenu() {
        utils.getPOST("/resource/menus", {}, function (res) {
            getList(res);
        });
    }

    function handlerTabClick(event) {
        var target = $(event.currentTarget);
        var className = target.attr("class").split(" ")[0];
        target.siblings().removeClass("active");
        target.addClass("active");
        $(".main-content iframe").hide();
        $(".main-content").find("iframe." + className).show();
        $(".item-ul li").removeClass("active");
        $(".item-ul").find("li." + className).addClass("active");
    }

    window.addTab = function (event, hasParent, id) {
        event.stopPropagation();
        var target = $(event.currentTarget);
        var url = target.data("link");
        var name = target.data("id");
        var text = target.data("text");
        var par = document;
        if (hasParent) {
            par = parent.document;
        }
        var dom = $("iframe.iframe" + name, par);
        var bodyWidth = $("body", par).width() - 220;
        var bodyHeight = $("body", par).height() - 102;
        $(".item-ul li", par).removeClass("active");
        target.addClass("active");
        $("iframe", par).hide();
        $(".tab-content li", par).removeClass("active");
        if (dom.length > 0) {
            $(".tab-content li.iframe" + name, par).find("b").click();
        }

        // 创建一个新的iframe
        var iframe = document.createElement('iframe');
        var liHtml = "<li class='iframe" + name + "'>" + text + "<b>x</b></li>"
        iframe.className = "iframe" + name;
        iframe.src = url;
        iframe.width = bodyWidth + "px";
        iframe.height = bodyHeight + "px";
        iframe.frameborder = "0";
        if (id) {
            iframe.id = id;
        }
        $(".main-content", par).append(iframe);
        $("iframe.iframe" + name, par).show();
        $(".tab-content", par).append(liHtml);
        $(".tab-content li.iframe" + name, par).addClass("active");
    }

    /**
     * 关闭Tab页面，并触发刷新按钮点击事件
     * @param sourceFrame
     */
    window.closeTab = function (sourceFrame) {

        var originFrameId = sourceFrame.id;
        var tabClass = sourceFrame.className;


        // 刷新原Tab

        $("iframe.iframe" + originFrameId, parent.document).show();
        $($("iframe.iframe" + originFrameId, parent.document)[0].contentWindow.document).find(".refresh-btn").click();
        $(".tab-content li.iframe" + originFrameId, parent.document).addClass("active");
        $(".item-ul li.iframe" + originFrameId, parent.document).addClass("active");

        // 关闭Tab
        $(".tab-content li." + tabClass, parent.document).remove();

        $("iframe." + tabClass, parent.document).remove();


    }

    function handlerTabClose(event) {
        event.stopPropagation();
        var target = $(event.currentTarget);
        var className = target.parent().attr("class").split(" ")[0];
        target.parent().remove();
        var next = $(".main-content").find("iframe." + className).next();
        var prev = $(".main-content").find("iframe." + className).prev();
        if (target.parent().hasClass("active")) {
            if (next.length > 0) {
                var nclass = next.attr("class");
                next.show();
                $(".tab-content li").removeClass("active");
                $(".tab-content li." + nclass).addClass("active");
                $(".item-ul li").removeClass("active");
                $(".item-ul").find("li." + nclass).addClass("active");
            } else if (prev.length > 0) {
                var pclass = prev.attr("class");
                prev.show();
                $(".tab-content li").removeClass("active");
                $(".tab-content li." + pclass).addClass("active");
                $(".item-ul li").removeClass("active");
                $(".item-ul").find("li." + pclass).addClass("active");
            } else {
                $("#content").show();
            }
        }
        $(".main-content").find("iframe." + className).remove();

    }

    getSize();

    $(window).on("resize", function () {
        getSize();
    });

    $(".alert-view .c-btn").click(function () {
        $(".alert-view").hide();
    });

    $("body").on("click", ".item-ul li", function (event) {
        addTab(event);
    });

    $("body").on("click", ".tab-content li", function (event) {
        handlerTabClick(event);
    });

    $("body").on("click", ".tab-content li b", function (event) {
        handlerTabClose(event);
    })

    $(window).on("changeMenu", function () {
        getMenu();
    });


})