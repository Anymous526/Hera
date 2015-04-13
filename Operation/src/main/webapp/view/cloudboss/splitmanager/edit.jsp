<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/commons/meta.jsp"%>
<html>
	<head>
		
		<title>菜单编辑</title>
		<link rel="stylesheet" type="text/css"
			href="scripts/resources/css/ext-all.css">
		
		<script type="text/javascript">  
		
			var contextPath='${ctx}'
		
		
        function checkForm(form){   
            if(form.parentId.value == "" ){   
                Ext.Msg.alert("错误提示","表单信息不健全！");   
                return false;   
            }   
            if(form.name.value == ""){   
                Ext.Msg.alert("错误提示","行业名称不能为空！");   
                return false;   
            }   
          
        }   
    </script>
    
  
		<script type='text/javascript' src='${ctx}/extjs/plugins/Ext.ux.form.js'></script>
		
	</head>
	<body style="background-color: white">
		<br />
		<br />
		<form action="ind.do?method=createIndustry" method="post"
			onsubmit="return checkForm(this)">
		
			<input type="hidden" name="parentId" value="${obj.parentId}" />
			
			<table align="center">
				<tr>
					<td width="80">
						行业名称：
					</td>
					<td>
						<input type="text" name="name" maxlength="20" value="${obj.name}" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<br />
						<input type="submit" name="submit" value="保存" />
						&nbsp;&nbsp;
						<input type="reset" name="reset" value="重置" />
						&nbsp;&nbsp;
						<input type="button" name="button" value="取消"
							onclick="window.parent.FormEditWin.close();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
