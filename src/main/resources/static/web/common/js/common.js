$(function() {
	var token = localStorage.getItem("token");
	var link = location.href;

	window.dataTableObj = {
		"processing": false,
	    "serverSide": true,
	    "paging" : true,
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
	}

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
			"width" : bodyWidth + "px",
			"height" : bodyHeight + "px"
		})
	}

	function getList(list) {
		var html = "";
			for(var i = 0; i < list.length; i ++){
				var name = list[i].text;
		         html += '<li><a><img src="../../images/cai.png" class="cai-img" />'
		              + name
					  + '<img src="../../images/arrow.png" class="arrow" /></a>'
		              + '<ul class="item-ul">';
		                 for(var j = 0; j < list[i].children.length; j ++){
		                 	var url = list[i].children[j].url;
		                 	var text = list[i].children[j].text;
		                 	var id = list[i].children[j].id;
				                html += '<li class="iframe'+id+'" data-text="'+text+'" data-link="'+url+'" data-id="'+ id +'"><span class="li-icon">-</span>'+text+'</li>';
		                 }
		             html += '</ul></li>';
		      }
		$(".menu-ul",parent.document).empty().html(html);
	}

	function getMenu() {
		utils.getPOST("/resource/menus",{},function(res) {
			getList(res);	
		});
	}

	function handlerTab(event) {
		var target = $(event.currentTarget);
		var className =	target.attr("class").split(" ")[0];
			target.siblings().removeClass("active");
			target.addClass("active");
			$(".main-content iframe").hide();
			$(".main-content").find("iframe." + className).show();
			$(".item-ul li").removeClass("active");
			$(".item-ul").find("li." + className).addClass("active");
	}

	function handlerPage(event) {
		console.log(event);
			event.stopPropagation();
			var target = $(event.currentTarget);
			var url = target.data("link");
			var name = target.data("id");
			var dom = $("iframe.iframe" + name);
			var bodyWidth = $(window).width() - 220;
			var bodyHeight = $(window).height() - 102;
			var text = target.data("text");
				$(".item-ul li").removeClass("active");
				target.addClass("active");
				$("iframe").hide();
				$(".tab-content li").removeClass("active");
				if (dom.length > 0) {
					$(".tab-content li.iframe" + name).addClass("active");
	                  dom.show();
				}else{
					var iframe = document.createElement('iframe');
					var liHtml = "<li class='iframe" + name + "'>" + text + "<b>x</b></li>"
						iframe.className = "iframe" + name;
						iframe.src = url;
						iframe.width = bodyWidth + "px";
						iframe.height = bodyHeight + "px";
						iframe.frameborder = "0";
						$(".main-content").append(iframe);
						$("iframe.iframe" + name).show();
						$(".tab-content").append(liHtml);
						$(".tab-content li.iframe" + name).addClass("active");
				}
		}

		function handlerTabDel(event) {
			event.stopPropagation();
			var target = $(event.currentTarget);
			var className =	target.parent().attr("class").split(" ")[0];
				target.parent().remove();
			var next = $(".main-content").find("iframe." + className).next();
			var prev = $(".main-content").find("iframe." + className).prev();
			if(target.parent().hasClass("active")) {
				if(next.length > 0) {
					var nclass = next.attr("class");
	                next.show();
	                $(".tab-content li").removeClass("active");
	                $(".tab-content li." + nclass).addClass("active");  
					$(".item-ul li").removeClass("active");
					$(".item-ul").find("li." + nclass).addClass("active");
				}else if(prev.length > 0) {
					var pclass = prev.attr("class");
					prev.show();
					$(".tab-content li").removeClass("active");
					$(".tab-content li." + pclass).addClass("active");  
					$(".item-ul li").removeClass("active");
					$(".item-ul").find("li." + pclass).addClass("active");
				}else{
					$("#content").show();
				}
			}
				$(".main-content").find("iframe." + className).remove();
				
		}

	getSize();

	$(window).on("resize",function() {
		getSize();
	});

	$(".alert-view .c-btn").click(function() {
		$(".alert-view").hide();
	});

	$("body").on("click",".item-ul li",function(event) {
		handlerPage(event);
	});

	$("body").on("click",".tab-content li",function(event) {
		handlerTab(event);
	});

	$("body").on("click",".tab-content li b",function(event) {
		handlerTabDel(event);
	})

	$(window).on("changeMenu",function() {
		getMenu();
	});


})