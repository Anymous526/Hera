var com = com ? com : {};
com.report = com.report ? com.report : {};

	com.report.select = function (obj){
		window.location.href="/manager/report/saleploy/find.htm?merchantId="+obj.value;
	}

	com.report.exp_list = function(){
		var myform = document.getElementById("myform");
		var oldAction = myform.action;
		myform.action = "/manager/report/saleploy/export.htm";
		myform.submit();
		myform.action = oldAction;
	}
	
	com.report.exp = function(){
		var merchantId = $("merchantId");
		if(!$chk(merchantId)) {
			window.location.href = "/manager/report/saleploy/export.htm";
			return;
		}
		window.location.href = "/manager/report/saleploy/export.htm?merchantId="+merchantId.get('value');
	}