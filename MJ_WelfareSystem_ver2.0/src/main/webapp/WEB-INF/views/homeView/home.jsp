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


<title>For MJ Students</title>
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
					<sec:authorize access="isAnonymous()">
						<jsp:include page="/WEB-INF/views/homeView/loginBox.jsp"></jsp:include>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
					</sec:authorize>				
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
<script>
	$(document).ready(function() {
      let result = '<c:out value="${Checker}"/>';
      checkAlert(result);

      function checkAlert(result) {
         if (result === '') {
            return;
         } else if (result === "NoTeamList") {
            alert("소속된 팀이 없습니다.");
         }else if(result==="NoUserID"){
            alert("없는 유저 아이디 입니다.");            
         }
      }
   });
</script>
</html>