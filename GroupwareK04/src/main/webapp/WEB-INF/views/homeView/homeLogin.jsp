<!-- 로그인 시 보이는 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/homeLayout.css" type="text/css">
    <link rel="stylesheet" href="css/menubar.css" type="text/css">
    <title>MJS Welfare System</title>
  </head>
  <body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include> 
	<div class="mbody">
        <div class="mcontWidth">
		<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include> 
      </div><!-- mcont_width -->
   </div><!-- mbody -->
   
  </body>
</html>