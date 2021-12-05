<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

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

<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/modifyTeam.js"></script>

<title>modify team</title>
</head>
<body id="page-top">
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/WEB-INF/views/homeView/topbar.jsp"></jsp:include>
				<div class="container-fluid"></div>
				<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-12 col-md-9">
					<div class="card o-hidden border-0 shadow-lg my-5" style="width: 498px; margin: 0 auto;">
						<div class="card-body p-0">
							<div class="col-lg-12">
								<div class="p-5">
									<div class="text-center">
										<h4 class="text-primary mb-5">팀 수정하기</h4>
									</div>
									<form action="modifyTeam?no=${TeamID}" name="ModifyTeam" method="POST" id="form">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<div class="row gx-3 mb-3">
			                            	<div class="col-md-6">
			                                   <label class="small mb-1" for="lectureName">과목명</label>
											   <input class="form-control bg-white" id="lectureName" name="lectureName" type="text" readonly value="${LectureName}">
			                               </div>
			                               <div class="col-md-6">
			                                   <label class="small mb-1" for="lectureProfessor">교수명</label>
											   <input class="form-control bg-white small" type="text" name="LectureProfessor" id="lectureProfessor" readonly value="${LectureProfessor}">
			                               </div>
			                            </div>
										<div class="mb-3">
											<label class="small mb-1" for="userEmail">팀 이름</label>
				                            <input class="form-control bg-white" id="teamName" name="TeamName" type="text" readonly value="${TeamName}">
			                            </div>
			                            
			                            <c:forEach items="${teamList}" var="teamList" varStatus="status">
			                            <div class="row gx-3 mb-3">
				                            <div class="col-md-6">
				                            	<label class="small mb-1" for="studentID">팀원 학번</label>
				                            	<input class="form-control bg-white small" id="studentID" name="StudentID" type="text" value="${teamList.getUserLoginID()}">
				                            </div>
				                            <div class="col-md-6">
			                                   <label class="small mb-1" for="studentName">팀원 이름</label>
			                                   <input class="form-control bg-white" id="studentName" name="StudentName" type="text" value="${teamList.getUserName()}">
			                               </div>
				                               <input type="button" id="deleteMember" type="button" class="btn btn-danger btn-xs p-1" style="position:absolute; margin-left:75%;" value="x">
				                        </div>
										</c:forEach>				
						                            
			                            <div class="mb-5" id="addStudent">
			                            	
										</div>
			                            
					                    <div class="user m-4 justify-center-center">
											<div class="text-center">
											<input type="button" value="팀원 추가" class="mr-2 btn btn-secondary btn-user" id="addMember">
											<input type="submit" value="수정" class="mr-2 btn btn-primary btn-user" id="modifyBtn">
											<a href="${path}/team/teamList"><input type="button" class="btn btn-light btn-user" value="목록" id="listButton"></a>
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
			<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
		</div>
	</div>

	<!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

</body>
</html>