<%@page import="com.monkey.util.AuthUtils"%>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>/view/user/user/css/index.css?v=1.0.0" />
<script type="text/javascript" src="<%=basePath%>/source/js/ajaxhook.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/themes/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/view/user/user/js/index.js"></script>
<script type="text/javascript" src="<%=basePath%>/source/js/common.js"></script>
<script type="text/javascript">
	function getBasePath(){
		return '<%=basePath%>';
	}
	function hasUpdateUserStatusPermission() {
		return '<%=AuthUtils.hasPermission("E1_01_04_03")%>';
	}
</script>
</head>
<body>
<table id="userList" title="用户列表" class="easyui-datagrid" fitColumns="true" style="width:100%; height:100%" fit="true" border="false"
	iconCls="myIcon-user" pagination="true" rownumbers="true" url="<%=basePath%>/user/user/list.html" data-options="singleSelect:true" toolbar="#toolbar">
	<thead>
		<tr>
			<th field="dbid" width="20">ID</th>
			<th field="user_name" width="60">用户名</th>
			<th field="roleName" width="60">角色</th>
			<th field="status" width="60" formatter="formatterStatus">
				<span>状态</span>
				<shiro:hasPermission name="E1_01_04_03">
					<img class="notice" src="<%=basePath%>/images/notice.png" title="点击可更改用户状态！">
				</shiro:hasPermission>
			</th>
		</tr>
	</thead>
</table>

<!-- 表格的工具条 -->
<div id="toolbar">
	<shiro:hasPermission name="E1_01_04_02">
		<a href="javascript:void(0)" class="easyui-linkbutton add_user_btn" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton update_user_btn" iconCls="icon-edit" plain="true">修改</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="E1_01_04_01"><a href="javascript:void(0)" class="easyui-linkbutton delete_user_btn" iconCls="icon-remove" plain="true">删除</a></shiro:hasPermission>
	<input name="keyword" value="${keyword}" style="line-height:18px; border:1px solid #ccc; border-radius: 4px">
	<a href="javascript:void(0)" class="easyui-linkbutton search_btn" plain="true" iconCls="icon-search">查询</a>
</div>

<!-- 添加和修改窗口 -->
<div id="user_manage_page" class="easyui-dialog" style="width:400px;height:300px;padding:10px 10px" closed="true" buttons="#dlg-buttons">
	<form id="userForm" method="post">
		<input type="hidden" name="dbid" value="">
		<table cellspacing="10px;">
			<tr>
				<td>用户名：</td>
       			<td><input name="userName" class="easyui-validatebox" required="true" style="width: 200px;"></td>
       		</tr>
       		<tr>
       			<td>密码：</td>
       			<td><input name="password" type="password" class="easyui-validatebox" required="true" style="width: 200px;"></td>
       		</tr>
       		<tr>
       			<td>重复密码：</td>
       			<td><input name="confirmPassword" type="password" class="easyui-validatebox" required="true" style="width: 200px;"></td>
       		</tr>
       		<tr>
       			<td>角色：</td>
       			<td>
       				<input id="role" name="role">
       			</td>
       		</tr>
       	</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton submit_btn" iconCls="icon-ok">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton cancel_btn" iconCls="icon-cancel" onclick="javascript:$('#user_manage_page').dialog('close')">关闭</a>
</div>

</body>
</html>