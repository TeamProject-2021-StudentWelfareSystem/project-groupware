<!-- 초기 home 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<script src="js/faqList.js"></script>
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/boardList.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>

<!-- Page level custom scripts -->
<script src="resources/vendor/bootstrap/js/demo/chart-area-demo.js"></script>
<script src="resources/vendor/bootstrap/js/demo/chart-pie-demo.js"></script>

<title>MJS FAQ System</title>
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

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h4 class="m-0 font-weight-bold text-primary">FAQ</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<div id="dataTable_wrapper"
									class="dataTables_wrapper dt-bootstrap4">
									<div class="row mb-3">
										<div class="col-sm-12 col-md-4">
										</div>
										<div class="col-sm-12 col-md-4">
											<!--<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search mt-3 pt-3" >
                        						<div class="input-group">
                            						<select class="form-control bg-light border-0 small" aria-label="Search" aria-describedby="basic-addon2">
                            							 <option value=" " selected>-선택-</option>
							                           	 <option value="강의실 예약/이용">강의실 예약/이용</option>
							                           	 <option value="팀원관리">팀원관리</option>
							                             <option value="커뮤니티">커뮤니티</option>
							                             <option value="후기">후기</option>
							                             <option value="건의">건의</option>
							                             <option value="기타">기타</option>
                            						</select>
                            						<input type="text" class="form-control bg-light border-0 small" placeholder="검색" aria-label="Search" aria-describedby="basic-addon2">
                            					<div class="input-group-append">
                                					<button class="btn btn-primary" type="button">
                                    				<i class="fas fa-search fa-sm"></i>
                                					</button>
                            					</div>
                       					 		</div>
                    						</form>
                    						-->
										</div>
										<div class="col-sm-12 col-md-4"></div>
									</div>
									<div class="row">
										<div class="col-sm-12">
										<form action="" name="InquiryFAQ" method="POST" id="form">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<table class="table table-bordered dataTable" id="dataTable"
												width="100%" cellspacing="0" role="grid"
												aria-describedby="dataTable_info" style="width: 100%;">
												<thead>
													<tr role="row">
														<th class="sorting" tabindex="0" aria-controls="dataTable"
															rowspan="1" colspan="1"
															aria-label="Office: activate to sort column ascending"
															style="width: 10%;">번호</th>
														<th class="sorting" tabindex="0" aria-controls="dataTable"
															rowspan="1" colspan="1"
															aria-label="Position: activate to sort column ascending"
															style="width: 75%;">제목</th>
														<th class="sorting" tabindex="0" aria-controls="dataTable"
															rowspan="1" colspan="1"
															aria-label="Position: activate to sort column ascending"
															style="width: 15%;">민원 분류</th>
														
													</tr>
												</thead>
												<tfoot>
													<tr>
														<th rowspan="1" colspan="1">번호</th>
														<th rowspan="1" colspan="1">제목</th>
														<th rowspan="1" colspan="1">민원 분류</th>
													</tr>
												</tfoot>
												<tbody>
													<tr>
														<td>1</td>
														<td onclick="display1();">커뮤니티 이용규칙 안내</td>
														<td>커뮤니티</td>
													</tr>
													<tr id="dis1">
													</tr>
													<tr>
														<td>2</td>
														<td onclick="display2();">커뮤니티 금지행위</td>
														<td>커뮤니티</td>
													</tr>
													<tr>
													<tr id="dis2">
													</tr>
													<tr>
														<td>3</td>
														<td onclick="display3();">허위사실 유포 및 명예 훼손 게시물에 대한 게시 중단 요청</td>
														<td>커뮤니티</td>
													</tr>
													<tr id="dis3">
														
													</tr>
													<tr>
														<td>4</td>
														<td onclick="display4();">강의실 예약 시 유의사항</td>
														<td>강의실 이용/예약</td>
													</tr>
													<tr id="dis4">
														
													</tr>
													<tr>
														<td>5</td>
														<td onclick="display5();">후기 작성 시 유의사항</td>
														<td>후기</td>
													</tr>
													<tr id="dis5">
														
													</tr>
												</tbody>
											</table>
											</form>
										</div>
									</div>
									
							</div>
						</div>
					</div>

				</div>
			</div>
<!--<c:set var="UserID" value="${UserID}" />
													<c:set var="UserIDFromWriter" value="${UserIDFromWriter}" />
													<c:if test="${UserID == UserIDFromWriter}">
													
													<c:forEach items="${faqList}" var="faqList"
														varStatus="status">
													<tr class="odd">
														<td><c:out value="${status.count}" /></td>
														<td id="title"><a href="${path}/inquiryContent?no=${inquiryFAQ.getIBoardID()}">
														<c:out value="${inquiryFAQ.getIBoardSubject()}" /></a></td>
													</tr>
													</c:forEach>
													</c:if>
												-->
		</div>
			<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
		</div>
	</div>
	
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
</body>