<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Please sign in</title>
<link
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
   crossorigin="anonymous">
<link rel="stylesheet" href="css/login.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/login.js"></script>
</head>
<body>
   <section id="login">
      <form class="form-signin" method="POST" action="${path}/login.do">
         <h2 id="h2">로그인</h2>
         <div class="select-member">
            <label for="member_student"> <input type="radio"
               class="radio" name="member" id="member_student" value="Y"
               onchange="setDisplay()" checked=""> 학생
            </label> &nbsp;&nbsp;&nbsp; <label for="member_teacher"> <input
               type="radio" class="radio" name="member" id="member_teacher"
               value="N" onchange="setDisplay()"> 강사
            </label>
         </div>

            <p>
               <label for="username" class="sr-only">userLoginID</label> <input
                  type="text" id="userLoginID" name="userLoginID"
                  class="input_info" placeholder="ID" required="" autofocus="">
            </p>

            <p>
               <label for="password" class="sr-only">userLoginPwd</label> <input
                  type="password" id="userLoginPwd" name="userLoginPwd"
                  class="input_info" placeholder="Password" required="">
            </p>
						
            <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
            <button class="button1" type="submit">로그인</button>
   
      </form>

      <p>
         아직 회원이 아니신가요? <label for="sign_up_new"><a href="signup.html"
            id="s_up">회원가입</a></label>
      </p>
      <div id="teacher_login" style="display: none;">
         <form name="login" action="" method="POST">
            <span class="id"> <input type="text" name="id" title="아이디"
               class="input_info" placeholder="강사명를 입력해주세요." required=""
               autocomplete="off">
            </span> <br> <span class="pw"> <input type="password" name="pw"
               title="비밀번호" class="input_info" placeholder="비밀번호를 입력해주세요."
               required="" autocomplete="off">
            </span> <br> <span class="button1"> <input type="submit"
               class="button1" value="로그인">
            </span>
         </form>
         아직 회원이 아니신가요? <label for="sign_up_new"><a href="signup.html"
            id="s_up">회원가입</a></label>

      </div>

   </section>
</body>
<whale-quicksearch translate="no"></whale-quicksearch>
</html>