$(document).ready(function() {

	$('#email_check').click(function() {
		if ($('#userEmail').val().length == 0) {
			alert('이메일을 입력해주세요.');
			return "email";
		} 
	});
	$(".inputBox").keyup(function(e) { 
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var v = $(this).val();
			$(this).val(v.replace(/[^a-z0-9]/gi,''));
		}
	});


	$('#email_valid').click(function() {
		if ($('#email_num').val().length == 0) {
			alert('인증번호를 입력해주세요.');
			return "email";
		}
	})
});
