<!-- 후기 작성 화면 -->

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
<link rel="stylesheet" href="../css/reviewContent.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>

<title>review content</title>
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
								<h2>후기</h2>
								<hr>
							</div>
						</section>
						<section>
						<span id="description">후기는 익명을 원칙으로 하여 개인정보가 공개되지 않습니다.<br><br><br></span>
						
							   <form action=""
			                     name="ReviewContent" enctype="multipart/form-data" method="POST"
			                     id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table id="contentTable">
									<!-- 검색한 사용자의 이름과 과목명 출력 -->
										<tr>
										<td><label for="SearchName">이름 : &nbsp; &nbsp;</label></td>
										<td colspan="5"><input type="text" id="searchName" name="SearchName" value="${SearchName}" disabled readonly></td>
										</tr>
										<tr>
										<td><label for="ClassName">과목명 : &nbsp; &nbsp;</label></td>
										<td colspan="5"><input type="text" id="className" name="ClassName" value="${ClassName}" disabled readonly></td>
										</tr>
										 
										<tr>
											<td colspan="6"><br><br><label for="evaluation"><b>평가 항목</b> &nbsp; </label></td>
										</tr>
										
										<tr>
										<!-- db에 저장된 값이 value와 같을 때 check 되게 해야함 -->
											<td><label for="item" id="item">적극성 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" value="${Positive}" onclick="return(false);">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" value="${Positive}" onclick="return(false);">낮음</label></td>
											<td><label><input type="radio" id="radioItem" value="${Positive}" onclick="return(false);">보통</label></td>
											<td><label><input type="radio" id="radioItem" value="${Positive}" onclick="return(false);">높음</label></td>
											<td><label><input type="radio" id="radioItem" value="${Positive}" onclick="return(false);">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">공헌도 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="${Positive}" onclick="return(false);">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="${Positive}" onclick="return(false);">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="${Positive}" onclick="return(false);">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="${Positive}" onclick="return(false);">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="${Positive}" onclick="return(false);">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">타인존중도 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="${Positive}" onclick="return(false);">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="${Positive}" onclick="return(false);">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="${Positive}" onclick="return(false);">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="${Positive}" onclick="return(false);">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="${Positive}" onclick="return(false);">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">유연성 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="${Positive}" onclick="return(false);">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="${Positive}" onclick="return(false);">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="${Positive}" onclick="return(false);">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="${Positive}" onclick="return(false);">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="${Positive}" onclick="return(false);">매우 높음</label></td>
										</tr>
									</table>
									</div><!-- section2 -->
									<hr>
			                     <div id="btn">			                       
			                        <a href="${path}/search/reviewList"><input type="button" value="목록" id="listButton"></a>
			                     </div>
							</form>
						</section>
					</div>
					<!-- right_box -->

				</div>

	</div>
	<!-- mbody -->
	</nav>
</body>
</html>