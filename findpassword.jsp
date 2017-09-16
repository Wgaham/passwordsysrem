<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
	function check(form) {
		if (form.userName.value.trim() == "") {
			alert("用户名不能为空！");
			return false;
		}
		if (form.newUsername.value.trim().toLowerCase() == "null") {
			alert("为了系统的稳定，这个用户名不被允许！");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
<center>
<h3>找回密码</h3>
<table>
<form action="userServlet" method="post">
<input type="hidden" name="flag" value="user">

</form>
</table>
</center>
</body>
</html>