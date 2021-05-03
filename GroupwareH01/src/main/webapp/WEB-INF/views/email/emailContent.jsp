<!-- 이메일 내용 출력 화면 -->

<% response.setHeader("Cache-Control","no-store"); response.setHeader("Pragma","no-cache"); response.setDateHeader("Expires",0); if (request.getProtocol().equals("HTTP/1.1")) response.setHeader("Cache-Control", "no-cache"); %>


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
<link rel="stylesheet" href="css/emailContent.css" type="text/css">

<title>email content</title>
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
							<li id="homeTab" class="tMenu"><a href="homeLogin"><span
									class="tmenuPackMover">홈</span></a></li>
							<li id="mailTab" class="tMenu"><a href="emailList"><span
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
								<h2>이메일</h2>
								<hr>
							</div>
						</section>
						<section>
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr id="title">
										<td><input type="text" name="EmailTitle" id="emailTitle"
											class="inputBox" placeholder="제목" disabled readonly
											value=${EmailTitle}></td>
									</tr>
									<tr id="receive">
										<td colspan="2"><input type="text" name="EmailSender"
											id="emailSender" class="inputBox" placeholder="보낸 이" disabled
											readonly value=${EmailSender}> <input type="text"
											name="EmailDate" id="emailDate" class="inputBox"
											placeholder="날짜" disabled readonly value=${EmailDate}></td>
									</tr>
									<tr id="content">
										<td>														
					                 <c:out value="${EmailContent}" escapeXml="false" />

									</tr>
								</table>
								<hr>
								<a href="emailList"><input type="button" value="목록"
									id="listButton"></a>
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
	</div>
	<!-- mjs_ws -->
</body>
</html>