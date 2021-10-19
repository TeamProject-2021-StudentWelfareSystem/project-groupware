<!-- 비밀번호 찾기 화면 -->

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

<!-- Custom fonts for this template-->
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet" href="css/buttons.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/findPassword.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>find password</title>
</head>
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
									<h1 class="h4 text-gray-900 mb-2">Find Password</h1>
									<div class="my-4"></div>
									<div class="small">
										비밀번호를 찾고자 하는 아이디와 이름, 이메일을 입력해주세요. <br>본인 확인 이메일 주소와 입력한
										이메일 주소가 일치해야, <br>인증번호를 받을 수 있습니다.
									</div>
								</div>
								<div class="my-5"></div>
								<form action="${path}/findPassword.do" name="FindPwd"
									method="POST" id="form" class="user">
									<div id="memberCheck">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												aria-describedby="emailHelp" placeholder="이름"
												name="UserName" id="userName" autofocus autocomplete="off"
												value="${UserName}">
										</div>
										<div class="form-group row">
											<div class="col-sm-6 mb-3 mb-sm-0">
												<input type="text" class="form-control form-control-user"
													placeholder="ID" name="UserLoginID" id="userLoginID"
													autocomplete="off" value="${UserLoginID}">
											</div>
											<div class="col-sm-6">
												<button type="submit" class="form-control form-control-user"
													name="IdCheck" id="check">계정 확인</button>
											</div>
										</div>
										<div class="my-4"></div>
										<hr>
										<div class="my-4"></div>
										<div class="form-group row">
											<div class="col-sm-6 mb-3 mb-sm-0">
												<input type="text" class="form-control form-control-user"
													name="UserEmail" placeholder="E-mail" autocomplete="off"
													value="${UserEmail}">
											</div>
											<div class="col-sm-6">
												<input type="text" class="form-control form-control-user"
													name="email" id="mju" placeholder="@mju.ac.kr" disabled
													readonly>
											</div>
										</div>
										<div class="form-group">
											<button type="submit"
												class="btn btn-primary btn-user btn-block" name="EmailCheck">인증번호
												받기</button>
										</div>
										<div class="my-4"></div>
										<hr>
										<div class="my-4"></div>
										<div class="form-group row">
											<div class="col-sm-6 mb-3 mb-sm-0">
												<input type="text" name="Number" id="emailNum"
													class="form-control form-control-user" placeholder="인증번호 6자리" value="${Number}">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
											</div>
											<div class="col-sm-6">
												<button type="submit" class="form-control form-control-user"
													id="emailValid" name="EmailValid">인증하기</button>
												<!-- alert 막아야 함 -->
											</div>
										</div>
										<button type="submit" name="SubmitName"
											class="btn btn-primary btn-user btn-block">
											임시 비밀번호 받기
											<!-- alert 띄우고 로그인 화면으로 이동 -->
										</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

</body>
</html>