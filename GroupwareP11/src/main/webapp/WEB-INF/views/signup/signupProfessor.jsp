<!-- 회원가입 화면 (교수) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/signup.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/signupProfessor.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>sign up</title>
</head>
<body>
	<div class="mjsWs">
		<div class="container">
			<section id="signUp">
				<h2>회원가입</h2>
				<br>
				
				<!--교수 회원가입-->
				<div id="signupProfessor" style="display: block;">
				<form action="${path}/signupProfessor.do" name="SignupProfessor" method="POST"
							id="form"> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
					<table>
							<tr>
								<td class="col1"><label for="id">아이디(교번)</label></td>
								<td class="col2"><input type="text" name="UserLoginID"
									id="userLoginID" class="inputBox" placeholder="" autofocus
									autocomplete="off" value=${UserLoginID}></td>
								<td class="col3"><input type="submit" value="중복 확인"
									name="IdCheck" id="idCheck"></td>
							</tr>
							<tr>
								<td class="col1"><label for="password">패스워드</label></td>
								<td class="col2"><input type="password" name="UserLoginPwd"
									id="userLoginPwd" class="inputBox" autocomplete="off"
									value=${UserLoginPwd}></td>
							</tr>
							<tr>
								<td class="col1"><label for="name">이름</label></td>
								<td class="col2"><input type="text" name="UserName"
									id="userName" class="inputBox" autocomplete="off"
									value=${UserName}></td>
							</tr>
							
							<tr>
								<td class="col1"><label for="phoneNumber">연락처</label></td>
								<td class="col2"><input type="text" name="UserPhoneNum"
									id="userPhoneNum" class="inputBox"  placeholder="숫자만 입력해주세요"
									autocomplete="off" value=${UserPhoneNum}></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorColleges">단과대학</label></td>
								<td class="col2"><select class="selectBox" name="ProfessorColleges"
									id="professorColleges">
										<option value="" selected>-선택-</option>
										<option value="인문대학">인문대학</option>
										<option value="사회과학대학">사회과학대학</option>
										<option value="경영대학">경영대학</option>
										<option value="법과대학">법과대학</option>
										<option value="ICT융합대학">ICT융합대학</option>
										<option value="미래융합대학">미래융합대학</option>
								</select></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorMajor">전공</label></td>
								<td class="col2"><select class="selectBox" name="ProfessorMajor"
									id="professorMajor">
										<option value="" selected>-선택-</option>
								</select><br><br></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorRoom">교수실</label></td>
								<td class="col2"><input type="text" name="ProfessorRoom"
									id="professorRoom" class="inputBox" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorRoomNum">교수실 전화번호</label></td>
								<td class="col2"><input type="text" name="ProfessorRoomNum"
									id="professorRoomNum" class="inputBox" autocomplete="off"></td>
							</tr>
						</table>
						<br>
						<div id="">
							<input type="submit" name="submitName" id="SignupComplete"
								value="회원가입">
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
</body>
</html>