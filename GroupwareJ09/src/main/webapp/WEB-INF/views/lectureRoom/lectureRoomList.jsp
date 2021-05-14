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
<link rel="stylesheet" href="css/lectureRoomList.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">
<script src="js/jquery-3.5.1.min.js"></script>

<title>manage page</title>
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
							<div class="userName"><input type=text name="UserName" id="userName"value=${UserName} disabled readonly>님<br>안녕하세요!</div>
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
								<h2>&nbsp;강의실 관리</h2>
								<hr>
							</div>
						</section>
						<section>
							<div class="section2">

								<h3>&nbsp; 대여 가능한 강의실 리스트</h3>
								<div id="search">
									<select name="SelectOption" id="selectOption">
										<option value="all">전체</option>
									</select> <input type="text" placeholder="검색어 입력하세요."> <input
										type="submit" value="검색">
								</div>
								<form action="${path}/lectureRoomList.do" name="lectureRoomList"
									method="POST" id="form">
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
														href="${path}/reservation?no={list.getLectureRoomNum()}">
														<c:out value="${list.getLectureRoomNum()}" /></a></td>
													<td><a href="${path}/reservation?no={list.getLectureRoomNum()}">
														<c:out value="${list.getLectureRoomLocation()}" /></a></td>
													<td><c:out value="${list.getFloor()}" /></td>
													<td><c:out value="${list.getLectureRoomType()}" /></td>
													<td><c:out value="${list.getMaximumPeople()}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<hr>
									<div id="page" class="btn">
										<div id="auth">
										 
											
										</div>
										<input type="button" value="←" id="leftList"> 
										<input type="button" value="1" id="pageList"> 
										<input type="button" value="→" id="rightList"> 
										<a href="${path}/reservationConfirm"><input type="button" value="예약 확인" id="list"></a>
										<a href="${path}/reservationModify"><input type="button" value="예약 수정" id="list"></a>
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