<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <div id="mjsfilm"
        style="z-index: 99997; position:absolute; display:none; width:100%;height:100%;
        background-color:#000000; filter:Alpha(opacity=60); opacity:0.4; -moz-opacity:0.6;"></div>
        <!--메뉴 -->
         <div id="authCheckDiv" align="center" style="z-index: 99999; position:absolute;"></div>
         <div class="menubar">
             <div class="menubarWidth">
                  <div class="menubarlogo"><!--로고추가하기-->
                  </div>
                  <div class="menubarMid">
                         <ul class="topInfo">
                                <li><a href="">사이트맵</a></li>
                            <li><a href="">마이페이지</a></li>
                                 <li><a href="">문의</a></li>
                             <li><a href="login">로그인</a></li>
                      </ul>
                      <!-- 메뉴 -->
                      <ul class="topMenu">
                        <li id="homeTab" class="tMenu">
                          <a href="home"><span class="tmenuPack mover">홈</span></a>
                        </li>
                             <li id="mailTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">메일</span></a>
                        </li>
                        <li id="gboardTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">게시판</span></a>
                        </li>
                        <li id="rectureRoomTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">강의실</span></a>
                        </li>
                        <li id="schedulingTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">일정관리</span></a>
                        </li>
                        <li id="memoTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">메모</span></a>
                        </li>
                        <li id="inquiryTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">조회</span></a>
                        </li>
                        <li id="teammatesTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">팀원관리</span></a>
                        </li>
                        <li id="documentsTab" class="tMenu">
                          <a href=""><span class="tmenuPack mover">문서관리</span></a>
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
            <!--로그인 전 화면-->
            <div class="loginMenu"><a href="login">로그인</a></div>
            <div class="joinMenu"><a href="infoConsent">회원가입</a></div> <!-- 개인정보동의 화면창으로 이동 -->
            <div class="findMenu"><a href="findPassword">비밀번호 찾기</a></div>
            </div><!-- left_info -->
            </div><!-- left_box -->
      </div><!-- mcont_width -->
   </div><!-- mbody -->
    </div><!-- mjs_ws -->
  </body>
</html>