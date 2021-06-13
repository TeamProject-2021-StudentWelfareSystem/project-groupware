<!-- 페이지 상단 메뉴바 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/homeLayout.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
</head>
<body>
	<div class="mheader">
		<!--메뉴바 -->
		<div class="menubar">
			<div class="menubarWidth">
				<div class="menubarMid">
					
					<nav id="navigation1">
						<div class="menubarLogo">
							<img id="logo1" src="../resources/image/logo.png">
							<img id="title1" src="../resources/image/title.png">
							<img id="logo2" src="resources/image/logo.png">
							<img id="title2" src="resources/image/title.png">
						</div>
						<ul id="topInfo">
							<!-- sign in -->
							<li><sec:authorize access="isAnonymous()">
									<a href="${path}/login">로그인</a>
								</sec:authorize>
								<form id="signUp" action="/login" method="POST">
									<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
								</form></li>
							<!-- sign out -->
							<li><sec:authorize access="isAuthenticated()">
									<a href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
								</sec:authorize>
							<form id="logout" action="${path}/logout.do" method="POST">
									<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /></form></li>
							<li><a href="${path}/inquiryList">문의</a></li>
							<li><sec:authorize access="isAuthenticated()"><a href="${path}/myPage?R=${UserRole}">마이페이지</a></sec:authorize></li>
                       		<li><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="${path}/admin/manageList">관리자 메뉴</a></sec:authorize></li>
						</ul>
					</nav>
					<!-- 메뉴 -->
					<nav id="navigation2">
						<ul id="topMenu">
							<li><a href="${path}/home">홈</a></li>
							<li><a href="${path}/email/emailLogin">메일</a></li>
							<li><a href="${path}/noticeList">게시판</a>
								<ul id="subMenu">
									<li><a href="${path}/noticeList">공지사항</a></li>
									<li><a href="${path}/communityList">커뮤니티</a></li>
								</ul></li>
							<li><a href="${path}/lectureRoom/lectureRoomList">강의실</a><ul id="subMenu">
									<li><a href="${path}/lectureRoom/lectureRoomList">강의실 예약</a></li>
									<li><a href="${path}/lectureRoom/reservationConfirm">예약확인</a></li>
								</ul></li>
							<li><a href="${path}/schedule/mySchedule">일정</a></li>
							<li><a href="${path}/search/searchUser">사용자 검색</a></li>
							<li><a href="${path}/team/teamList">&nbsp;&nbsp; 팀 &nbsp;&nbsp;</a><ul id="subMenu">
									<li><a href="${path}/team/searchLecture">팀 생성</a></li>
									<li><a href="${path}/team/teamList">팀 조회</a></li>
									<li><a href="${path}/team/searchMyTeam">후기 작성</a></li>
								</ul></li>
								<li><a href="${path}/team/myTeamList">문서</a></li>
						</ul>
					</nav>
				</div>
				<!-- menubar_mid -->
			</div>
			<!-- menubar_width -->
		</div>
		<!-- menubar -->
	</div><!-- mheader -->

</body>
</html>