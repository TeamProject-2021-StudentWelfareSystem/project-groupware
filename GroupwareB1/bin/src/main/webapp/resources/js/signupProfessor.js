

$(document).ready(function(){

    $('#SignupComplete').click(function(){
        if($('#userLoginID1').val().length==0 ||
        $('#userLoginPwd').val().length==0 ||
        $('#userName').val().length==0||
        $('#userPhoneNum').val().length==0||
        $('#professorColleges option:selected').val()==" "||
        $('#professorMajor option:selected').val()==" "||
        $('#professorRoom').val().length==0||
        $('#professorRoomNum').val().length==0){
            alert("필수항목을 모두 입력해주세요.");
            return false;

        } else if($('#userLoginPwd').val().length<8){
            alert("비밀번호는 8자보다 많아야합니다.");
            return false;
        } else {
            alert("회원가입 성공!");
        }
    });
    
    $('#email_check').click(function(){
        if($('#userEmail').val().length==0){
          alert('이메일을 입력해주세요.');
       } else{
       alert("메일 전송이 완료되었습니다.");

       alert(temp);
      setTimeout(function(){
         alert("인증 시간이 경과하였습니다. 인증번호를 다시 받아주세요.");
       }, 3000);
       return "signup.do";
       
       }
     });
    

    $('#userLoginID1').keypress(function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
            event.preventDefault(); 
        }
    });

   











});