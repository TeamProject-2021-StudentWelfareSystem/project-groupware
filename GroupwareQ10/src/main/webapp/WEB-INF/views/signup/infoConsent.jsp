<!-- 회원가입 시 정보 동의 받는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>information consent</title>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/infoConsent.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link rel="stylesheet" href="css/infoConsent.css">
<link rel="stylesheet" href="css/buttons.css">
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
</head>
<body>
<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5"
					style="width: 498px; margin: 0 auto;">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="col-lg-12">
							<div class="p-5">
								<div class="text-center">
									<h1 class="h4 text-gray-900 mb-4">개인 정보 동의</h1>
								</div>
								<!-- 약관동의 -->
								<div class="terms mt-2">
									<div class="termsCheckAll small text-gray-900 mb-4">
										<span class="inputCheck"> <input type="checkbox" id="checkAll">
										<label for="checkAll"> <span class="checkAllText"> 이용약관, 개인정보 수집 및 이용에 모두 동의합니다. </span></label>
										</span>
									</div>
									<ul class="col-sm-12">
										<li class="consentList"><div class="small text-gray-900 mb-2"><span class="inputCheck">
												<input type="checkbox" id="termsService" name="TermsService" class="check">
												<label for="termsService"> 이용약관 동의 <span class="termsNecessary">(필수)</span></label></span></div>
											<div class="border-left-primary">
												<!-- 이용약관 동의 -->
												<div class="policy small pl-2 mb-4">여러분을 환영합니다.
														명지대학교 학생 복지 시스템을 이용해 주셔서 감사합니다. 본
														약관은 다양한 해당 서비스의 이용과 관련하여 명지대 학생 복지 시스템 서비스의 정보를 제공하고, 이를
														이용하는 명지대 학생 복지 시스템 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러
														여러분의 명지대 학생 복지 시스템 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.</div>
											</div>
										</li>
										
										<li class="consentList"><div class="small text-gray-900 mb-2"><span class="inputCheck">
												<input type="checkbox" id="termsPrivacy" name="TermsPrivacy" class="check">
												<label for="termsPrivacy" class="collectPersonal">개인정보 수집 및 이용 동의
												<span class="termsNecessary">(필수)</span></label></span></div>
												
											<div class="border-left-primary">
												<!-- 개인정보 수집 및 이용에 대한 안내 -->
												<div class="policy small pl-2 mb-4">
													개인정보보호법에 따라 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 
													개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니
													 자세히 읽은 후 동의하여 주시기 바랍니다.
												</div>
											</div></li>
									</ul>
								</div>
								<div class="form-group text-center">
									<div class="user">
										<a href="${path}/home" class="btn btn-secondary btn-user mr-3">취소</a> <a href="${path}/emailAuthentication" class="btn btn-primary btn-user" id="btnAgree">확인</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	    <!-- Bootstrap core JavaScript-->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>
