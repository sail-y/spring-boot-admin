var width = window.innerWidth;
var url = location.href;
var pathname = location.pathname;
	if (width > 960) {
		if (url.indexOf("home") > -1 ) {
			location.href = "../web/home/home.html";
		}else if (pathname == "/") {
			location.href = "./template/views/web/home/home.html";
		}
	}