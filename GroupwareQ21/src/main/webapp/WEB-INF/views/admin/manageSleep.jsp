<!-- 관리자 메뉴-휴면계정관리 클릭 시 휴면 계정 리스트 출력 화면 -->

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
<!-- ajax 통신을 위한 meta tag -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<meta charset="utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="../stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet"
	href="../resources/vendor/fontawesome-free/css/all.min.css"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link rel="stylesheet" href="../css/sb-admin-2.min.css">
<link rel="stylesheet" href="../css/boardList.css" type="text/css">
<link rel="stylesheet" href="../css/manageList.css" type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/manageList.js"></script>

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

<title>manage sleep list</title>
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
					<div class="card shadow mb-4">
						<div class="card-text p-3">
							<ul class="nav nav-tabs">
							  <li class="nav-item">
							    <a class="nav-link" href="${path}/admin/manageList">회원 관리</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link active border-bottom-primary" aria-current="page" href="${path}/admin/manageSleep">휴면 계정 관리</a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link" href="${path}/admin/manageSecession">탈퇴 계정 관리</a>
							  </li>
							</ul>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<div id="dataTable_wrapper"
									class="dataTables_wrapper dt-bootstrap4">
									<div class="row mb-3">
										<div class="col-sm-12 col-md-6">
										</div>
										<div class="col-sm-12 col-md-2">
											<form
												class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search mt-3 pt-3">
												<div class="input-group">
													<input type="text"
														class="form-control bg-light border-0 small"
														placeholder="검색" aria-label="Search"
														aria-describedby="basic-addon2">
													<div class="input-group-append">
														<button class="btn btn-primary" type="button">
															<i class="fas fa-search fa-sm"></i>
														</button>
													</div>
												</div>
											</form>
										</div>
									</div>
									<form action="${path}/admin/manageList.do" name="ManageList"
										method="POST" id="form">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<table class="table table-bordered dataTable sleepUserList"
											style="width: 100%">
											<thead role="row">
												<tr>
													<th><input type="checkbox" class="checkAll"
														id="checkAll" name="CheckAll"></th>
													<th class="sorting">번호</th>
													<th class="sorting">학번</th>
													<th class="sorting">성명</th>
													<th class="sorting">전화번호</th>
													<th class="sorting">이메일</th>
													<th class="sorting">직책</th>
													<th class="sorting">권한</th>
													<th class="sorting">접속기록</th>
												</tr>
											</thead>
											<c:forEach items="${DormantList}" var="DormantList"
												varStatus="status">
												<tr>
													<td class="col-md-1"><input type="checkbox"
														name="RowCheck" class="check"
														value="${DormantList.getUserLoginID()}" /></td>
													<td><c:out value="${pageMaker.totalCount - ((pageMaker.cri.page-1) * 10) - status.index}" /></td>
													<td><c:out value="${DormantList.getUserLoginID()}" /></td>
													<td><c:out value="${DormantList.getUserName()}" /></td>
													<td><c:out value="${DormantList.getUserPhoneNum()}" /></td>
													<td><c:out value="${DormantList.getUserEmail()}" /></td>
													<c:if test="${DormantList.getUserRole()=='STUDENT'}">
														<td>학생</td>
													</c:if>
													<c:if test="${DormantList.getUserRole()=='ADMIN'}">
														<td>관리자</td>
													</c:if>
													<c:if test="${DormantList.getAuthority()=='ROLE_USER'}">
														<td>사용자</td>
													</c:if>
													<c:if test="${DormantList.getAuthority()=='ROLE_ADMIN'}">
														<td>관리자</td>
													</c:if>
													<td class="col-md-2"><c:out value="${DormantList.getLoginDate()}" /></td>
												</tr>
											</c:forEach>
										</table>
									
									<hr>
									<div class="form-group row">
										<div class="col-sm-12 col-md-3 row"></div>
										<div class="col-sm-12 col-md-4">
											<div class="dataTables_paginate paging_simple_numbers"
												id="dataTable_paginate">
												<ul class="pagination">
											        <li class="paginate_button page-item previous ${(pageMaker.cri.page==1) ||(pageMaker.cri.page)==(pageMaker.endPage) ? 'disabled':''}"
			                                          id="dataTable_previous">
			                                          <a href='<c:url value="/admin/manageSleep?page=${pageMaker.cri.page-1}"/>'
			                                          aria-controls="dataTable" data-dt-idx="0" tabindex="0"
			                                          class="page-link">Previous</a></li>
			                                       
			                                       <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
			                                       <li class="paginate_button page-item ${pageMaker.cri.page==pageNum ? 'active':'' }">
			                                          <a href='<c:url value="/admin/manageSleep?page=${pageNum}"/>' 
			                                          aria-controls="dataTable" data-dt-idx="1"
			                                          tabindex="0" class="page-link">${pageNum}</a></li>   
			                                       </c:forEach>            
			                                       <c:if test="${pageMaker.endPage > 0 }">
			                                       <li class="paginate_button page-item next ${(pageMaker.cri.page)==(pageMaker.endPage) ? 'disabled':'' }" id="dataTable_next">
			                                          <a href='<c:url value="/admin/manageSleep?page=${pageMaker.cri.page+1 }"/>'
			                                          aria-controls="dataTable" data-dt-idx="7" tabindex="0"
			                                          class="page-link">Next</a></li>
			                                       </c:if>
											    </ul>
											</div>
										</div>
										<div class="col-sm-12 col-md-3 row">
											<div class="col-sm-12 col-md-4">
												<input type="button" value="탈퇴"
													class="deleteButton btn btn-danger btn-user btn-block"
													id="delete">
											</div>
											<div class="col-sm-12 col-md-4">
												<input type="button" value="복구"
													class="recoveryButton btn btn-info btn-user btn-block"
													id="recovery">
											</div>
											<div class="col-sm-12 col-md-4">
												<a href="${path}/admin/manageList"><input type="button"
													value="목록" class="btn btn-primary btn-user btn-block"
													id="list"></a>
											</div>
										</div>
									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
				<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
			
		</div>
	</div>
	<script>
		// ajax 통신을 위한 csrf 설정
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		if (token && header) {
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		}
		$(".recoveryButton").click(function() {
			var CheckArr = new Array();
			var list = $("input[name='RowCheck']");
			for (var i = 0; i < list.length; i++) {
				if (list[i].checked) {
					CheckArr.push(list[i].value);
				}
			}

			if (CheckArr.length == 0) {
				alert("선택된 사용자가 없습니다.");
			} else {

				var confirm_val = confirm("정말 복구 처리하시겠습니까?");
				if (confirm_val) {
					$.ajax({
						url : "dormantRecovery.do",
						type : "POST",
						traditional : true,
						data : {
							CheckArr : CheckArr
						},
						success : function(jdata) {
							if (jdata = 1) {
								alert("복구 성공");
								location.reload();
							} else {
								alert("복구 실패");
							}
						},
						error : function() {
							alert("서버통신 오류");
						}
					});
				}
			}
		});

		$(".deleteButton").click(function() {
			var CheckArr = new Array();
			var list = $("input[name='RowCheck']");
			for (var i = 0; i < list.length; i++) {
				if (list[i].checked) {
					CheckArr.push(list[i].value);
				}
			}

			if (CheckArr.length == 0) {
				alert("선택된 사용자가 없습니다.");
			} else {

				var confirm_val = confirm("정말 탈퇴 처리하시겠습니까?");
				if (confirm_val) {
					$.ajax({
						url : "dormantWithdrawal.do",
						type : "POST",
						traditional : true,
						data : {
							CheckArr : CheckArr
						},
						success : function(jdata) {
							if (jdata = 1) {
								alert("탈퇴 성공");
								location.reload();
							} else {
								alert("탈퇴 실패");
							}
						},
						error : function() {
							alert("서버통신 오류");
						}
					});
				}
			}
		});
	</script>
</body>
</html>