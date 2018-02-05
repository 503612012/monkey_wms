<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/view/user/menu/css/index.css" />
<script type="text/javascript" src="<%=basePath%>/source/js/ajaxhook.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/themes/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/view/user/menu/js/index.js"></script>
<script type="text/javascript" src="<%=basePath%>/source/js/common.js"></script>
<script type="text/javascript">
	function getBasePath(){
		return '<%=basePath%>';
	}
</script>
</head>
<body>
<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton open_btn" iconCls="icon-add" plain="true">展开所有</a>
	<a href="javascript:void(0)" class="easyui-linkbutton close_btn" iconCls="icon-remove" plain="true">收缩所有</a>
</div>
<table title="目录列表" class="easyui-treegrid" fitColumns="true" style="width:100%; height:100%" fit="true" border="false" id="menu_tg"
	iconCls="myIcon-outline" url="<%=basePath%>/user/menu/list.html" rownumbers="true" idField="id" treeField="name" toolbar="#toolbar">
	<thead>
		<tr>
			<th field="name" width="20">目录名称</th>
			<th field="role" width="80" formatter="formatterOuner">拥有者</th>
			<th field="sort" width="20">排序值</th>
		</tr
	</thead>
</table>
</body>
</html>