<!-- 회원가입 - 정보 동의 - 이메일 인증 - "학생/교수 선택하는 화면" -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/signupSelect.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/signupSelect.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>회원 가입</title>
</head>
<body>
<h2>회원가입</h2>
	<div class="container">
	<br>
	<section id="signUp">
	<form id="signupSelect" name="select" method="GET" action="">
		<div id="selectButton">
		<span> <a href="signupStudent" id="btnSelect" class="button"
			role="button">학생 회원가입</a>
		</span>
		</div>
		
		<div id="selectButton">
		<span> <a href="signupProfessor" id="btnSelect" class="button"
			role="button">교수 회원가입</a>
		</span>
		</div>
	</form>
	</section>
	</div>
	
</body>
</html>