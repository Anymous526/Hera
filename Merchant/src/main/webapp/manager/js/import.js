//打开DIV层   
	function open() {
		var root = new Element("form",{"id":"uploadForm","action":"/manager/member/info/merchant_import_member.htm","method":"post"});
		root.set('enctype','multipart/form-data');
		root.setAttribute('encoding','multipart/form-data');
		var div = new Element("div",{"class":"tckk"}).inject(root);
		var div1 = new Element("div",{"class":"tckk_con"}).inject(div);
		var div2 = new Element("div",{"class":"Credit_title","style":"font-weight:bold; font-size:14px;  _background:#f2f6f9;  _padding-left:10px; line-height:27px; _border-bottom:1px solid #ccc;"}).inject(div1);
		var closebtn = new Element("img",{"class":"closeimg","src":"/manager/image/btn/close.jpg"}).inject(div2);
		var title = new Element("span",{"html":"会员上传"}).inject(div2);
		var div3 = new Element("div",{"class":"S_Reminded"}).inject(div1);
		var ul = new Element("ul").inject(div3);
		var li = new Element("li",{"style":"background:none; margin-top:10px; *margin-top:-15px; _margin-top:10px;"}).inject(ul);
		var fileupload = new Element("input",{"class":"hmk","type":"file","name":"importFile","id":"fileupload"}).inject(li);
		var submit = new Element("input",{"type":"image","style":"vertical-align:middle","src":"/manager/image/btn/sc.jpg"}).inject(li);
		submit.set("disabled","disabled");
		var pop = new POP.showBox("modify",root,300,150);
		var uploadForm = document.getElementById("uploadForm");
		function validateFileExt(el) {
			var path =el.value.split(".")[1];
			if(el.value == null && el.value== "") {
				POP.alert("必须上传文件");
				return false;
			}
			if(path.indexOf("xls") == -1) {
				POP.alert("文件格式不正确");
				return false;
			}
			return true;
		} 

		$("fileupload").addEvent("change",function() {
			var flag = validateFileExt(this);
			if(flag) {
				submit.erase("disabled");
			}
		});
		
		
		closebtn.addEvent("click",function(){
			pop.close();
		});
		
		
	}