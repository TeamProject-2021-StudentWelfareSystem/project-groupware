<!-- 강의실 리스트 화면 -->

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
<!-- ajax 통신을 위한 meta tag -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/imageOption1.css" type="text/css">
<link rel="stylesheet" href="../css/lectureRoomList.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/lectureRoomList.js"></script>

<title>manage page</title>
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
							<h2>&nbsp;강의실 예약</h2>
							<hr>
						</div>
					</section>
					<section>
						<div class="section2">

							
							<div id="search">
								<select name="SelectOption" id="selectOption">
									<option value="all">전체</option>
								</select> <input type="text" placeholder="검색어 입력하세요."> <input
									type="submit" value="검색">
							</div>
							<h3>&nbsp; 대여 가능한 강의실 리스트</h3>
							<form action="${path}/lectureRoom/lectureRoomList.do"
								name="lectureRoomList" method="POST" id="form">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table class="userList">
									<thead>
										<tr>
											<th id="tWidth">번호</th>
											<th id="tWidth">강의실 번호</th>
											<th id="tWidth">강의실 위치</th>
											<th id="tWidth">층</th>
											<th id="tWidth">강의실 종류</th>
											<th id="tWidth">최대 인원</th>
										</tr>
										<hr>
									</thead>

									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr>
												<td><c:out value="${status.count}" /></td>
												<td><a
													href="${path}/lectureRoom/reservation?no=${list.getLectureRoomNo()}">
														<c:out value="${list.getLectureRoomNo()}" />
												</a></td>
												<td><a
													href="${path}/lectureRoom/reservation?no=${list.getLectureRoomNo()}">
														<c:out value="${list.getRoomLocation()}" />
												</a></td>
												<td><c:out value="${list.getRoomFloor()}" /></td>
												<td><c:out value="${list.getRoomType()}" /></td>
												<td><c:out value="${list.getMaxNumOfPeople()}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- js로 옮겨주세요 -->
								<script>
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
								</script>
								<hr>
								<div id="page" class="btn">
									<div id="auth"></div>
									<input type="button" value="←" id="leftList"> <input
										type="button" value="1" id="pageList"> <input
										type="button" value="→" id="rightList"> 
								</div>
							</form>
						</div>
						<!-- section2 -->
					</section>
				</div>
				<!-- right_box -->
			</div>
			<!-- mcont_width -->
		</div>
		<!-- mbody -->
	</nav>
</body>
</html>