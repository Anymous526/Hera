var com = com ? com : {};
com.couponreport = com.couponreport ? com.couponreport : {};

com.couponreport.export_excel = function(){
	var startDate = document.getElementById("startCreateDate").value;
	var endDate = document.getElementById("endCreateDate").value;
	if( !$chk(startDate)|| !$chk(endDate)){
		return ;
	}
	window.location.href = "/manager/report/coupon/exp_excel.htm?startCreateDate="+startDate+"&&endCreateDate="+endDate;
}


	
	
