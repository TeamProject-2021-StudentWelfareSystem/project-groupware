<!-- 403 error 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>access denied</title>

</head>
<body>
	<div class="mjsWs">
		<h6 style="font-size: large">권한이 없어 접근이 불가합니다.<br>관리자에게 문의하세요.</h6>
	</div>
	<div id="content">
		<a href="${path}/homeLogin" id="btnCancel" class="button" role="button">홈으로 이동</a>
	</div>
</body>
</html>