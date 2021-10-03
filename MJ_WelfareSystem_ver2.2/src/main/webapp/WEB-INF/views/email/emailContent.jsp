<!-- 이메일 내용 출력 화면 -->

<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");
%>


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
<link rel="stylesheet" href="../css/sb-admin-2.min.css">
<link rel="stylesheet" href="../css/boardList.css" type="text/css">
<link rel="stylesheet" href="../css/buttons.css">
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

<!-- Page level custom scripts -->
<script src="../resources/vendor/bootstrap/js/demo/chart-area-demo.js"></script>
<script src="../resources/vendor/bootstrap/js/demo/chart-pie-demo.js"></script>

<title>email content</title>
</head>
<body id="pagetop">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/WEB-INF/views/homeView/topbar.jsp"></jsp:include>
				<div class="container-fluid">
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h4 class="m-0 font-weight-bold text-primary">이메일</h4>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<div class="form-group col-sm-12">
										<div class="form-group row text-gray-800 ml-1 my-1">
											<div class="font-weight-bold">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
												<c:out value="${EmailTitle}"></c:out>
											</div>
											<br>
											<div>
												<input type="text" name="EmailDate" id="emailDate" class="input-sm input-tp small" 
												style="width: 150px" disabled readonly value="${EmailDate}">
											</div>
										</div>	
											<div>
												<label for="date" class="col-form-label"><span class="small">보낸 이</span></label>
												<label>[</label><input type="text" name="EmailSender" id="emailSender" class="input-sm input-tp small" 
												style="width: 260px" placeholder="보낸 이" disabled readonly value="${EmailSender}"><label>]</label>
											</div>
										<hr>
										<div class="form-group row my-3">
											<div class="card-body">
												<div class="ckeditor_contents">
													<p class="card-text">
														<c:out value="${EmailContent}" escapeXml="false" />
													</p>
												</div>
											</div>
										</div>
										<hr>
									</div>
								<hr>
								<a href="emailList"><input type="button" value="목록"
									id="listButton" class="btn btn-primary btn-user btn-block"></a>
							</div>
						</div>
					</div>
					<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>