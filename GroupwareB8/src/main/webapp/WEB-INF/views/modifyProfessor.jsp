<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoModify.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/infoModify.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>information modify</title>
</head>
<body>
	<div class="mjs_ws">
		<div class="container">
			<section id="modify">
				<h2>개인 정보 수정</h2>
				<br>
				<!--교수 정보 수정-->
				<div id="modifyProfessor" style="display: block;">
					<form action="" name="ModifyProfessor" method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table>
							<!-- ID, 이름 수정 불가 -->
							<tr>
								<td class="col1"><label for="id">아이디(학번)</label></td>
								<td class="col2"><input type="text" name="UserLoginID"
									id="userLoginID" class="inputBox" placeholder=""
									autocomplete="off" readonly value=${UserLoginID}></td>
							</tr>

							<tr>
								<td class="col1"><label for="name">이름</label></td>
								<td class="col2"><input type="text" name="UserName"
									id="userName" class="inputBox" autocomplete="off" readonly
									value=${UserName}></td>
							</tr>
							<tr>
								<td class="col1"><label for="email">이메일</label></td>
								<td class="col2"><input type="text" name="Email"
									id="userEmail" class="inputBox" autocomplete="off" readonly
									value=${Email}></td>
								<td class="col3"><input type="text" value="@mju.ac.kr"
									disabled readonly id="mju" class="inputBox" autocomplete="off"></td>
							<tr>
								<td class="col1"><label for="phoneNumber">연락처</label></td>
								<td class="col2"><input type="text" name="UserPhoneNum"
									id="userPhoneNum" class="inputBox" placeholder="숫자만 입력해주세요"
									autocomplete="off"></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorColleges">단과대학</label></td>
								<td class="col2"><select name="ProfessorColleges"
									id="professorColleges">
										<option value=" "></option>
										<option value="humanities">인문대학</option>
										<option value="socialScience">사회과학대학</option>
										<option value="business">경영대학</option>
										<option value="law">법과대학</option>
										<option value="ictConvergence">ict융합대학</option>
										<option value="futureConvergence">미래융합대학</option>
								</select></td>
							</tr>
							<tr>
								<td class="col1"><label for="professorMajor">전공</label></td>
								<td class="col2"><select name="ProfessorMajor"
									id="professorMajor">
										<option value=" "></option>
										<option value="korean">국어국문학과</option>
										<option value="english">영어영문학과</option>
										<option value="chinese">중어중문학과</option>
										<option value="japanese">일어일문학과</option>
										<option value="history">사학과</option>
										<option value="literatureInfo">문헌정보학과</option>
										<option value="arabRegion">아랍지역학과</option>
										<option value="artHistory">미술사학과</option>
										<option value="philosophy">철학과</option>
										<option value="creativeWriting">문예창작학과</option>
										<option value="administration">행정학과</option>
										<option valu**e="economy">경제학과</option>
										<option value="politicalDiplomacy">정치외교학과</option>
										<option value="digitalMedia">디지털미디어학과</option>
										<option value="pediatrics">아동학과</option>
										<option value="youthGuidance">청소년지도학과</option>
										<option value="managementInformation">경영정보학과</option>
										<option value="internationalTrade">국제통상학과</option>
										<option value="law">법학과</option>
										<option value="convergenceSoftware">융합소프트웨어학부</option>
										<option value="digitalContent">디지털콘텐츠디자인학과</option>
										<option value="creativeConvergenceTalent">창의융합인재학부</option>
										<option value="socialWelfare">사회복지학과</option>
										<option value="realEstate">부동산학과</option>
										<option value="legalAdministration">법무행정학과</option>
										<option value="psychotherapy">심리치료학과</option>
										<option value="futureConvergenceManagement">미래융합경영학과</option>
										<option value="multiDesign">멀티디자인학과</option>
										<option value="contract">계약학과</option>
								</select></td>
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
								<td class="col2"><h5>정보 공개를 체크하면 해당 정보가 공개로 표시됩니다</h5> <input
									type="checkbox" name="UserInfo" id="cUserName" value="userName">이름 &nbsp;
									<input type="checkbox" name="UserInfo" id="cUserEmail" value="">이메일 &nbsp;
									<input type="checkbox" name="UserInfo" id="cUserPhone" value="">연락처 &nbsp;
									<input type="checkbox" name="UserInfo" id="cUserMajor"
									value="userMajor">전공 &nbsp; <br></td>
							</tr>
						</table>
						<div id="">
							<input type="submit" name="SubmitName" id="modifyCompleteP"
								value="수정"> <input type="button" name="Cancel"
								id="cancelBtn" value="취소">
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
</body>
</html>