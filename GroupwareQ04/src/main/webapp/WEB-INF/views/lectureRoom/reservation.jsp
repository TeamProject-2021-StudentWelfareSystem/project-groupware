<!-- 강의실 예약 화면 -->

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
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/reservation.js"></script>

<style>
label {line-height: 2.0em;}
</style>

<title>lecture room reservation</title>
</head>
<body id="page-top">
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
										<h4 class="text-gray-900 mb-5">강의실 예약</h4>
									</div>
									<form action="LectureRoomReservation?roomNum=${LectureRoomNo}"
										name="LectureRoomReservation" method="POST" id="form">
										<div class="section2">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="roomNum">강의실</label>
												</div>
												<div class="col-sm-6">
													<input type="text" name="LectureRoomNo" id="lectureRoomNo"
														class="form-control form-control-user" disabled readonly
														value="${LectureRoomNo}">
												</div>
											</div>
											<div class="form-group row">
												<div class="col-sm-6">
													<label for="date">날짜 &nbsp; &nbsp; </label>
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
													<label for="time">시간</label>
												</div>
												<div class="col-sm-6">
													<c:set var="StartTime" value="${StartTime}" />
													<select name="ReservationStartTime"
														id="reservationStartTime" class="col-sm-12">
														<option value=" " selected>-선택-</option>
														<option class="09:00:00" value="09:00~11:00">09:00~11:00</option>
														<option class="11:00:00" value="11:00~13:00">11:00~13:00</option>
														<option class="13:00:00" value="13:00~15:00">13:00~15:00</option>
														<option class="15:00:00" value="15:00~17:00">15:00~17:00</option>
														<option class="17:00:00" value="17:00~19:00">17:00~19:00</option>
														<option class="19:00:00" value="19:00~21:00">19:00~21:00</option>
													</select>
													<c:forEach items="${StartTime}" var="StartTime"
														varStatus="status">
														<c:if
															test="${StartTime.getReservationStartTime() == '09:00:00'}">
															<script>
																$(
																		"select option[class*='09:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
														<c:if
															test="${StartTime.getReservationStartTime() == '11:00:00'}">
															<script>
																$(
																		"select option[class*='11:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
														<c:if
															test="${StartTime.getReservationStartTime() == '13:00:00'}">
															<script>
																$(
																		"select option[class*='13:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
														<c:if
															test="${StartTime.getReservationStartTime() == '15:00:00'}">
															<script>
																$(
																		"select option[class*='15:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
														<c:if
															test="${StartTime.getReservationStartTime() == '17:00:00'}">
															<script>
																$(
																		"select option[class*='17:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
														<c:if
															test="${StartTime.getReservationStartTime() == '19:00:00'}">
															<script>
																$(
																		"select option[class*='19:00:00']")
																		.prop(
																				'disabled',
																				true);
															</script>
														</c:if>
													</c:forEach>
												</div>
											</div>

											<div class="form-group row">
												<div class="col-sm-6">
													<label for="maxNum">최대인원</label>
												</div>
												<div class="col-sm-5">
													<input type="text" name="MaxNumOfPeople"
														id="maxNumOfPeople" class="form-control form-control-user"
														disabled readonly value="${MaxNumOfPeople}">
												</div>
												<div>
													<label>명</label>
												</div>
											</div>

											<div class="form-group row">
												<div class="col-sm-6">
													<label for="reservationNum">예약인원</label>
												</div>
												<div class="col-sm-5">
													<input type="text" name="ReservationNumOfPeople"
														id="reservationNumOfPeople"
														class="form-control form-control-user"
														value="${ReservationNumOfPeople}">
												</div>
												<div>
													<label>명</label>
												</div>
											</div>


											<hr>
										</div>
										<!-- section2 -->
										<div id="btn" class="form-group row justify-content-center">
											<div class="col-sm-6 user text-center">
												<input type="submit" value="예약" id="saveButton"
													class="btn btn-primary btn-user">
												<a href="${path}/lectureRoom/lectureRoomList"> <input
													type="button" value="이전" id="listButton"
													class="btn btn-light btn-user">
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