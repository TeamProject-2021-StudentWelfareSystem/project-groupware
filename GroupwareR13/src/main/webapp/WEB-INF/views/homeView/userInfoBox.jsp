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
</head>
<body>
   <div class="card o-hidden" style="width: 320px">
      <div class="card-body p-0">
         <div class="col-lg-12">
            <div class="p-4">
               <div class="user">
                <sec:authorize access="hasRole('ROLE_USER')">
                  <input type=text class="col-sm-3 mb-0 font-weight-bold text-gray-800" name="UserName" id="userInfo"
                     value="${UserName}" disabled readonly>님 | 
                     <a href="${path}/myPage?R=${UserRole}" class="h6" style="color:black; text-decoration:none">내 정보</a>
                     <a class="btn btn-secondary btn-logout" id="logoutBtn" href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
                     <div class="my-4"></div>            
                     <div class="btn btn-light btn-icon-split">
                        <span class="icon small font-weight-bold text-gray-800">소속</span>
                                <input type=text class="small" name="Colleges" id="userInfo1" value="${Colleges}" disabled readonly>
                            </div>
                            <div class="my-2"></div>
                            <div class="btn btn-light btn-icon-split">
                        <span class="icon small font-weight-bold text-gray-800">학과</span>
                                <input type=text class="small" name="UserMajor" id="userInfo1" value="${UserMajor}" disabled readonly>
                            </div>
                            <div class="my-2"></div>
                            <div class="btn btn-light btn-icon-split">
                        <span class="icon small font-weight-bold text-gray-800">학년</span>
                                <input type="text" class="small" name="UserGrade" id="userInfo1" value="${UserGrade}" disabled readonly> <span class="small" id="userGrade">학년</span>
                            </div>
                     </sec:authorize>
                     <sec:authorize access="hasRole('ROLE_ADMIN')">
                     <input type=text class="col-sm-3 mb-0 font-weight-bold text-gray-800" name="UserName" id="userInfo" value="${UserName}" disabled readonly> 관리자님 환영합니다!
                     <div class="text-center my-4">
                        <a class="btn btn-secondary btn-logout" id="logoutBtn2" href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
                     </div>            
                     </sec:authorize>                           
                  <div class="userRole">
                     <input type=hidden name="UserRole" id="userInfo" value="${UserRole}">
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</html>