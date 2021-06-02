
function addTeamMember() {

		$("#addMember").click(function(){
			$("#addTeamMember").append("<tr><td colspan='2'><hr></td><tr>")
			$("#addTeamMember").append("<tr><td><label for='studentId'>학번  &nbsp; &nbsp; </label></td> <td><input type='text' name='StudentID' id='studentId' class='inputBox'  value=''></td></tr>")
			$("#addTeamMember").append("<tr><td><label for='teamLeader'>팀원명  &nbsp; &nbsp; </label></td> <td><input type='text' name='StudentName' id='teamLeader' class='inputBox'  value=''></td></tr>")
					
			$("#addTeamMember").child().remove();
			
		});
		
		
	}
$(document).ready(function(){
$("#lecture").change(function(){
	var 성서와인간이해 = ["-선택-", "강창섭", "남성혁", "황훈식", "이주형", "권영주", "이주형", "오사랑", "우동리", "웬캄빈", "조미라", "장재호", "한상민", "조미라"];
	var 채플 =["교목실s"];

	var changeItem;

	if(this.value == "성서와인간이해"){
		changeItem = 성서와인간이해;
		
	} else if(this.value == "채플"){
		changeItem == 채플;
		
	}
	$("#lectureProfessor").empty();
	for(var count = 0; count < changeItem.length; count++){
		var option = $("<option>" + changeItem[count] + "</option>");
		$("#lectureProfessor").append(option);
	}
	});
});