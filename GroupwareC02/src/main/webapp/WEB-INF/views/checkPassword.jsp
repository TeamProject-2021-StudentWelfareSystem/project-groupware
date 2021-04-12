<!-- 마이페이지에서 수정하기 버튼 클릭 시 비밀번호 확인하는 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoModify.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/infoModify.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>check password</title>
</head>
<body>
	<div class="mjs_ws">
		<div id="content">
			<section id="checkPW">
				<h2>비밀번호 확인</h2>
				<br>
				<div id="showPW">
					<form action="${path}/checkPassword.do" name="CheckPW" method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table>
							<tr>
								<td class="col1"><label for="userPW">비밀번호 입력</label></td>
								<td class="col2"><input type="password" class="inputBox"
									id="userLoginPwd" name="UserLoginPwd" value=${UserLoginPwd}></input></td>

							</tr>
						</table>
						<div>
							<a href="modifyStudent"><input type="button" id="modifyBtn"
								value="확인"></a> <input type="button" name="Cancel"
								id="cancelBtn" value="취소">
						</div>

					</form>
				</div>
			</section>
		</div>
	</div>
</body>
</html>