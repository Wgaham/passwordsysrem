<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WgahamKey账号密码管理系统</title>
<script language="javascript">
	(function isIE() { //ie?
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			document
					.write("<center>本系统在IE下可能体验不佳（特别是IE9以下版本），推荐使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">前往下载</a></center>");
	})();
	//(if(isIE()==true){
	// document.getElementById("a").innerHTML = "本系统在IE下可能体验不佳，请使用更加现代的浏览器比如Google Chrome<a href=\"http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html\">下载链接</a>";
	//}))
	function check(form) {
		if (form.userName.value.trim() == "") {
			alert("用户名不能为空！");
			return false;
		}
		if (form.userPassword.value.trim() == "") {
			alert("用户密码不能为空！");
			return false;
		} else
			return true;
	}
</script>
</head>
<body>
	<center>
	<h2>WgahamKey账号密码管理系统</h2>
		<h3>登陆系统</h3>
		<form action="loginServlet" method="post"
			onsubmit="return check(this);">
			<table align="center">
				<tr>
					<th>用户名</th>
					<th><input type="text" name="userName" maxlength=20></th>
				</tr>
				<tr>
					<th>密码</th>
					<th><input type="password" name="userPassword" maxlength=16></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="登陆"></th>
				</tr>
			</table>
		</form>
		<!--<a href="findpassword.jsp">找回密码</a>-->
		<a href="register.jsp">新用户注册</a><br>
		本系统帮助你记忆你的账号和密码，您只需要记住本系统的账号密码即可。<br>
		每一条记录被称为一条“钥匙”，请注意每一条钥匙有一个唯一的钥匙名称，不可重复！
	</center>
</body>
</html>