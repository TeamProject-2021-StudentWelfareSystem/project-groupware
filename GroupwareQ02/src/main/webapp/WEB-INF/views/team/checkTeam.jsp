<!-- 팀 리스트에서 팀 선택 후 조회 화면 -->

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
<link rel="stylesheet" href="../css/createTeamContent.css"
	type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<title>check team</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
	<div class="mbody">
		<div class="mcontWidth">

			<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
			<div class="rightBox">
				<section>
					<div class="section">
						<br>
						<h2>팀 조회</h2>
						<hr>
					</div>
				</section>

				<section>
					<form action="DeleteTeam?no=${TeamID}" name="DeleteTeam"
						method="POST" id="form">
						<div class="section2">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table id="contentTable">
								<tr>
									<td><label for="lectureName">과목명 &nbsp; &nbsp;</label></td>
									<td><input type="text" name="LectureName" id="lectureName"
										class="inputBox" disabled readonly value="${LectureName}"></td>
								<tr>
									<td><label for="lectureProfessor">교수명 &nbsp;
											&nbsp;</label></td>
									<td><input type="text" name="LectureProfessor"
										id="lectureProfessor" class="inputBox" disabled readonly
										value="${LectureProfessor}"></td>
								</tr>
								<tr>
									<td><label for="teamName">팀 이름 &nbsp; &nbsp; </label></td>
									<td><input type="text" name="TeamName" id="teamName"
										class="inputBox" disabled readonly value="${TeamName}"></td>
								</tr>
								<c:forEach items="${teamList}" var="teamList" varStatus="status">
									<tr>
										<td><label for="studentID">학번 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="UserLoginID"
											id="userLoginID" class="inputBox" disabled readonly
											value="${teamList.getUserLoginID()}"></td>

										<td><label for="studentName">팀원명 &nbsp; &nbsp; </label></td>
										<td><input type="text" name="UserName" id="userName"
											class="inputBox" disabled readonly
											value="${teamList.getUserName()}"></td>
									</tr>
								</c:forEach>
							</table>
							<hr>
						</div>
						<!-- section2 -->
						<div id="btn">
							<a href="${path}/team/teamList"> <input type="button"
								value="목록" id="listButton"></a>

							<c:set var="UserLoginID" value="${UserLoginID}" />
							<c:set var="TeamLeaderID" value="${TeamLeaderID}" />
							<c:if test="${UserLoginID == TeamLeaderID}">
								<a href="${path}/team/modifyTeam?no=${TeamID}"> <input
									type="button" value="수정" id="listButton"></a>
								<input type="submit" value="삭제" id="deleteButton">
							</c:if>
						</div>
					</form>
				</section>
			</div>
			<!-- right_box -->


		</div>
		<!-- mcont_width -->
	</div>
	<!-- mbody -->
</body>
</html>