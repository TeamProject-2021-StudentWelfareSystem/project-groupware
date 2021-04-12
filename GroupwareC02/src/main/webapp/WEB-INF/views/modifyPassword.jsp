<!-- 마이페이지에서 비밀번호 변경하기 버튼 클릭 시 나오는 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

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
<title>modify password</title>
</head>
<body>
	<div class="mjs_ws">
		<div class="container">
			<section id="modify">
				<h2>비밀번호 수정</h2>
				<br>
				<div id="modifyPassword" style="display: block;">
					<form action="${path}/modifyPassword.do" name="ModifyPassword" method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table>
							<tr>
								<td class="col1"><label for="pw">현재 비밀번호</label></td>
								<td class="col2"><input type="password" name="UserLoginPwd"
									id="userLoginPwd" class="inputBox" autocomplete="off"
									value=${UserLoginPwd}></td>
							</tr>
							<tr>
								<td class="col1"><label for="newPw">새 비밀번호</label></td>
								<td class="col2"><input type="password" name="UserNewPwd"
									id="userNewPwd" class="inputBox" autocomplete="off"
									value=${UserNewPwd}></td>
							</tr>
							<tr>
								<td class="col1"><label for="newPwCheck">새 비밀번호 확인</label></td>
								<td class="col2"><input type="password" name="UserNewPwdCheck"
									id="userNewPwdCheck" class="inputBox" autocomplete="off" onchange="isSame()"
									value=${UserNewPwdCheck}></td>
							</tr>
							<tr> <td colspan="3"><div id="pwValue"></div></td></tr>
						</table>
						<div id="">
							<input type="submit" name="SubmitName" id="modifyCompletePW"
								value="수정"> <input type="button" name="Cancel"
								id="cancelBtn" value="취소">
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
</body>
</html>