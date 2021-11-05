<!-- 게시판 내용 출력 화면 -->

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
<title>notice modify</title>
<style>
.ck-editor__editable {
	min-height: 500px;
}
</style>
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
					<h5 class="m-0 font-weight-bold text-primary">수정</h5>
				</div>
				<div class="card-body">
				 <form action="NoticeModify.do?${_csrf.parameterName}=${_csrf.token}"
							name="NoticeModify" enctype="multipart/form-data" method="POST" id="form">
					<div class="form-group col-sm-10 my-5 ml-4">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						
						<div class="form-group row my-4">
						<label for="noticeTitle" class="col-sm-2 col-form-label"><span class="font-weight-bold text-gray-800">제목</span></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="noticeTitle" name="NoticeTitle" 
								value="${NoticeTitle}">
						</div>
						</div>
						<input type="hidden" class="col-sm-5 form-control my-2" name="NoticeWriter" id="noticeWriter" 
							value="${NoticeWriter}" disabled readonly>
						<input type="hidden" name="Date" id="date" class="col-sm-5 form-control my-2"
							placeholder="날짜가 자동으로 입력됩니다." disabled readonly value="${BoardDate}">
						<div class="form-group row my-3">
						<label for="noticeContent" class="col-sm-2 col-form-label"><span class="font-weight-bold text-gray-800">내용</span></label>
						<div class="col-sm-10">
							<textarea name="NoticeContent" class="form-control" id="noticeContent">${NoticeContent}</textarea>
						    <script>
						    ClassicEditor
						        .create( document.querySelector( '#noticeContent' ) )
						        .catch( error => {
						            console.error( error );
						        } );
						    </script>
						</div>
						</div>
						
						 <div class="form-group row">
				            <label for="files" class="col-sm-2 my-1 col-form-label"><span class="font-weight-bold text-gray-800">첨부 파일 추가</span></label>
				            <div class="col-sm-10 user" id="addFile">
				            	<input type="button" id="fileAddButton" value="파일추가" class="btn btn-secondary btn-user mb-3">
				                <div class="custom-file mb-3" id="inputFile">
				                    <input name="UploadFile" id="boardFile" type="file" class="custom-file-input">
				                    <label class="custom-file-label" for="customFile">파일을 선택해 주세요</label>
				                </div>
				           	</div>
				         </div>
				         
				         <div class="form-group row">
				         	<label for="files" class="col-sm-2 my-2 col-form-label"><span class="font-weight-bold text-gray-800">기존 첨부 파일</span></label>
				         	<div class="col-sm-10 my-2" id="fileIndex">
								<c:forEach var="NoticeFile" items="${NoticeFile}" varStatus="var">
									<div>
										<input type="hidden" id="bFileID" name="BFileID_${var.index}" value="${NoticeFile.BFileID}"> 
										<input type="hidden" id="originalFileName" name="FILE_NAME" value="BFileID_${var.index}"> 
										<a href="#" id="fileName" onclick= "return false;">${NoticeFile.BOriginalFileName}
										</a>(${NoticeFile.BFileSize}kb)
										<input type="button" id="fileDel" onclick="fnDel('${NoticeFile.BFileID}','BFileID_${var.index}');" 
										type="button" class="btn btn-danger btn-xs m-0 p-1" value="x"><br>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="user text-center row p-3 ml-5 justify-content-center">
						<input type="submit" value="수정 완료" id="listButton" class="btn btn-primary btn-user mr-2">
			            <a href="${path}/noticeList"><input type="button" value="목록" id="listButton" class="btn btn-secondary btn-user"></a>
						<input type="hidden" id="fileDeleteList" name="FileDeleteList[]" value="">
						<input type="hidden" id="fileDeleteNameList" name="FileDeleteNameList[]" value=""> 
						<input type="hidden" id="boardID" name="BoardID" value="${BoardID}">
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