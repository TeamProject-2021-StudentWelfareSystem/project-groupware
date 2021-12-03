<!-- 초기 home 화면 -->
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


<link rel="stylesheet" href="../css/lectureRoomList.css" type="text/css">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
	

<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<link href="../css/boardList.css" rel="stylesheet">
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


<title>search my team</title>
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
				<div class="row justify-content-center">
				<div class="col-xl-10 col-lg-12 col-md-9">
					<div class="card o-hidden border-0 shadow-lg my-5" style="width: 498px; margin: 0 auto;">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="col-lg-12">
								<div class="p-5">
									<div class="text-center">
										<h4 class="text-primary mb-5">후기 작성</h4>
									</div>
                                    <form action="reviewWrite" name="SearchTeam" method="GET" id="form">
                                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    	<div class="form-group">
											<div class="input-group">
                            					<select name="Team" id="team" class="form-control text-center" onchange="optionChange(this)">
													 <option value="" selected>-팀 선택-</option>
														 <c:forEach items="${TeamList}" var="TeamList" varStatus="status">
													 		<option><c:out value="${TeamList}"/></option>
													 </c:forEach>
												 </select>
												 <div class="input-group-append">
													 <button class="btn btn-primary" name="Search" id="search" onClick="notSelectValue()">
	                                    				<i class="fas fa-search fa-sm"></i>
                                					</button>
                            					</div>
												 
                       					 	</div>
										</div>
                          			</form>
				      			</div>           
                  			</div>
                		</div>
					</div><!-- end of container-fluid -->
				</div>
		</div>
		</div>
		</div>
		<jsp:include page="/WEB-INF/views/homeView/footer.jsp"></jsp:include>
	</div>
	
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    </div>
</body>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/searchUser.js"></script>	

</html>