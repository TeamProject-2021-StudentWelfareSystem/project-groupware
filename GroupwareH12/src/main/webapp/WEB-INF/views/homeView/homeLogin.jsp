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
    <div class="mjsWs">
	<div class="mheader">
      <!--메뉴바 -->
   <div class="menubar">
       <div class="menubarWidth">
            <div class="menubarLogo"><!--로고추가하기-->
            </div>
            <div class="menubarMid">
            <nav id="navigation1">
            <ul id="topInfo">
            <!-- sign out -->
            <li><sec:authorize access="isAuthenticated()">
                     <a href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
                  </sec:authorize>
                  <form id="logout" action="${path}/logout.do" method="POST">
                     <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                  </form>
               </li>
               <li><a href="${path}/inquiryList">문의</a></li>
               <li><a href="${path}/myPage?R=${UserRole}">마이페이지</a></li>
               <li><a href="">사이트맵</a></li>
            </ul>
          </nav>
            <!-- 메뉴 -->
            <nav id="navigation2">
              <ul id="topMenu">
                  <li><a href="homeLogin">홈</a></li>
                  <li><a href="${path}/email/emailLogin">메일</a></li>
                  <li><a href="${path}/communityList">게시판</a>
                    <ul id="subMenu">
                      <li><a href="${path}/noticeList">공지사항</a></li>
                      <li><a href="${path}/communityList">커뮤니티</a></li>
                      <li><a href="#">후기</a></li>
                    </ul>
                  </li>
                  <li><a href="">강의실</a></li>
                  <li><a href="">일정관리</a></li>
                  <li><a href="">조회</a></li>
                  <li><a href="">팀원관리</a></li>
                  <li><a href="">문서관리</a></li>
                </ul>
            </nav>
          </div><!-- menubar_mid -->
      </div><!-- menubar_width -->
   </div><!-- menubar -->
</div><!-- mheader -->

	<div class="mbody">
        <div class="mcontWidth">
        <div class="leftBox">
          <div class="leftInfo">
            <!--로그인 후 화면-->
            <div class=img>
                <img src="user.png" alt="userimg" width="50" height="50">
            </div>
            <br>
            <div class=userName><input type=text name="UserName" id="userName" style="background-color: white" value=${UserName} disabled readonly>님<br>안녕하세요!</div>
            <div class="userColleges"><h4 color="blue">소속 : <input type=text name="SC" id="sc" style="background-color: white" value=${SC} disabled readonly></h4></div>
               <div class="userMajor"><h4 color="blue">학과 : <input type=text name="UserMajor" id="userMajor" style="background-color: white" value=${UserMajor} disabled readonly></h4></div>
            <div class="userGrade"><h4 color="blue">학년 : <input type=text name="Grade" id="grade" style="background-color: white" value=${Grade} disabled readonly></h4></div>
            </div><!-- left_info -->
            </div><!-- left_box -->
      </div><!-- mcont_width -->
   </div><!-- mbody -->
    </div><!-- mjs_ws -->
  </body>
</html>