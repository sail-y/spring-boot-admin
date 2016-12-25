$(function() {
	var token = localStorage.getItem("token");
	var link = location.href;
	window.resourceData = localStorage.getItem("resourceList");
	if (resourceData) {
		resourceData = $.parseJSON(resourceData);
	}

	function getSize() {

		var bodyWidth = $(window).width() - 220;
		var bodyHeight = $(window).height() - 102;

		$("#content").css({
			"width" : bodyWidth + "px",
			"height" : bodyHeight + "px"
		})
	}

	getSize();


	if (!token && link.indexOf("login") < 0) {
	    window.location.href = "../login/login.html";
	}

	$(window).on("resize",function() {
		getSize();
	});

	$(".alert-view .c-btn").click(function() {
		$(".alert-view").hide();
	});
})