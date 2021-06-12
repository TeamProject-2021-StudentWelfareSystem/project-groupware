
/* 새 비밀번호 다를 경우 text 띄우기 */
function isSame(){
    var password = $('#userNewPwd').val();
    var pwcheck = $('#userNewPwdCheck').val();
    if(password == pwcheck){
    $('#pwValue').text('비밀번호가 일치합니다.').css('color', 'steelblue');
    }else{
     $('#pwValue').text('비밀번호가 일치하지 않습니다.').css('color', 'red');  
    }
}

$(document).ready(function(){
   
   /* checkPassword에서 비밀번호 미 입력시, 8자 미만 입력 시 */
   $('#modifyBtn').click(function(){
        if($('#userLoginPwd').val().length==0){
            alert("비밀번호를 입력해주세요.");
            return checkPassword;
        } else if($('#userLoginPwd').val().length<8){
            alert("비밀번호는 8자보다 많아야합니다.");
            return checkPassword;
        }
    })

   /* 학생 정보 변경  */
    $('#modifyComplete').click(function(){
		 alert("수정 완료!");
		 window.opener.location.reload();    
	     window.close();
    });

   /* 교수 정보 변경 */
   $('#modifyCompleteP').click(function(){
		 alert("수정 완료!");
		 window.opener.location.reload();    
	     window.close();
    });
   
   /* 연락처 숫자만 입력 가능 */
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
   
   /* 교수실 숫자만 입력 가능 */
   $('#professorRoom').keypress(function (event) {
           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
               event.preventDefault(); 
           }
       });
   $("#professorRoom").keyup(function(e) { 
      if (!(e.keyCode >=37 && e.keyCode<=40)) {
         var v = $(this).val();
         $(this).val(v.replace(/[^a-z0-9]/gi,''));
      }
   });
   
   /* 교수실 전화번호 숫자만 입력 가능 */
   $('#professorRoomNum').keypress(function (event) {
           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
               event.preventDefault(); 
           }
       });
   $("professorRoomNum").keyup(function(e) { 
      if (!(e.keyCode >=37 && e.keyCode<=40)) {
         var v = $(this).val();
         $(this).val(v.replace(/[^a-z0-9]/gi,''));
      }
   });

 /* 비밀번호 변경 */
   $('#modifyCompletePW').click(function(){
        if($('#userNewPwd').val().length==0||
        $('#userNewPwdCheck').val().length==0){
            alert("필수항목을 모두 입력해주세요.");
            return false;
        }

	    else if($('#userNewPwd').val().length < 8||
	        $('#userNewPwdCheck').val().length < 8){
	         alert("비밀번호는 8자보다 많아야합니다.");
	         return false;
	      }

	   /* 새 비밀번호가 일치하지 않는 경우 alert */      
	    else if($('#userNewPwd').val() != $('#userNewPwdCheck').val()) {
	         alert("비밀번호를 다시 확인해주세요.");
	         return false;
	      }

		/* 비밀번호 변경 버튼 클릭 시 창 닫기 */
		else {
		  alert("변경 완료!");
		    window.opener.location.reload(); 
	      	window.close();
		}
	   
    });
  

   /* 취소 버튼 클릭 시 창 닫기 */
   $('#cancelBtn').click(function(){
      window.close();
   });

});