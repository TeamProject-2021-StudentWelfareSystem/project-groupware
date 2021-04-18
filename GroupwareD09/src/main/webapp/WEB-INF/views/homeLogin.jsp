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
    <title>MJS Welfare System</title>
  </head>
  <body>
    <div class="mjsWs">

      <div class="mheader">
        <!--배경화면-->
        <div id="mjsFilm"
        style="z-index: 99997; position:absolute; display:none; width:100%;height:100%;
        background-color:#000000; filter:Alpha(opacity=60); opacity:0.4; -moz-opacity:0.6;"></div>
        <!--메뉴 -->
         <div id="authCheckDiv" align="center" style="z-index: 99999; position:absolute;"></div>
         <div class="menubar">
             <div class="menubarWidth">
                  <div class="menubarLogo"><!--로고추가하기-->
                  </div>
                  <div class="menubarMid">
                  <ul class="topInfo">
                     <li><a href="">사이트맵</a></li>
                     <li><a href="myPageStudent">마이페이지</a></li>
                     <li><a href="">문의</a></li>
                     <li><a href="${path}/admin/manageList">관리자 메뉴</a></li>
                     <!-- sign out -->
                     <li><sec:authorize access="isAuthenticated()">
                           <a href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
                        </sec:authorize>
                        <form id="logout" action="${path}/logout.do" method="POST">
                           <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                        </form>
                     </li>
                  </ul>
                  
                  <!-- 메뉴 -->
                      <ul class="topMenu">
                        <li id="homeTab" class="tMenu">
                          <a href="homeLogin"><span class="tmenuPackMover">홈</span></a>
                        </li>
                             <li id="mailTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">메일</span></a>
                        </li>
                        <li id="gboardTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">게시판</span></a>
                        </li>
                        <li id="rectureRoomTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">강의실</span></a>
                        </li>
                        <li id="schedulingTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">일정관리</span></a>
                        </li>
                        <li id="memoTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">메모</span></a>
                        </li>
                        <li id="inquiryTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">조회</span></a>
                        </li>
                        <li id="teammatesTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">팀원관리</span></a>
                        </li>
                        <li id="documentsTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">문서관리</span></a>
                        </li>
                      </ul>
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
            <div class=userName><input type=text name="UserName" value=${UserName} readonly>님<br>안녕하세요!</div>
            <div class="userColleges"><h4 color="blue">소속 : <input type=text name="SC" value=${SC} readonly></h4></div>
               <div class="userMajor"><h4 color="blue">학과 : <input type=text name="UserMajor" value=${UserMajor} readonly></h4></div>
            <div class="userGrade"><h4 color="blue">학년 : <input type=text name="Grade" value=${Grade} readonly></h4></div>
            </div><!-- left_info -->
            </div><!-- left_box -->
      </div><!-- mcont_width -->
   </div><!-- mbody -->
    </div><!-- mjs_ws -->
  </body>
</html>