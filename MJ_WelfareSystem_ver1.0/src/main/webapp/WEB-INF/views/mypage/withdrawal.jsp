<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoModify.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/withdrawal.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>withdrawal</title>
</head>
<body>
	<div class="mjsWs">
		<div id="content">
			<section id="withdrawal">
				<h2>회원 탈퇴</h2>
				<br>
				<div id="beforeWithdrawal">
					<form  action ="${path}/withdrawal" name="CheckPW" method="POST" id="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}"/>
							<tr>
								<td class="col1"><label for="withdrawalMessage">정말 회원 탈퇴를 하시겠습니까?</label></td>
							</tr>
						<p class="termsCheck">
							<span class="inputCheck">
								<input type="checkBox" id="termsWithdrawal" name="TermsWithdrawal" class="check" >
								<label for="termsWithdrawal">
									<span class="checkText"> 본인은 해당 계정의 탈퇴에 동의합니다. </span></label>
							</span>
						</p>
						<div>
							<input type="submit" id="agreeBtn" name="AgreeWithdrawal"
								value="확인">
							<input type="button"id="cancelBtn" 
								value="취소">	
						</div>
					</form>
					
				</div>
			</section>
		</div>
		<div>
			<form id="logout" name="Logout" action="${path}/logout.do" method="POST">
			<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /></form>
		</div>
	</div>
</body>
</html>