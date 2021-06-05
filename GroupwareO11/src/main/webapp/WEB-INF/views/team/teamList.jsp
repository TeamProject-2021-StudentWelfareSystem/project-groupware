<!-- 팀 리스트 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<link rel="stylesheet" href="../css/teamList.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<title>team list</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
	<div class="mbody">
		<div class="mcontWidth">

			<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
			<div class="rightBox">
				<section id="totalTeamList">
					<div class="section">
						<br>
						<h2>팀 조회하기</h2>
						<hr>
					</div>
					<div class="section2">
						<div id="search">
							<select name="SelectOption" id="selectOption">
								<option value="all">전체</option>
							</select> <input type="text" placeholder="검색어 입력하세요."> <input
								type="submit" value="검색">
						</div>

						<form action="" name="TeamList" method="POST" id="form">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table class="teamList">
								<thead>
									<tr>
										<th class="col1">번호</th>
										<th class="col2">과목명</th>
										<th class="col3">교수명</th>
										<th class="col4">팀 이름</th>
									</tr>
									<hr>
								</thead>
								<tbody>
									<c:forEach items="${teamList}" var="teamList"
										varStatus="status">
										<tr>
											<td><c:out value="${status.count}" /></td>
											<!-- 해당 팀에 속하는 사람만 들어갈 수 있게 해야함(팀 DB에 있는 멤버 아이디와 사용자 아이디가 같아야 접속 가능하게?) -->
											<td id="title"><a
												href="${path}/team/checkTeam?no=${teamList.getTeamID()}">
													<c:out value="${teamList.getClassName()}" />
											</a></td>
											<td><c:out value="${teamList.getClassProfessorName()}" /></td>
											<td><c:out value="${teamList.getTeamName()}" /></td>
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
						<!-- js로 옮겨주세요 -->
						<script>
							$(document).ready(function() {
								let result = '<c:out value="${Contain}"/>';
								console.log(result);
								checkAlert(result);

								function checkAlert(result) {
									if (result === '') {
										return;
									} else if (result === "true") {
										alert("팀 생성이 완료되었습니다.");
									} else if (result == "Nothing") {
										alert("해당 팀에 소속되어있지 않습니다.");
									}
								}
							});
						</script>
					</div>
					<!-- section2 -->
				</section>
			</div>
			<!-- right_box -->


		</div>
		<!-- mcont_width -->
	</div>
	<!-- mbody -->
</body>
</html>