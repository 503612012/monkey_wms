<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Monkey</title>
<link rel="shortcut icon" href="<%=basePath%>/images/logo.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/source/css/login.css" />
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/source/js/login.js"></script>
<script type="text/javascript">
	function getBasePath(){
		return '<%=basePath%>';
	}
</script>
</head>
<body>
	<div class="login_body">
		<div class="header">
			<h1>HELLO MONKEY!</h1>
		</div>
		<div class="box">
			<form>
				<table>
					<tr>
						<td>用户名:</td>
						<td>
							<input type="text" name="userName">
						</td>
					</tr>
					<tr>
						<td>密&emsp;码:</td>
						<td>
							<input type="password" name="password">
						</td>
					</tr>
					<tr>
						<td>验证码:</td>
						<td class="captcha_td">
							<input type="text" name="captcha" size="7" />
							<span><img src="<%=basePath%>/sys/getCaptcha.html" class="refresh_captcha"/></span>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input type="button" id="submit_btn" value="登录">
							<input type="reset" value="重置">
						</td>
					</tr>				
				</table>
			</form>
		</div>
		<div>
			<h3 style="color: red;">${errorMsg}</h3>
		</div>
	</div>
</body>
</html>