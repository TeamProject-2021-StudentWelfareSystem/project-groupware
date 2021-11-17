$(document).ready(function() {
		let result = '<c:out value="${Checker}"/>';
		console.log(result);
		checkAlert(result);
	
		function checkAlert(result) {
			if (result === '') {
				return;
			}
			if (result === "true") {
				alert("성공적으로 예약이 취소 되었습니다.");
			} else if (result === "Noting") {
				alert("예약한 강의실이 없습니다.");
			}else if (result === "ExceptionNum") {
				alert("예약 가능한 최대 인원을 초과 했습니다.");
			}else if(result === "DuplicateReservationExist"){
				alert("이미 예약한 강의실이 존재합니다. 강의실 취소 후 다시 시도 바랍니다.");	
			}else if(result ==="reservationConfirm"){
				alert("예약이 완료되었습니다.");
			}
	
		}
	});		