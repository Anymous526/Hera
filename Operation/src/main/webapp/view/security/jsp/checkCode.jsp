<%@ page language="java" pageEncoding="UTF-8"%>

<% 	
	String code = request.getParameter("code");
	String trueRandCode = (String)session.getAttribute("trueRandCode");
	if(code == null ||trueRandCode == null || !trueRandCode.toLowerCase().equals(code.toLowerCase())){
		response.getWriter().write("false");
	}else{
		response.getWriter().write("true");
	}
%>	