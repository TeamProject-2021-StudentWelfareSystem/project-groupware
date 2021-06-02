<!-- 회원가입 시 정보 동의 받는 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/infoConsent.css">
	<script src="js/jquery-3.5.1.min.js"></script>
	<script src="js/infoConsent.js"></script>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link
		href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
		rel="stylesheet">
	
	<title>information consent</title>
</head>
<body>
	<div class="mjsWs">
	<div id="content">
		<h2>개인 정보 동의</h2>
			<div class="form">
				<form id="consentForm" name="consent" method="GET" action="">

					<!-- 약관동의 -->
					<div class="terms">
						<p class="termsCheckAll">
							<span class="inputCheck">
								<input type="checkbox" id="checkAll">
								<label for="checkAll">
									<span class="checkAllText"> 이용약관, 개인정보 수집 및 이용에 모두 동의합니다. </span></label>
							</span>
						</p>
						<ul class="termsBoxList">
							<li class="consentList"><span class="inputCheck">
								<input type="checkbox" id="termsService" name="TermsService" class="check"> 
								<label for="termsService">이용약관 동의 <span class="termsNecessary">(필수)</span>
								</label></span>
								<div class="termsBox" id="divService">
									<!-- 이용약관 동의 -->
									<div class="policy">
										<h4 class="policyTitle">여러분을 환영합니다.</h4>
										<p class="policyText">명지대학교 학생 복지 시스템을 이용해 주셔서 감사합니다. 
											본 약관은 다양한 해당 서비스의 이용과 관련하여 명지대 학생 복지 시스템 서비스의 정보를 제공하고,
											이를 이용하는 명지대 학생 복지 시스템 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며,
											아울러 여러분의 명지대 학생 복지 시스템 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.</p>
									</div>
								</div>
							</li>
							<li class="consentList"><span class="inputCheck">
								<input type="checkbox" id="termsPrivacy" name="TermsPrivacy" class="check"> 
								<label for="termsPrivacy" class="collectPersonal">개인정보 수집 및 이용 동의<span class="termsNecessary">(필수)</span></label></span>
								<div class="termsBox" id="divPrivacy">
									<!-- 개인정보 수집 및 이용에 대한 안내 -->
									<div class="policy">
										<p class="policyText">개인정보보호법에 따라 회원가입 신청하시는 분께
											수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의
											거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.</p>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- 약관동의 -->
					<a name="agreeBottom"></a>
					<div class="btnArea">
						<span><a href="home"
							id="btnCancel" class="button" role="button">취소</a></span>
						<span> <a href="emailAuthentication"
							id="btnAgree" class="button" role="button">확인</a></span>
					</div>
				</form>

			</div>
		</div>
</div>
</body>
</html>
