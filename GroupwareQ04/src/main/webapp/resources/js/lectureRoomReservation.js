$('#saveButton').onclick(function(){
	if($("#reservationStartTime option:selected").val()==" "){
		alert('예약시간을 선택해주세요.');
	}
	return false;
})