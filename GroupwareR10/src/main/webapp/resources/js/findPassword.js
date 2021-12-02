$(document).ready(function() {
    
    /* 학번, 인증번호입력 칸 한/영 막기 */
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
    
    $('#emailNum').keypress(function (event) {
           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
              event.preventDefault(); 
            } 
       });
    $("#emailNum").keyup(function(e) { 
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^a-z0-9]/gi,''));
        }
    });    

    $('#emailCheck').click(function(){
           if($('#userName').val().length==0){
            alert('이름을 입력해주세요.');
        }else if($('#userEmail').val().length==0){
                 alert('이메일을 입력해주세요.');
          } else {     
         setTimeout(function(){
            alert("인증 시간이 경과하였습니다. 인증번호를 다시 받아주세요.");
          }, 1800000);
  
          const Mail = document.getElementById('email').value;
          var Domain = document.getElementById('mju').value;
          temp = Mail + Domain; // temp는 사용자가 입력한 메일주소 + 도메인입니다.
       
          }
        });    
        
        /* 인증하기 버튼 클릭 시*/
        $('#emailValid').click(function(){
            if($('#emailNum').val().length==0){
                alert('인증번호를 입력해주세요.');
                return false;
            }
        });
        
        /*findPassword 비밀번호 찾기 클릭 시*/
        $('#submitID').click(function(){
            if($('#emailNum').val().length==0){
                alert('인증번호를 입력해주세요.');
                return false;
            } else {
                document.location.href="showPassword";
                }
        });
        
            
        /*showPassword 로그인하기 클릭 시*/
        $('#doLogin').click(function(){
            document.location.href="login";
        });
        
    });