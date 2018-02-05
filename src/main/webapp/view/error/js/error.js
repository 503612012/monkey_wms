//@ sourceURL=view/error/js/common.js
$(function() {
	var startNum = 5;
	setInterval(function() {
		if (startNum > 1) {
			startNum--;
			$("#time").html(startNum + "秒");
		} else {
			location.href = history.go(-1);
		}
	}, 1000);
})