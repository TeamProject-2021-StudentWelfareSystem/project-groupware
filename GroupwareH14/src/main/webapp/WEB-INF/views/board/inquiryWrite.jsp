<!-- 문의 작성 화면 -->


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
<link rel="stylesheet" href="css/boardContent.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">

<title>inquiry write</title>
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
							<li><a href="myPageStudent">마이페이지</a></li>
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
							<li id="homeTab" class="tMenu"><a href="homeLogin"><span
									class="tmenuPackMover">홈</span></a></li>
							<li id="mailTab" class="tMenu"><a href="emailLogin"><span
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

					<!-- left_box -->
					<div class="rightBox">
						<section>
							<div class="section">
								<br>
								<h2>글 작성하기</h2>
								<hr>
							</div>
						</section>
						<section>
							<form action="InquiryWrite.do" name="InquiryWrite"
								method="POST" id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table id="contentTable">
										<tr>
											<td><label for="title">제목 &nbsp; &nbsp; </label><input
												type="text" name="InquiryTitle" id="inquiryTitle"
												class="inputBox" value=${InquiryTitle}></td>
										</tr>
										<tr>
											<td colspan="2"><label for="writer">작성자 &nbsp; </label><input
												type="text" name="InquiryWriter" id="inquiryWriter"
												class="inputBox" value=${InquiryWriter}>
												<label for="type">&nbsp;&nbsp; 민원분류 선택 &nbsp; </label>
											<select name="InquiryType" id="inquiryType">
												<option value="" selected>-선택-</option>
												<option value="lectureRoom">강의실 예약/이용</option>
												<option value="teamMembers">팀원관리</option>
												<option value="community">커뮤니티</option>
												<option value="review">후기</option>
												<option value="suggestion">건의</option>
											</select>
											</td>
										</tr>
										<tr>
											<td><label for="email">이메일 &nbsp; </label><input
												type="text" name="InquiryEmail" id="inquiryEmail"
												class="inputBox" value=${InquiryEmail}></td>
										</tr>
										<tr>
											<td><label for="phoneNum">연락처 &nbsp; </label><input
												type="text" name="InquiryPhoneNum" id="inquiryPhoneNum"
												class="inputBox" value=${InquiryPhoneNum}></td>
										</tr>
										<tr>
											
										</tr>
										<tr>
											<td><textarea name="InquiryContent" id="inquiryContent"
												class="inputBox" placeholder="내용을 입력하세요"></textarea></td>
										</tr>
									</table>
									<hr>

								</div>
								<!-- section2 -->
								<div>
									<input type="submit" value="저장" id="listButton"> <a
										href="${path}/inquiryList"><input type="button"
										value="이전" id="listButton"></a>
								</div>
							</form>
						</section>
					</div>
					<!-- right_box -->

				</div>

			</div>
			<!-- mcont_width -->
	</div>
	<!-- mbody -->
	</nav>
	</div>
	<!-- mjs_ws -->
</body>
</html>