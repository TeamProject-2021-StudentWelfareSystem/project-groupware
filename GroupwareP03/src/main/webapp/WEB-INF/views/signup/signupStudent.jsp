<!-- 회원가입 화면 (학생) -->

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
<script src="js/signupStudent.js"></script>
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
				<!--학생 회원가입-->
				<div id="signupStudent" style="display: block;">
					<form action="${path}/signupStudent.do" name="SignupStudent"
						method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table>
							<tr>
								<td class="col1"><label for="id">아이디(학번)</label></td>
								<td class="col2"><input type="text" name="UserLoginID"
									id="userLoginID" class="inputBox" placeholder="" autofocus
									autocomplete="off" value=${UserLoginID}></td>

								<td class="col3"><input type="submit" value="중복 확인"
									name="IdCheck" id="idCheck"></td>
							</tr>

							<tr>
								<td class="col1"><label for="password">패스워드</label></td>
								<td class="col2"><input type="password" name="UserLoginPwd"
									id="userLoginPwd" class="inputBox" autocomplete="off"  value=${UserLoginPwd}></td>
							</tr>
							<tr>
								<td class="col1"><label for="name">이름</label></td>
								<td class="col2"><input type="text" name="UserName"
									id="userName" class="inputBox" autocomplete="off" value=${UserName}></td>
							</tr>

							<tr>
								<td class="col1"><label for="gender">성별</label></td>
								<td class="col2"><select name="StudentGender" id="studentGender">
										<option value=" " selected>-선택-</option>
										<option value="남자">남</option>
										<option value="여자">여</option>
								</select></td>
							</tr>
							<tr>
								<td class="col1"><label for="phoneNumber">연락처</label></td>
								<td class="col2"><input type="text" name="UserPhoneNum"
									id="userPhoneNum" class="inputBox" placeholder="숫자만 입력해주세요" autocomplete="off" value=${UserPhoneNum}></td>
							</tr>
							<tr>
								<td class="col1"><label for="grade">학년</label></td>
								<td class="col2">
								<select name="StudentGrade" id="studentGrade">
										<option value=" " selected>-선택-</option>
										<option value="1">1학년</option>
										<option value="2">2학년</option>
										<option value="3">3학년</option>
										<option value="4">4학년</option>
								</select></td>
							</tr>
							<tr>
								<td class="col1"><label for="studentColleges">단과대학</label></td>
								<td class="col2"><select class="selectBox" name="StudentColleges"
									id="studentColleges">
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
								<td class="col1"><label for="studentMajor">전공</label></td>
								<td class="col2"><select class="selectBox" name="StudentMajor"
									id="studentMajor">
										<option value="-선택-" selected>-선택-</option>
								</select><br><br></td>
							</tr>
							<tr id="doubleMajor">
								<td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
								<td class="col2"><label for="memberDoubleMajor"> <input
										type="radio" class="radio" name="member"
										id="memberDoubleMajor" value="Y" onchange="setDisplay()"
										checked> 있음
								</label> &nbsp;&nbsp;&nbsp; <label for="memberSingleMajor"> <input
										type="radio" class="radio" name="member"
										id="memberSingleMajor" value="N" onchange="setDisplay()">
										없음
								</label> &nbsp;&nbsp;&nbsp; <select name="StudentDoubleMajor"
									id="studentDoubleMajor">
										<option value=" " selected>-선택-</option>
										<option value="국어국문학과">국어국문학과</option>
										<option value="영어영문학과">영어영문학과</option>
										<option value="중어중문학과">중어중문학과</option>
										<option value="일어일문학과">일어일문학과</option>
										<option value="사학과">사학과</option>
										<option value="문헌정보학과">문헌정보학과</option>
										<option value="아랍지역학과">아랍지역학과</option>
										<option value="미술사학과">미술사학과</option>
										<option value="철학과">철학과</option>
										<option value="문예창작학과">문예창작학과</option>
										<option value="행정학과">행정학과</option>
										<option value="경제학과">경제학과</option>
										<option value="정치외교학과">정치외교학과</option>
										<option value="디지털미디어학과">디지털미디어학과</option>
										<option value="아동학과">아동학과</option>
										<option value="청소년지도학과">청소년지도학과</option>
										<option value="경영정보학과">경영정보학과</option>
										<option value="국제통상학과">국제통상학과</option>
										<option value="법학과">법학과</option>
										<option value="융합소프트웨어학부">융합소프트웨어학부</option>
										<option value="디지털콘텐츠디자인학과">디지털콘텐츠디자인학과</option>
										<option value="창의융합인재학부">창의융합인재학부</option>
										<option value="사회복지학과">사회복지학과</option>
										<option value="부동산학과">부동산학과</option>
										<option value="법무행정학과">법무행정학과</option>
										<option value="심리치료학과">심리치료학과</option>
										<option value="미래융합경영학과">미래융합경영학과</option>
										<option value="멀티디자인학과">멀티디자인학과</option>
										<option value="계약학과">계약학과</option>
								</select><br><br></td>
							</tr>
						</table>
						<div id="">
							<input type="submit" name="submitName" id="SignupComplete"
								value="회원가입">
						</div>
					</form>
				</div>

				<br> <br>


			</section>
		</div>
	</div>
</body>
</html>