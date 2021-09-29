<!-- 마이페이지 화면 (학생) -->

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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/buttons.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="vendor/bootstrap/js/sb-admin-2.min.js"></script>

<link rel="stylesheet" href="css/myPage.css" type="text/css">

<title>my page</title>
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
</head>
<body id="page-top">
<!-- Page Wrapper -->
    <div id="wrapper">
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
			 <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
				<jsp:include page="/WEB-INF/views/homeView/topbar.jsp"></jsp:include>
				<div class="container-fluid">
								
				</div>
			</div>
			
			<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
			
	</div>

	</div>
	    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
</body>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">
					<div class="rightBox">
						<div class="subMenuBox">
						<ul>
							<li><a href="${path}/myPage?R=${UserRole}">내 정보 확인</a></li>
							<li><a href="${path}/myPostList">내 게시글 조회</a></li>
							<li><a href="${path}/confirmMyReservation">강의실 예약 조회</a></li>
							<li><a href="${path}/myInquiryList">내 문의 조회</a></li>
						</ul>
					</div>
						<section id="memberInfo">
							<h2>회원 정보</h2>
							<br>
							<!--마이페이지 (학생)-->
							<div id="myPageStudent" style="display: block;">

								<form action="${path}/myPageStudent.do" name="StudentPage" method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table>
										<tr>
											<td class="col1"><label for="id">아이디(학번)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
											<td class="col2"><input type="text" name="UserLoginID"
												id="userLoginID" class="inputBox" placeholder="" autofocus
												autocomplete="off" disabled readonly value=${UserLoginID}></td>

										</tr>
										<tr>
											<td class="col1"><label for="name">이름</label></td>
											<td class="col2"><input type="text" name="UserName"
												id="userName" class="inputBox" autocomplete="off" disabled
												readonly value=${UserName}></td>
										</tr>
										
										<tr>
											<td class="col1"><label for="gender">성별</label></td>
											<td class="col2"><input type="text" name="StudentGender"
												id="studentGender" class="inputBox" disabled readonly
												value=${StudentGender}></td>
										</tr>

										<tr>
											<td class="col1"><label for="phoneNumber">연락처</label></td>
											<td class="col2"><input type="text" name="UserPhoneNum"
												id="userPhoneNum" class="inputBox" autocomplete="off"
												disabled readonly value=${UserPhoneNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="grade">학년</label></td>
											<td class="col2"><input type="text" name="StudentGrade"
												id="studentGrade" class="inputBox" disabled readonly
												value=${StudentGrade}></td>
										</tr>
										<tr>
											<td class="col1"><label for="studentColleges">단과대학</label></td>
											<td class="col2"><input type="text"
												name="StudentColleges" id="studentColleges" class="inputBox"
												disabled readonly value=${StudentColleges}></td>
										</tr>
										<tr>
											<td class="col1"><label for="studentMajor">전공</label></td>
											<td class="col2"><input type="text" name="StudentMajor"
												id="studentMajor" class="inputBox" disabled readonly
												value=${StudentMajor}></td>
										</tr>
										<tr id="doubleMajor">
											<td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
											<td class="col2"><label for="member_DoubleMajor"></label>
												<input type="text" name="StudentDoubleMajor"
												id="studentDoubleMajor" class="inputBox" disabled readonly
												value=${StudentDoubleMajor}></td>
										</tr>
										<tr>
											<td class="col1"><label for="email">이메일</label></td>
											<td class="col2"><input type="text" name="UserEmail"
												id="userEmail" class="inputBox" autocomplete="off" disabled
												readonly value=${UserEmail}></td>
											<td class="col4"><input type="email" value="@mju.ac.kr"
												disabled readonly name="email" id="mju" class="inputBox"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td class="col1"><label for="infoOpen">정보 공개</label></td>
											<td class="col2"><input type="text" name="UserInfoOpen"
												id="userInfoOpen" class="inputBox" autocomplete="off"
												disabled readonly value=${UserInfoOpen}></td>
										</tr>
										
									</table>
								</form>
							</div>
							<br> <br>
							<div id="btnDiv">
								<a href="checkPassword"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyBtn" value="수정하기">
								</a> <a href="checkPassword3"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyPWBtn" value="비밀번호 변경하기">
								</a> <a href="checkPassword2"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="withdrawBtn" value="회원 탈퇴">
								</a>
							</div>
						</section>
					</div><!-- right_box -->
			</div><!-- mcont_width -->
		</div><!-- mbody -->
	</nav>

</body>
</html>