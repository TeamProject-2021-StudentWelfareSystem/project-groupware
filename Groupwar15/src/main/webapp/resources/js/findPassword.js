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
	      $('#email_num').show();
	     $('#email_valid').show();
	     
	     setTimeout(function(){
	        alert("인증 시간이 경과하였습니다. 인증번호를 다시 받아주세요.");
	      }, 3000);
	      return "signup.do";
	      
	      const Mail = document.getElementById('email').value;
	      var Domain = document.getElementById('mju').value;
	      temp = Mail + Domain; // temp는 사용자가 입력한 메일주소 + 도메인입니다.
	   
	      }
	    });	
		$('#email_valid').click(function(){
			if($('#email_num').val().length==0){
	         alert('인증번호를 입력해주세요.');
			 return false;
	      } else {
			/*인증번호 일치하지 않을 때*/
			alert("인증번호가 일치하지 않습니다.");
			return false;
			/*인증번호 일치 했을 때
			alert("인증에 성공하였습니다.");
			document.location.href="showPassword";
			*/
			}
			
		})
			
		/*showPassword 로그인하기 클릭 시*/
		$('#doLogin').click(function(){
			document.location.href="login";
		});
