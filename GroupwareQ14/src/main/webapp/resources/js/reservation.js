$(document).ready(function(){

    $('#saveButton').click(function(){
        if($('#reservationNumOfPeople').val().length==0 || $('#reservationNumOfPeople').val()==0 ) {
			alert("예약 인원을 작성해주세요.");
			return false;
		}
        else if($('#reservationStartTime option:selected').val()==" "){
            alert("예약 시간을 선택해주세요.");
            return false;

        } 
    })


});
