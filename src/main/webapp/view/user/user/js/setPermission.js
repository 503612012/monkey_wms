//@ sourceURL=view/user/user/js/setPermission.js
$(function() {
	// 角色改变事件
	$("#role").combobox({
		onChange: function(n, o) {
			if (n == '') {
				$('#permission_tree').css("display", "none");
				return;
			}
			// 加载树
			$('#permission_tree').css("display", "");
			$('#permission_tree').tree({
				url: getBasePath() + "/user/role/getPermissonTreeData.html?roleId=" + n,
				method: "GET",
				animate: true,
				lines: true,
				onClick: function(node) {
					if (node.state == 'closed' && (!$("#permission_tree").tree('isLeaf', node.target))) { // 状态为关闭而且非叶子节点
						$(this).tree('expand', node.target); // 点击文字展开菜单
						if (node.attributes && node.attributes.url) {
							openPanel(node.attributes.url, node.text);
						}
					} else {
						if ($("#permission_tree").tree('isLeaf', node.target)) { // 状态为打开而且为叶子节点
							if (node.attributes && node.attributes.url) {
								openPanel(node.attributes.url, node.text);
							}
						} else {
							$(this).tree('collapse', node.target); // 点击文字关闭菜单
						}
					}   
				},
				onCheck: function(node, checked) {
					var childList = $(this).tree('getChildren', node.target);
					if (childList.length > 0) { // 有子菜单，点击的时候级联子菜单
						var checkedTrue = function() {
							childList.map(function(currentValue) {
								$("#" + currentValue.domId).parent().find(".tree-checkbox").removeClass("tree-checkbox0").removeClass("tree-checkbox2").addClass("tree-checkbox1");
							});
						};
						var checkedFalse = function() {
							$.each(childList, function(index, currentValue) {
								$("#" + currentValue.domId).parent().find(".tree-checkbox").removeClass("tree-checkbox1").removeClass("tree-checkbox2").addClass("tree-checkbox0");
							});
						};
						var checkChangeProperties = checked == true ? checkedTrue() : checkedFalse();
					} else { // 没有子菜单，即为叶子节点，点击的时候级联父节点
						var parentNode = $('#permission_tree').tree('getParent', node.target); //得到父节点
						if (checked == true) { // 选中子节点
							var checkBoxList = $("#" + node.domId).parent().parent().find(".tree-checkbox");
							for (var i=0; i<checkBoxList.length; i++) {
								if ($(checkBoxList[i]).hasClass("tree-checkbox0")) {
									$("#" + parentNode.domId).find(".tree-checkbox").removeClass("tree-checkbox0").removeClass("tree-checkbox1").addClass("tree-checkbox2");
									return;
								}
							}
							$("#" + parentNode.domId).find(".tree-checkbox").removeClass("tree-checkbox0").removeClass("tree-checkbox2").addClass("tree-checkbox1");
						} else { // 取消选中子节点
							var checkBoxList = $("#" + node.domId).parent().parent().find(".tree-checkbox");
							for (var i=0; i<checkBoxList.length; i++) {
								if ($(checkBoxList[i]).hasClass("tree-checkbox1")) {
									$("#" + parentNode.domId).find(".tree-checkbox").removeClass("tree-checkbox0").removeClass("tree-checkbox1").addClass("tree-checkbox2");
									return;
								}
							}
						}
					}
				}
			});
		}
	});
	
	// 全部展开关闭
	$(".expandAll").on("click", function() {
		var that = $(this);
		if (that.hasClass("open")) { // 折叠
			$('#permission_tree').tree("collapseAll");
			that.find(".l-btn-text").html("全部展开");
			that.removeClass("open");
			that.find(".icon-remove").removeClass("icon-remove").addClass("icon-add");
		} else { // 展开
			$('#permission_tree').tree("expandAll");
			that.find(".l-btn-text").html("全部折叠");
			that.addClass("open");
			that.find(".icon-add").removeClass("icon-add").addClass("icon-remove");
		}
	});
	
	// 提交
	$(".submit_btn").on("click", function() {
		var list = $('#permission_tree').find(".tree-checkbox"); // 不是获取选中的，而是获取所有节点
		var data = new Array();
		var roleId = $("#role").combobox('getValue');
		for (var i=0; i<list.length; i++) {
			var menuCode = $("#permission_tree").tree("getNode", $(list[i]).parent()).id;
			
			var checked = false;
			if ($(list[i]).hasClass("tree-checkbox2")) {
				checked = true;
			} else if ($(list[i]).hasClass("tree-checkbox1")) {
				checked = true;
			}
			data.push({"menuCode": menuCode, "checked": checked});
		}
		$.ajax({
			url: getBasePath() + '/user/menu/setPermission.html',
			type: 'POST',
			async: true,
			data: {
				"data": JSON.stringify(data),
				"roleId": roleId
			},
			dataType: "json",
			success: function(result) {
				if (result.code != 200) {
					$.messager.alert("系统提示！", result.data, "error");
					return;
				}
				$.messager.alert("系统提示！", "设置成功", "info");
			}
		});
	});
});