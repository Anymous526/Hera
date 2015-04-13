<%@ page language="java" pageEncoding="UTF-8" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
		<%
			try {
				request.setCharacterEncoding("UTF-8");
				String fileName = request.getParameter("file");
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				String path = request.getSession().getServletContext().getRealPath("/")+"merchantFile"+File.separator+fileName;
				InputStream inputStream = new FileInputStream(path);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
				outputStream = null;
				out.clear();
				out = pageContext.pushBody();
			} catch (Exception e) {
				System.out.print(e);
			}
		%>
	</body>
</html>

