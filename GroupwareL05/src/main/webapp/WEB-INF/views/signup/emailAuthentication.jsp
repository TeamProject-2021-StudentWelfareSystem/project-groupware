<!-- 회원가입 시 이메일 인증하는 화면 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/emailAuthentication.css">
<link rel="stylesheet" href="css/signupselect.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/email.js"></script>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>회원 가입</title>
</head>
<body>
<div id="mjsWs">
	<h2>회원 가입</h2>
	<div class="container">
		<br>
		<section id="signUp">
			<form action="${path}/email.do" id="signupSelect" name="select"
				method="POST">
				<table>
					<tr>
						<td class="col1"><label for="email">이메일</label></td>
						<td class="col2"><input type="text" name="Email"
							id="userEmail" class="inputBox" autocomplete="off" value=${Email}>
						</td>
						<td class="col4"><input type="text" value="@mju.ac.kr"
							disabled readonly id="mju" class="inputBox" autocomplete="off"></td>
						<td class="col3"><input type="submit" value="인증번호 받기"
							name="EmailCheck" id="emailCheck"></td>
					</tr>
					<tr>
						<td class="col1"><label for="timer"></label></td>
						<td class="col2"><input type="text" name="Number"
							id="emailNum" class="inputBox" value=${Number}></td>
						<td class="col3"><input type="submit" value="인증하기"
							name="EmailValid" id="emailValid"></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div id="btn">
					<input type="submit" value="확인" id="btnAgree" name="BtnAgree">
				</div>
			</form>

		</section>
	</div>
</div>
</body>
</html>