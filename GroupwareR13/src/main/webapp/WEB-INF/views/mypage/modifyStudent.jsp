<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/infoModify.js"></script>

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

<!-- Custom scripts for all pages-->
<script src="resources/vendor/bootstrap/js/sb-admin-2.min.js"></script>        
<title>information modify</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5" style="width:498px; margin:0 auto;">
                    <div class="card-body">
                        <div class="col-lg-12">
                             <div class="p-2">
                             <div class="text-center">
                                     <h4 class="text-gray-900 mb-4">개인 정보 수정</h4>
                             </div>
                             <form action="${path}/modifyStudent.do" name="ModifyStudent" method="POST" id="form">
                             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                             <div class="text-center mb-5">
                             	<span class="small text-gray-900">작성하지 않은 정보는 수정되지 않습니다.<br>연락처, 학년, 정보 공개 이외의 정보의 수정을 원하는 회원은 관리자에게 문의 바랍니다.</span>
                             </div>
                             <div class="row gx-3 mb-3">
                             	<div class="col-md-6">
                                    <label class="small mb-1" for="userLoginID">아이디(학번)</label>
                                    <input class="form-control bg-white small" id="userLoginID" name="UserLoginID" type="text" readonly value="${UserLoginID}">
                                </div>
                                <div class="col-md-6">
                                    <label class="small mb-1" for="userName">이름</label>
                                    <input class="form-control bg-white" id="userName" name="UserName" type="text" readonly value="${UserName}">
                                </div>
                             </div>
                             <div class="row gx-3 mb-3">
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="studentGender">성별</label>
                                      <input class="form-control bg-white" id="studentGender" name="StudentGender" type="text" readonly value="${StudentGender}">
                                  </div>
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="userPhoneNum">연락처</label>
                                      <input class="form-control bg-white" id="userPhoneNum" name="UserPhoneNum" type="text" value="${UserPhoneNum}" placeholder="숫자만 입력해주세요">
                                  </div>
                              </div>
                             <div class="row gx-3 mb-3">
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="studentGrade">학년</label>
                                      <div>
                                      <select class="selectOption" name="StudentGrade" id="studentGrade">
				                           <option value=" " selected>-선택-</option>
				                           <option value="1학년">1학년</option>
				                           <option value="2학년">2학년</option>
				                           <option value="3학년">3학년</option>
				                           <option value="4학년">4학년</option>
				                     </select>
				                     </div>
                                  </div>
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="studentColleges">단과대학</label>
                                      <input class="form-control bg-white" id="studentColleges" name="StudentColleges" type="text" readonly value="${StudentColleges}">
                                  </div>
                              </div>
                             <div class="row gx-3 mb-3">
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="studentMajor">전공</label>
                                      <input class="form-control bg-white" id="studentMajor" name="StudentMajor" type="text" readonly value="${StudentMajor}">
                                  </div>
                                  <div class="col-md-6">
                                      <label class="small mb-1" for="studentDoubleMajor">복수전공</label>
                                      <input class="form-control bg-white" id="studentDoubleMajor" name="StudentDoubleMajor" type="text" readonly value="${StudentDoubleMajor}">
                                  </div>
                              </div>
					<div class="mb-3">
						<label class="small mb-1" for="userEmail">이메일</label>
                              <input class="col-md-10 form-control bg-white" id="userEmail" name="UserEmail" type="text" readonly value="${Email}@mju.ac.kr">
                             </div>
                             <div class="mb-5">
                                 <label class="small mb-1" for="userInfoOpen">정보 공개</label>
                                 <div class="text-center">
                                 	<div class="mb-2">
                                 		<span class="small text-gray-900">정보 공개를 체크하면 해당 정보가 공개로 표시됩니다.</span>
                                 	</div>
                                 	<div class="text-gray-900">
				                       <c:set var="OpenPhoneNum" value="${OpenPhoneNum}" />
				                        <c:if test="${OpenPhoneNum == '전화번호'}">
				                          <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone" checked="checked"><span class="mr-2">연락처</span></c:if>
				                  		 <c:if test="${OpenPhoneNum == '비공개'}">
				                          <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone"><span class="mr-2">연락처</span></c:if>
				                        <c:set var="OpenGrade" value="${OpenGrade}" />
				                        <c:if test="${OpenGrade == '학년'}">
				                          <input type="checkbox" name="UserGrade" id="cUserGrade" value="UserGrade" checked="checked"><span class="mr-2">학년</span></c:if>
				                        <c:if test="${OpenGrade == '비공개'}">
				                          <input type="checkbox" name="UserGrade" id="cUserGrade" value="UserGrade"><span class="mr-2">학년</span></c:if>
				                     </div>
				                  </div>
                             </div>
                             <div class="user m-4 justify-center-center">
									<div class="text-center">
										<input type="submit" name="ModifyComplete" id="modifyComplete" class="btn btn-primary btn-user" value="수정"> 
										<input type="button" name="Cancel" id="cancelBtn" class="btn btn-light btn-user" value="취소">
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
</body>
</html>