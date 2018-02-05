<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.monkey.constant.Constant"%>
<%@page import="com.monkey.vo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
User currentUser = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Monkey</title>
<link rel="shortcut icon" href="<%=basePath%>/images/logo.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/source/css/index.css" />
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/themes/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/source/js/index.js"></script>
<script type="text/javascript">
	function getBasePath(){
		return '<%=basePath%>';
	}
</script>
</head>

<body class="easyui-layout index_page">
	<div region="north" class="banner_box">
		<div>
			<font color="blue" size="10">This is banner</font>
		</div>
		<div class="currect_info_box">
			当前用户：<font color="red"><%=currentUser.getUserName()%></font>&emsp;<a href="javascript:void(0);" class="logout">退出</a>
		</div>
	</div>
	
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" data-options="tools:'#tab-tools'" id="panel_box">
			<div title="首页">
				<font color="blue" size="10">This is welcome page</font>
			</div>
		</div>
	</div>
	
	<div region="west" minWidth="200px" class="menu_box" title="菜单" split="true">
		<ul class="easyui-tree" id="menu_tree"></ul>
	</div>
	
	<div region="south" class="footer">
		版权所有&copy; <a href="http://www.vhzsqm.com">www.vhzsqm.com</a>
	</div>
</body>
</html>
