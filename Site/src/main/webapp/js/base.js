//对象实例化，如果已经存在一个对象，则获取，否则新建一个对象
var com = com? com : {};
com.vlives = com.vlives? com.vlives : {};
com.vlives.base = com.vlives.base? com.vlives.base : {};

/*** ------------------------------ Observer pattern ------------------------------ ***/

//com.vlives.base.Observer = new Class(
//{
//	initialize: function() {
//		this.fns = [];
//	},
//
//	subscribe: function(fn) {
//		this.fns.push(fn);
//	},
//	
//	unsubscribe: function(fn) {
//		this.fns = this.fns.filter(
//			function (f) {
//				if (f != fn) {
//					return f;
//				}
//			}
//		);
//	},
//	
//	fire : function(e, obj) {
//        var scope = obj || window;
//        this.fns.forEach(
//            function(el) {
//                el.call(scope, e);
//            }
//        );
//    }
//})

/*** ------------------------------ Cookie工具类 ------------------------------ ***/
com.vlives.base.isLogin = function() {
	if (com.vlives.base.getCookie("auto_login") != "") {
		return true;
	}
	
	if (com.vlives.base.getCookie("user_principal") != "") {
		return true;
	}
	
	return false;
}

com.vlives.base.getCookie = function(c_name) {
	var i,x,y ;
	var ARRcookies = document.cookie.split(";");
	for (i = 0; i < ARRcookies.length; i++) {
		x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
		y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
		x = x.replace(/^\s+|\s+$/g,"");
		if (x == c_name) {
			return unescape(y);
	    }
	}
	
	return "";
}

/*** ------------------------------ DOM工具类 ------------------------------ ***/

// 限制输入字符，区分中文按两个字符算
// 注：此函数在limit.js文件导入后才起作用
/*
 * Input:输入框的ID值 show:显示剩余数字的ID值，需要有value属性的DOM limitNum:限制的字符数
 */
com.vlives.base.limitInput = function(Input, show, limitNum) {
	var lim = new limit();
	lim.txtNote = $(Input);
	lim.txtLimit = $(show);
	lim.limitCount = limitNum;
	lim.isbyte = true;
	lim.init();
}

// 删除DIV 注：此处是删除DIV,而不是隐藏某个DIV
com.vlives.base.deleteDiv = function(div) {
	var parent = $(div);
	if (parent)
		parent.destroy();
}
// 增加无结果的DIV

com.vlives.base.addNoResultDiv = function(delayDIV,messText) {
	var parent = $(delayDIV);
	var div=new Element('li',{
		'html':messText
	});
	div.setStyle('height',"50px");
	div.setStyle('background','none');
	parent.adopt(div);
}
// 判断一个select中的某个值被选中
/*
 * seldiv:选择器的父ID selval:检测的被选中的值
 */
com.vlives.base.selValue = function(seldiv, selval) {
	var sel = $(seldiv);
	var selchilds = sel.getChildren();
	selchilds.each(function(item, index) {
		if (item.value == selval) {
			item.set('selected', 'selected');
		}
	});
}

// 删除父元素下的所有元素
// 注：此处是删除
/*
 * parent:父元素ID
 */
com.vlives.base.delAllElement = function(parentDIV) {
	var parent=$(parentDIV);
	parent.empty();
}

// tab切换
com.vlives.base.switchTab = function(i,now,old,tabname) {
	var tabs = $(tabname).getElementsByTagName("li");
	for ( var j = 0; j < tabs.length; j++) {
		if (j == i) {
			// tabs[j].addClass("now"); //IE下不行
			tabs[j].className = now;
		} else {
			tabs[j].className = old;
			// tabs[j].removeClass("now");
		}
	}
}

// 显示某个DIV
com.vlives.base.show = function(elementId) {
	$(elementId).style.display = "block";
}

// 隐藏某个DIV
com.vlives.base.hide = function(elementId) {
	$(elementId).style.display = "none";
}

// 弹出指定地址的窗口
com.vlives.base.PopupWindow = function(URLStr, left, top, width, height, newWin, scrollbars) {
	var popupWin;

	if (typeof (newWin) == "undefined")
        newWin = false;

    if (typeof (left) == "undefined")
        left = 100;

    if (typeof (top) == "undefined")
        top = 0;

    if (typeof (width) == "undefined")
        width = 800;

    if (typeof (height) == "undefined")
        height = 760;

    if (newWin) {
        window.open(URLStr, '', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=' + scrollbars + ',resizable=yes,copyhistory=yes,width=' + width + ',height=' + height + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY=' + top + '');
        return;
    }

    if (typeof (scrollbars) == "undefined") {
        scrollbars = 0;
    }

    if (popupWin) {
        if (!popupWin.closed) popupWin.close();
    }
    popupWin = window.open(URLStr, 'popUpWin', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=' + scrollbars + ',resizable=yes,copyhistory=yes,width=' + width + ',height=' + height + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY=' + top + '');
    popupWin.focus();
}

/*** ------------------------------ 数字工具类 ------------------------------ ***/
//判断输入是否为数字
com.vlives.base.isNumber = function(input) {
	if (/^[0-9]*$/.test(input)) {
		return true;
	} else {
		return false;
	}
}

/*** ------------------------------ 字符串工具类 ------------------------------ ***/

//产生任意位的随机字符串
com.vlives.base.RndNum = function(num){
	var rnd = "";
	for ( var i = 0; i < num; i++)
		rnd += Math.floor(Math.random() * 10);
	return rnd;
}

//判断是否是中文字符
/*
 * char:要判断的字符
 */
com.vlives.base.isChinese = function(char) {
	var latinChar = /[u00-uFF]/;
	return !latinChar.test(char);
}

//获得字符串长度，其中中文字符长度为2
com.vlives.base.getStringLength = function(input) {
	var length = 0;
	for (i = 0; i < input.length; i++) {
		if (isChinese(input.charAt(i)) == true)
			length = length + 2;
		else
			length = length + 1;
	}
	
	return length;
}

//截取一段字符，需要判断中英文字符
/*
 * input:要截取的字符串 length:要截取的长度值
 */
com.vlives.base.truncateString = function(input, length) {
	var inputLength = com.vlives.base.getStringLength(input);
	
	// 截取长度
	var ret = "";
	if (inputLength > length) {
		var pos = 0;
		var i = 0;
		while (pos < length) {
			if (isChinese(input.charAt(i)) == true) {
				ret += input.charAt(i);
				pos = pos + 2;
			} else {
				ret += input.charAt(i);
				pos = pos + 1;
			}
			
			i++;
		}
		// append("..")
		return ret + "..";
	} else {
		return input;
	}
}

//获取字符串中的一段字符
com.vlives.base.substring = function (src, begin, end) {
	if (src.indexOf(begin)<0 || src.indexOf(end)<0)
		return false;
	var beginPosition = src.indexOf(begin) + begin.length;
	var endPosition = src.indexOf(end);
	var ret = src.substring(beginPosition, endPosition);
	return ret;
}

/*** ------------------------------ 邮件，手机号码等工具类 ------------------------------ ***/

//检查手机号码是否有效
com.vlives.base.isMobile = function(value) {
	if (/^13\d{9}$/.test(value) || (/^15[0-35-9]\d{8}$/.test(value)) || (/^18[05-9]\d{8}$/.test(value))) {
		return true;
	} else {
		return false;
	}
}

// 屏蔽中间4位手机号
com.vlives.base.makeMobile = function(value) {
	var str="";
	if (!isMobile(value))
		{
		return value;
		}
	// str=value.substr(0,4)+'****'+value.substr(-3,3); //IE不行
	str = value.substr(0,4) + '****' + value.substr(8,3);
	return str;
}

// 检查邮件地址是否正确
com.vlives.base.checkEmail = function(email){
    var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if (!emailRegExp.test(email)||email.indexOf('.')==-1){
        return false;
    }else{
        return true;
    }
}

/*** ------------------------------ 会生活业务工具类 ------------------------------ ***/

//获得点评等级对应的图标
com.vlives.base.getLevelIcon = function(grade) {
	var html = '';
	
	var level = (grade * 2).toFixed(0);
	
	for(var i = 1; i <= 5; i++) {
		if (i * 2 <= level) {
			html += '<img src="/images/icon/xxd.png" />';
		} else if ((i * 2 - 1) <= level && level < i * 2) {
			html += '<img src="/images/icon/xxd2.png" />';
		} else {
			html += '<img src="/images/icon/xxd1.png" />';
		}
	}
	
	return html;
}

/*** ------------------------------ 其他工具类 ------------------------------ ***/

//阻止event冒泡
com.vlives.base.stopPropagation = function(e) {
	if (!e)
		e = window.event;
	if (window.event) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}
}

// Ajax 请求异常处理
com.vlives.base.ajaxFailure = function() {
	new POP.msgBox("服务器开小差了，请稍后重试。");
}