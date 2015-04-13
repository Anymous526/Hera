/**
 * fomcheck 验证框架的扩展
 */
 
 /**
  * 用户登录时判断用户是否存在
  * @param {} el
  * @return {Boolean}
  */
 function validateLoginNameExist(el){
	var exist = com.user.Exist("mobileOrUserName",el.value)
    if (!exist) {
        el.errors.push("手机号或用户帐号不存在.");
        return false;
    } else {
        return true;
    }
}

 /**
  * 用户登录时判断用户是否存在
  * @param {} el
  * @return {Boolean}
  */
 function validateLoginNameExists(el){
 	var mobileOrUserNames= el.value.split(";");
 	if(mobileOrUserNames.length>10) {
 		 el.errors.push("群发用户不能操作10个");
	        return false;
 	}
 	var validate = true;
 	mobileOrUserNames.each(function(mobileOrUserName) {
 		var exist = com.user.Exist("mobileOrUserName",mobileOrUserName)
	    if (!exist) {
	        el.errors.push(mobileOrUserName+" 用户不存在.");
	        validate = false;
	        return;
	       
	    } 
 	});
 	return validate;
}


 /**
  * 检查用手机是否存在
  * @param {} el
  * @return {Boolean}
  */
 function validateMobileExist(el){
	var exist = com.user.Exist("mobile",el.value)
    if (exist) {
        el.errors.push("手机已存在.");
        return false;
    } else {
        return true;
    }
}

 /**
  * 检查用户名是否存在
  * @param {} el
  * @return {Boolean}
  */
 function validateUserNameExist(el){
	var exist = com.user.Exist("userName",el.value)
    if (exist) {
        el.errors.push("用户名已存在.");
        return false;
    } else {
        return true;
    }
}

 /**
  * 检查邮箱是否存在
  * @param {} el
  * @return {Boolean}
  */
 function validateMailExist(el){
	var exist = com.user.Exist("mail",el.value)
    if (exist) {
        el.errors.push("邮箱已存在.");
        return false;
    } else {
        return true;
    }
}

 /**
  *不能全部为数字检查
  * @param {} el
  * @return {Boolean}
  */
function validateNotAllDigit(el){
	var value = el.value;
	var vail = value.test("^[^0-9]+$");
	if(vail) {
		el.errors.push("不能全部为数字.");
        return false;
	}
	return true;
}

 /**
  *不能全部为字母检查
  * @param {} el
  * @return {Boolean}
  */
function validateNotAllAlpha(el){
	var value = el.value;
	var vail = value.test("^[^a-z]+$","i");
	if(vail) {
		el.errors.push("不能全部为字母.");
        return false;
	}
	return true
}
 /**
  *不能全部为字母和数字
  * @param {} el
  * @return {Boolean}
  */
function validateNotAllAlphaAndDigit(el){
	 var value = el.value;
	 var vail = value.test("^[a-z0-9]+$","i");
	 if(vail) {
		el.errors.push("不能全部为数字和字母.");
        return false;
	}
	return true;
}
/**
 * 验证字符长度(英文长度为1，中文长度为2)
 * @param {} el
 * @param {} minLen
 * @param {} maxLen
 * @return {Boolean}
 */
function validateChartLength(el,minLen,maxLen) {
	 var value = el.get("value").trim();
	 var len = 0;
	 
	 for (var i=0; i<value.length; i++) {  
       if (value.charCodeAt(i)>127 || value.charCodeAt(i)==94) {  
            len += 2;  
        } else {  
            len ++;  
        }  
    }
    if(len<minLen) {
    	el.errors.push("长度不能小于"+minLen+"个字符");
        return false;
    }
    if(len>maxLen) {
    	el.errors.push("长度不能大于"+maxLen+"个字符");
        return false;
    }
	return true;
}

var illegalWords = null;
/**
 * 短信非法字验证
 * @param {} el
 * @return {Boolean}
 */
validatorIllegalWord = function(el) {
	if(!$chk(illegalWords)) {
		new Request.JSON({
			method:"get",
			async:false,
			url:"/manager/illegal/findAll.htm",
			onComplete:function(json){
				illegalWords=json.illegalWords;
			}
		}).send();
	}
	var value = el.get("value");
	if(!$chk(illegalWords))
		return true;
	for(var i = 0;i<illegalWords.length;i++) {
		var illegalWord = illegalWords[i];
		if(value.indexOf(illegalWord.content)>=0) {
			el.errors.push("短信内容有非法字："+illegalWord.content);
        	return false;
		}
	}
	return true;
}
