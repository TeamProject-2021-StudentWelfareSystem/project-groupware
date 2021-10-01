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
<meta charset="UTF-8">
</head>
<body>
	<div class="card o-hidden border-0 shadow-lg my-5" style="width: 320px">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="col-lg-12">
				<div class="p-4">
				<h1 class="h6 text-gray-900 mb-4">Welcome!</h1>
				<div class="user">
				<a href="login" class="btn btn-primary btn-user btn-block">로그인</a>
				</div>
				<div class="card-body text-center">
					<a class="small" href="${path}/findPassword" id="findPassword">비밀번호 찾기</a> /
					<a class="small" href="${path}/infoConsent" id="s_up">회원가입</a>
				</div>	
				</div>
			</div>
		</div>
	</div>
</body>
</html>