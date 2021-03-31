/*학생/교수별 다른 회원가입 적용*/
function setDisplay(){
		if($('input:radio[id="member_student"]').is(':checked')){

				$('#teacher_signup').hide();
				$('#student_signup').show();
		} else{
				$('#student_signup').hide();
				$('#teacher_signup').show();
		}
}
$(document).ready(function(){
		$("#submit").click(function(){
      if($("#name").val().length==0 ||
      $("#id").val().length==0 ||
      $("#password").val().length==0||
      $("#student_Id").val().length==0||
      $("#department").val().length==0||
      $("#grade").val().length==0||
      $("#email").val().length==0){
    alert("필수항목을 모두 입력해주세요.");
    return false;
      }
     if($("#password").val().length<8){
       alert("비밀번호는 8자보다 많아야합니다.");
       return false;
     }

});

    $("#email_check").click(function(){
      alert("메일 전송이 완료되었습니다.");
			$("#email_num").show();
			$("#email_valid").show();
      setTimeout(function(){
        alert("인증 시간이 경과하였습니다.");
      }, 3000);
      return false;
    });
		$("#email_valid").click(function(){
			alert("인증번호가 일치하지 않습니다.");
			return false;
		})
});

  
