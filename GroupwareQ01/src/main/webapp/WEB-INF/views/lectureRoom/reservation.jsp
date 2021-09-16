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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/lectureRoomContent.css"
	type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/reservation.js"></script>

<title>lecture room reservation</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
	<nav>
		<div class="mbody">
			<div class="mcontWidth">
				<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
				<div class="rightBox">
					<section>
						<div class="section">
							<br>
							<h2>강의실 예약</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="LectureRoomReservation?roomNum=${LectureRoomNo}"
							name="LectureRoomReservation" method="POST" id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr>
										<td><label for="roomNum">강의실 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="LectureRoomNo"
											id="lectureRoomNo" class="inputBox" disabled readonly
											value=${LectureRoomNo}></td>
									</tr>
									<tr>
										<td><label for="date">날짜 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="ReservationDate"
											id="reservationDate" class="inputBox" disabled readonly
											value=${ReservationDate}> <script>
												$(function() {
													$("#reservationDate")
															.datepicker();
													$("#reservationDate")
															.datepicker(
																	'setDate',
																	new Date);
												});
											</script></td>
									</tr>
									<tr>
										<td><label for="time">시간 &nbsp; &nbsp; </label></td>
										<c:set var="StartTime" value="${StartTime}" />
										<td><select name="ReservationStartTime"
											id="reservationStartTime">
												<option value=" " selected>-선택-</option>
												<option class="09:00:00" value="09:00~11:00">09:00~11:00</option>
												<option class="11:00:00" value="11:00~13:00">11:00~13:00</option>
												<option class="13:00:00" value="13:00~15:00">13:00~15:00</option>
												<option class="15:00:00" value="15:00~17:00">15:00~17:00</option>
												<option class="17:00:00" value="17:00~19:00">17:00~19:00</option>
												<option class="19:00:00" value="19:00~21:00">19:00~21:00</option>
										</select></td>
										<c:forEach items="${StartTime}" var="StartTime"
											varStatus="status">
											<c:if
												test="${StartTime.getReservationStartTime() == '09:00:00'}">
												<script>
													$(
															"select option[class*='09:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
											<c:if
												test="${StartTime.getReservationStartTime() == '11:00:00'}">
												<script>
													$(
															"select option[class*='11:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
											<c:if
												test="${StartTime.getReservationStartTime() == '13:00:00'}">
												<script>
													$(
															"select option[class*='13:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
											<c:if
												test="${StartTime.getReservationStartTime() == '15:00:00'}">
												<script>
													$(
															"select option[class*='15:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
											<c:if
												test="${StartTime.getReservationStartTime() == '17:00:00'}">
												<script>
													$(
															"select option[class*='17:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
											<c:if
												test="${StartTime.getReservationStartTime() == '19:00:00'}">
												<script>
													$(
															"select option[class*='19:00:00']")
															.prop('disabled',
																	true);
												</script>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td colspan="2"><label for="maxNum">최대인원 &nbsp;
												&nbsp; </label> <input type="text" name="MaxNumOfPeople"
											id="maxNumOfPeople" class="inputBox" disabled readonly
											value=${MaxNumOfPeople}><label>&nbsp;명</label></td>
									</tr>
									<tr>
										<td colspan="2"><label for="reservationNum">예약인원
												&nbsp; &nbsp; </label> <input type="text"
											name="ReservationNumOfPeople" id="reservationNumOfPeople"
											class="inputBox" value=${ReservationNumOfPeople}><label>&nbsp;명</label>
										<td>
									</tr>
								</table>
								<hr>
							</div>
							<!-- section2 -->
							<div id="btn">
								<input type="submit" value="예약" id="saveButton"> <a
									href="${path}/lectureRoom/lectureRoomList"><input
									type="button" value="이전" id="listButton"></a>
							</div>
						</form>
					</section>
				</div>
				<!-- right_box -->

			</div>

		</div>
		<!-- mcont_width -->
		<!-- mbody -->
	</nav>
	<!-- mjs_ws -->
</body>
</html>