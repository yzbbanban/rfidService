var userId = 0;

function getUserInfo() {

	$.ajax({
		url: "http://106.15.188.62:9999/rfid" + "/user/getUserInfo.do",
//		url: "http://www.uuabb.cn/rfid" + "/user/getUserInfo.do",
		type: "get",
		dataType: "json",
		success: function(result) {
			if(result.code == 10000) {
				var data = result.result;
				console.log("----" + data);
				var tUser = $("#tUser");
				var tb = "";
				for(var i = 0; i < data.length; i++) {
					var id = data[i].id;
					var name = data[i].user_name;
					var email = data[i].email;
					var userType = data[i].user_type;
					if(userType == null) {
						userType = "";
					}
					var resultValue = id + "," + userType;
					console.log(id + "," + name + "," + email + ",|" + resultValue)
					tb += "<tr>" +
						"<th>" + id + "</th>" +
						"<th>" + name + "</th>" +
						"<th>" + email + "</th>" +
						"<th><button class='btn btn-primary' onclick='selectUserType(" + resultValue + ")'>更改类型</button></th>" +
						"</tr>"
					var $tb = $(tb);
					//绑定 id
					$tb.data("id", id);
				}
				tUser.html(tb);

			} else {
				alert(result.message);
			}
		},
		error: function() {
			alert("获取失败");
		}
	})
}

function selectUserType(id, bnew,bbinding, bin, bout, brecy) {
	userId = id;
	console.log("----> id: " + id);
	var res = bnew + ","+bbinding +","+ bin + "," + bout + "," + brecy;
	console.log("----> res: " + res);
	res = res.toString();
	$("#bucketNew").prop("checked", false)
	$("#bucketBingding").prop("checked", false)
	$("#bucketIn").prop("checked", false)
	$("#bucketOut").prop("checked", false)
	$("#bucketRecycle").prop("checked", false)
	if(res.length >= 4) {
		if(res.indexOf("-8") != -1) {
			$("#bucketNew").prop("checked", true)
		}
		if(res.indexOf("0") != -1) {
			$("#bucketBingding").prop("checked", true)
		}
		if(res.indexOf("1") != -1) {
			$("#bucketIn").prop("checked", true)
		}
		if(res.indexOf("2") != -1) {
			$("#bucketOut").prop("checked", true)
		}
		if(res.indexOf("3") != -1) {
			$("#bucketRecycle").prop("checked", true)
		}
	}
	$('#myModal').modal();

}

function updateUserInfo() {
	console.log("--ccc-->" + userId)
	var bucketNew=$("#bucketNew").is(':checked')
	var bucketBingding=$("#bucketBingding").is(':checked')
	var bucketIn=$("#bucketIn").is(':checked')
	var bucketOut=$("#bucketOut").is(':checked')
	var bucketRecycle=$("#bucketRecycle").is(':checked')
	var userType="";
	if(bucketNew){
		userType+="-8,";
	}
	if(bucketBingding){
		userType+="0,";
	}
	if(bucketIn){
		userType+="1,"
	}
	if(bucketOut){
		userType+="2,"
	}
	if(bucketRecycle){
		userType+="3,"
	}
	userType=userType.substr(0,userType.length-1);
	console.log("--userType-->" + userType)
	$.ajax({
		url: "http://106.15.188.62:9999/rfid" + "/user/updateUser.do",
//		url: "http://www.uuabb.cn/rfid" + "/user/updateUser.do",
		type: "post",
		data: {
			"id": userId,
			"user_type": userType
		},
		dataType: "json",
		success: function(result) {
			//alert(result.status);
			if(result.code = 10000) {
				alert("更新成功");
				getUserInfo();
			}
		},
		error: function() {
			alert("更新失败");
		}
	})
}