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
        <div class="leftBox">
          <div class="leftInfo">
            <!--로그인 후 화면-->
            <div class=userName><input type=text name="UserName" id="userName"value=${UserName} disabled readonly>님<br>안녕하세요!</div>
            <div class="userColleges"><h4>소속 : <input type=text name="SC" id="sc" value=${SC} disabled readonly></h4></div>
               <div class="userMajor"><h4>학과 : <input type=text name="UserMajor" id="userMajor" value=${UserMajor} disabled readonly></h4></div>
            <div class="userGrade"><h4>학년 : <input type=text name="Grade" id="grade" value=${Grade} disabled readonly></h4></div>
            </div><!-- left_info -->
            </div><!-- left_box -->
      </div><!-- mcont_width -->
   </div><!-- mbody -->
  </body>
</html>