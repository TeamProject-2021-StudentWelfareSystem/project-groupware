<!-- 로그아웃 시 출력하는 left box -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="leftBox">
		<div class="leftInfo">
			<!--로그인 전 화면-->
			<div class="loginMenu">
				<a href="login"><input type="button" id="loginMenu" value="로그인"></a>
			</div>
			<div class="infoMenu">
				<a href="findPassword">비밀번호 찾기</a> / <a href="infoConsent">회원가입</a>
			</div>
			<!-- 개인정보동의 화면창으로 이동 -->

		</div>
		<!-- left_info -->
	</div>
	<!-- left_box -->
</body>
</html>