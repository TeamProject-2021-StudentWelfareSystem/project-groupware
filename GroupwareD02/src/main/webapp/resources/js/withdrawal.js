 $(document).ready(function() {
     	$('#agreeBtn').click(function() {
        if($('input:checkBox[name=TermsWithdrawal]').is(':checked') == false) {
        	alert("회원 탈퇴 동의에 체크해주세요");
        	return false;
        }
		else{
			alert("회원 탈퇴가 정상적으로 완료되었습니다.");
        	window.close();
		}
        });

		$('#cancelBtn').click(function(){
			alert("회원 탈퇴가 취소되었습니다.");
			window.close();
		});
        
      });
      
