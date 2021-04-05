/*학생/교수별 다른 로그인 적용
function setDisplay(){
		if($('input:radio[id="member_student"]').is(':checked')){

				$('#teacher_login').hide();
				$('#student_login').show();
		} else{
				$('#student_login').hide();
				$('#teacher_login').show();
		}
}
*/


$(document).ready(function() {
  $("#submit").click(function(){
    if($("#mid").val().length==0 || $("#pw").val().length==0) {
      alert("필수항목이 입력되지 않았습니다.");
      return false;
    }
  });
});
