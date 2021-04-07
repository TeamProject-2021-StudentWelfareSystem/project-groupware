 $(document).ready(function() {
	$('#userLoginID').keypress(function (event) {
    	   if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
    	      event.preventDefault(); 
    	    } 
    	    });
	
	$('#email_check').click(function(){
	       if($('#userName').val().length==0){
			alert('이름을 입력해주세요.');
		}else if($('#userEmail').val().length==0){
	        	 alert('이메일을 입력해주세요.');
	      } else{
	      alert("메일 전송이 완료되었습니다.");
	     
	     setTimeout(function(){
	        alert("인증 시간이 경과하였습니다. 인증번호를 다시 받아주세요.");
	      }, 300000);
	      return "signup.do";
	      
	      const Mail = document.getElementById('email').value;
	      var Domain = document.getElementById('mju').value;
	      temp = Mail + Domain; // temp는 사용자가 입력한 메일주소 + 도메인입니다.
	   
	      }
	    });	
		
		/*findPassword 비밀번호 찾기 클릭 시*/
		$('#submitId').click(function(){
			document.location.href="showPassword";
		});
		
			
		/*showPassword 로그인하기 클릭 시*/
		$('#doLogin').click(function(){
			document.location.href="login";
		});
		
	});
