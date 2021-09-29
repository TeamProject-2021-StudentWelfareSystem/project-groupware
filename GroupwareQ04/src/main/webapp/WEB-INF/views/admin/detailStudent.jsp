<!-- 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 -->

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/userManage.css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/userManage.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
   rel="stylesheet">
<title>user info management</title>
</head>

<body>

   <jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
   
      <nav>
         <div class="mbody">
            <div class="mcontWidth">
               <jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
               <div class="rightBox">
                  <section id="section">
                     <div class="section">
                        <h2>&nbsp;사용자 관리</h2>
                        <hr>
                     </div>
                  </section>
                  <section id="section2">
                     <!-- 학생 사용자 -->
                     <div id="studentInfo">
                        <h3>&nbsp;사용자 정보</h3>
                        <form action="" name="ModifyStudent" method="POST" id="form">
                           <table>
                              <tr>
                                 <td class="col1"><label for="id">&nbsp;아이디(학번)&nbsp;</label></td>
                                 <td class="col2"><input type="text" name="UserLoginID"
                                    id="userLoginID" class="inputBox" placeholder="" autofocus
                                    autocomplete="off" disabled readonly value=${UserLoginID}></td>
                                 <td class="col1"><label for="name">이름</label></td>
                                 <td class="col2"><input type="text" name="SUserName"
                                    id="userName" class="inputBox" autocomplete="off" disabled
                                    readonly value="${SUserName}"></td>
                              </tr>
                              <tr>
                                 <td class="col1"><label for="gender">성별</label></td>
                                 <td class="col2"><input name="StudentGender"
                                    id="studentGender" class="inputBox" disabled readonly
                                    value="${StudentGender}"></td>
                                 <td class="col1"><label for="phoneNumber">연락처</label></td>
                                 <td class="col2"><input type="text" name="UserPhoneNum"
                                    id="userPhoneNum" class="inputBox" autocomplete="off"
                                    disabled readonly value="${UserPhoneNum}"></td>
                              </tr>
                              <tr>
                                 <td class="col1"><label for="grade">학년</label></td>
                                 <td class="col2"><input name="StudentGrade"
                                    id="studentGrade" class="inputBox" disabled readonly
                                    value="${StudentGrade}"></td>
                                 <td class="col1"><label for="studentColleges">단과대학</label></td>
                                 <td class="col2"><input name="StudentColleges"
                                    id="studentColleges" class="inputBox" disabled readonly
                                    value="${StudentColleges}"></td>
                              </tr>
                              <tr>
                                 <td class="col1"><label for="studentMajor">전공</label></td>
                                 <td class="col2"><input name="StudentMajor"
                                    id="studentMajor" class="inputBox" disabled readonly
                                    value="${StudentMajor}"></td>
                                 <td class="col1"><label for="studentDoubleMajor">복수전공</label></td>
                                 <td class="col2"><label for="member_DoubleMajor"></label>
                                    <input name="StudentDoubleMajor" id="studentDoubleMajor"
                                    class="inputBox" disabled readonly
                                    value="${StudentDoubleMajor}"></td>
                              </tr>
                              <tr>
                                 <td class="col1"><label for="email">이메일</label></td>
                                 <td class="col2"><input type="text" name="UserEmail"
                                    id="userEmail" class="inputBox" autocomplete="off" disabled
                                    readonly value="${UserEmail}"></td>
                                 <td class="col3"><input type="email" value="@mju.ac.kr"
                                    disabled readonly name="email" id="mju" class="inputBox"
                                    autocomplete="off"></td>
                              </tr>
                              <tr>
                                 <td class="col1"><label for="infoOpen">정보 공개</label></td>
                                 <td class="col2"><input type="text" name="StudentInfoOpen"
                                    id="userInfoOpen" class="inputBox" autocomplete="off"
                                    disabled readonly value="${StudentInfoOpen}"></td>
                              </tr>
                           </table>
                        </form>
                     </div>
                     <!-- studentInfo -->
                     <div id="btnDiv">
                        <input type="button" id="deleteBtn" value="삭제"> <a
                           href="manageModifyStudent?no=${UserLoginID}"
                           onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
                           <input type="button" id="listBtn" value="수정">
                        </a> <a href="manageList"><input type="button" id="listBtn"
                           value="목록"></a>
                     </div>
                  </section>
               </div>
               <!-- right_box -->
            </div>
            <!-- mcont_width -->
         </div>
         <!-- mbody -->
      </nav>
</body>
</html>