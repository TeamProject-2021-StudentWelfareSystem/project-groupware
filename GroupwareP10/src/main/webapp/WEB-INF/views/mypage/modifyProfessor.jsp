<!-- 마이페이지 - 수정하기 - 비밀번호 확인 후 정보 수정하는 화면 (교수) -->

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
            <br>
            <!--교수 정보 수정-->
            <div id="modifyProfessor" style="display: block;">
               <form action="${path}/modifyProfessor.do" name="ModifyProfessor.do"
                  method="POST" id="form">
                  <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}" />
                  <table>
                     <!-- ID, 이름, 단과대학, 전공 수정 불가 -->
                     <tr>
                        <td class="col1"><label for="id">아이디(학번)</label></td>
                        <td class="col2"><input type="text" name="UserLoginID"
                           id="userLoginID" class="inputBox" placeholder=""
                           autocomplete="off" disabled readonly value=${UserLoginID}></td>
                     </tr>

                     <tr>
                        <td class="col1"><label for="name">이름</label></td>
                        <td class="col2"><input type="text" name="UserName"
                           id="userName" class="inputBox" autocomplete="off" disabled
                           readonly value=${UserName}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="email">이메일</label></td>
                        <td class="col2"><input type="text" name="Email"
                           id="userEmail" class="inputBox" autocomplete="off" disabled
                           readonly value=${Email}></td>
                        <td class="col3"><input type="text" value="@mju.ac.kr"
                           disabled readonly id="mju" class="inputBox" autocomplete="off"></td>
                     <tr>
                        <td class="col1"><label for="phoneNumber">연락처</label></td>
                        <td class="col2"><input type="text" name="UserPhoneNum"
                           id="userPhoneNum" class="inputBox" placeholder="숫자만 입력해주세요"
                           autocomplete="off" value=${UserPhoneNum}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="professorColleges">단과대학</label></td>
                        <td class="col2"><input name="ProfessorColleges"
                           id="professorColleges" class="inputBox" disabled readonly
                           value=${ProfessorColleges}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="professorMajor">전공</label></td>
                        <td class="col2"><input name="ProfessorMajor"
                           id="professorMajor" class="inputBox" disabled readonly
                           value=${ProfessorMajor}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="professorRoom">교수실</label></td>
                        <td class="col2"><input type="text" name="ProfessorRoom"
                           id="professorRoom" class="inputBox" autocomplete="off"
                           value=${ProfessorRoom}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="professorRoom">교수실
                              전화번호</label></td>
                        <td class="col2"><input type="text" name="ProfessorRoomNum"
                           id="professorRoomNum" class="inputBox" autocomplete="off"
                           value=${ProfessorRoomNum}> <br>
                        <br></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="infoOpen">정보 공개</label></td>
                        <td class="col2"><h5>정보 공개를 체크하면 해당 정보가 공개로 표시됩니다</h5> 
                        <c:set var="OpenPhoneNum" value="${OpenPhoneNum}" />
                         <c:if test="${OpenPhoneNum == '전화번호'}">
                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone" checked="checked">연락처 &nbsp; </c:if>
                   		 <c:if test="${OpenPhoneNum == '비공개'}">
                           <input type="checkbox" name="UserPhone" id="cUserPhone" value="UserPhone">연락처 &nbsp; </c:if>
                        <br></td>
                     </tr>
                  </table>
                  <div id="">
                     <input type="submit" name="ModifyCompleteP" id="modifyCompleteP"
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