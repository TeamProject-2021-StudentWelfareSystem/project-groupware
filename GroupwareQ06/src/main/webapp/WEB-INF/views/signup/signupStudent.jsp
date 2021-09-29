<!-- 회원가입 화면 (학생) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/signupStudent.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
   rel="stylesheet">
   
<link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
	<script src="js/pwShowHide.js"></script><%--비밀번호 감추기 js--%>
<title>sign up</title>
</head>
<body class="bg-gradient-primary">
    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5" style="width:498px; margin:0 auto;">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
               
                    <div class="col-lg-12">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">회원가입</h1>
                            </div>
                            <form class="user" action="${path}/signupStudent.do" name="SignupStudent" method="POST" id="form">
                            <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="UserLoginID" id="userLoginID" placeholder="아이디(학번)"
                                              autocomplete="off" value="${UserLoginID}">
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <input type="submit" class="form-control form-control-user" name="IdCheck" id="idCheck" value="중복확인"><!--중복확인버튼 -->
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="UserName" id="userName" placeholder="이름">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" name="UserLoginPwd" id="userLoginPwd" placeholder="패스워드">
                                    <i class="fa fa-eye" id="icon1"></i>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" name="UserLoginPwdCheck" id="userLoginPwdCheck" placeholder="패스워드 확인">
                                    <i class="fa fa-eye" id="icon2"></i>
                                </div>
                                <!-- 비밀번호 일치/불일치 띄우기 -->
<!--							<div>
									<c:if test="${not empty ERRORMSG}">						        
										<p style="font-size:13px; color:red">${ERRORMSG}</p>
									     <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>		     
									</c:if>
								</div>
-->                                 
                                 <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="UserPhoneNum" id="userPhoneNum" placeholder="연락처">
                                </div>
                                
                                <div class="form-group row">
                                   <div class="col-sm-6 mb-3 mb-sm-0">
                                       <select class="form-control" name="StudentGender" id="studentGender">
                                          <option value=" " selected> 성별 </option>
                                          <option value="남자"> 남 </option>
                                          <option value="여자"> 여 </option>
                                       </select>
                                </div>
                               
                                <div class="col-sm-6">
                                    <select class="form-control" name="StudentGrade" id="studentGrade" >
                                       <option value=" " selected> 학년 </option>
                                      <option value="1">1학년</option>
                                       <option value="2">2학년</option>
                                       <option value="3">3학년</option>
                                       <option value="4">4학년</option> 
                                    </select>
                                </div>
                               </div> 
                               
                                <div class="form-group row">
                                   <div class="col-sm-6 mb-3 mb-sm-0">
                                       <select class="form-control" name="StudentColleges" id="studentColleges" >
                                             <option value="" selected> 단과대학 </option>
                                          <option value="인문대학">인문대학</option>
                                          <option value="사회과학대학">사회과학대학</option>
                                          <option value="경영대학">경영대학</option>
                                          <option value="법과대학">법과대학</option>
                                          <option value="ICT융합대학">ICT융합대학</option>
                                          <option value="미래융합대학">미래융합대학</option>
                                    </select>
                                </div>
                                
                                <div class="col-sm-6">
                                    <select class="form-control form-control-user" name="StudentMajor" id="studentMajor" placeholder="전공">
                                       <option value="-선택-" selected> 전공 </option>
                                    </select>
                                </div>
                                </div>
                                <hr>
                                <div class="form-group row">
                                   <div class="col-sm-6 mb-3 mb-sm-0">
                                   <h6 class="small text-weight-bold text-gray-900" id="dmj">복수전공</h6>
                                </div>
                                <div class="col-sm-6 mb-2 radio">
                                    <input type="radio" class="radio text-gray-900 ml-2" name="member"
                                    id="memberDoubleMajor" value="Y" onchange="setDisplay()"
                                    checked><label class="small radio text-gray-900 ml-2">있음</label>
                                    <input type="radio" class="radio text-gray-900 ml-2" name="member"
                                    id="memberSingleMajor" value="N" onchange="setDisplay()">
                                    <label class="small radio text-gray-900">없음</label>
                              </div>
                                 <select class="col-sm-11 ml-3 mb-3 form-control form-control-user"
                                 name="StudentDoubleMajor" id="studentDoubleMajor">
	                              <option value=" " selected> 학과 </option>
	                              <option value="국어국문학과">국어국문학과</option>
	                              <option value="영어영문학과">영어영문학과</option>
	                              <option value="중어중문학과">중어중문학과</option>
	                              <option value="일어일문학과">일어일문학과</option>
	                              <option value="사학과">사학과</option>
	                              <option value="문헌정보학과">문헌정보학과</option>
	                              <option value="아랍지역학과">아랍지역학과</option>
	                              <option value="미술사학과">미술사학과</option>
	                              <option value="철학과">철학과</option>
	                              <option value="문예창작학과">문예창작학과</option>
	                              <option value="행정학과">행정학과</option>
	                              <option value="경제학과">경제학과</option>
	                              <option value="정치외교학과">정치외교학과</option>
	                              <option value="디지털미디어학과">디지털미디어학과</option>
	                              <option value="아동학과">아동학과</option>
	                              <option value="청소년지도학과">청소년지도학과</option>
	                              <option value="경영정보학과">경영정보학과</option>
	                              <option value="국제통상학과">국제통상학과</option>
	                              <option value="법학과">법학과</option>
	                              <option value="융합소프트웨어학부">융합소프트웨어학부</option>
	                              <option value="디지털콘텐츠디자인학과">디지털콘텐츠디자인학과</option>
	                              <option value="창의융합인재학부">창의융합인재학부</option>
	                              <option value="사회복지학과">사회복지학과</option>
	                              <option value="부동산학과">부동산학과</option>
	                              <option value="법무행정학과">법무행정학과</option>
	                              <option value="심리치료학과">심리치료학과</option>
	                              <option value="미래융합경영학과">미래융합경영학과</option>
	                              <option value="멀티디자인학과">멀티디자인학과</option>
	                              <option value="계약학과">계약학과</option>
	                        </select>
                                </div>
                                <input type="submit" name="submitName" id="SignupComplete"
                        value="회원가입" class="btn btn-primary btn-user btn-block my-2">
                                 
                                <hr>
                                <a href="${path}/home" class="btn btn-google btn-user btn-block">메인 화면으로</a>
                                
                               
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="${path}/findPassword">비밀번호를 잊으셨나요?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="${path}/login">이미 계정이 있나요? 로그인하세요!</a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>


</body>
</html>