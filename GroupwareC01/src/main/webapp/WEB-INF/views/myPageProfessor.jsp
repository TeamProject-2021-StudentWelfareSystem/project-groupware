<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/modify.css">
<script src="js/jquery-3.5.1.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>sign up</title>
</head>
<body>
	<div class="mjs_ws">
		<div class="container">
			<section id="signup">
				<h2>회원 정보</h2>
				<br>

				<!--학생 회원가입-->
				<div id="student_signup" style="display: block;">
					
						<form action="${path}/signup.do" name="signup" method="POST"
							id="form">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<table>
							<tr>
								<td class="col1"><label for="id">아이디(학번)</label></td>
								<td class="col2"><input type="text" name="userLoginID"
									id="userLoginID" class="inputBox" placeholder="" autofocus
									autocomplete="off" disabled readonly value="20150123"></td>
								
							</tr>
							<tr>
								<td class="col1"><label for="name">이름</label></td>
								<td class="col2"><input type="text" name="userName"
									id="userName" class="inputBox" autocomplete="off" disabled readonly value="김교수"></td>
							</tr>
							<tr>
								<td class="col1"><label for="phoneNumber">연락처</label></td>
								<td class="col2"><input type="text" name="userPhoneNum"
									id="userPhoneNum" class="inputBox" autocomplete="off" value="010-1234-9999"disabled readonly></td>
							</tr>
							<tr>
								<td class="col1"><label for="studentColleges">단과대학</label></td>
								<td class="col2"><input name="studentColleges"
									id="studentColleges" class="inputBox"disabled readonly></td>
							</tr>
							<tr>
								<td class="col1"><label for="studentMajor">전공</label></td>
								<td class="col2"><input name="studentMajor"
									id="studentMajor" class="inputBox"disabled readonly></td>
                            </tr>
                            <tr>
                        		<td class="col1"><label for="professorRoom">교수실</label></td>
                        		<td class="col2"><input name="professorRoom"
                           		id="professorRoom" class="inputBox"disabled readonly></td>
                     		</tr>
                             <tr>
                        		<td class="col1"><label for="professorRoom">교수실 전화번호</label></td>
                        		<td class="col2"><input name="professorRoomNum"
                           		id="professorRoomNum" class="inputBox"disabled readonly></td>
                     		</tr>
							<tr>
								<td class="col1"><label for="email">이메일</label></td>
								<td class="col2"><input type="text" name="userEmail"
									id="userEmail" class="inputBox" autocomplete="off"  disabled readonly></td>
								<td class="col4"><input type="email" value="@mju.ac.kr" disabled readonly name="email" id="mju"
									class="inputBox" autocomplete="off"></td>
							
							</tr>
							
							</table>
						</form>
					
				</div>

				<br> <br> 
				<div id="">
                    <a href="modifyStudent"><input type="submit" id="modifyBtn" value="수정하기"></a>
					<a href="homeLogin"><input type="submit" id="completeBtn" value="확인"></a>
				</div>
                
				
			</section>
		</div>
	</div>
</body>
</html>