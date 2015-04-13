var com = com ? com : {};
com.report = com.report ? com.report : {};

	com.report.selectToday = function (obj){
		window.location.href="/manager/report/memberinfo/today.htm?merchantId="+obj.value;
	}
	
	com.report.selectMonth = function (obj){
		window.location.href="/manager/report/memberinfo/month.htm?merchantId="+obj.value;
	}

	com.report.exp_list = function(){
		var myform = document.getElementById("myform");
		var oldAction = myform.action;
		myform.action = "/manager/report/memberinfo/list_export.htm";
		myform.submit();
		myform.action = oldAction;
	}
	
	com.report.exp_month = function(){
		var merchantId = $("merchantId");
		if(!$chk(merchantId)) {
			window.location.href = "/manager/report/memberinfo/month_export.htm";
			return;
		}
		window.location.href = "/manager/report/memberinfo/month_export.htm?merchantId="+merchantId.get('value');
	}
	
	com.report.exp_today = function(){
		var merchantId = $("merchantId");
		if(!$chk(merchantId)) {
			window.location.href = "/manager/report/memberinfo/today_export.htm";
			return;
		}
		window.location.href = "/manager/report/memberinfo/today_export.htm?merchantId="+merchantId.get('value');
	}