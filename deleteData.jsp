<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除钥匙</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
</script>
</head>
<body>
	<center>
		<h3>删除一条钥匙</h3>
		<table align="center">
			<form action="mainServlet" method="post">
				<input type="hidden" name="flag" value="delete">
				<tr>
					<th>输入要删除的钥匙名称</th>
					<th><input type="text" name="dataName" maxlength=20></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="删除记录" /></th>
				</tr>
			</form>
		</table>
		<a href="mainServlet">返回主页面</a>
	</center>
</body>
</html>