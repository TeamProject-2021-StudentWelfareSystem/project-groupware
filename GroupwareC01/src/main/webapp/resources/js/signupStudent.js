
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
        $('#userMajor option:selected').val()==" "||
        $('#userColleges option:selected').val()==" "){
            alert("필수항목을 모두 입력해주세요.");
            return false;

        } else if($('#userLoginPwd').val().length<8){
            alert("비밀번호는 8자보다 많아야합니다.");
            return false;
        } 
    })

    $('#userLoginID').keypress(function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
            event.preventDefault(); 
        }
    });

	$("#userLoginID").keyup(function(e) { 
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var v = $(this).val();
			$(this).val(v.replace(/[^a-z0-9]/gi,''));
		}
	});
	
	$('#userPhoneNum').keypress(function (event) {
	        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
	            event.preventDefault(); 
	        }
	    });
	$("#userPhoneNum").keyup(function(e) { 
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var v = $(this).val();
			$(this).val(v.replace(/[^a-z0-9]/gi,''));
		}
	});
	

});
