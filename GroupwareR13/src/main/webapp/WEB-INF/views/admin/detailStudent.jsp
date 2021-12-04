<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<link rel="../stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/buttons.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>

<title>my page</title>
</head>
<body id="page-top">
    <div id="wrapper">
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
				<jsp:include page="/WEB-INF/views/homeView/topbar.jsp"></jsp:include>
				<div class="container-fluid">
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h5 class="m-0 font-weight-bold text-primary text-center">회원 정보</h5>
					</div>
						<div class="card-body mt-3">
							<div class="col-sm-12" style="width:498px; margin: 0 auto;">
								<form action="${path}/detailStudent.do" name="StudentPage" method="POST" id="form">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								
                                <div class="row gx-3 mb-3">
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="userLoginID">아이디(학번)</label>
                                         <input class="form-control bg-white" id="userLoginID" name="UserLoginID" type="text" readonly value="${UserLoginID}">
                                     </div>
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="userName">이름</label>
                                         <input class="form-control bg-white" id="userName" name="SUserName" type="text" readonly value="${SUserName}">
                                     </div>
                                 </div>
                                <div class="row gx-3 mb-3">
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="studentGender">성별</label>
                                         <input class="form-control bg-white" id="studentGender" name="StudentGender" type="text" readonly value="${StudentGender}">
                                     </div>
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="userPhoneNum">연락처</label>
                                         <input class="form-control bg-white" id="userPhoneNum" name="UserPhoneNum" type="text" readonly value="${UserPhoneNum}">
                                     </div>
                                 </div>
                                <div class="row gx-3 mb-3">
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="studentGrade">학년</label>
                                         <input class="form-control bg-white" id="studentGrade" name="StudentGrade" type="text" readonly value="${StudentGrade}">
                                     </div>
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="studentColleges">단과대학</label>
                                         <input class="form-control bg-white" id="studentColleges" name="StudentColleges" type="text" readonly value="${StudentColleges}">
                                     </div>
                                 </div>
                                <div class="row gx-3 mb-3">
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="studentMajor">전공</label>
                                         <input class="form-control bg-white" id="studentMajor" name="StudentMajor" type="text" readonly value="${StudentMajor}">
                                     </div>
                                     <div class="col-md-6">
                                         <label class="small mb-1" for="studentDoubleMajor">복수전공</label>
                                         <input class="form-control bg-white" id="studentDoubleMajor" name="StudentDoubleMajor" type="text" readonly value="${StudentDoubleMajor}">
                                     </div>
                                 </div>
								<div class="mb-3">
									<label class="small mb-1" for="userEmail">이메일</label>
	                                <input class="col-md-6 form-control bg-white" id="userEmail" name="UserEmail" type="text" readonly value="${UserEmail}@mju.ac.kr">
                                </div>
                                <div class="mb-5">
                                    <label class="small mb-1" for="userInfoOpen">정보 공개</label>
                                    <input class="col-md-6 form-control bg-white" id="userInfoOpen" name="UserInfoOpen" type="text" readonly value="${UserInfoOpen}">
                                </div>
								</form>
								<div class="user m-4 justify-center-center">
									<div class="text-center">
										<a href="manageModifyStudent?no=${UserLoginID}" onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
											<input type="button" id="modifyBtn" class="btn btn-primary btn-user" value="정보 수정"></a>
										
										<input type="button" id="withdrawBtn" class="deleteButton btn btn-danger btn-user" value="탈퇴">
										<a href="manageList">
											<input type="button" id="listBtn" class="btn btn-light btn-user" value="목록"></a>
										</div>
								</div>
							</div>
						</div>
				</div>
			</div>
			
			<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
	</div>

	</div>
	    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
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
			if (result === "true") {
				alert("성공적으로 예약이 취소 되었습니다.");
			} else if (result === "Noting") {
				alert("예약한 강의실이 없습니다.");
			}
		}
	});
</script>
</body>
</html>