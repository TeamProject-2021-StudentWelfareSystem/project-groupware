<!-- 마이페이지 화면 (교수) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/myPage.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">
<script src="js/jquery-3.5.1.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>my page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">
					<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
					<div class="rightBox">
						<div class="subMenuBox">
							<ul>
								<li><a href="${path}/myPage?R=${UserRole}">내 정보 확인</a></li>
								<li><a href="${path}/myPostList">내 게시글 조회</a></li>
								<li><a href="${path}/confirmMyReservation">강의실 예약 조회</a></li>
								<li><a href="${path}/myInquiryList">내 문의 조회</a></li>
							</ul>
						</div>
						<section id="memberInfo">
							<h2>회원 정보</h2>
							<br>
							<!--마이페이지 (교수)-->
							<div id="myPageProfessor" style="display: block;">

								<form action="${path}/myPageProfessor.do" name="ProfessorPage" method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table>
										<tr>
											<td class="col1"><label for="id">아이디(학번)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
											<td class="col2"><input type="text" name="UserLoginID"
												id="userLoginID" class="inputBox" placeholder="" autofocus
												autocomplete="off" disabled readonly value=${UserLoginID}></td>

										</tr>
										<tr>
											<td class="col1"><label for="name">이름</label></td>
											<td class="col2"><input type="text" name="UserName"
												id="userName" class="inputBox" autocomplete="off" disabled
												readonly value=${UserName}></td>
										</tr>
										
										<tr>
											<td class="col1"><label for="phoneNumber">연락처</label></td>
											<td class="col2"><input type="text" name="UserPhoneNum"
												id="userPhoneNum" class="inputBox" autocomplete="off"
												disabled readonly value=${UserPhoneNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="ProfessorColleges">단과대학</label></td>
											<td class="col2"><input type="text"
												name="ProfessorColleges" id="professorColleges" class="inputBox"
												disabled readonly value=${ProfessorColleges}></td>
										</tr>
										<tr>
											<td class="col1"><label for="ProfessorMajor">전공</label></td>
											<td class="col2"><input type="text" name="ProfessorMajor"
												id="professorMajor" class="inputBox" disabled readonly
												value=${ProfessorMajor}></td>
										</tr>
										<tr>
											<td class="col1"><label for="Room">교수실</label></td>
											<td class="col2"><input type="text" name="ProfessorRoom"
												id="professorRoom" class="inputBox" autocomplete="off" disabled
												readonly value=${ProfessorRoom}></td>
										</tr>
										<tr>
											<td class="col1"><label for="RoomNum">교수실 전화번호</label></td>
											<td class="col2"><input type="text" name="ProfessorRoomNum"
												id="professorRoomNum" class="inputBox" autocomplete="off" disabled
												readonly value=${ProfessorRoomNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="email">이메일</label></td>
											<td class="col2"><input type="text" name="UserEmail"
												id="userEmail" class="inputBox" autocomplete="off" disabled
												readonly value=${UserEmail}></td>
											<td class="col4"><input type="email" value="@mju.ac.kr"
												disabled readonly name="email" id="mju" class="inputBox"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td class="col1"><label for="infoOpen">정보 공개</label></td>
											<td class="col2"><input type="text" name="UserInfoOpen"
												id="userInfoOpen" class="inputBox" autocomplete="off"
												disabled readonly value=${UserInfoOpen}></td>
										</tr>
									</table>
								</form>
							</div>
							<br> <br>
							<div id="btnDiv">
								<a href="checkPassword"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyBtn" value="수정하기">
								</a> <a href="modifyPassword"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyPWBtn" value="비밀번호 변경하기">
								</a> <a href="checkPassword2"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="withdrawBtn" value="회원 탈퇴">
								</a>
							</div>
						</section>
					</div><!-- right_box -->
			</div><!-- mcont_width -->
		</div><!-- mbody -->
	</nav>
</div><!-- mjs_ws -->
</body>
</html>