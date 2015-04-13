var com = com ? com : {};
com.coupon = com.coupon ? com.coupon : {};

com.coupon.showTitle = function(){
	var value = $("title").get("value").trim();
	if(value == ""){
		POP.alert("标题不能为空");
		return;
	}
	 
	 var len = 0;
	 for (var i=0; i<value.length; i++) {  
       if (value.charCodeAt(i)>127 || value.charCodeAt(i)==94) {  
            len += 2;  
        } else {  
            len ++;  
        }  
    }
	 if(len < 2){
			return;
		}
	 if(len > 14){
			return;
		}
	var parent = new Element("div",{"class":"POS01"});
	var div = new Element("div",{"class":"POS_btn01"}).inject(parent);
	var closebtn = new Element("img",{"src":"/manager/image/btn/close01.jpg"}).inject(div);
	var h1 = new Element("h1",{"html":"商户当前可用券"});
	var title = new Element("p",{"html":"1、"+$("title").get("value")});
	h1.inject(parent);
	title.inject(parent);
	var pop = new POP.showBox("modify",parent,300,350);

	closebtn.addEvent("click",function(){
		pop.close();
	});
}

com.coupon.showMobile = function(){
	if($("ployContent").get("value") == ""){
		POP.alert("内容不能为空");
		return;
	}
	if($("ployContent").get("value").length > 54){
		POP.alert("内容不能超过54个字");
		return;
	}
	var parent = new Element("div",{"class":"IPHONE"});
	var div = new Element("div",{"class":"POS_btn"}).inject(parent);
	var closebtn = new Element("img",{"src":"/manager/image/btn/close01.jpg"}).inject(div);
	var title = new Element("p",{"html":$("ployContent").get("value")+"<br />券号：12345678【会生活】"}).injectAfter(div);
	var pop = new POP.showBox("modify",parent,300,350);

	closebtn.addEvent("click",function(){
		pop.close();
	});
}

com.coupon.showWeb = function(){
	if($("ployContent").get("value") == ""){
		POP.alert("内容不能为空");
		return;
	}
	if($("ployContent").get("value").length > 54){
		POP.alert("内容不能超过54个字");
		return;
	}
	var parent = new Element("div",{"class":"Comb"});
	var div = new Element("div",{"class":"com_bb"}).inject(parent);
	var div1 = new Element("div",{"class":"B_close"}).inject(div);
	var div2 = new Element("div",{"class":"Quan","style":"padding-top:0;border:none"}).inject(div);
	var div3 = new Element("div",{"class":"Q_left"}).inject(div2);
	var div4 = new Element("div",{"class":"q_info"}).inject(div3);
	var div5 = new Element("div",{"class":"q_info_left"}).inject(div4);
	
	var h1 = new Element("h1").inject(div5);
	var h2 = new Element("h2").inject(div5);
	var h3 = new Element("h3").inject(div5);
	var img = new Element("img").inject(div4);
	
	var div6 = new Element("div",{"class":"Q_zt","style":"border:none"}).inject(div3);
//	var b = new Element("b",{"html":""}).inject(div6);
	var div7 = new Element("div",{"class":"Q_btn"}).inject(div3);
	var a = new Element("a",{"style":"margin-top:5px; margin-top:-24px","html":"领用"}).inject(div7);
	
	var div8 = new Element("div",{"class":"Q_right"}).inject(div2);
	
	var p = new Element("p",{"style":"padding-bottom:0"}).inject(div8);
	p.readOnly=true;
	if($('introduction').get("value").trim() == ""){
		p.set("html",$('ployContent').get("value"));
	} else {
		p.set("html",$('introduction').get("value"));
	}
	
	var textarea = new Element("textarea",{"html":p.get("html")});
	var modfiy = new Element("h1").inject(div8);
	var save = new Element("h1");
	var modfiy_a = new Element("a",{"href":"javascript:void(0);","html":"编辑"}).inject(modfiy);
	var save_a = new Element("a",{"href":"javascript:void(0);","html":"保存"}).inject(save);

	var div9 = new Element("div",{"style":"clear:both"}).inject(div2);
	var div10 = new Element("div",{"style":"clear:both"}).inject(div);

	var pop = new POP.showBox("modify",parent,600,250);

	h1.set("html",substring($("merchantName").get("value")));
	h2.set("html",$("title").get("value"));
	h3.set("html",$("validEndDate").get("text"));
	if($("merchantHead").get("value") == ""){
		img.set("src","/manager/image/buss/s_01.gif");
	}else{
		img.set("src",$("merchantHead").get("value"));
	}

	modfiy_a.addEvent("click",function(){
		p.hide();
		modfiy.hide();
		textarea.inject(div8);
		save.inject(div8);
	});
	
	save_a.addEvent("click",function(){
		if(textarea.get("value").length > 100){
			POP.alert("内容不能超过100个字");
			return;
		}
		$('introduction').set("value",textarea.get("value"));
		pop.close();
	});
	
	div1.addEvent("click",function(){
		pop.close();
	});
}

function substring(str) {
	
	if (str.length > 6)
		return str.substr(0,6)+"...";
	else 
		return str;
}

com.coupon.showPos = function(){
	com.coupon.showTitle();
}

