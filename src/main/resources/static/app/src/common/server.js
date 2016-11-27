

const host = location.protocol + "//" + location.host;

// ,{
// 	"headers" : {
// 		"Cookie" : mycookie
// 	}
// }
export default class Server {

	fetchData (path,params,callback) {
		var paramStr = "?";
		for(var key in params) {
			paramStr += (key + "=" + params[key] + "&");
		}
		fetch(path+paramStr)
			.then((response) => response.text())
			.then((responseText) => {
		    	var res = JSON.parse(responseText);
		    	callback(res);
	  	})
	  	.catch((error) => {
	    	console.warn(error);
	    	callback(error);
	  	});
	}

	fetchGet (path,params,callback) {
		var paramStr = "?";
		for(var key in params) {
			paramStr += (key + "=" + params[key] + "&");
		}
		fetch(path+paramStr,{
			method:"get"
		})
			.then((response) => response.text())
			.then((responseText) => {
		    	var res = JSON.parse(responseText);
		    	callback(res);
		  	})
	  	.catch((error) => {
	    	console.warn(error);
	    	callback(error);
	  	});
	}

	fetchPost (path,params,callback) {
		const postData = {};
		for(var item in params) {
			postData[item] = params[item];
		}
		fetch(path, {
		  method: 'POST',
		  headers: {
		    'Accept': 'application/json',
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify(postData)
		})
		.then((response) => response.json())
	    .then((responseJson) => {
	        alert(JSON.stringify(responseJson))
	      })
	    .catch((error) => {
	        console.error(error);
	    });
	}

	login (email,password,code,callback) {
		this.fetchGet(toolhost + "/user/publicLogin",{
	        "email": email,
	        "password": password,
	        "captcha": code
	    },callback)
	}

	


}
