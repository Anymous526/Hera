<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"  /> 
<link rel="stylesheet" type="text/css" href="${ctx}/css/welcome.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/extjs/ext-3.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/adapter/ext/ext-base-debug.js"></script>
<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/extjs/ext-3.2.1/ext-lang-zh_CN.js"></script>	
</head>


<body class="mainframe">
	<div class="welcome"><img src="${ctx}/images/welcome/icon/menu23.gif" align="absmiddle"> 当前版本为：[ <span>高阳会生活运营管理平台</span> ]欢迎您使用！</div>
	<div class="usercard">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本系统功能包括商户管理、营销活动管理、会员管理以及系统设置管理等。<br>
	</div>
	<div class="safe"> 
		<table>
			<tr>
				<th><img src="${ctx}/images/welcome/icon/menu25.gif" align="absmiddle"> 系统使用安全注意事项</th>
			</tr>
			<tr>
				<td>
					<span>→ 使用Windows XP/Vista的用户，请打开Windows XP/Vista自带的防火墙。</span>
					<span>→ 使用Windows XP/Vista的用户，请关闭远程功能。</span> 
					<span>→ 定期下载安装最新的操作系统和浏览器安全程序或补丁。 </span>
					<span>→ 安装反病毒软件和防火墙软件，并及时升级更新。 </span>
					<span>→ 长时间无人操作电脑时，请退出后台管理系统并锁定您的计算机。 </span>
					<span>→ 请保证您的密码安全，不要告诉他人，并定期更改您的登录密码。 </span>			
				</td>
			</tr>			
		</table>
	</div>
	<br>
	<div>
		<ul class="maintab">
			<li class="ADmenuOn"><img src="${ctx}/images/welcome/icon/menu26.gif" align="absmiddle"> 其它信息</li>							
		</ul>
	</div>
	<div class="maintable">
	<div class="viewtable">
		<table>
			<thead>
				<tr>
					<th>系统运行信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>				
					<td>Java(TM) 2 Runtime Environment, Standard Edition 1.6</td>
				</tr>
				<tr>			
					<td>Java HotSpot(TM) Client VM 1.6</td>
				</tr>	
				<tr>			
					<td>Sun Microsystems Inc.</td>
				</tr>	
				<tr>			
					<td>Java Virtual Machine Specification</td>
				</tr>																
			</tbody>		
		</table>
	</div>
	</div>
<script type="text/javascript">
	Ext.onReady(function() {
		Ext.Ajax.request({
			url: '${ctx}/sq.do?method=readCount',
			success: function(response){
				var data = Ext.decode(response.responseText);
				if( data.success ){
					count = data.smsCount;
					if( count < data.alertQuantity ) {
						Ext.Msg.show({
							title:'提醒',
							msg: '系统剩余短信数量为' + count + '条，已低于系统告警下限',
							buttons: Ext.Msg.OK,
							icon: Ext.MessageBox.WARNING
						});
					}
				}
			}
		});
	});
</script>
</body>
</html>