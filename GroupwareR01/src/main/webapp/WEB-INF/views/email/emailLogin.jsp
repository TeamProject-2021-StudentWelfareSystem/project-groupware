<!-- 이메일 로그인 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Please sign in</title>

<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/login.js"></script>

<%--비밀번호 감추기 아이콘 링크 --%>
<link rel="stylesheet" href="../css/pwShowHide.css">
<%--비밀번호 감추기 아이콘 css --%>
<script src="../js/pwShowHide.js"></script>
<%--비밀번호 감추기 js--%>

<link rel="../stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="../preconnect" href="https://fonts.gstatic.com">
<link href="../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/buttons.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body id="page-top">

		<div class="container">

			 <!-- Outer Row -->
        <div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5" style="width:498px; margin:0 auto;">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="col-lg-12">
                                <div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">이메일 로그인</h1>
									</div>
									<form action="${path}/email/emailList" name="EmailLogin"
										method="POST" id="form" class="user">
										<div id="memberCheck">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /></input>
											<div id="content">
												<div class="form-group row">
													<div class="col-sm-6">
														<input type="text" class="form-control form-control-user"
															name="EmailLoginID" id="emailLoginID" autofocus
															autocomplete="off" value="${EmailLoginID}"
															aria-describedby="emailHelp" placeholder="ID">
													</div>
													<div class="col-sm-6">
														<input type="text" class="form-control form-control-user"
															name="email" id="mju" placeholder="@mju.ac.kr" disabled
															readonly>
													</div>
												</div>
												<div class="form-group">
													<input type="password"
														class="form-control form-control-user"
														name="EmailLoginPwd" id="emailLoginPW" autocomplete="off"
														value="${EmailLoginPwd}" placeholder="Password">
												</div>

											</div>
											<div>
												<input type="submit"
													class="btn btn-primary btn-user btn-block"
													name="SubmitName" id="submitId" value="로그인">
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script>
		$(document).ready(function() {
			let result = '<c:out value="${Checker}"/>';
			console.log(result);
			checkAlert(result);

			function checkAlert(result) {
				if (result === '') {
					return;
				}
				if (result === "LoginFail") {
					alert("아이디 또는 비밀번호를 체크해주세요");
				}
			}
		});
	</script>
</body>
</html>