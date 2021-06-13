<!-- 마이페이지 - 수정하기 - 비밀번호 확인 후 정보 수정하는 화면 (학생) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoModify.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/infoModify.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
   rel="stylesheet">
<title>information modify</title>
</head>
<body>
   <div class="mjsWs">
      <div class="container">
         <section id="modify">
            <h2>개인 정보 수정</h2>
            <!--학생 정보 수정-->
            <div id="modifyStudent" style="display: block;">
               <form action="${path}/modifyStudent.do" name="ModifyStudent.do" method="POST" id="form">
                  <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}" />
                  <table>
                     <!-- ID, 이름, 이메일, 성별, 단과대학, 전공, 복수전공 수정 불가 -->
                     <tr>
                        <td class="col1"><label for="id">아이디(학번)</label></td>
                        <td class="col2"><input type="text" name="UserLoginID"
                           id="userLoginID" class="inputBox" placeholder=""
                           autocomplete="off" disabled readonly value="${UserLoginID}"></td>
                     </tr>

                     <tr>
                        <td class="col1"><label for="name">이름</label></td>
                        <td class="col2"><input type="text" name="UserName"
                           id="userName" class="inputBox" autocomplete="off" disabled readonly
                           value="${UserName}"></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="email">이메일</label></td>
                        <td class="col2"><input type="text" name="Email"
                           id="userEmail" class="inputBox" autocomplete="off" disabled readonly
                           value="${Email}"></td>
                        <td class="col3"><input type="text" value="@mju.ac.kr"
                           disabled readonly id="mju" class="inputBox" autocomplete="off"></td>
                     <tr>
                        <td class="col1"><label for="gender">성별</label></td>
                        <td class="col2"><input name="StudentGender"
                           id="studentGender" class="inputBox" disabled readonly value="${StudentGender}"></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="phoneNumber">연락처</label></td>
                        <td class="col2"><input type="text" name="UserPhoneNum"
                           id="userPhoneNum" class="inputBox" placeholder="숫자만 입력해주세요"
                           autocomplete="off" value="${UserPhoneNum}"></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="grade">학년</label></td>
                        <td class="col2"><select name="StudentGrade"
                           id="studentGrade">
                              <option value=" " selected>-선택-</option>
                              <option value="1학년">1학년</option>
                              <option value="2학년">2학년</option>
                              <option value="3학년">3학년</option>
                              <option value="4학년">4학년</option>
                        </select></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="studentColleges">단과대학</label></td>
                        <td class="col2"><input name="StudentColleges" 
                        id="studentColleges" class="inputBox" disabled readonly value=${StudentColleges}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="studentMajor">전공</label></td>
                        <td class="col2"><input name="StudentMajor" 
                        id="studentMajor" class="inputBox" disabled readonly value=${StudentMajor}></td>
                     </tr>
                     <tr id="doubleMajor">
                        <td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
                        <td class="col2"><input name="StudentDoubleMajor" 
                        id="studentDoubleMajor" class="inputBox" disabled readonly value=${StudentDoubleMajor}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="infoOpen">정보 공개</label></td>
                        <td class="col2"><h5>정보 공개를 체크하면 해당 정보가 공개로 표시됩니다</h5>
                        <c:set var="OpenPhoneNum" value="${OpenPhoneNum}" />
                         <c:if test="${OpenPhoneNum == '전화번호'}">
                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone" checked="checked">연락처 &nbsp; </c:if>
                   		 <c:if test="${OpenPhoneNum == '비공개'}">
                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone">연락처 &nbsp; </c:if>
                         <c:set var="OpenGrade" value="${OpenGrade}" />
                         <c:if test="${OpenGrade == '학년'}">
                           <input type="checkbox" name="UserGrade" id="cUserGrade" value="UserGrade" checked="checked">학년 &nbsp; </c:if>
                         <c:if test="${OpenGrade == '비공개'}">
                           <input type="checkbox" name="UserGrade" id="cUserGrade" value="UserGrade">학년 &nbsp; </c:if>
                           
                        <br></td>
                     </tr>
                  </table>
                  <div id="">
                     <input type="submit" name="ModifyComplete" id="modifyComplete"
                        value="수정"> <input type="button" name="Cancel"
                        id="cancelBtn" value="취소">
                  </div>
               </form>
            </div>
         </section>
      </div>
   </div>
</body>
</html>