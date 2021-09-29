<!-- 게시판 내용 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
        
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/buttons.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/29.2.0/classic/ckeditor.js"></script>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/boardContent.js"></script>   

<title>community content</title>
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
				<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h5 class="m-0 font-weight-bold text-primary">커뮤니티</h5>
				</div>
				<div class="card-body">
               	
				<form action="CommunityDelete.do?boardID=${BoardID}"
							name="CommunityDelete" method="POST" id="form">
					<div class="form-group col-sm-10 my-5 ml-4">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group row my-4">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="communityTitle" name="CommunityTitle" disabled readonly
								value="${CommunityTitle}">
							<c:out value="" escapeXml="false"/></td>
						</div>
						</div>
						<input type="text" class="col-sm-5 form-control my-2" name="CommunityWriter" id="communityWriter" 
							value="${CommunityWriter}" disabled readonly>
						<input type="text" name="Date" id="date" class="col-sm-5 form-control my-2"
							placeholder="날짜가 자동으로 입력됩니다." disabled readonly value="${BoardDate}">
						
						<div class="form-group row my-3">
						<div class="col-sm-10">
						<div class="ckeditor_contents">
							<p class="card-text" <pre>${CommunityContent}</pre>> </p>
							
						</div>
							
						</div>
						</div>
				         <div class="form-group row">
				         	<label for="files" class="col-sm-2 my-2 col-form-label"><h1 class="h6 font-weight-bold text-gray-800">첨부 파일</h1></label>
				         	
				         	<div class="col-sm-10 my-2" id="fileIndex">
								<c:forEach var="CommunityFile" items="${CommunityFile}">
					         	<a href="#" onclick="FileDown('${CommunityFile.BFileID}'); return false;">${CommunityFile.BOriginalFileName}</a>(${CommunityFile.BFileSize}kb)
								</c:forEach>
							</div>
						</div>
						<div class="user text-center row p-3 ml-5 justify-content-center">
				            <a href="${path}/communityList"><input type="button" value="목록" id="listButton" class="btn btn-secondary btn-user mr-2"></a>
								<!-- 접속한 UserID와 해당 글을 작성한 UserID가 같을 때 수정/삭제 버튼 보이게 하기 -->
								<c:set var="UserID" value="${UserID}" />
								<c:set var="UserIDFromWriter" value="${UserIDFromWriter}" />
								<c:if test="${UserID == UserIDFromWriter}">
									<a href="${path}/communityModify?no=${BoardID}">
									<button type="button" class="btn btn-primary btn-user mr-2" id="modifyButton" >수정</button></a>
									<button type="submit" class="btn btn-danger btn-user" id="deleteButton" onclick="if(!confirm('삭제 하시겠습니까?')){return false;}">삭제</button>
								</c:if>
			            </div>
					</div>
				</form>
				<form name="readForm" role="form" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" id="bFileID"
						name="BFileID" value="">
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