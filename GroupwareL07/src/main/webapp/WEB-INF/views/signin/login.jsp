<!-- 로그인 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> <%--비밀번호 감추기 아이콘 링크 --%>
<link rel="stylesheet" href="css/pwShowHide.css"> <%--비밀번호 감추기 아이콘 css --%>
<script src="js/pwShowHide.js"></script><%--비밀번호 감추기 js--%>
</head>
<body>
	<div class="mjsWs">
		<div id="content">
			<section id="login">
				<h2>로그인</h2>
				<br>
				<form action="${path}/login.do" name="Login" method="POST" id="form">
					<div>
						<c:if test="${not empty ERRORMSG}">						        
							<p style="color:red">${ERRORMSG}</p>
						     <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>		     
						</c:if>
					</div>
						
					<div id="content">
						<table>						
							<tr>	
								<td class="col1"><input type="text" class="inputBox"
									name="UserLoginID" id="userLoginID" autofocus
									autocomplete="off" value="${UserLoginID}" placeholder="ID"></td>
							</tr>
							<tr>
								<td class="col1"><input type="password" class="inputBox"
									name="UserLoginPwd" id="userLoginPW" autocomplete="off" value="${UserLoginPwd}" placeholder="Password">
									<i class="fa fa-eye fa-lg" id="icon"></i></td>
							</tr>
							<tr>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /></input>
							</tr>
						</table>
					</div>
					
					<div id="content">
						<input type="submit" name="SubmitName" id="submitId"
							value="로그인">
					</div>
				</form>
				<p>아직 회원이 아니신가요? <label for="sign_up_new"><a href="infoConsent"
					   id="s_up">회원가입</a></label> <!--개인정보동의창으로 이동-->
				 </p>
				 <p>비밀번호를 잊으셨나요? <label for="findPassword"><a href="findPassword"
						id="findPassword" >비밀번호 찾기</a></label>
				</p>
			</section>
		</div>
	</div>
</body>
</html>