<!-- 이메일 리스트 화면 -->
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
<link rel="stylesheet" href="../css/emailList.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">

<title>email list</title>
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
								<h2>이메일 목록</h2>
								<hr>
							</div>
						</section>
						<section>
							<div class="section2">


								<form action="" name="EmailList" method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table class="emailList">
										<thead>
											<tr>
												<th class="col1">번호</th>
												<th class="col2">보낸 이</th>
												<th class="col3">제목</th>

											</tr>
											<hr>
										</thead>

										<tbody>
											<c:set var="i" value="0" />
											<c:set var="j" value="3" />
											<c:forEach items="${EmailList}" var="emailList"
												varStatus="status">
												<tr>
													<td><c:out value="${emailList.getCounter()}" /></td>
													<td><c:out value="${emailList.getFrom()}" /></td>
													<td><a href="${path}/email/emailContent?no=${emailList.getCounter()}"><input value="${emailList.getTitle()}" disabled readonly /></a></td>
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