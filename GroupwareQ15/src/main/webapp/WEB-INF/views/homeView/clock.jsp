<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<!-- The main CSS file -->
<link rel="stylesheet" href="resources/clock/css/style.css" type="text/css" />
</head>
<body>

	<div id="clock" class="light">
		<div class="display">
			<div class="weekdays"></div>
			<div class="ampm"></div>
			<div class="alarm"></div>
			<div class="digits"></div>
		</div>
	</div>

	<!-- JavaScript Includes -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
	<script src="resources/clock/js/script.js"></script>

</body>
</html>