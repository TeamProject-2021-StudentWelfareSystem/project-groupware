<!-- 문의 내용 출력 화면 -->

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

<title>inquiry content</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">

					<!-- left_box -->
					<div class="rightBox">
						<section>
							<div class="section">
								<br>
								<h2>게시판</h2>
								<hr>
							</div>
						</section>
						<section>
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr id="title">
										<td><input type="text" name="InquiryTitle"
											id="inquiryTitle" class="inputBox" placeholder="제목" disabled
											readonly value=${InquiryTitle}></td>
									</tr>
									<tr id="receive">
										<td colspan="2"><input type="text" name="InquiryWriter"
											id="inquiryWriter" class="inputBox" placeholder="작성자"
											disabled readonly value=${InquiryWriter}> <input
											type="text" name="Date" id="date" class="inputBox"
											placeholder="날짜" disabled readonly value=${BulletinDate}>
										</td>
									</tr>
									<tr id="content">
										<td><textarea name="InquiryContent" id="inquiryContent"
												class="inputBox" placeholder="내용" readonly></textarea> <c:out
												value="${InquiryContent}" escapeXml="false" /></td>
									</tr>
								</table>
								<hr>

							</div>
							<!-- section2 -->
						</section>
					</div>
					<!-- right_box -->
					<div id="btn">
						<a href="inquiryList"><input type="button" value="목록"
							id="listButton"></a>
						<!-- 접속한 UserID와 해당 글을 작성한 UserID가 같을 때 수정/삭제 버튼 보이게 하기 -->
						<c:if test="${list.UserLoginID == board.UserID}">
							<a href="${path}/inquiryModify"><button type="button"
									id="modifyButton">수정</button></a>
							<button type="button" id="deleteButton" onClick="boardDelete()">삭제</button>
						</c:if>
					</div>

				</div>
				<!-- mcont_width -->
			</div>
			<!-- mbody -->
		</nav>
</body>
</html>