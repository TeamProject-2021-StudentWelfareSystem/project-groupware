<!-- 게시판 내용 출력 화면 -->

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
<link rel="stylesheet" href="css/boardImageOption2" type="text/css">
<link rel="stylesheet" href="css/boardContent.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/boardContent.js"></script>

<title>community content</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>


	<nav>
		<div class="mbody">
			<div class="mcontWidth">
				<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
				<div class="rightBox">
					<section>
						<div class="section">
							<br>
							<h2>커뮤니티</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="CommunityDelete.do?boardID=${BoardID}"
							name="CommunityDelete" method="POST" id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr id="title">
										<td><input type="text" name="CommunityTitle"
											id="communityTitle" class="inputBox" placeholder="제목"
											disabled readonly value="${CommunityTitle}"></td>
									</tr>
									<tr id="receive">
										<td colspan="2"><input type="text" name="CommunityWriter"
											id="communityWriter" class="inputBox" placeholder="작성자"
											disabled readonly value="${CommunityWriter}"> <input
											type="text" name="Date" id="date" class="inputBox"
											placeholder="날짜" disabled readonly value="${BoardDate}">
										</td>
									</tr>
									<tr id="content">
										<td><textarea name="CommunityContent"
												id="communityContent" class="inputBox"
												placeholder="${CommunityContent}" disabled readonly></textarea>
											<c:out value="" escapeXml="false" /></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td><label for="attachment">첨부파일 :</label><br></td>

										<td><c:forEach var="CommunityFile"
												items="${CommunityFile}">
												<a href="#"
													onclick="FileDown('${CommunityFile.BFileID}'); return false;">${CommunityFile.BOriginalFileName}</a>(${CommunityFile.BFileSize}kb)
										</c:forEach></td>
									</tr>
								</table>
							</div>
							<div id="btn">
								<a href="communityList"><input type="button" value="목록"
									id="listButton"></a>
								<!-- 접속한 UserID와 해당 글을 작성한 UserID가 같을 때 수정/삭제 버튼 보이게 하기 -->
								<c:set var="UserID" value="${UserID}" />
								<c:set var="UserIDFromWriter" value="${UserIDFromWriter}" />
								<c:if test="${UserID == UserIDFromWriter}">
									<a href="${path}/communityModify?no=${BoardID}"><button
											type="button" id="modifyButton" >수정</button></a>
									<button type="submit" id="deleteButton" onclick="if(!confirm('삭제 하시겠습니까?')){return false;}">삭제</button>
								</c:if>
							</div>
						</form>
					</section>
						<form name="readForm" role="form" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="hidden" id="bFileID"
							name="BFileID" value="">
					</form>
				</div>
				<!-- right_box -->
			</div>
			<!-- mcont_width -->
		</div>
		<!-- mbody -->
	</nav>
</body>
</html>