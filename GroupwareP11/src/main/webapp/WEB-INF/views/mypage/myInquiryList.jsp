<!-- 내 문의 리스트 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/myBoardList.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">

<title>my inquiry list</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">

					<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include> 
					<div class="rightBox">
					<div class="subMenuBox">
						<ul>
							<li><a href="${path}/myPage?R=${UserRole}">내 정보 확인</a></li>
							<li><a href="${path}/myPostList">내 게시글 조회</a></li>
							<li><a href="${path}/confirmMyReservation">강의실 예약 조회</a></li>
							<li><a href="${path}/myInquiryList">내 문의 조회</a></li>
						</ul>
					</div>
						<section id="boardlist">
							<div class="section">
								<br>
								<h2>내 문의 목록</h2>
								<hr>
							</div>
							<div class="section2">
								<div id="search">
									<select name="SelectOption" id="selectOption">
										<option value="all">전체</option>
									</select> <input type="text" placeholder="검색어 입력하세요."> <input
										type="submit" value="검색">
								</div>
								
								<form action="" name="MyInquiryList" method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table class="inquiryList">
										<thead>
											<tr>
												<th class="col1">번호</th>
												<th class="col2">제목</th>
												<th class="col3">작성자</th>
												<th class="col4">작성일</th>
												<th class="col1">상태</th>
											</tr>
											<hr>
										</thead>
										<tbody>
										<!-- 내가 작성한 문의만 볼 수 있게해야함 -->
										<!--<c:set var="UserID" value="${UserID}" />
										<c:set var="UserIDFromWriter" value="${UserIDFromWriter}" />
										<c:if test="${UserID == UserIDFromWriter}">-->
										
											<c:forEach items="${MyInquiryList}" var="MyInquiryList"
												varStatus="status">
												<tr>
												<td><c:out value="${status.count}" /></td>
													<td><a href="${path}/inquiryContent?no=${MyInquiryList.getIBoardID()}">
														<c:out value="${MyInquiryList.getIBoardSubject()}" /></a></td>
													<td><c:out value="${MyInquiryList.getIBoardWriter()}" /></td>
													<td><c:out value="${MyInquiryList.getIBoardDate()}" /></td>
													<td><c:out value="${MyInquiryList.getState()}" /></td>
												</tr>
										</c:forEach>
										</c:if>
										</tbody>
									</table>
									<hr>
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