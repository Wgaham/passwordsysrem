<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户密码</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
	function check(form) {
		if (form.newPasswordOK.value != form.newPassword.value) {
			alert("两次输入的密码不同！");
			return false;
		}
		if (form.newPassword.value.trim() == "") {
			alert("新的密码不能为空！");
			return false;
		}
		var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
		var reg1 = /[\uFF00-\uFFEF]/;
		if (reg.test(form.newPassword.value) == true
				|| reg1.test(form.newPassword.value) == true) {
			alert("账号密码不符合条件！（不能含有unicode CJK(中日韩)统一表意字符及全角符号）");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
		<h3>修改用户密码</h3>
		<%
			String userName = (String) session.getAttribute("userName");
		%>
		<table align="center">
			目前登录用户为：<%=userName%>
			<form action="mainServlet" method="post"
				onsubmit="return check(this);">
				<input type="hidden" name="flag" value="user">
				<tr>
					<th>新密码</th>
					<th><input type="password" name="newPassword" maxlength=16></th>
				</tr>
				<tr>
					<th>确认密码</th>
					<th><input type="password" name="newPasswordOK" maxlength=16></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="提交修改" /></th>
				</tr>
			</form>
		</table>
		<a href="mainServlet">返回主页面</a>
	</center>
</body>
</html>