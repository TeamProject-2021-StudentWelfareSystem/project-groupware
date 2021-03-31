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
    <div class="mjs_ws">

      <div class="mheader">
        <!--배경화면-->
        <div id="mjs_film"
        style="z-index: 99997; position:absolute; display:none; width:100%;height:100%;
        background-color:#000000; filter:Alpha(opacity=60); opacity:0.4; -moz-opacity:0.6;"></div>
        <!--메뉴 -->
         <div id="auth_check_div" align="center" style="z-index: 99999; position:absolute;"></div>
         <div class="menubar">
	          <div class="menubar_width">
		            <div class="menubar_logo"><!--로고추가하기-->
		            </div>
	               <div class="menubar_mid">
			                <ul class="top_info">
				                    <li><a href="">사이트맵</a></li>
                            <li><a href="">마이페이지</a></li>
				                     <li><a href="">문의</a></li>
                             <li><a href="login">로그인</a></li>
                      </ul>
                      <!-- 메뉴 -->
                      <ul class="top_menu">
                        <li id="home_tab" class="t_menu">
                          <a href="home"><span class="tmenu_pack mover">홈</span></a>
                        </li>
					              <li id="mail_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">메일</span></a>
                        </li>
                        <li id="gboard_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">게시판</span></a>
                        </li>
                        <li id="rectureRoom_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">강의실</span></a>
                        </li>
                        <li id="scheduling_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">일정관리</span></a>
                        </li>
                        <li id="memo_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">메모</span></a>
                        </li>
                        <li id="inquiry_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">조회</span></a>
                        </li>
                        <li id="teammates_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">팀원관리</span></a>
                        </li>
                        <li id="documents_tab" class="t_menu">
                          <a href=""><span class="tmenu_pack mover">문서관리</span></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>

      <div class="mbody">
        <div id="mask"></div>
        <div class="mcont_width">
        <div class="left_box">
          <div class="left_info">
            <!--로그인 전 화면-->
            <div class="login_menu"><a href="login">로그인</a></div>
            <div class="join_menu"><a href="signup">회원가입</a></div>
            <div class="find_menu"><a href="">아이디/비밀번호 찾기</a></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>