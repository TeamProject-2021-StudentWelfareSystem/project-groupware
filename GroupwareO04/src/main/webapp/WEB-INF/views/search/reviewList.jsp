<!-- review 리스트 화면 -->

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
<!-- ajax 통신을 위한 meta tag -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/searchReview.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>

<title>review List page</title>
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
							<h2>&nbsp;review 검색</h2>
							<hr>
						</div>
					</section>
					<section>
						<div class="section2">

							<h3>&nbsp; review 리스트</h3>
							<form action="" name="ReviewList"
								method="POST" id="form">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table class="reviewList">
									<thead>
										<tr>
											<th id="1">번호</th>
											<th id="1">과목명</th>
											<th id="2">후기 점수</th>
										</tr>
										<hr>
									</thead>
									<tbody >
									
									</tbody>
									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr>
												<td><a href="${path}/search/reviewContent?no=${list.getReviewID()}"><c:out value="${status.count}" /></a></td>
												<td><a href="${path}/search/reviewContent?no=${list.getReviewID()}"> <c:out value="${list.getUserMajor()}" /></a></td>
												<td><a href="${path}/search/reviewContent?no=${list.getReviewID()}"><c:out value="${list.getReviewScore()}" /></a></td>
												
											</tr>
											
										</c:forEach>
									</tbody>
								</table>
								<hr>
								<div id="score" class="">
								후기 평점 : </div>
								<hr>
								<div id="page" class="btn">
									
									<input type="button" value="←" id="leftList"> <input
										type="button" value="1" id="pageList"> <input
										type="button" value="→" id="rightList"> 
								</div>
							</form>
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