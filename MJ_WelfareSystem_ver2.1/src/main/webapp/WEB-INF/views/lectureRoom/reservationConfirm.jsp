<!-- 강의실 예약 확인 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="../stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="../preconnect" href="https://fonts.gstatic.com">
<link href="../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/buttons.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/reservation.js"></script>

<title>lecture room reservation confirm</title>
</head>
<body>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/WEB-INF/views/homeView/topbar.jsp"></jsp:include>
				<div class="container-fluid"></div>
			</div>
			<div class="row justify-content-center">

				<div class="col-xl-10 col-lg-12 col-md-9">

					<div class="card o-hidden border-0 shadow-lg my-5"
						style="width: 498px; margin: 0 auto;">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="col-lg-12">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">강의실 예약 확인</h1>
									</div>
									<form action="ReservationConfirm" id="reservationConfirm"
										name="ReservationConfirm" method="POST" id="form">
										<div class="section2">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="roomNum">강의실 &nbsp; &nbsp; </label>
												</div>
												<div class="col-sm-6">
													<input type="text" name="LectureRoomNo" id="lectureRoomNo"
														class="form-control form-control-user" disabled readonly
														value="${LectureRoomNo}">
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="date">날짜</label>
												</div>
												<div class="col-sm-6">
													<input type="text" name="ReservationDate"
														id="reservationDate"
														class="form-control form-control-user" disabled readonly
														value="${ReservationDate}">
												</div>
												<script>
													$(function() {
														$("#reservationDate")
																.datepicker();
														$("#reservationDate")
																.datepicker(
																		'setDate',
																		new Date);
													});
												</script>
											</div>

											<div class="form-group row">
												<div class="col-sm-6">
													<label for="time">시간 &nbsp; &nbsp; </label>
												</div>
												<div class="col-sm-6">
													<input type="text" name="ReservationStartTime"
														id="reservationStartTime"
														class="form-control form-control-user" disabled readonly
														value="${ReservationStartTime}">
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="maxNum">최대인원 &nbsp; &nbsp; </label>
												</div>
												<div class="col-sm-5">
													<input type="text" name="MaxNumOfPeople"
														id="maxNumOfPeople" class="form-control form-control-user"
														disabled readonly value="${MaxNumOfPeople}">
												</div>
												<div>
													<label>&nbsp;명</label>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="reservationNum">예약인원 &nbsp; &nbsp; </label>
												</div>
												<div class="col-sm-5">
													<input type="text" name="ReservationNumOfPeople"
														id="reservationNumOfPeople"
														class="form-control form-control-user" disabled readonly
														value="${ReservationNumOfPeople}">
												</div>
												<div>
													<label>&nbsp;명</label>
												</div>
											</div>
											<hr>
										</div>
										<!-- section2 -->
										<div id="btn" class="form-group row">
											<div class="col-sm-6">
												<input type="submit" value="삭제" id="deleteButton"
													class="btn btn-primary btn-user btn-block">
											</div>
											<div class="col-sm-6">
												<a href="${path}/lectureRoom/lectureRoomList"> <input
													type="button" value="이전" id="listButton"
													class="form-control form-control-user">
												</a>
											</div>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
		</div>
	</div>

	<!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
	
</body>
</html>