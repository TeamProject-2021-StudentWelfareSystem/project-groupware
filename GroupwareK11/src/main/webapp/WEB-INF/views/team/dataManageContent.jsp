<!-- 자료 관리 출력 화면 -->

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
<link rel="stylesheet" href="../css/boardContent.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">

<title>data management content</title>
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
							<h2>팀 자료</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="DataDelete.do" name="DataDelete" method="POST" id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr id="title">
										<td><input type="text" name="BoardSubject"
											id="boardSubject" class="inputBox" placeholder="제목" disabled
											readonly value=${BoardSubject}></td>
									</tr>
									<tr id="receive">
										<td colspan="2"><input type="text" name="BoardWriter"
											id="boardWriter" class="inputBox" placeholder="작성자" disabled
											readonly value=${BoardWriter}> <input type="text"
											name="BoardDate" id="boardDate" class="inputBox"
											placeholder="날짜" disabled readonly value=${BoardDate}>
										</td>
									</tr>
									<tr id="content">
										<td><textarea name="BoardContent" id="boardContent"
												class="inputBox" placeholder="내용" disabled readonly></textarea>
											<c:out value="${BoardContent}" escapeXml="false" /></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td><label for="attachment">첨부파일</label></td>
										<td><input type="text" class="attachedFile"
											name="AttachedFile" id="attachedFile"></td>
									</tr>
								</table>
							</div>


							<div id="btn">
								<a href="meetingLogList"><input type="button" value="목록"
									id="listButton"></a>
								<!-- 접속한 UserID와 해당 글을 작성한 UserID가 같을 때 수정/삭제 버튼 보이게 하기 -->
								 <c:set var = "UserID" value = "${UserID}"/>
								 <c:set var = "UserIDFromWriter" value = "${UserIDFromWriter}"/>
								 <c:if test="${UserID == UserIDFromWriter}">
									<a href="${path}/team/dataManageModify?boardID=${BoardID}"><button type="button" id="modifyButton">수정</button></a>
									<button type="submit" id="deleteButton">삭제</button>
								</c:if>
							</div>
							</form>
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