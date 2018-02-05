//@ sourceURL=view/user/user/js/index.js
$(function() {
	// 改变用户状态
	$("body").on("click", ".datagrid-cell-c1-status > font", function() {
		// 这里需要判断权限
		var flag = hasUpdateUserStatusPermission();
		if (flag == 'false') {
			return;
		}
		var status = $(this).text();
		var msg = "";
		if (status == "可用") {
			status = '2';
			msg = "您确定要禁用该用户吗？";
		} else {
			status = '1';
			msg = "您确定要启用该用户吗？";
		}
		var dbid = $(this).parent().parent().parent().find("td[field=dbid]>div").html();
		$.messager.confirm("系统提示！", msg, function(r) {
			if (r) {
				$.ajax({
					url: getBasePath() + "/user/user/updateStatus.html",
					type: "POST",
					async : true,
					dataType: "json",
					data: {
						"id": dbid,
						"status": status
					},
					success: function(result) {
						if (result.code != 200) {
							$.messager.alert("系统提示！", result.data, "error");
							return;
						} else {
							$("#userList").datagrid("reload");
						}
					}
				});
			}
		});
	});
	
	// 添加用户页面
	$(".add_user_btn").on("click", function() {
		$("#user_manage_page").dialog('open').dialog('setTitle', '添加用户');
		$('#userForm').form('clear');
		$("input[name=userName]").attr("disabled", false);
		$('#role').combobox({
		    url: getBasePath() + '/user/role/findAll.html',
		    valueField: 'id',
		    textField: 'roleName'
		});
	});
	
	// 修改用户页面
	$(".update_user_btn").on("click", function() {
		var row = $('#userList').datagrid('getSelected');
		if (row) {
			$("#user_manage_page").dialog('open').dialog('setTitle', '修改用户');
			$('#role').combobox({
			    url: getBasePath() + '/user/role/findAll.html',
			    valueField: 'id',
			    textField: 'roleName'
			});
			$("input[name=userName]").attr("disabled", true).val(row.user_name);
			$("input[name=password]").val(row.password);
			$("input[name=confirmPassword]").val(row.password);
			$("input[name=role]").val(row.roleId);
			$("input[name=dbid]").val(row.dbid);
		} else {
			$.messager.alert("系统提示！", "请选择一条记录！", "error");
		}
	});
	
	// 删除用户
	$(".delete_user_btn").on("click", function() {
		var row = $('#userList').datagrid('getSelected');
		if (row) {
			$.messager.confirm("系统提示！", "您确定要删除这条记录吗？", function(r) {
				if (r) {
					$.post(getBasePath() + "/user/user/delete.html", {
						id: row.dbid
					}, function(result) {
						if (result.code != 200) {
							$.messager.alert("系统提示！", result.data, "error");
							return;
						}
						$.messager.alert("系统提示！", "删除成功！");
						$("#userList").datagrid("reload");
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
		var password = $("input[name=password]").val();
		var confirmPassword = $("input[name=confirmPassword]").val();
		if (password != confirmPassword) {
			$.messager.alert("系统提示！", "密码不一致，请重新输入！", "error");
			return;
		}
		if (password == '') {
			$.messager.alert("系统提示！", "请输入密码！", "error");
			return;
		}
		var userName = $("input[name=userName]").val();
		if (userName == '') {
			$.messager.alert("系统提示！", "请输入用户名！", "error");
			return;
		}
		var roleId = $("input[name=role]").val();
		if (roleId == '') {
			$.messager.alert("系统提示！", "请选择用户角色！", "error");
			return;
		}
		$.ajax({
			url: getBasePath() + "/user/user/addOrUpdate.html",
			type: "POST",
			async : true,
			dataType: "json",
			data: {
				"userName": userName,
				"password": password,
				"roleId": roleId,
				"id": dbid
			},
			success: function(result) {
				if (result.code != 200) {
					$.messager.alert("系统提示！", result.data, "error");
					return;
				} else {
					$.messager.alert("系统提示！", "操作成功");
					$('#user_manage_page').dialog('close');
					$("#userList").datagrid("reload");
				}
			}
		});
	});
	
	// 查询按钮点击事件
	$(".search_btn").on("click", function() {
		var keyword = $("input[name=keyword]").val();
		$('#userList').datagrid('load', getBasePath() + '/user/user/list.html?keyword=' + keyword);
		$('#userList').datagrid('reloadFooter', getBasePath() + '/user/user/list.html?keyword=' + keyword);
	});
});

/**
 * 格式化用户状态
 */
function formatterStatus(val, row) {
	if (val == 1) {
		return '<font color=blue>可用</font>';
	} else {
		return '<font color=red>禁用</font>';
	}
}
