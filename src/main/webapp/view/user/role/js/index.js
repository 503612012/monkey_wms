//@ sourceURL=view/user/role/js/index.js
$(function() {
	// 添加角色页面
	$(".add_role_btn").on("click", function() {
		$("#role_manage_page").dialog('open').dialog('setTitle', '添加角色');
		$('#roleForm').form('clear');
	});
	
	// 修改角色页面
	$(".update_role_btn").on("click", function() {
		var row = $('#roleList').datagrid('getSelected');
		if (row) {
			$("#role_manage_page").dialog('open').dialog('setTitle', '修改角色');
			$("input[name=roleName]").val(row.roleName);
			$("input[name=dbid]").val(row.dbid);
		} else {
			$.messager.alert("系统提示！", "请选择一条记录！", "error");
		}
	});
	
	// 删除角色
	$(".delete_role_btn").on("click", function() {
		var row = $('#roleList').datagrid('getSelected');
		if (row) {
			$.messager.confirm("系统提示！", "您确定要删除这条记录吗？", function(r) {
				if (r) {
					$.post(getBasePath() + "/user/role/delete.html", {
						id: row.dbid
					}, function(result) {
						if (result.code != 200) {
							$.messager.alert("系统提示！", result.data, "error");
							return;
						}
						$.messager.alert("系统提示！", "删除成功！");
						$("#roleList").datagrid("reload");
					}, 'json');
				}
			});
		} else {
			$.messager.alert("系统提示！", "请选择一条记录！", "error");
		}
	});
	
	// 提交
	$(".submit_btn").on("click", function() {
		var dbid = $("input[name=dbid]").val();
		var roleName = $("input[name=roleName]").val();
		if (roleName == '') {
			$.messager.alert("系统提示！", "请输入角色名！", "error");
			return;
		}
		$.ajax({
			url: getBasePath() + "/user/role/addOrUpdate.html",
			type: "POST",
			async : true,
			dataType: "json",
			data: {
				"roleName": roleName,
				"id": dbid
			},
			success: function(result) {
				if (result.code != 200) {
					$.messager.alert("系统提示！", result.data, "error");
					return;
				} else {
					$.messager.alert("系统提示！", "操作成功");
					$('#role_manage_page').dialog('close');
					$("#roleList").datagrid("reload");
				}
			}
		});
	});
	
	// 查询按钮点击事件
	$(".search_btn").on("click", function() {
		var keyword = $("input[name=keyword]").val();
		$('#roleList').datagrid('load', getBasePath() + '/user/role/list.html?keyword=' + keyword);
		$('#roleList').datagrid('reloadFooter', getBasePath() + '/user/role/list.html?keyword=' + keyword);
	});
});
