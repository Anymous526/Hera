//添加清除样式DIV
function addClearDiv(divname) {
	var newDiv = new Element('div');
	newDiv.setStyle('clear', 'both');
	$(divname).adopt(newDiv);
}
// 添加缓存数据样式DIV
function addDelayDiv(divname, delayDIV) {
	var parent = $(divname);
	if (!$(delayDIV)){
		var newDiv = new Element('div', {
			'class' : 'alern',
			'html' : '<img src="/images/bg/page2_spinner.gif" />数据加载中......'
		});
		newDiv.id = delayDIV;
		parent.adopt(newDiv);
	}
	
}

// 添加错误信息提示DIV
function addErrorDiv(divname, classname, delayDIV, html) {
	var parent = $(divname);
	if (!$(delayDIV)) {
		var newDiv = new Element('div', {
			'class' : classname,
			'html' : html
		});
		newDiv.id = delayDIV;
		parent.adopt(newDiv);
	} else {
		$(delayDIV).style.display = "";
	}
}

// 判断一个select中的某个值被选中
function selValue(seldiv, selval) {
	var sel = $(seldiv);
	var selchilds = sel.getChildren();
	selchilds.each(function(item, index) {
		if (item.value == selval) {
			item.set('selected', 'selected');
		}
	});
}

// 删除父元素下的所有元素
function delAllElement(parent) {
	// var childs=parent.getChilden();
	// if (childs.length>0)
	parent.empty();
}

// 关闭DIV
function closeDIV(DIVid) {
	$(DIVid).dispose();
}

function isChinese(char) {
	var latinChar = /[u00-uFF]/;
	return !latinChar.test(char);
}

// 截取一段字符，需要判断中英文字符
function truncateString(content, length) {
	//得到字符长度
	var charLength = 0;
	for (i = 0; i < content.length; i++) {
		if (isChinese(content.charAt(i)) == true)
			charLength = charLength + 2;
		else
			charLength = charLength + 1;
	}
	
	//截取长度
	var ret = "";
	if (charLength > length) {
		var pos = 0;
		var i = 0;
		while (pos < length) {
			if (isChinese(content.charAt(i)) == true) {
				ret += content.charAt(i);
				pos = pos + 2;
			} else {
				ret += content.charAt(i);
				pos = pos + 1;
			}
			
			i++;
		}
		// append("..")
		return ret + "..";
	} else {
		return content;
	}
}

// 获取随机数字
function RndNum(n) {
	var rnd = "";
	for ( var i = 0; i < n; i++)
		rnd += Math.floor(Math.random() * 10);
	return rnd;
}
//限制输入字符，区分中文按两个字符算
/*
 * Input:输入框的ID值
 * show:显示剩余数字的ID值，需要有value属性的DOM
 * limitNum:限制的字符数
 */
function limitInput(Input, show, limitNum) {
	var lim = new limit();
	lim.txtNote = $(Input);
	lim.txtLimit = $(show);
	lim.limitCount = limitNum;
	lim.isbyte = true;
	lim.init();
}

//检查邮件地址是否正确
function checkEmail(email){
    var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if (!emailRegExp.test(email)||email.indexOf('.')==-1){
        return false;
    }else{
        return true;
    }
}

// 阻止event冒泡
function stopPropagation(e) {
	if (!e)
		e = window.event;

	if (window.event) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}

}