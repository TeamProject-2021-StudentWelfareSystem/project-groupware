<!-- 팀 수정 화면 -->

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
<script src="../js/createTeam.js"></script>

<title>modify team</title>
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
							<h2>팀 수정하기</h2>
							<hr>
						</div>
					</section>
					<section>
						<form action="modifyTeam?no=${TeamID}" name="ModifyTeam" method="POST"
							id="form">
							<div class="section2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table id="contentTable">
									<tr>
										<td><label for="lectureName">과목명 &nbsp; &nbsp;</label></td>
										<td colspan="2"><input type="text" name="LectureName"
											id="lectureName" class="inputBox" disabled readonly
											value="${LectureName}"></td>
									</tr>
									<tr>
										<td><label for="lectureProfessor">교수명 &nbsp;
												&nbsp;</label></td>
										<td colspan="2"><input type="text" name="LectureProfessor"
											id="lectureProfessor" class="inputBox" disabled readonly
											value="${LectureProfessor}"></td>
									</tr>
									<tr>
										<td><label for="teamName">팀 이름 &nbsp; &nbsp; </label></td>
										<td colspan="2"><input type="text" name="TeamName" id="teamName"
											class="inputBox" disabled readonly value="${TeamName}"></td>
									</tr>

									<c:forEach items="${teamList}" var="teamList"
										varStatus="status">
										
										
										<tr>
											<td colspan="2"><input type="checkbox" name="CheckBox"><label for="studentID">학번 &nbsp; &nbsp; </label></td>
											<td><input type="text" name="StudentID"
												id="studentID" class="inputBox"
												value="${teamList.getUserLoginID()}"></td>

											<td><label for="studentName">팀원명 &nbsp; &nbsp; </label></td>
											<td><input type="text" name="StudentName" id="studentName"
												class="inputBox" value="${teamList.getUserName()}"></td>
										</tr>
										
										
									</c:forEach>

								</table>
								<table id="contentTable">
								</table>


								<hr>
							</div>
							<!-- section2 -->
							<div id="btn">
								<input type="button" value="팀원 추가" id="addMember"
									onclick="addTeamMember()"> <input type="button" id="deleteMember"
									value="팀원 삭제" onclick="delRow()"> <input type="submit"
									value="수정 완료" id="modifyButton"> <a
									href="${path}/team/teamList"><input type="button"
									value="목록" id="listButton"></a>
							</div>
							<script language="javaScript">
								function delRow() {

									var table = document
											.getElementById("contentTable");

									var rowCnt = table.rows.length;

									for (var i = 0; i < rowCnt; i++) {

										var row = table.rows[i];

										var chkBox = row.cells[0].childNodes[0];

										if (chkBox != null
												&& chkBox.checked == true) {

											table.deleteRow(i);

											rowCnt--;

											i--;

										}

									}

								}
							</script>
						</form>
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