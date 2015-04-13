Ext.onReady(function() {
	Ext.MessageBox.minWidth = 280;
	Ext.MessageBox.maxWidth = 300;
	Ext.QuickTips.init();

	// 错误信息
	var error = JustIn.util.getUrlParam('login_error');
	JustIn.messager.msg('Welcome', '欢迎登录高阳会生活运营管理平台！', -1, 'color:green;');
	if (error) {

		if (error == 'code_error') {
			JustIn.messager.msg('错误', '验证码输入错误！', 3, 'color:red;');
		}
		if (error == 'user_psw_error') {
			JustIn.messager.msg('错误', '无效的用户名或密码，请重试！', 3, 'color:red;');
		}
		if (error == 'too_many_user_error') {
			JustIn.messager.msg('错误', '多处使用同一用户名登录,请等候！', 3, 'color:red;');
		}
		if (error == 'session_expired') {
			JustIn.messager.msg('会话失效', '连接超时或该用户在其他地点登录！', 3,
					'color:red;');
		}
	}
	 
	var loginForm = new Ext.FormPanel({
		el : 'login-tabs',
		id : 'loginForm',
		name : 'loginForm',
		autoTabs : true,
		activeTab : 0,
		deferredRender : false,
		height : 150,
		border : false,
		onSubmit : Ext.emptyFn,
		submit : function() {
			loginForm.getForm().getEl().dom.action = contextPath + '/j_spring_security_check';
			loginForm.getForm().getEl().dom.submit();
		},
		items : {
			xtype : 'tabpanel',
			activeTab : 0,
			border : false,
			defaults : {
				autoHeight : true,
				bodyStyle : 'padding:10px'
			},
			items : [{
				title : '身份认证',
				contentEl : 'loginInfo',
				layout : 'form',
				defaults : {
					width : 230
				},
				items : [{
					xtype : 'textfield',
					cls : 'user',
					fieldLabel : '登录名',
					name : 'j_username',
					id : 'j_username_id',
					style : 'font-size: 15px; border-width: 2px;height: 25px;',
					allowBlank : false,
					blankText : '登录名不能为空',
					maxLength : 32,
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '登录名应由数字、26个英文字母以及中文汉字组成'
				}, {
					xtype : 'textfield',
					cls : 'key',
					fieldLabel : '密码',
					name : 'j_password',
					id : 'j_password_id',
					style : 'font-size: 15px; border-width: 2px;height: 25px;',
					inputType : 'password',
					allowBlank : false,
					blankText : '密码不能为空',
					maxLength : 32,
					regex : /^[a-zA-Z0-9\u4E00-\u9FA5]*$/,
					regexText : '密码应由数字、26个英文字母以及中文汉字组成'
				}, {
					xtype : 'textfield',
					cls : 'key',
					fieldLabel : '验证码',
					name : 'randCode',
					id : 'randCode',
					regex : /^[0-9A-Za-z]{4}$/,
					regexText : '验证码为4位,可包含数字、英文字母',
					width : 85,
					style : 'font-size: 15px; border-width: 2px;height: 25px;',
					allowBlank : false,
					blankText : '验证码不能为空'
				}, 
					{
					xtype : 'textfield',
					fieldLabel : '',
					name : 'errorMsg',
					id : 'errorMsg',
					width : 85,
					style : 'font-size: 12px; color:red; border-width: 0px;height: 20px;',
					readOnly : true
				}
//				, {	
//					xtype:'panel',
//					border:false,
//					id:'csId',
//					html : '<a href="#" onclick="showRegForm()">用户注册</a>&nbsp;&nbsp;<a href="#" onclick="showRetrieveForm()">用户找回密码</a>'
//				}
				]

			}, {
				title : '技术版本更新信息',
				layout : '',
				html : 'V1.0 <br><br>1、项目Web层采用SpringMvc+AJAX技术实现<br>2、页面表现采用EXT<br>3、数据层采用Hibernate<br>4、构架采用Spring模式',
				defaults : {
					width : 230
				}
			}, {
				title : '关于',
				layout : '',
				html : '<b>高阳会生活运营管理平台</b><br> <br> 版权所有 &copy 2011 高阳科技有限公司  <br> <br>访问网站:<a href="http://www.vlives.com.cn" target="_blank" title="点此访问公司主页">http://www.vlives.com.cn</a>',
				defaults : {
					width : 230
				}
			}]
		}
	});
	
	var win = new Ext.Window({
				el : 'login-win',
				width : 460,
				height : 280,
				closeAction : 'hide',
				plain : true,
				modal : false,
				collapsible : false,
				maximizable : false,
				draggable : false,
				closable : false,
				resizable : false,
				animateTarget : document.body,
				items : [loginForm],
				buttons : [	
				           {
							id:'login',
							text : '登录',
							handler : function() {
				        	   var cp = new Ext.state.CookieProvider();
				        	   Ext.state.Manager.setProvider(cp);
				        	   cp.set("j_username_id", Ext.get('j_username_id').dom.value);
				        	   cp.set("j_password_id", Ext.get('j_password_id').dom.value);
								if (loginForm.getForm().isValid()) {
									var code = Ext.getCmp('randCode').getValue();
									Ext.Ajax.request({
										url : contextPath + '/view/security/jsp/checkCode.jsp?code='+code,
										success : function(response){
											var flag = Ext.decode(response.responseText);
											if(flag){
												formSubmit();
											}else{
												showError();
											}
										}
									});								
								}
							}

						}, {
							text : '重置',
							handler : function() {
								loginForm.form.reset();
							}
						}]

			}

	);
	
	var formSubmit = function(){
		loginForm.getForm().submit({
			waitTitle : '提示',
			method : 'post',
			waitMsg : '正在验证您的身份,请稍候.....',
			success : onLoginSuccess,
			failure : onFailure
		});	
	};
	
	var showError = function(){
		Ext.getCmp('errorMsg').setValue('   验证码错误');
		var randImg = document.getElementById('randCodeImg');
		randImg.src = contextPath+'/view/security/jsp/randCodeImg.jsp?random='+Math.random();
	};
	
	/* 在用户校验失败的时候判断是否是服务器连接失败 */
	var onFailure = function(form, action) {
		if (action.failureType == 'server') {
			var obj = Ext.util.JSON.decode(action.response.responseText);
			Ext.Msg.alert('用户登录', '登录失败' + obj);
		} else {
			Ext.Msg.alert('用户登录', '登录失败');
		}
	};

	var onLoginSuccess = function(form, action) {
		win.hide();
	};
	win.show();
	
	var randCodeFiled = Ext.getDom('randCode');
	var codeContainer = Ext.get(randCodeFiled.parentNode);
	var onclickStr = "var randImg = document.getElementById('randCodeImg');randImg.src = '"+contextPath+"/view/security/jsp/randCodeImg.jsp?random='+Math.random(); ";
	codeContainer.createChild([{
				tag : 'span',
				html : '&nbsp;&nbsp;'
			},{
				tag : 'img',
				id : 'randCodeImg',
				style : 'Height:20px;Width:80px;',
				src : contextPath + '/view/security/jsp/randCodeImg.jsp',
				align : 'absbottom'
			}, {
				tag : 'span',
				html : '&nbsp;&nbsp;'
			}, {
				tag : 'a',
				onclick : onclickStr,
				href : '#',
				html : '换一张'
			}

	]);
	
	Ext.getCmp('randCode').on('valid', function(self, event) {
		Ext.getCmp('errorMsg').setValue('');
		Ext.getCmp('login').focus();						 
	});

	document.getElementById('banner').style.display = '';// 显示banner
});

//	function showRegForm() {
//		Ext.QuickTips.init();
//		var customerRegisterForm = new Ext.ux.CustomerRegisterForm({		
//		});	
//		customerRegisterForm.show();	
//	}
//	
//	function showRetrieveForm() {
//		Ext.QuickTips.init();
//		var retrievePasswordForm = new Ext.ux.RetrievePasswordForm({	
//		});	
//		retrievePasswordForm.show();	
//	}
