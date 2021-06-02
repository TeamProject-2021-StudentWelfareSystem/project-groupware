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

<title>review write</title>
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
								<h2>후기 작성하기</h2>
								<hr>
							</div>
						</section>
						<section>
						<span id="description">팀원에 대한 후기를 작성하는 페이지입니다.
						<br>후기는 익명을 원칙으로 하여 여러분의 답변은 개인정보가 공개되지 않음을 알려드립니다.<br>
						후기는 수정 및 삭제가 불가하오니 신중하게 고민 후 답변 부탁드립니다. 감사합니다.<br><br></span>
						
							   <form
			                     action="ReviewWrite?${_csrf.parameterName}=${_csrf.token}"
			                     name="ReviewWrite" enctype="multipart/form-data" method="POST"
			                     id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table id="contentTable">
									
										<tr>
										<!-- 선택한 팀에 속한 팀원명과 학번 출력(만약 가능하면 본인 제외하고) -->
										<td><label for="TeamInfo">팀원 &nbsp; &nbsp;</label></td>
										<td colspan="5"><select name="TeamUser" id="team"
											onchange="optionChange2(this)">
												<option value="" selected>-선택-</option>
												<c:forEach items="${TeamUserList}" var="TeamUserList"
													varStatus="status">
													<option><c:out value="${TeamUserList}" /></option>
												</c:forEach>
										</select></td>
										</tr>
										
										<tr>
											<td colspan="6"><br><br><label for="evaluation"><b>평가 항목</b> &nbsp; </label></td>
											
										</tr>
										<tr>
										<!-- name이 같아야 중복 선택 막을 수 있음 -->
											<td><label for="item" id="item">적극성 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Positive" value="1">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Positive" value="2">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Positive" value="3">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Positive" value="4">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Positive" value="5">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">공헌도 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="1">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="2">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="3">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="4">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Contribute" value="5">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">타인존중도 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="1">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="2">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="3">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="4">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Respect" value="5">매우 높음</label></td>
										</tr>
										<tr>
											<td><label for="item" id="item">유연성 &nbsp; </label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="1">매우 낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="2">낮음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="3">보통</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="4">높음</label></td>
											<td><label><input type="radio" id="radioItem" name="Flexible" value="5">매우 높음</label></td>
										</tr>
									</table>
									</div><!-- section2 -->
									<hr>
			                     <div id="btn">			                       
			                        <br> <input type="submit" value="저장" id="saveButton">
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