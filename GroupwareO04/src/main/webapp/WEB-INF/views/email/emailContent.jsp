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
<link rel="stylesheet" href="../css/emailContent.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">

<title>email content</title>
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
											value="${EmailTitle}"></td>
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
</body>
</html>