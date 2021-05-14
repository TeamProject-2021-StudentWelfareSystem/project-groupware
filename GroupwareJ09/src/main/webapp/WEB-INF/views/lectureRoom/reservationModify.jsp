<!-- 강의실 예약 수정 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../css/lectureRoomContent.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<title>lecture room reservation modify</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">
					<div class="leftBox">
						<div class="leftInfo">
							<!--로그인 후 화면-->
							<br>
							<div class=userName><input type=text name="UserName" id="userName"value=${UserName} disabled readonly>님<br>안녕하세요!</div>
				            <div class="userColleges"><h4>소속 : <input type=text name="SC" id="sc" value=${SC} disabled readonly></h4></div>
				            <div class="userMajor"><h4>학과 : <input type=text name="UserMajor" id="userMajor" value=${UserMajor} disabled readonly></h4></div>
				            <div class="userGrade"><h4>학년 : <input type=text name="Grade" id="grade" value=${Grade} disabled readonly></h4></div>
						</div>
						<!-- left_info -->
					</div>
					<!-- left_box -->
					<div class="rightBox">
						<section>
							<div class="section">
								<br>
								<h2>강의실 예약 수정</h2>
								<hr>
							</div>
						</section>
						<section>
							<form action="ReservationModify.do" name="ReservationModify" method="POST" id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<table id="contentTable">
										<tr>
											<td><label for="roomNum">강의실 &nbsp; &nbsp; </label></td>
											<td><select name="LectureRoomNo" id="lectureRoomNo">
													<option value="1135" selected>1135</option>
													<option value="1125">1125</option>
													<option value="1150">1150</option>
													<option value="1209">1209</option>
													<option value="1213">1213</option>
													<option value="1217">1217</option>
													<option value="1221">1221</option>
													<option value="1241">1241</option>
													<option value="1246">1246</option>
													<option value="1250">1250</option>
													<option value="1254">1254</option>
													<option value="1256">1256</option>
													<option value="1321">1321</option>
													<option value="1325">1325</option>
													<option value="4112">4112</option>
													<option value="4115">4115</option>
													<option value="4217">4217</option>
													<option value="4219">4219</option>
													<option value="4325">4325</option>
													<option value="4327">4327</option>
													<option value="4415">4415</option>
													<option value="4419">4419</option>
													<option value="4501">4501</option>
													<option value="4507">4507</option>
													<option value="4509">4509</option>
													<option value="4511">4511</option>
													<option value="4513">4513</option>
													<option value="4515">4515</option>
													<option value="4519">4519</option>
													<option value="4707">4707</option>
													<option value="4709">4709</option>
													<option value="4711">4711</option>
													<option value="4713">4713</option>
													<option value="4717">4717</option>
													<option value="4718">4718</option>
													<option value="4719">4719</option>
													<option value="4720">4720</option>
													<option value="9114">9114</option>											
											</select></td>
										</tr>
										<tr>
											<td><label for="date">날짜 &nbsp; &nbsp; </label></td>
											<td><input type="text" name="ReservationDate" 
												id="reservationDate" class="inputBox" 
												disabled readonly value=${ReservationDate}>
												<script>
											        $(function () {
											            $("#reservationDate").datepicker();
											            $("#reservationDate").datepicker('setDate', new Date);
											        });
											    </script>

											</td>
										</tr>
										<tr>
											<td><label for="time">시간 &nbsp; &nbsp; </label></td>
											<td><select name="ReservationStartTime" id="reservationStartTime">
													<option value=" " selected>-선택-</option>
													<option value="09">09:00~11:00</option>
													<option value="11">11:00~13:00</option>
													<option value="13">13:00~15:00</option>
													<option value="15">15:00~17:00</option>	
													<option value="17">17:00~19:00</option>
													<option value="19">19:00~21:00</option>									
											</select></td>
										</tr>
										<tr>
											<td colspan="2"><label for="maxNum">최대인원 &nbsp; &nbsp; </label>
											<input type="text" name="MaxNumOfPeople" id="maxNumOfPeople"
												class="inputBox" disabled readonly value=${MaxNumOfPeople}><label>&nbsp;명</label>
											</td>
										</tr>
										<tr>
											<td colspan="2"><label for="reservationNum">예약인원 &nbsp; &nbsp; </label>
											<input type="text" name="ReservationNumOfPeople" id="reservationNumOfPeople"
												class="inputBox" value=${ReservationNumOfPeople}><label>&nbsp;명</label>
											<td>
										</tr>
									</table>
									<hr>
								</div>
								<!-- section2 -->
								<div>
									<input type="submit" value="수정" id="modifyButton">
									<a href="${path}/lectureRoom/lectureRoomList"><input type="button" value="이전" id="listButton"></a>
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