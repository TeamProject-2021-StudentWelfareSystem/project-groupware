function DeleteReservation(checker) {
	
	console.log("0");
	
	$("#checker").attr("value", checker);
	if(checker == "true"){
	console.log("1");
	 alert('성공적으로 예약이 취소되었습니다');
	}else if(checker == "false"){
	 alert('관리자에게 문의를 해주십시오');
	 	console.log("2");
	 
	}
}