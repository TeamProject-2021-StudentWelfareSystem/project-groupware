
$(document).on("click", "#deleteMember", function() {
	$(this).parent().remove();

});
	$(document).ready(function(){
		$("#addMember").click(function(){
			$("#addStudent").append("<div class='row gx-3 mb-3'><div class='col-md-6'><label class='small mb-1' for='studentID'>팀원 학번</label><input class='form-control bg-white small' id='studentID' name='StudentID' type='text' value=''></div><div class='col-md-6'><label class='small mb-1' for='studentName'>팀원 이름</label><input class='form-control bg-white' id='studentName' name='StudentName' type='text' value=''></div><input type='button' id='deleteMember' type='button' class='btn btn-danger btn-xs p-1' style='position:absolute; margin-left:75%;' value='x'></div>");				
			$("#addStudent").child().remove();
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

		$('#modifyBtn').click(function(){
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

