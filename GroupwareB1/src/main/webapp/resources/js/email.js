$(document).ready(function() {

	$('#email_check').click(function() {
		if ($('#userEmail').val().length == 0) {
			alert('이메일을 입력해주세요.');
			return "email";
		} else {
			setTimeout(function() {
				alert("인증 시간이 경과하였습니다. 인증번호를 다시 받아주세요.");
				return "email";
			}, 180000);
		}
	});

	$('#email_valid').click(function() {
		if ($('#email_num').val().length == 0) {
			alert('인증번호를 입력해주세요.');
			return "email";
		}
	})














});
