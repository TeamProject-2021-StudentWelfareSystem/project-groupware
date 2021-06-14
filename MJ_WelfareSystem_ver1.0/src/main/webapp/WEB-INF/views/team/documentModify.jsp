<!-- 자료 관리 수정 화면 -->

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
<link rel="stylesheet" href="../css/documentBoardContent.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/boardContent.js"></script>

<title>document modify</title>
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
							<h2>팀 자료 수정하기</h2>
							<hr>
						</div>
					</section>
					<section>
						<form 
							action="documentModify.do?${_csrf.parameterName}=${_csrf.token}"
							name="DocumentModify" method="POST" enctype="multipart/form-data"
							id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr>
										<td><label for="title">제목 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="BoardSubject"
											id="boardSubject" class="inputBox" value="${BoardSubject}">
										</td>
									</tr>
									<tr>
										<td><label for="writer">작성자 &nbsp; </label></td>
										<td><input type="text" name="BoardWriter"
											id="boardWriter" class="inputBox" value="${BoardWriter}">
											<input type="text" name="BoardDate" id="boardDate"
											class="inputBox" placeholder="날짜" disabled readonly
											value="${BoardDate}"></td>
									</tr>
									<tr id="content">
										<td colspan="2" id="content"><textarea
												name="BoardContent" id="boardContent" class="inputBox"
												placeholder="${BoardContent}"></textarea></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td id="fileIndex">
										<c:forEach var="TeamBoardFile"
												items="${TeamBoardFile}" varStatus="var">
												<div>
                                    <input type="hidden" id="fileID" name="TFileID_${var.index}"value="${TeamBoardFile.TFileID}"> 
						<input	type="hidden" id="originalFileName" name="FILE_NAME" value="TFileID_${var.index}">
						<a href="#" id="TfileName" onclick="return false;">${TeamBoardFile.TOriginalFileName}
						</a>(${TeamBoardFile.TFileSize}kb)
						<input type="button" id="fileDel" onclick="fnDel('${TeamBoardFile.TFileID}','TFileID_${var.index}');"value="삭제"> <br>
												</div>
											</c:forEach></td>
									</tr>
									<tr>
										<td><label for="attachment">첨부파일</label></td>
										<td><input type="file" name="BoardFile" id="boardFile"
											class="inputBox" placeholder="파일을 첨부하세요."></td>
										<td><input type="button" id="fileAddButton" value="파일추가"
											onclick="FileNameAddFile()"></td>
									</tr>
								</table>
							</div>
							<!-- section2 -->
							<div id="btn">
								<input type="submit" value="수정 완료" id="listButton">
								 <a href="${path}/team/myTeamList"> 
								 <input type="button" value="목록" id="listButton"></a> 
								 <input type="hidden" id="fileDeleteList" name="FileDeleteList[]" value=""> 
								 <input type="hidden" id="fileDeleteNameList" name="FileDeleteNameList[]" value=""> 
								 <input type="hidden" id="tBoardID" name="TBoardID" value="${TBoardID}">
								  <input type="hidden" id="teamID" name="TeamID" value="${TeamID}">
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