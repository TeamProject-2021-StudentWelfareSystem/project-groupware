<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/myPage.css">
<script src="js/jquery-3.5.1.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
   rel="stylesheet">
<title>MyPageStudent</title>
</head>
<body>
   <div class="mjs_ws">
   
      <div class="container">
         <section id="memberInfo">
            <h2>회원 정보</h2>
            <br>

            <!--학생 회원가입-->
            <div id="student_signup" style="display: block;">
               
                  <form action="${path}/myPageStudent.do" name="myPageStudent" method="POST"
                     id="form"> <input type="hidden"
               name="${_csrf.parameterName}" value="${_csrf.token}" />
                     <table>
                     <tr>
                        <td class="col1"><label for="id">아이디(학번)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                        <td class="col2"><input type="text" name="userLoginID"
                           id="UserLoginID" class="inputBox" autocomplete="off" disabled readonly value=${userLoginID}></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="name">이름</label></td>
                        <td class="col2"><input type="text" name="userName"
                           id="userName" class="inputBox" autocomplete="off" disabled readonly value=${userName}></td>
                     </tr>

                     <tr>
                        <td class="col1"><label for="gender">성별</label></td>
                        <td class="col2"><input name="studentGender"
                           id="studentGender" class="inputBox" disabled value=${studentGender} readonly></td>
                     </tr>
                     
                     <tr>
                        <td class="col1"><label for="phoneNumber">연락처</label></td>
                        <td class="col2"><input type="text" name="userPhoneNum"
                           id="userPhoneNum" class="inputBox" disabled value=${userPhoneNum}  readonly></td>
                     </tr>
                     <tr>
                              <td class="col1"><label for="grade">학년</label></td>
                              <td class="col2"><input name="studentGrade"
                                 id="studentGrade" class="inputBox"disabled value=${studentGrade} readonly></td>
                           </tr>
                     <tr>
                        <td class="col1"><label for="studentColleges">단과대학</label></td>
                        <td class="col2"><input name="studentColleges"
                           id="studentColleges" class="inputBox" disabled value=${studentColleges} readonly></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="studentMajor">전공</label></td>
                        <td class="col2"><input name="studentMajor"
                           id="studentMajor" class="inputBox"disabled value=${studentMajor} readonly></td>
                     </tr>
                     <tr id="doubleMajor">
                        <td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
                        <td class="col2"><label for="member_DoubleMajor"></label> 
                                <input name="studentDoubleMajor"
                           id="studentDoubleMajor"class="inputBox" disabled value=${studentDoubleMajor}  readonly></td>
                     </tr>
                     <tr>
                        <td class="col1"><label for="email">이메일</label></td>
                        <td class="col2"><input type="text" name="userEmail"
                           id="userEmail" class="inputBox" autocomplete="off" disabled value =${userEmail}  readonly></td>
                     
                     </tr>
                     
                     </table>
                  </form>
               
            </div>

            <br> <br> 
            <div id="">
                    <a href="checkPassword" onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
                    <input type="button" id="modifyBtn" value="수정하기"></a>
                    <a href="modifyPassword" onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
                    <input type="button" id="modifyPWBtn" value="비밀번호 변경하기"></a>
            </div>
                
            
         </section>
      </div>
   </div>
</body>
</html>