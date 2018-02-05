<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/view/user/user/css/setPermission.css?v=1.0.0" />
<script type="text/javascript" src="<%=basePath%>/source/js/ajaxhook.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/easyui/themes/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/view/user/user/js/setPermission.js"></script>
<script type="text/javascript" src="<%=basePath%>/source/js/common.js"></script>
<script type="text/javascript">
	function getBasePath(){
		return '<%=basePath%>';
	}
</script>
</head>
<body>
<div class="role_box">
	<span>用户角色：</span>
	<select id="role" class="easyui-combobox" style="width:200px;">
		<option value="">--请选择角色--</option>
		<c:forEach items="${list}" var="role">
			<option value="${role.id}">${role.roleName}</option>
		</c:forEach>
	</select>
	<a href="#" class="easyui-linkbutton expandAll" iconCls="icon-add">全部展开</a>
</div>
<div class="tree_box">
	<ul id="permission_tree" class="easyui-tree" data-options="checkbox:true, cascadeCheck: false"></ul>
</div>
<div>
	<a href="javascript:void(0)" class="easyui-linkbutton submit_btn" iconCls="icon-ok">保存</a>
</div>
</body>
</html>