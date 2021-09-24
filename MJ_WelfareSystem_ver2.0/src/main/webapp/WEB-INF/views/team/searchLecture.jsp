<!-- 팀 추가 선택 시 강의 검색 화면 -->

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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
   rel="stylesheet">
<link rel="stylesheet" href="../css/createTeamContent.css"
   type="text/css">
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<link rel="stylesheet"
   href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/createTeam.js"></script>

<title>search Lecture</title>
</head>
<body>
   <jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
   <nav>
      <div class="mbody">
         <div class="mcontWidth">
            <jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include>
            <div class="rightBox">
               <section>
                  <div class="section">
                     <br>
                     <h2>강의 검색</h2>
                     <hr>
                  </div>
               </section>
               <section>
                  <form action="createTeam?LectureName=${LectureName}"
                     name="SearchLecture" method="GET" id="form">
                     <div class="section2">
                        <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                        <table id="contentTable">
                           <tr>
                              <td><label for="lectureName">과목명 &nbsp; &nbsp;</label></td>
                              <td><input type="text" name="LectureName"
                                 id="lectureName" class="inputBox" value="${LectureName}"></td>
                              <td><input type="submit" name="Search" id="search"
                                 value="검색">
                           </tr>

                        </table>
                        <hr>
                     </div>
                     <!-- section2 -->
                  </form>
                  <script>
                     $(document)
                           .ready(
                                 function() {
                                    let result = '<c:out value="${Checker}"/>';
                                    checkAlert(result);
                                    function checkAlert(result) {
                                       if (result === '') {
                                          return;
                                       } else if (result === "NoLecture") {
                                          alert("잘못된 검색어입니다.");
                                       } else if (result === "UserNotFound") {
                                          alert("잘못된 정보를 기입하셨습니다. 정보 확인 후 다시 입력해주세요");
                                       }
                                    }
                                 });
                  </script>
               </section>
            </div>
            <!-- right_box -->

         </div>

      </div>
      <!-- mcont_width -->
      <!-- mbody -->
   </nav>
   <!-- mjs_ws -->
</body>
</html>