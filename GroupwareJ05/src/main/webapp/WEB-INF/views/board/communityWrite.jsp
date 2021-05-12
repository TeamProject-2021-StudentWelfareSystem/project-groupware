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

<title>community write</title>
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
							<h2>글 작성하기</h2>
							<hr>
						</div>
					</section>
					<section>
						<form
							action="communityWrite?${_csrf.parameterName}=${_csrf.token}"
							name="CommunityWrite" enctype="multipart/form-data" method="POST"
							id="form">
							
							<div class="section2">
								<table id="contentTable">
									<tr>
										<td><label for="title">제목 &nbsp; &nbsp; </label><input
											type="text" name="CommunityTitle" id="communityTitle"
											class="inputBox" value=${CommunityTitle}></td>
									</tr>
									<tr>
										<td><label for="writer">작성자 &nbsp; </label><input
											type="text" name="CommunityWriter" id="communityWriter"
											class="inputBox" value=${CommunityWriter}></td>
									</tr>
									<tr>
										<td><textarea name="CommunityContent"
												id="communityContent" class="inputBox"
												placeholder="내용을 입력하세요"></textarea></td>
									</tr>

								</table>
								<hr>

							</div>
							<!-- section2 -->
							<div>
								<input type="file" name="UploadFile" value="파일찾기"> <br>
								<br> <input type="submit" value="저장" id="saveButton">
								<a href="${path}/communityList"><input type="button"
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