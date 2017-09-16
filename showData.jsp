<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="dataBean" class="wgaham.javabean.dataBean"
	scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看钥匙</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
</script>
</head>
<body>
	<%
		String userName = (String) session.getAttribute("userName");
	%>
	登录用户：<%=userName%><br>
	<a href="insertData.jsp">增加记录</a>
	<a href="deleteData.jsp">删除记录</a>
	<a href="updateData.jsp">修改记录</a> （注意！钥匙名称无法修改，请选择删除后重建）
	<br>
	<a href="updateUserPassword.jsp">修改用户密码</a>
	<a href="exit.jsp">退出系统</a>
	<center>
		<h3>钥匙列表</h3>
		<table border=1>
			<tr>
				<th>钥匙名称</th>
				<th>账号</th>
				<th>密码</th>
			</tr>
			<%
				String[][] record = dataBean.getTableRecord();
				for (int i = 0; i < record.length; i++) {
			%><tr>
				<%
					for (int j = 0; j < record[i].length; j++) {
				%>
				<td><%=record[i][j]%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>

	</center>

</body>
</html>