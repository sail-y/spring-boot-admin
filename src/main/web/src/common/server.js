
import api from 'vue-resource'
const host = location.protocol + "//" + location.host;


// ,{
// 	"headers" : {
// 		"Cookie" : mycookie
// 	}
// }
export default  {

	fetchData:function (path,params,callback) {
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
	},

	fetchGet:function (path,params,callback) {

		api.get(path,params).then(function(res){
			console.log(res);

		})
	}

	


}
