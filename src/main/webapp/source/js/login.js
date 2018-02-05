//@ sourceURL=source/js/login.js
$(function() {
	// 解决java后台跳出iframe的解决方法
	if (window != top) {
		top.location.href = location.href; 
	}
	
	// 刷新验证码
	$(".refresh_captcha").on("click", function(){
		$(this).attr("src", getBasePath() + "/sys/getCaptcha.html?random=" + new Date().getTime());
	})
	
	// 绑定回车事件
	$(document).keydown(function(e){ 
		var curKey = e.which; 
	    if (curKey == 13) { 
	    	$("#submit_btn").click(); 
	        return false; 
	    } 
    }); 
	
	// 绑定登录按钮事件
	$("#submit_btn").on("click", function() {
		var userName = $("input[name=userName]").val();
		if ($.trim(userName) == '') {
			alert("请输入用户名！");
			return;
		}
		var password = $("input[name=password]").val();
		if ($.trim(password) == '') {
			alert("请输入密码！");
			return;
		}
		var captcha = $("input[name=captcha]").val();
		if ($.trim(captcha) == '') {
			alert("请输入验证码！");
			return ;
		}
		$.ajax({
			url: getBasePath() + "/sys/login.html",
			type: "POST",
			async : true,
			data: {
				"userName": userName,
				"password": password,
				"captcha": captcha
			},
			dataType: "json",
			success: function(result) {
				if (result.code != 200) {
					alert(result.data);
					$(".refresh_captcha").trigger("click");
	        		$("input[name=captcha]").val("");
					return;
				}
				location.href = getBasePath() + "/index.html";
			}
		});
	});
});