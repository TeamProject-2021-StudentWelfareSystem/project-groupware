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
<link rel="stylesheet" href="css/boardContent.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/boardContent.js"></script>


<title>community modify</title>
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
							<h2>글 수정하기</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="CommunityModify.do?boardID=${BoardID}"
							name="CommunityModify" method="POST" id="form" enctype="multipart/form-data">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr>
										<td><label for="title">제목 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="CommunityTitle"
											id="communityTitle" class="inputBox"
											value="${CommunityTitle}"></td>
									</tr>
									<tr>
										<td><label for="writer">작성자 &nbsp; </label></td>
										<td><input type="text" name="CommunityWriter"
											id="communityWriter" class="inputBox"
											value="${CommunityWriter}"> <input type="text"
											name="Date" id="date" class="inputBox"
											placeholder="날짜가 자동으로 입력됩니다." disabled readonly
											value="${BoardDate}"></td>
									</tr>
									<tr id="content">
										<td colspan="2" id="content"><textarea
												name="CommunityContent" id="communityContent"
												class="inputBox" placeholder="${CommunityContent}"></textarea></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td><label for="attachment">첨부파일</label></td>
										<td><input type="file" name="UploadFile" id="boardFile1"
												class="inputBox" placeholder="파일을 첨부하세요." ></td>
									</tr>
									<tr>
										<td id="fileIndex">
											<c:forEach var="CommunityFile" items="${CommunityFile}" varStatus="var">
												<div>
													<input type="hidden" id="FILE_NO" name="BFileID_${var.index}" value="${CommunityFile.BFileID}">
													<input type="hidden" id="FILE_NAME" name="BStoredFileName" value="BFileID_${var.index}">
													<a href="#" onclick="FileDown('${CommunityFile.BFileID}'); return false;">${CommunityFile.BOriginalFileName}</a>(${CommunityFile.BFileSize}kb)
													<button id="fileDel" onclick="fn_del('${CommunityFile.BFileID}','FILE_NO_${var.index}');" type="button">삭제</button><br>
												</div>
											</c:forEach>
										</td>
									</tr>
									<tr>
									<td><input type="button" id="fileAddButton" value="파일추가" onclick="FileNameAddFile()"></td>
									</tr>
									</table>
							</div>
							<!-- section2 -->
							<div>
								<input type="submit" value="수정 완료" id="listButton"> <a
									href="${path}/communityList"><input type="button"
									value="목록" id="listButton"></a>
							</div>

							<input type="hidden" id="FileList" name="FileList[]" value="">
							<input type="hidden" id="FileNameList" name="FileNameList[]" value="">

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
</body>
</html>