<!-- 후기 작성 메뉴 선택 시 팀 선택 화면 -->

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
<link rel="stylesheet" href="../css/createTeamContent.css"
	type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/reviewWrite.js"></script>

<title>search Lecture</title>
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
							<h2>후기 작성</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="reviewWrite" name="SearchTeam" method="GET"
							id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<!-- 내가 속한 팀 이름과 과목명 출력 -->
									<tr>
										<td><label for="Team">팀 선택 &nbsp; &nbsp;</label></td>
										<td><select name="Team" id="team"
											onchange="optionChange(this)">
												<option value="" selected>-선택-</option>
												<c:forEach items="${TeamList}" var="TeamList"
													varStatus="status">
													<option><c:out value="${TeamList}" /></option>
												</c:forEach>
										</select></td>
										<td><input type="submit" name="Search" id="search"
											value="검색">
									</tr>

									<script>
										$(document)
												.ready(
														function() {
															let result = '<c:out value="${Checker}"/>';
															console.log(result);
															checkAlert(result);

															function checkAlert(
																	result) {
																if (result === '') {
																	return;
																}
																if (result === "Complete") {
																	alert("성공적으로 후기작성이 완료 되었습니다.");
																}
																if(result === "Fail"){
																	alert("후기는 1명에게 1번씩만 입력 가능합니다");
																}

															}
														});
									</script>



								</table>
								<hr>
							</div>
							<!-- section2 -->
						</form>
						<script>
							
						</script>
					</section>
				</div>
				<!-- right_box -->

			</div>

		</div>
		<!-- mcont_width -->
		<!-- mbody -->
	</nav>
	<!-- mjs_ws -->
</body>
</html>