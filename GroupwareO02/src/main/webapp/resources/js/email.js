$(document).ready(function() {

	$('#emailCheck').click(function() {
		if ($('#userEmail').val().length == 0) {
			alert('이메일을 입력해주세요.');
			return false;
		} else {
			return "email";
		}
	});
	$(".inputBox").keyup(function(e) {
		if (!(e.keyCode >= 37 && e.keyCode <= 40)) {
			var v = $(this).val();
			$(this).val(v.replace(/[^a-z0-9]/gi, ''));
		}
	});


	$('#emailValid').click(function() {
		if ($('#emailNum').val().length == 0) {
			alert('인증번호를 입력해주세요.');
			return false;
		} else {
			return "email";
		}
	})
});
