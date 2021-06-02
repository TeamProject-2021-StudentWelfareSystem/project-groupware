<!-- 비밀번호 찾기 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/findPassword.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/findPassword.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>find password</title>
</head>
<body>
	<div class="mjsWs">
		<div id="content">
			<section id="findPwd">
				<h2>비밀번호 찾기</h2>
				<br>
				<p>비밀번호를 찾고자 하는 아이디와 이름, 이메일을 입력해주세요</p>
				<p>본인확인 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.</p>

				<form action="${path}/findPassword.do" name="FindPwd" method="POST"
					id="form">
					<div id="memberCheck">
						<table>
							<tr>
								<td class="col1"><label for="id">아이디(학번)</label></td>
								<td class="col2"><input type="text" class="inputBox"
									name="UserLoginID" id="userLoginID" autofocus
									autocomplete="off" value=${UserLoginID} ></td>
							</tr>
							<tr>
								<td class="col1"><label for="userName">이름</label></td>
								<td class="col2"><input type="text" class="inputBox"
									name="UserName" id="userName" autocomplete="off"value=${UserName}></td>
								<td class="col3"><input type="submit" name="IdCheck"
									id="idCheck" value="확인"></td>	
							</tr>
							<tr>
								<td class="col1"><label for="email">이메일</label></td>
								<td class="col2"><input type="text" name="UserEmail"
									id="userEmail" class="inputBox" autocomplete="off" value = ${UserEmail}></td>
								<td class="col4"><input type="text" value="@mju.ac.kr"
									disabled readonly name="email" id="mju" class="inputBox"
									autocomplete="off" value=${email}></td>
								<td class="col3"><input type="submit" value="인증번호 받기" name="EmailCheck"
									id="emailCheck"></td>
							</tr>
							<tr>
								<td class="col1"><label for="timer"></label></td>
								<td class="col2"><input type="text" name="Number"
									id="emailNum" class="inputBox" value=${Number}><input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /></td>
								<td class="col3"><input type="submit" value="인증하기" name ="EmailValid"
									id="emailValid"></td>
							</tr>
						</table>
					</div>
					
					<div id="">
							<input type="submit" name="SubmitName" id="submitID"
								value="비밀번호 찾기">
						</div>
				</form>
			</section>
		</div>
	</div>
</body>
</html>