<!-- 회원가입 시 이메일 인증하는 화면 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/signupselect.css">
<link rel="stylesheet" href="css/emailAuthentication.css">
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/email.js"></script>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>회원 가입</title>
</head>

<body class="bg-gradient-primary sidebar-toggled">

    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5" style="width:498px; margin:0 auto;">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4"> 이메일 인증 </h1>
                            </div>
                            <form class="user" action="${path}/email.do" id="signupSelect" name="select" method="POST">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="Email"
												id="userEmail" autocomplete="off" placeholder="이메일" value=${Email}>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user"
                                        		disabled readonly id="mju" class="inputBox" autocomplete="off" placeholder="@mju.ac.kr">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="form-control form-control-user" value="인증번호 받기"
											name="EmailCheck" id="emailCheck">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="Number"
												id="emailNum" placeholder="인증 번호" value=${Number} >
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="submit" class="bg-gray-100 form-control form-control-user" value="인증하기"
												name="EmailValid" id="emailValid" >
                                    </div>
                                </div>
                                <a href="${path}/signupStudent" class="btn btn-primary btn-user btn-block">
                                    이메일 인증 완료
                                </a>
                                <hr>
                                <a href="${path}/home" class="btn btn-google btn-user btn-block">
                                    <i class="fab fa-google fa-fw"></i> 메인 화면으로
                                </a>
                               <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="${path}/findPassword">비밀번호 찾기</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="${path}/login">로그인</a>
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