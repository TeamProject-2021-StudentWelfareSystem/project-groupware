<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/signup.css">
	<script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/signup.js"></script>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="css/homeLayout.css" type="text/css">
	<style type="css/homeMenu.css"> a { text-decoration:none } </style>
	<style type="css/homeLayout.css"></style>
	<title>sign up</title>
</head>
<body>
  <div class="mjs_ws">

    <div class="mjs_ws_header">
      <!--배경화면-->
      <div id="mjs_film"
      style="z-index: 99997; position:absolute; display:none; width:100%;height:100%;
      background-color:#000000; filter:Alpha(opacity=60); opacity:0.4; -moz-opacity:0.6;"></div>
      <!--메뉴 -->
       <div id="auth_check_div" align="center" style="z-index: 99999; position:absolute;"></div>
       <div class="top_menu_total">
          <div class="top_menu_width">
              <div class="top_menu_logo"><!--로고추가하기-->
              </div>
               <div class="top_menu_mid">
                    <ul class="top_info">
                          <li><a href="">사이트맵</a></li>
                          <li><a href="">마이페이지</a></li>
                           <li><a href="">문의</a></li>
                           <li><a href="login.html">로그인</a></li>
                    </ul>
                    <!-- 메뉴 -->

                    <ul class="top_menu">
                      <li id="home_tab" class="t_menu">
                        <a href=""><span class="tmenu_pack mover">홈</span></a>
                      </li>
                      <li id="mail_tab" class="t_menu">
                        <a href=""><span class="tmenu_pack mover">메일</span></a>
                      </li>
                      <li id="gboard_tab" class="t_menu">
                        <a href=""><span class="tmenu_pack mover">게시판</span></a>
                      </li>
                      <li id="rectureRoom_tab" class="t_menu">
                        <a href=""><span class="tmenu_pack mover">강의</span></a>
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

  </div>

    <div class="container">
    <section id="signup">
        <h2>회원가입</h2><br>
        <table>
            <form action="${path}/signup.do" name="signup" method="POST" id="form">
            <tr>
                <td class="col1"><label for="id">아이디</label></td>
                <td class="col2"><input type="text" name="userLoginID" id="userLoginID" class="inputBox"  placeholder="" autocomplete="off"></td>
                <td class="col3"><input type="submit" value="확인" id="idcheck"></td>
            </tr>
            <tr>
                <td class="col1"><label for="password">패스워드</label></td>
                <td class="col2"><input type="password" name="userLoginPwd" id="userLoginPwd" class="inputBox"  autocomplete="off"></td>
            </tr>
            <tr>
                <td class="col1"><label for="name">이름</label></td>
                <td class="col2"><input type="text" name="userName" id="userName" class="inputBox"  autofocus autocomplete="off"></td>
            </tr>
            <tr>
                <td class="col1"><label for="phoneNumber">연락처</label></td>
                <td class="col2"><input type="text" name="userPhoneNum" id="userPhoneNum" class="inputBox"   autocomplete="off"></td>
            </tr>
            <tr>
                <td class="col1"><label for="email">이메일</label></td>
                <td class="col2"><input type="email" name="userEmail" id ="userEmail" class="inputBox"  autocomplete="off"></td>
                <td class="col3"><input type="submit" value="인증번호 받기" id="email_check"></td>
            </tr>
            <tr>
              <td class="col1"><label for="timer"></label></td>
              <td class="col2"><input type="text" name="number" id="email_num" class="inputBox" style="display:none"></td>
              <td class="col3"><input type="submit" value="인증하기" id="email_valid" style="display:none"></td>
            </tr>
        </table>
<!-- 
        <br>

        <div id="radio">
            <label for="member_student">
                <input type="radio" class="radio" name="userRole" id="userRole" value="Y"  checked>
                student
            </label>
            &nbsp;&nbsp;&nbsp;
        <label for="member_teacher" >
                <input type="radio" class="radio" name="userRole" id="userRole" value="N" >
                professor
        </label>
        </div>
 -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        <div id="">
            <input type="submit" value="회원가입" id="submit">
        </div>
        </form>
    </section>
    </div>
</body>
</html>