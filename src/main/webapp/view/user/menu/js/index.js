//@ sourceURL=view/user/menu/js/index.js
$(function() {
	// 全部展开
	$(".open_btn").on("click", function() {
		$("#menu_tg").treegrid('expandAll');
	});
	// 全部折叠
	$(".close_btn").on("click", function() {
		$("#menu_tg").treegrid('collapseAll');
	});
});

/**
 * 格式化拥有者
 */
function formatterOuner(value) {
	if (value) {
		var str = '';
		$.ajax({
			url: getBasePath() + "/user/role/findByIds.html",
			type: "GET",
			data: {
				"ids": value
			},
			dataType: "json",
			success: function(result) {
				if (result.code != 200) {
					$.messager.alert("系统提示！", "格式化拥有者失败！", "error");
					return;
				}
				var owner = new Array();
				for (var i=0; i<result.data.length; i++) {
					owner.push(result.data[i].roleName);
				}
				str = owner.join(",");
			}
		});
		return str;
	} else {
		return '';
	}
}