<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加钥匙</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
	function check(form) {
		if (form.dataName.value == "") {
			alert("钥匙名称不能为空！");
			return false;
		}
		if (form.dataAccount.value == "") {
			alert("账号名不能为空！");
			return false;
		}
		if (form.dataPassword.value == "") {
			alert("账号密码不能为空！");
			return false;
		}
		var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
		var reg1 = /[\uFF00-\uFFEF]/;
		if (reg.test(form.dataPassword.value) == true
				|| reg1.test(form.dataPassword.value) == true) {
			alert("账号密码不符合条件！（不能含有unicode CJK(中日韩)统一表意字符及全角符号）");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
		<h3>添加一条新钥匙</h3>
		<table align="center">
			<form action="mainServlet" method="post"
				onsubmit="return check(this);">
				<input type="hidden" name="flag" value="insert">
				<tr>
					<th>钥匙名称</th>
					<th><input type="text" name="dataName" maxlength=20></th>
				</tr>
				<tr>
					<th>账号名称</th>
					<th><input type="text" name="dataAccount" maxlength=20></th>
				</tr>
				<tr>
					<th>账号密码</th>
					<th><input type="text" name="dataPassword" maxlength=16></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="添加记录" /></th>
				</tr>
			</form>
		</table>
		<a href="mainServlet">返回主页面</a>
	</center>
</body>
</html>