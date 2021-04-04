
function setDisplay(){
    if($('input:radio[id="member_DoubleMajor"]').is(':checked')){
		$('#studentDoubleMajor').show();
	} else{
		$('#studentDoubleMajor').hide();
	}
}


$(document).ready(function(){

    $('#SignupComplete').click(function(){
        if($('#userLoginID').val().length==0 ||
        $('#userLoginPwd').val().length==0 ||
        $('#userName').val().length==0||
        $('#studentGender option:selected').val()==" "||
        $('#userPhoneNum').val().length==0||
        $('#studentGrade option:selected').val()==" "||
        $('#studentMajor option:selected').val()==" "||
        $('#studentColleges option:selected').val()==" "){
            alert("필수항목을 모두 입력해주세요.");
            return "signStudnet";

        } else if($('#userLoginPwd').val().length<8){
            alert("비밀번호는 8자보다 많아야합니다.");
            return"signStudnet";
        } 
    })

    $('#userLoginID').keypress(function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
            event.preventDefault(); 
        }
    });

	$('#userPhoneNum').keypress(function (event) {
	        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
	            event.preventDefault(); 
	        }
	    });
});
