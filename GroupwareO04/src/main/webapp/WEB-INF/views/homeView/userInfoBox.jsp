<!-- 모든 페이지 고정 left box -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/homeLayout.css" type="text/css">
    <title>MJS Welfare System</title>
  </head>
  <body>
        <div class="leftBox">
          <div class="leftInfo">
            <!--로그인 후 화면-->
            <div class=userName><input type=text name="UserName" id="loginUserName"value=${UserName} disabled readonly>님<br>안녕하세요!</div>
            <sec:authorize access="hasRole('ROLE_USER')">
            <div class="userColleges"><h4>소속 : <input type=text name="Colleges" id="sc" value=${Colleges} disabled readonly></h4></div>
            <div class="userMajor"><h4>학과 : <input type=text name="UserMajor" id="userMajor" value=${UserMajor} disabled readonly></h4></div>
            <div class="userRole"><input type=hidden name="UserRole" id="userRole" value=${UserRole}></div>
            </sec:authorize>
            </div><!-- left_info -->
            </div><!-- left_box -->
  </body>
</html>