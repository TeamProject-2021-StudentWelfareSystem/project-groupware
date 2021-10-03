<!-- 후기 작성 화면 -->

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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
	
<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/buttons.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	

<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/reviewWrite.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>

<title>review write</title>
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
				<div class="container-fluid text-gray-900">
				<div class="card shadow my-4">
				<div class="card-header text-center py-1">
					<h5 class="m-0 font-weight-bold text-primary m-3">후기 작성</h5>
					<p class="small">
						후기는 익명을 원칙으로 하여 다른 회원들에게 개인정보가 공개되지 않음을 알려드립니다.<br>
						또한 수정 및 삭제가 불가하오니 신중하게 고민 후 답변 부탁드립니다.
					</p>
				</div>
				<div class="card-body">
				<form action="reviewWrite?${_csrf.parameterName}=${_csrf.token}" 
					name="ReviewWrite" enctype="multipart/form-data" method="POST" id="form">
					<div class="form-group col-sm-12">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group row justify-content-center text-center text-gray-900 my-3">
							<div class="input-group col-sm-4 mb-5">
							  <div class="input-group-prepend">
							    <label class="input-group-text font-weight-bold" for="teamUser">팀원</label>
							  </div>
							  <select class="custom-select text-center" name="TeamUser" id="teamUser" onchange="optionChange2(this)">
							    <option value="" selected>-팀원 선택-</option>
							    <c:forEach items="${TeamUserList}" var="TeamUserList" varStatus="status">
									<option><c:out value="${TeamUserList}" /></option>
								</c:forEach>
							  </select>
							</div>
							<table class="table table-bordered text-gray-900 col-sm-10 mb-5">
							  <thead class="table-primary">
							    <tr>
							      <th colspan="6">평가 항목</th>
							    </tr>
							  </thead>
							  <tbody>
							    <tr>
							      <th scope="row" class="table-secondary">적극성</th>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="positive1" name="Positive" class="custom-control-input" value="매우 낮음">
										 <label class="custom-control-label" for="positive1">매우 낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="positive2" name="Positive" class="custom-control-input" value="낮음">
										 <label class="custom-control-label" for="positive2">낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="positive3" name="Positive" class="custom-control-input" value="보통">
										 <label class="custom-control-label" for="positive3">보통</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="positive4" name="Positive" class="custom-control-input" value="높음">
										  <label class="custom-control-label" for="positive4">높음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="positive5" name="Positive" class="custom-control-input" value="매우 높음">
										  <label class="custom-control-label" for="positive5">매우 높음</label>
									</div></td>
							    </tr>
							    <tr>
							      <th scope="row" class="table-secondary">공헌도</th>
							      <td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="contribute1" name="Contribute" class="custom-control-input" value="매우 낮음">
										 <label class="custom-control-label" for="contribute1">매우 낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="contribute2" name="Contribute" class="custom-control-input" value="낮음">
										 <label class="custom-control-label" for="contribute2">낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="contribute3" name="Contribute" class="custom-control-input" value="보통">
										 <label class="custom-control-label" for=contribute3>보통</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="contribute4" name="Contribute" class="custom-control-input" value="높음">
										  <label class="custom-control-label" for="contribute4">높음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="contribute5" name="Contribute" class="custom-control-input" value="매우 높음">
										  <label class="custom-control-label" for="contribute5">매우 높음</label>
									</div></td>
							    </tr>
							    <tr>
							      <th scope="row" class="table-secondary">타인존중도</th>
							      <td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="respect1" name="Respect" class="custom-control-input" value="매우 낮음">
										 <label class="custom-control-label" for="respect1">매우 낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="respect2" name="Respect" class="custom-control-input" value="낮음">
										 <label class="custom-control-label" for="respect2">낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="respect3" name="Respect" class="custom-control-input" value="보통">
										 <label class="custom-control-label" for=respect3>보통</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="respect4" name="Respect" class="custom-control-input" value="높음">
										  <label class="custom-control-label" for="respect4">높음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="respect5" name="Respect" class="custom-control-input" value="매우 높음">
										  <label class="custom-control-label" for="respect5">매우 높음</label>
									</div></td>
							    </tr>
							    <tr>
							      <th scope="row" class="table-secondary">유연성</th>
							      <td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="flexible1" name="Flexible" class="custom-control-input" value="매우 낮음">
										 <label class="custom-control-label" for="flexible1">매우 낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="flexible2" name="Flexible" class="custom-control-input" value="낮음">
										 <label class="custom-control-label" for="flexible2">낮음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										 <input type="radio" id="flexible3" name="Flexible" class="custom-control-input" value="보통">
										 <label class="custom-control-label" for=flexible3>보통</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="flexible4" name="Flexible" class="custom-control-input" value="높음">
										  <label class="custom-control-label" for="flexible4">높음</label>
									</div></td>
									<td><div class="custom-control custom-radio custom-control-inline">
										  <input type="radio" id="flexible5" name="Flexible" class="custom-control-input" value="매우 높음">
										  <label class="custom-control-label" for="flexible5">매우 높음</label>
									</div></td>
							    </tr>
							  </tbody>
							</table>
						</div>
						<hr>
					</div>
					<div class="user text-center row p-3 justify-content-center">
						<button type="submit" class="btn btn-primary btn-user mr-2" id="saveButton">저장</button>
						<a href="${path}/team/searchMyTeam">
							<input type="button" value="이전" id="listButton" class="btn btn-secondary btn-user">
						</a>
		            </div>
				</form>
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