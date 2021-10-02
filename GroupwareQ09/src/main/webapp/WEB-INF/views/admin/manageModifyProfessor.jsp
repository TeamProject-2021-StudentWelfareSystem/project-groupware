<!-- manageProfessor에서 수정 버튼 클릭 시 출력되는 교수 정보 수정 화면-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/infoModify.css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/manageModify.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title> manage information modify</title>
</head>
<body>
<div class="mjsWs">
		<div class="container">
			<section id="modify">
				<h2>사용자 정보 수정</h2>
				<br>
				<!--학생 정보 수정-->
				<div id="manageModifyProfessor" style="display: block;">
					<form action="" name="ManageModifyProfessor" method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
							<!-- 아이디, 이메일 수정 불가 -->
						<table>
							<tr>
								<td class="col1"><label for="id">아이디(학번)</label></td>
								<td class="col2"><input type="text" name="UserLoginID"
									id="userLoginID" class="inputBox" autocomplete="off" disabled readonly value=${UserLoginID}></td>
							</tr>

							<tr>
								<td class="col1"><label for="name">이름</label></td>
								<td class="col2"><input type="text" name="UserName"
									id="userName" class="inputBox" autocomplete="off" value=${UserName}></td>
							</tr>
							<tr>
								<td class="col1"><label for="email">이메일</label></td>
								<td class="col2"><input type="text" name="Email"
									id="userEmail" class="inputBox" autocomplete="off" disabled readonly value=${Email}></td>
								<td class="col3"><input type="text" value="@mju.ac.kr"
									disabled readonly id="mju" class="inputBox" autocomplete="off"></td>

							<tr>
								<td class="col1"><label for="phoneNumber">연락처</label></td>
								<td class="col2"><input type="text" name="UserPhoneNum"
									id="userPhoneNum" class="inputBox" placeholder="숫자만 입력해주세요"
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
								<td class="col1"><label for="professorRoom">교수실
										전화번호</label></td>
								<td class="col2"><input type="text" name="ProfessorRoomNum"
									id="professorRoomNum" class="inputBox" autocomplete="off"><br>
								<br></td>
							</tr>
							<tr>
								<td class="col1"><label for="infoOpen">정보 공개</label></td>
								<td class="col2"><h5>정보 공개를 체크하면 해당 정보가 공개로 표시됩니다</h5>
		                        <c:set var="OpenPhoneNum" value="${OpenPhoneNum}" />
		                         <c:if test="${OpenPhoneNum == '전화번호'}">
		                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone" checked="checked">연락처 &nbsp; </c:if>
		                   		 <c:if test="${OpenPhoneNum == '비공개'}">
		                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone">연락처 &nbsp; </c:if>
		                        <br></td>
							</tr>
						</table>
						<div id="">
							<input type="submit" name="ModifyComplete" id="modifyComplete"
								value="수정 완료"> <input type="button" name="Cancel"
								id="cancelBtn" value="취소">
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
</body>
</html>