<!-- 회의록 수정 화면 -->

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
<link rel="stylesheet" href="css/boardContent.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">

<title>meeting log modify</title>
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
								<h2>회의록 수정하기</h2>
								<hr>
							</div>
						</section>
						<section>
							<form action="MeetingLogModify.do" name="MeetingLogModify" method="POST" id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<table id="contentTable">
										<tr>
											<td><label for="title">제목 &nbsp; &nbsp; </label></td>
											<td><input type="text" name="BoardSubject" id="boardSubject"
												class="inputBox" value=${BoardSubject}>
											</td>
										</tr>
										<tr>
											<td><label for="writer">작성자 &nbsp; </label></td>
											<td><input type="text" name="BoardWriter" id="boardWriter"
												class="inputBox"  value=${BoardWriter}>
												<input type="text" name="BoardDate" 
												id="boardDate" class="inputBox" placeholder="날짜" 
												disabled readonly value=${BoardDate}>
											</td>
										</tr>
										<tr id="content">
											<td colspan="2" id="content">
											<textarea name="BoardContent" id="boardContent"
												class="inputBox" placeholder="내용을 입력하세요"></textarea>
											</td>
										</tr>
									</table>
								</div>
								<!-- section2 -->
								<div>
									<input type="submit" value="수정 완료" id="listButton"> 
									<a href="${path}/meetingLogList">
									<input type="button" value="목록" id="listButton"></a>
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
</body>
</html>