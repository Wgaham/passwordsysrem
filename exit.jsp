<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出成功</title>
</head>
<body>
	<center>
		退出成功，3秒后跳转登录界面
		<%
		session.invalidate();
		response.setHeader("Refresh", "1;url=login.jsp");
	%>
	</center>
</body>
</html>