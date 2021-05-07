<!-- 비밀번호 찾기 - 이메일 인증 후 임시 비밀번호 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/findPassword.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/findPassword.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>show password</title>
</head>
<body>
	<div class="mjsWs">
		<div id="content">
			<section id="findPwd">
				<h2>비밀번호 찾기</h2>
				<div id="showPwd">
				<label for="userPwd">임시 비밀번호</label>
				<input class="userLoginPwd" id="userLoginPwd" name="UserLoginPwd" readonly value=${UserLoginPwd}></input>
				</div>	
				<div id="doLoginBtn"><input type="button" id="doLogin" value="로그인하기"></div>
			</section>
		</div>
	</div>
</body>
</html>