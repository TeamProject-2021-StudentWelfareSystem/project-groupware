
$(document).ready(function(){
    $('#saveButton').click(function(){
        if($('#teamUser option:selected').val()==""){
            alert("팀원을 선택해주세요.");
            return false;
        } else if($("input:radio[name='Positive']").is(":checked")== false || 
				$("input:radio[name='Contribute']").is(":checked")== false || 
				$("input:radio[name='Respect']").is(":checked")== false || 
				$("input:radio[name='Flexible']").is(":checked")== false){
			alert("평가 항목을 모두 입력해주세요.");
			return false;
		}
		else {
			 alert("저장 완료!");
		}
    });

	 $('#search').click(function(){
        if($('#team option:selected').val()==""){
            alert("팀을 선택해주세요.");
            return false;
        }
    });

});