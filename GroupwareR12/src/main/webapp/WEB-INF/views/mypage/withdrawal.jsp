<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link href="css/buttons.css" rel="stylesheet">

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/withdrawal.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="js/withdrawal.js"></script>
<title>withdrawal</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5" style="width:498px; margin:0 auto;">
                    <div class="card-body">
                        <div class="col-lg-12">
                             <div class="p-5">
                             	<div class="text-center">
                                     <h4 class="text-gray-900 mb-4">회원 탈퇴</h4>
                                </div>
                                <form action="${path}/withdrawal" name="CheckPw" method="POST" id="form">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                
                                <div class="form-group user">
                                <div class="text-center">
                                	<label for="withdrawalMessage" class="text-weight-bold text-gray-900">정말 탈퇴 하시겠습니까?</label><br>
                                	<span class="small mt-3 text-gray-900">회원 탈퇴 시 회원님의 개인 정보는 6개월 이내에 영구 삭제되며, 작성하신 글과 댓글은 자동으로 삭제되지 않습니다.</span>
                                	<div class="my-4">
	                                	<p class="termsCheck">
											<span class="inputCheck">
												<input type="checkBox" id="termsWithdrawal" name="TermsWithdrawal" class="check">
												<label for="termsWithdrawal">
													<span class="checkText h6 text-gray-900">동의합니다</span></label>
											</span>
										</p>
									</div>
								</div>
                                </div>
                                     
                               <div class="user m-4 justify-center-center">
									<div class="text-center">
										<input type="submit" id="agreeBtn" class="btn btn-primary btn-user" value="확인">
						                <input type="button" name="Cancel" id="cancelBtn" class="btn btn-light btn-user" value="취소">
									</div>
								</div> 
                                </form>
                                <form id="logout" name="Logout" action="${path}/logout.do" method="POST">
									<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
								</form>
                            </div>
                        </div>
                    </div>
				</div>
            </div>
        </div>
   </div>
</body>
</html>