 $(document).ready(function() {
     	$('#agreeBtn').click(function() {
        if($('input:checkBox[name=TermsWithdrawal]').is(':checked') == false) {
        	alert("회원 탈퇴 동의에 체크해주세요");
        	return withdrawal;
        }
		else{
			alert("회원 탈퇴가 정상적으로 완료되었습니다.");
				window.opener.location.href = "javascript:document.getElementById('logout').submit();";
        	window.close();
		}
        });

		$('#cancelBtn').click(function(){
			alert("회원 탈퇴가 취소되었습니다.");
			
			window.close();
		});
        
      });
      
