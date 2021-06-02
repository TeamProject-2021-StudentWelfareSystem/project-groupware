<!-- review 검색 화면 -->

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
<script src="../js/manageList.js"></script>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/searchUser.js"></script>
<title>review search page</title>
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
							<h2>&nbsp;사용자 검색</h2>
							<hr>
						</div>
					</section>
					<section>
						<div class="section2">

							<h3>&nbsp; 사용자 리스트</h3>
							<div id="search">
								<input type="text" placeholder="사용자명을 입력하세요." id="searchKeyWord"
									name="SearchKeyWord"> <input type="button" value="검색"
									id="search" class = "searchButton">
							</div>

							<script>
							$(".searchButton").click(function() {			
								
								var SearchKeyWord = $('#searchKeyWord');
								var data={key:SearchKeyWord.val()};
								var token = $("input[name='_csrf']").val();
								var header = "X-CSRF-TOKEN";
								$.ajax({
									type: "POST",
									url: "searchUser.do",
									data:  JSON.stringify(data),
									cache: false,
									dataType: "json",
									contentType: "application/json; charset=UTF-8",
								beforeSend: function(xhr) {
										xhr.setRequestHeader(header, token);
									},
									success: function(response) {
										
										console.log(response.PhoneNum);
									}
								});
							});
							</script>
							<form action="" name="UserList" method="POST" id="form">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table class="userList">
									<thead>
										<tr>
											<th id="1">번호</th>
											<th id="1">이름</th>
											<th id="2">학과</th>
											<th id="2">이메일</th>
											<th id="2">휴대폰</th>

										</tr>
										<hr>
									</thead>

									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr>
												<td><c:out value="${status.count}" /></td>
												<td><a
													href="${path}/search/reviewList?no=${list.getReviewID()}">
														<c:out value="${list.getUserName()}" />
												</a></td>
												<td><a href="${path}"><c:out
															value="${list.getOpenMajor()}" /></a></td>
												<td><c:out value="${list.getOpenEmail()}" /></td>
												<td><c:out value="${list.getOpenPhoneNum()}" /></td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
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