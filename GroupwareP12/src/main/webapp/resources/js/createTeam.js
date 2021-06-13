
function addTeamMember() {

		$("#addMember").click(function(){
			$("#contentTable").append("<tr><td colspan='2'><input type='checkbox' name='CheckBox'><label for='studentID'>학번 &nbsp; &nbsp; </label></td><td><input type='text' name='StudentID'id='studentID' class='inputBox'value=''></td><td><label for='studentName'>팀원명 &nbsp; &nbsp; </label></td><td><input type='text' name='StudentName' id='studentName'class='inputBox' value=''></td></tr>");				
			$("#contentTable").child().remove();
			
		});
		
		
	}
	$(document).ready(function(){
		 $('#search').click(function(){
	         if($('#lectureName').val().length==0){
	            alert("과목명을 입력해주세요.");
	            return false;
	        }
    	});
			
		$('#createButton').click(function(){
	         if($('#lecture option:selected').val()==""){
	            alert("과목명을 선택해주세요");
	            return false;
			} else if($('#teamName').val().length==0){
	            alert("팀 이름을 입력해주세요");
	            return false;
			} else if($('#studentID').val().length==0 || 
				$('#studentName').val().length==0 ||
				$('#studentId').val().length==0 || 
				$('#teamLeader').val().length==0){
	            alert("팀원 정보를 모두 입력해주세요");
	            return false;
			} else {
				alert("팀 생성 완료!");
			}
    	});
		
		$('#modifyButton').click(function(){
	         if($('#studentID').val().length==0 || 
				$('#studentName').val().length==0 ||
				$('#studentId').val().length==0 || 
				$('#teamLeader').val().length==0){
	            alert("팀원 정보를 모두 입력해주세요");
	            return false;
			} else {
				alert("팀 수정 완료!");
			}
    	});		
		/* 학번 숫자만 입력 가능 */
	   $('#studentID').keypress(function (event) {
	           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
	               event.preventDefault(); 
	           }
	   });
	   $("#studentID").keyup(function(e) { 
	      if (!(e.keyCode >=37 && e.keyCode<=40)) {
	         var v = $(this).val();
	         $(this).val(v.replace(/[^a-z0-9]/gi,''));
			}
	   });

	   $('#studentId').keypress(function (event) {
	           if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
	               event.preventDefault(); 
	           }
	       });
	   $("#studentId").keyup(function(e) { 
	      if (!(e.keyCode >=37 && e.keyCode<=40)) {
	         var v = $(this).val();
	         $(this).val(v.replace(/[^a-z0-9]/gi,''));
	      }	
  		 });

});