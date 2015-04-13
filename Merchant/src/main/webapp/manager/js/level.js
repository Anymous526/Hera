var com = com ? com : {};
com.level = com.level ? com.level : {};

com.level.modify = function(id){
	window.location.href = "/manager/member/level/"+id+"/modify.htm";
}
 
 
function presentPoint(el){
	if(el.value<0){
		el.errors.push("必须大于等于0");
		return false;
	}
	if($chk(el.value)){
		if(el.value>=100000){
			el.errors.push("不能大于100000");
			return false;
		}
	}
	return true;
}