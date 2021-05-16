<!-- 문의 수정 화면 -->

<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");
%>


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


<title>inquiry modify</title>
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
						<form action="InquiryModify.do" name="InquiryModify" method="POST"
							id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr>
										<td><label for="title">제목 &nbsp; &nbsp; </label></td>
										<td><input
											type="text" name="InquiryTitle" id="inquiryTitle"
											class="inputBox" value=${Inquiryitle}></td>
									</tr>
									<tr>
										<td><label for="writer">작성자 &nbsp; </label></td>
										<td><input
											type="text" name="InquiryWriter" id="inquiryWriter"
											class="inputBox" value=${InquiryWriter}>
											<input
											type="text" name="Date" id="date" class="inputBox"
											placeholder="날짜가 자동으로 입력됩니다." disabled readonly value=${BoardDate}></td>
									</tr>
									<tr id="content">
											<td colspan="2" id="content"><input type="text"
												name="InquiryContent" id="inquiryContent"
												class="inputBox" placeholder="내용을 입력하세요"
												value=${InquiryContent}></td>
										</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td><label for="attachment">첨부파일</label></td>
										<td><input type="file" name="UploadFile" id="boardFile"
												class="inputBox" placeholder="파일을 첨부하세요."></td>
									</tr>
									<tr>
										<td id="fileIndex">
									<c:forEach var="InquiryFile" items="${InquiryFile}" varStatus="var">
									<div>
										<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${InquiryFile.BFileID}">
										<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
										<a href="#" id="fileName" onclick="FileDown('${InquiryFile.BFileID}'); return false;">${InquiryFile.BOriginalFileName}</a>(${InquiryFile.BFileSize}kb)
										<input type="button" id="fileDel" onclick="fnDel'${InquiryFile.BFileID}','FILE_NO_${var.index}');" value="삭제">
										<br>
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
									href="${path}/inquiryList"><input type="button" value="목록"
									id="listButton"></a>
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
</body>
</html>