<!-- 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 -->

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/userManage.css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/userManage.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>user info management</title>
</head>

<body>

	<div class="mjsWs">

		<div class="mheader">
			<!--배경화면-->
			<div id="mjsFilm"
				style="z-index: 99997; position: absolute; display: none; width: 100%; height: 100%; background-color: #000000; filter: Alpha(opacity = 60); opacity: 0.4; -moz-opacity: 0.6;"></div>
			<!--메뉴 -->
			<div id="authCheckDiv" align="center"
				style="z-index: 99999; position: absolute;"></div>
			<div class="menubar">
				<div class="menubarWidth">
					<div class="menubarLogo">
						<!--로고추가하기-->
					</div>
					<div class="menubarMid">
						<ul class="topInfo">
							<li><a href="">사이트맵</a></li>
							<li><a href="manageList">관리자메뉴</a></li>
							<li><a href="">문의</a></li>
							<!-- sign out -->
							<li><sec:authorize access="isAuthenticated()">
									<a href="#"
										onclick="document.getElementById('logout').submit();">로그아웃</a>
								</sec:authorize>
								<form id="logout" action="${path}/logout.do" method="POST">
									<input name="${_csrf.parameterName}" type="hidden"
										value="${_csrf.token}" />
								</form></li>
						</ul>
						<!-- 메뉴 -->
						<ul class="topMenu">
							<li id="homeTab" class="tMenu"><a href="${path}/admin/homeAdmin"><span
									class="tmenuPackMover">홈</span></a></li>
							<li id="mailTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">메일</span></a></li>
							<li id="gboardTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">게시판</span></a></li>
							<li id="rectureRoomTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">강의실</span></a></li>
							<li id="schedulingTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">일정관리</span></a></li>
							<li id="memoTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">메모</span></a></li>
							<li id="inquiryTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">조회</span></a></li>
							<li id="teammatesTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">팀원관리</span></a></li>
							<li id="documentsTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">문서관리</span></a></li>
						</ul>
					</div>
					<!-- menubar_mid -->
				</div>
				<!-- menubar_width -->
			</div>
			<!-- menubar -->
		</div>
		<!-- mheader -->
		<nav>
			<div class="mbody">
				<div class="mcontWidth">
					<div class="leftBox">
						<div class="leftInfo">
							<!--로그인 후 화면-->
							<div class=img>
								<img src="user.png" alt="userimg" width="50" height="50">
							</div>
							<br>
							<div class=userName>님, 안녕하세요!</div>
							<div class="userColleges">
								<h4 color="blue">소속 :</h4>
							</div>
							<div class="userGrade">
								<h4 color="blue">학년 :</h4>
							</div>

						</div>
					</div>
					<div class="rightBox">
						<section id="section">
							<div class="section">
								<h2>&nbsp;사용자 관리</h2>
								<hr>
							</div>
						</section>
						<section id="section2">
							<!-- 학생 사용자 -->
							<div id="studentInfo">
								<h3>&nbsp;사용자 정보</h3>
								<form action="" name="ModifyStudent" method="POST" id="form">
									<table>
										<tr>
											<td class="col1"><label for="id">&nbsp;아이디(학번)&nbsp;</label></td>
											<td class="col2"><input type="text" name="UserLoginID"
												id="userLoginID" class="inputBox" placeholder="" autofocus
												autocomplete="off" disabled readonly value=${UserLoginID}></td>
											<td class="col1"><label for="name">이름</label></td>
											<td class="col2"><input type="text" name="UserName"
												id="userName" class="inputBox" autocomplete="off" disabled
												readonly value=${UserName}></td>
										</tr>
										<tr>
											<td class="col1"><label for="gender">성별</label></td>
											<td class="col2"><input name="StudentGender"
												id="studentGender" class="inputBox" disabled readonly
												value=${StudentGender}></td>
											<td class="col1"><label for="phoneNumber">연락처</label></td>
											<td class="col2"><input type="text" name="UserPhoneNum"
												id="userPhoneNum" class="inputBox" autocomplete="off"
												disabled readonly value=${UserPhoneNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="grade">학년</label></td>
											<td class="col2"><input name="StudentGrade"
												id="studentGrade" class="inputBox" disabled readonly
												value=${StudentGrade}></td>
											<td class="col1"><label for="studentColleges">단과대학</label></td>
											<td class="col2"><input name="StudentColleges"
												id="studentColleges" class="inputBox" disabled readonly
												value=${StudentColleges}></td>
										</tr>
										<tr>
											<td class="col1"><label for="studentMajor">전공</label></td>
											<td class="col2"><input name="StudentMajor"
												id="studentMajor" class="inputBox" disabled readonly
												value=${StudentMajor}></td>
											<td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
											<td class="col2"><label for="member_DoubleMajor"></label>
												<input name="StudentDoubleMajor" id="studentDoubleMajor"
												class="inputBox" disabled readonly
												value=${StudentDoubleMajor}></td>
										</tr>
										<tr>
											<td class="col1"><label for="email">이메일</label></td>
											<td class="col2"><input type="text" name="UserEmail"
												id="userEmail" class="inputBox" autocomplete="off" disabled
												readonly value=${UserEmail}></td>
											<td class="col3"><input type="email" value="@mju.ac.kr"
												disabled readonly name="email" id="mju" class="inputBox"
												autocomplete="off"></td>
										</tr>
										<c:forEach items="${UserInfoOpen}" var="UserInfoOpen"
											varStatus="status">
											<tr>
												<td class="col1"><label for="infoOpen">정보 공개</label></td>
												<td class="col2"><input type="text" name="UserInfoOpen"
													id="userInfoOpen" class="inputBox" autocomplete="off"
													disabled readonly> <c:out
														value="${UserInfoOpen.getOpenName()}" /> <c:out
														value="${UserInfoOpen.getOpenEmail()}" /> <c:out
														value="${UserInfoOpen.getOpenPhoneNum()}" /> <c:out
														value="${UserInfoOpen.getOpenMajor()}" /> <c:out
														value="${UserInfoOpen.getOpenGrade()}" /></td>
											</tr>
										</c:forEach>
									</table>
								</form>
							</div>
							<!-- studentInfo -->
							<div id="btnDiv">
								<input type="button" id="deleteBtn" value="삭제"> <a
									href="manageModifyStudent"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="listBtn" value="수정">
								</a> <a href="manageList"><input type="button" id="listBtn"
									value="목록"></a>
							</div>
						</section>
					</div>
					<!-- right_box -->
				</div>
				<!-- mcont_width -->
			</div>
			<!-- mbody -->
		</nav>
	</div>
	<!-- mjs_ws -->
</body>
</html>